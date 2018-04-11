package com.wnc.sof1.sumtext;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * 不用parseInt, 改为手动解析
 * 
 * @author wnc
 *
 */
public class ManualCharArrayCompute extends Aggre {

	@Override
	public long compute() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(DataProcess.path));
		String line;
		long total = 0;
		while ((line = br.readLine()) != null) {
			char chs[] = line.toCharArray();
			int mul = 1;
			for (int i = chs.length - 1; i >= 0; i--) {
				char c = chs[i];
				switch (c) {
				case '0':
					break;
				case '1':
					total += mul;
					break;
				case '2':
					total += (mul << 1);
					break;
				case '4':
					total += (mul << 2);
					break;
				case '8':
					total += (mul << 3);
					break;
				default:
					total += (mul * ((byte) c - (byte) ('0')));
				}
				mul *= 10;
			}
		}
		br.close();
		return total;
	}

	public static void main(String[] args) throws Exception {
		long result = new ManualCharArrayCompute().avg10();
		System.out.println("ManualCompute计算耗时" + result);
	}

}
