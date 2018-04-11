package com.wnc.sof1.sumtext;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 多线程方式处理
 * 
 * @author wnc
 *
 */
public class SumForkCompute extends Aggre {

	@Override
	public long compute() throws Exception {
		RandomAccessFile raf = new RandomAccessFile(DataProcess.path, "r");
		ForkJoinPool pool = new ForkJoinPool();

		byte buf[] = new byte[1 * 1024 * 1024];
		final FileChannel ch = raf.getChannel();
		int fileLength = (int) ch.size();
		final MappedByteBuffer mb = ch.map(FileChannel.MapMode.READ_ONLY, 0, fileLength);
		SumTaskResult result = new SumTaskResult();
		while (mb.hasRemaining()) {
			int len = Math.min(mb.remaining(), buf.length);
			mb.get(buf, 0, len);
			SumForkTask task = new SumForkTask(buf, 0, len);
			result.append(pool.invoke(task));
		}
		ch.close();
		raf.close();
		pool.shutdown();
		return result.subtotal;
	}

	private class SumTaskResult {
		long subtotal;
		int leftPartial;
		int leftMulCount;
		int rightPartial;

		public void append(SumTaskResult rightward) {
			subtotal += rightward.subtotal + rightPartial * rightward.leftMulCount + rightward.leftPartial;
			rightPartial = rightward.rightPartial;
		}
	}

	private class SumForkTask extends RecursiveTask<SumTaskResult> {

		private byte buf[];
		// startPos inclusive, endPos exclusive
		private int startPos;
		private int endPos;

		public SumForkTask(byte buf[], int startPos, int endPos) {
			this.buf = buf;
			this.startPos = startPos;
			this.endPos = endPos;
		}

		private SumTaskResult computeDirectly() {
			SumTaskResult result = new SumTaskResult();
			int pos = startPos;

			result.leftMulCount = 1;

			while ((buf[pos] >= 48) && (buf[pos] <= 57)) {
				result.leftPartial = result.leftPartial * 10 + buf[pos] - 48;
				result.leftMulCount *= 10;
				pos++;
			}

			int acc = 0;
			for (int i = pos; i < endPos; i++)
				if ((buf[i] >= 48) && (buf[i] <= 57))
					acc = acc * 10 + buf[i] - 48;
				else {
					result.subtotal += acc;
					acc = 0;
				}

			result.rightPartial = acc;
			return result;
		}

		@Override
		protected SumTaskResult compute() {
			if (endPos - startPos < 64)
				return computeDirectly();
			int mid = (endPos + startPos) / 2;
			SumForkTask left = new SumForkTask(buf, startPos, mid);
			left.fork();
			SumForkTask right = new SumForkTask(buf, mid, endPos);
			SumTaskResult rRes = right.compute();
			SumTaskResult lRes = left.join();
			lRes.append(rRes);
			return lRes;
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("SumForkCompute计算耗时" + new SumForkCompute().avg10());
	}
}
