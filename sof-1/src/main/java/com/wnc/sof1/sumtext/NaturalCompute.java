package com.wnc.sof1.sumtext;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * 逐行解析
 * 
 * @author wnc
 *
 */
public class NaturalCompute extends Aggre {

	@Override
	public long compute() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(DataProcess.path));
		String line;
		long total = 0;
		while ((line = br.readLine()) != null) {
			int k = Integer.parseInt(line);
			total += k;
		}
		br.close();
		return total;
	}

	public static void main(String[] args) throws Exception {
		long result = new NaturalCompute().avg10();
		System.out.println("NaturalCompute计算耗时" + result);
	}

}
