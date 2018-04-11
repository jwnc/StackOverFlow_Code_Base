package com.wnc.sof1.sumtext;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;

public class DataProcess {
	public static String path = "D:\\个人学习\\SOF\\sumtext\\data.txt";

	/**
	 * 生成100W条数据, 数据范围0-1亿
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Random rnd = new Random();
		for (int i = 0; i < 1000000; i++) {
			FileUtils.write(new File(path), rnd.nextInt(100000000) + System.getProperty("line.separator"), true);
		}
	}
}
