package com.wnc.sof1.sumtext;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBuffer代替RandomAccessFile读取字节流
 * 
 * @author wnc
 *
 */
public class MappedByteBufferCompute extends Aggre {

	@Override
	public long compute() throws Exception {
		RandomAccessFile raf = new RandomAccessFile(DataProcess.path, "r");
		byte buf[] = new byte[16 * 1024];
		final FileChannel ch = raf.getChannel();
		int fileLength = (int) ch.size();
		final MappedByteBuffer mb = ch.map(FileChannel.MapMode.READ_ONLY, 0, fileLength);
		int acc = 0;
		long total = 0;
		while (mb.hasRemaining()) {
			int len = Math.min(mb.remaining(), buf.length);
			mb.get(buf, 0, len);
			for (int i = 0; i < len; i++)
				if ((buf[i] >= 48) && (buf[i] <= 57))
					acc = acc * 10 + buf[i] - 48;
				else {
					total += acc;
					acc = 0;
				}
		}
		ch.close();
		raf.close();
		return total;
	}

	public static void main(String[] args) throws Exception {
		long result = new MappedByteBufferCompute().avg10();
		System.out.println("MappedByteBufferCompute计算耗时" + result);
	}

}
