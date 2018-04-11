# StackOverFlow_Code_Base

## sof-1/sumtext [Fastest way to sum integers in text file](https://stackoverflow.com/questions/25606833/fastest-way-to-sum-integers-in-text-file)
大数据求和方式对比, 数据量100M条. 首先执行DataProcess生成测试数据, 然后运行CompareClient:  
 NaturalCompute计算耗时66  
 NaturalCompute2计算耗时59  
 ManualCompute计算耗时112  
 BinaryBackwardCompute计算耗时17  
 BinaryForWardCompute计算耗时13  
 MappedByteBufferCompute计算耗时12  
 SumForkCompute计算耗时30  

***

## sof-1/list2map [Java: How to convert List to Map](https://stackoverflow.com/questions/4138364/java-how-to-convert-list-to-map)
提供三种方式进行转换  
NatureConvert: 原始循环方式  
AbstractConvert: 泛型方式
```
public static <K, T> Map<K, T> toMapBy(List<T> list, Function<? super T, ? extends K> mapper) {
		return list.stream().collect(Collectors.toMap(mapper, Function.identity()));
	}
```
MapsConvert: Google工具类方式
```
Maps.uniqueIndex(students, Student::getId)
```
