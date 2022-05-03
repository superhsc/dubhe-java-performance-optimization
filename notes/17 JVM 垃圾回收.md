# 17 JVM 垃圾回收



Java 中的一些代码优化技巧，和 JVM 的关系非常大，比如逃逸分析对非捕获型 Lambda 表达式的优化。



## JVM 内存区域划分 （运行时数据区域）

如下图所示，内存区域划分主要包括：

1. 程序计数器

2. Java 虚拟机栈

3. 本地方法栈

4. Java 堆

5. 方法区

6. 运行时常量池

7. 直接内存

![](http://assets.processon.com/chart_image/6229f44f637689082feec5fa.png)