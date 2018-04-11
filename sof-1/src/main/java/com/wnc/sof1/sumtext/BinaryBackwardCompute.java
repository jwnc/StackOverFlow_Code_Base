package com.wnc.sof1.sumtext;

import java.io.RandomAccessFile;

/**
 * 从后往前扫描
 * 
 * @author wnc
 *
 */
public class BinaryBackwardCompute extends Aggre {
	/**
	 * 使用魔法数硬编码比较快, 使用常量则会多花20+毫秒
	 */
	private static final int ZERO_ANSII_CODE = 48;
	private static final int NINE_ANSII_CODE = 57;

	@Override
	public long compute() throws Exception {
		RandomAccessFile raf = new RandomAccessFile(DataProcess.path, "r");
		int lastRead = (int) raf.length();
		byte buf[] = new byte[8 * 1024 * 1024];
		int mul = 1;
		long total = 0;
		while (lastRead > 0) {
			int len = Math.min(buf.length, lastRead);
			raf.seek(lastRead - len);
			raf.readFully(buf, 0, len);
			// 计算剩余未读字节长度
			lastRead -= len;
			for (int i = len - 1; i >= 0; i--) {
				if ((buf[i] >= 48) && (buf[i] <= 57)) {
					total += mul * (buf[i] - 48);
					mul *= 10;
				} else
					mul = 1;
			}
		}
		raf.close();
		return total;
	}

	public static void main(String[] args) throws Exception {
		long result = new BinaryBackwardCompute().avg10();
		System.out.println("BinaryBackCompute计算耗时" + result);
	}

}
