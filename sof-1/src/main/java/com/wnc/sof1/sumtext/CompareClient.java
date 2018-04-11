package com.wnc.sof1.sumtext;

public class CompareClient {
	public static void main(String[] args) throws Exception {
		System.out.println("NaturalCompute计算耗时" + new NaturalCompute().avg10());
		System.out.println("NaturalCompute2计算耗时" + new NaturalCompute2().avg10());
		System.out.println("ManualCompute计算耗时" + new ManualCharArrayCompute().avg10());
		System.out.println("BinaryBackwardCompute计算耗时" + new BinaryBackwardCompute().avg10());
		System.out.println("BinaryForWardCompute计算耗时" + new BinaryForwardCompute().avg10());
		System.out.println("MappedByteBufferCompute计算耗时" + new MappedByteBufferCompute().avg10());
		System.out.println("SumForkCompute计算耗时" + new SumForkCompute().avg10());
	}
}
