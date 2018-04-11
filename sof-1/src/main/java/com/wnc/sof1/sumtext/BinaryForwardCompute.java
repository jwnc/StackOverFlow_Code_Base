package com.wnc.sof1.sumtext;

import java.io.RandomAccessFile;

/**
 * 从前往后扫描
 * 
 * @author wnc
 *
 */
public class BinaryForwardCompute extends Aggre {

	@Override
	public long compute() throws Exception {
		RandomAccessFile raf = new RandomAccessFile(DataProcess.path, "r");
		int fileLength = (int) raf.length();
		byte buf[] = new byte[16 * 1024];
		int acc = 0;
		long total = 0;
		int read = 0;
		while (read < fileLength) {
			int len = Math.min(buf.length, fileLength - read);
			raf.readFully(buf, 0, len);
			read += len;
			for (int i = 0; i < len; i++) {
				if ((buf[i] >= 48) && (buf[i] <= 57))
					acc = acc * 10 + buf[i] - 48;
				else {
					total += acc;
					acc = 0;
				}
			}
		}
		raf.close();
		return total;
	}

	public static void main(String[] args) throws Exception {
		long result = new BinaryForwardCompute().avg10();
		System.out.println("BinaryForwardCompute计算耗时" + result);
	}

}
