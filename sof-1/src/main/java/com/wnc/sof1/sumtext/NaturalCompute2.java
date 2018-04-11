package com.wnc.sof1.sumtext;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * 一点小改变
 * 
 * @author wnc
 *
 */
public class NaturalCompute2 extends Aggre {

	@Override
	public long compute() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(DataProcess.path));
		String line;
		long total = 0;
		while ((line = br.readLine()) != null) {
			// int k = Integer.parseInt(line);
			total += Integer.parseInt(line);
		}
		br.close();
		return total;
	}

	public static void main(String[] args) throws Exception {
		long result = new NaturalCompute2().avg10();
		System.out.println("NaturalCompute2计算耗时" + result);
	}

}
