package com.wnc.sof1.sumtext;

public abstract class Aggre {
	private static final int TIMES = 10;

	/**
	 * 计算10次的平均时间
	 * 
	 * @return
	 * @throws Exception
	 */
	public long avg10() throws Exception {
		long l = System.currentTimeMillis();
		for (int i = 0; i < TIMES; i++) {
			compute();
		}
		return (System.currentTimeMillis() - l) / TIMES;
	}

	public abstract long compute() throws Exception;
}
