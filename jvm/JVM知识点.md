
```puml
@startmindmap
* java运行数据区
**_ PC程序计数器
***_ 记录当前线程执行字节码位置
***_:字节码解释器通过改变程序计数器来依次读取指令，从而实现代码的流程控制，
如：顺序执行、选择、循环、异常处理。;
***_ 线程私有

**_ 虚拟机栈
***_ 方法被执行，Java虚拟机同步创建一个栈帧
****_ 栈帧
*****_ 局部变量表
******_ 存放编译期可知的各种数据类型、对象引用（reference 类型，句柄或者直接指针）
*****_ 操作数栈
******_ 存放方法执行中间计算结果
*****_ 动态链接
******_:在 Java 源文件被编译成字节码文件时，所有的变量和方法引用都作为符号引用（Symbilic Reference）保存在Class 文件的常量池里。
当一个方法要调用其他方法，需要将常量池中指向方法的符号引用转化为其在内存地址中的直接引用。
动态链接的作用就是为了将符号引用转换为调用方法的直接引用。;
*****_ 方法出口
***_ 线程私有

**_ 本地方法栈
**_ 堆
***_ 对象实例
***_ 多个线程在使用堆内存采取TLAB

**_ 方法区
***_ 类型信息、常量、静态变量、即时编译器编译后的代码缓存等数据
***_ 线程共享
**_ 直接内存
***_ java程序与操作系统内核共享内存，mmap技术

@endmindmap
```


```puml
@startmindmap
* 对象创建
**_ 类加载检查
**_ 分配内存
***_ 方法
****_ 碰撞指针
*****_ 适用场景：堆内存规整（即没有碎片）的情况下
*****_ 原理：用过的内存全部整合到一边，没有用过的内存放在另一边，中间有一个分界指针，只需要向没有用过的内存方向将该指针移动对象内存大小位置即可
*****_ 使用该分配方式的GC收集器：serial,parNew
****_ 空闲列表
*****_ 适用场景：堆内存不规整的情况下
*****_ 原理：虚拟机维护一个列表，该列表中记录哪些位置是可以使用的，再分配的时候，找一块足够大的内存区域来划分对象实例，最后更新列表即可
*****_ 使用该分配方式的GC收集器：CMS
***_ 内存分配并发问题
****_ CAS重试
****_ TLAB

**_ 初始化零值
***_ 此时对象已经可以使用，但使用会出现问题，
***_ 有： if (object == null) then false
**_ 设置对象头
**_ 执行init方法


@endmindmap

```


```puml
@startmindmap
* 对象的内存布局
**_ 普通对象
***_ 对象头
***_ 类型指针
****_ 配置参数 -XX:+UseCompressedClassPointers 
****_ 实际占用 为4字节 不开启为8字节
***_ 实例数据
****_ 配置参数 -XX:+UseCompressedOops
****_:4字节 不开启为8字节 
Oops Ordinary Object Pointers;
***_ 对其填充 8的倍数

**_ 数组对象
***_ 对象头
***_ 类型指针
***_ 数组数据
***_ 数组长度：4字节
***_ 对其填充 8的倍数
@endmindmap

```

```puml
@startmindmap
* 对象的访问定位
**_ 句柄
***_:如果使用句柄的话，那么 Java 堆中将会划分出一块内存来作为句柄池，
reference 中存储的就是对象的句柄地址，而句柄中包含了对象实例数据与类型数据各自的具体地址信息。;
**_ 直接指针
***_: 如果使用直接指针访问，
那么 Java 堆对象的布局中就必须考虑如何放置访问类型数据的相关信息，而 reference 中存储的直接就是对象的地址。;

@endmindmap

```







```puml
@startmindmap
* 对象是否存活
**_ 引用计数法
***_ 对象内部维护一个计数器
***_ 循环引用
**_ 可达性分析
***_ GC Roots的对象
****_ 虚拟机栈（栈帧中的局部变量表引用的对象） 
****_ 常量引用对象
****_ 静态属性引用的对象 
****_ 被锁持有的对象
****_ 本地方法引用的对象

@endmindmap
```

```puml
@startmindmap
* 引用
**_ 强引用
***_ Object o = new Object()
***_ 不会回收
**_ 软引用
***_ 内存不够回收
***_ SoftReference
**_ 弱引用
***_ GC就回收
***_ WeakReference
**_ 虚引用
***_ 唯一目的:在这个对象被收集器回收时收到一个系统通知。
***_ PhantomReference

@endmindmap
```

```puml
@startmindmap
* 如何判断一个常量是废弃常量
**_:JDK1.7 之前运行时常量池逻辑包含字符串常量池存放在方法区, 
此时 hotspot 虚拟机对方法区的实现为永久代;

**_:JDK1.7 字符串常量池被从方法区拿到了堆中, 
这里没有提到运行时常量池,也就是说字符串常量池被单独拿到堆,运行时常量池剩下的东西还在方法区, 也就是 hotspot 中的永久代 。;

**_:JDK1.8 hotspot 移除了永久代用元空间(Metaspace)取而代之, 
这时候字符串常量池还在堆, 运行时常量池还在方法区, 只不过方法区的实现从永久代变成了元空间(Metaspace);

@endmindmap
```

```puml
@startmindmap
* 如何判断一个类是无用的类
**_ 该类所有的实例都已经被回收，也就是 Java 堆中不存在该类的任何实例。
**_ 加载该类的 ClassLoader 已经被回收
**_ 该类对应的 java.lang.Class 对象没有在任何地方被引用，无法在任何地方通过反射访问该类的方法。


@endmindmap
```





```puml
@startmindmap
* 垃圾回收算法
**_ 标记清除
***_ 空间碎片
**_ 复制
***_ 浪费空间
**_ 标记整理
***_ 需要移动对象
**_ 分代收集算法

@endmindmap
```


```puml
@startmindmap
* 垃圾回收器
**_ 年轻代
***_ 复制
****_ serial
****_ Parallel Scavenge
****_ parNew
**_ 老年代
***_ 标记整理
****_ serial old
****_ Parallel old
***_ 标记清除
****_ CMS
*****_ 过程
******_ 1初始标记
******_ 2并发标记
******_ 3重新标记
******_ 4并发清除
*****_ 13 需要STW 即暂停用户线程
*****_ 4 浮动垃圾
*****_ 最早并发垃圾回收器
**_ G1 
***_ 堆内存分为region，region可随意扮演新生代或者老年代 
**_ ZGC


@endmindmap
```



```puml
@startmindmap
* 类加载过程
**_ 加载
***_ 将字节码文件加载到内存
***_ 将字节流的静态属性转换成方法区运行时数据结构
***_ 生成该类的Class对象

**_ 链接
***_ 验证
****_ 文件格式验证
*****_ 验证字节流是否符合Class文件格式的规范

*****_:例如 1.是否以0xCAFEBABE开头、
2. 主次版本号是否在当前虚拟机的处理范围之内、
3. 常量池中的常量是否有不被支持的类型;

****_ 元数据验证
*****_ 对字节码描述的信息进行语义分析，以保证其描述的信息符合java语言规范的要求
*****_: 例如1. 这个类是否有父类
这个类是否被继承了不允许继承的类(final修饰的类)等等;

****_ 字节码验证
*****_ 最复杂的一个阶段，通过控制数据流和控制流分析，确定程序语义是合法的、符合逻辑的。
*****_ 比如保证任意时刻操作数栈和指令代码序列都能配合工作

****_ 符号引用验证
*****_ 确保解析动作能正常运行

***_ 准备
****_ 仅为类变量进行内存分配(即静态变量)，实例变量会随着对象一块分配到java堆中

****_ 从概念上来说，类变量所使用的内存都应该在方法区
*****_ JDK7之前:hotspot 使用永久代来实现方法区。
*****_ JDK7之后:hotspot 将字符串常量池、静态变量等移动到堆中，这个时候类变量会随着Class对象一起存放到Java堆中。
****_ 这里所设置的初始值通常情况下是数据类型默认的零值(如0、0L、null、false等)
*****_:private static int i = 111 
此时 i == 0  then true 
;
*****_ 如类变量被final 修饰 则此时该值不会为零值 



***_ 解析
****_:解析阶段是虚拟机将常量池内的符号引用替换为直接引用的过程。
解析动作主要针对类或接口、字段、类方法、接口方法、方法类型、方法句柄和调用限定符 7 类符号引用进行。;
*****_ 符号引用
******_ 符号引用就是一组符号来描述目标，可以是任何字面量。

*****_ 直接引用
******_ 指向目标的指针、相对偏移量或一个间接定位到目标的句柄

**_ 初始化
***_ 执行初始化方法 <clinit> ()方法的过程
@endmindmap
```


```puml

@startmindmap
* 类加载的时机
**_ new、getstatic、putstatic 或 invokestatic 字节码指令
***_ JVM执行new指令时会初始化类。即当程序创建一个类的实例
***_ JVM执行getstatic指令时会初始化类。即程序访问类的静态变量（不是常量，常量会被加载到运行时数据区）
***_ JVM执行putstatic指令时会初始化类。即程序给类的静态变量赋值。
***_ JVM执行invokestatic指令时会初始化类。即程序调用类的静态方法。
**_ 反射 
***_ Class.forname("..."), newInstance() 等等。如果类没初始化，需要触发其初始化。
**_ 父类未初始化
**_ 加载main()的类
**_ MethodHandle 或者VarHandle轻量级的反射调用机制


@endmindmap
```

```puml

@startmindmap
* 类加载器
**_ BootstrapClassLoader(启动类加载器)
**_ ExtensionClassLoader(扩展类加载器)
**_ AppClassLoader(应用程序加载器)

@endmindmap
```


#### 双亲委派

```java
public class ClassLoaderDemo {
    public static void main(String[] args) {
        System.out.println("ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader());
        System.out.println("The Parent of ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader().getParent());
        System.out.println("The GrandParent of ClassLodarDemo's ClassLoader is " + ClassLoaderDemo.class.getClassLoader().getParent().getParent());
    }
}
```
output

```text
ClassLodarDemo's ClassLoader is sun.misc.Launcher$AppClassLoader@18b4aac2
The Parent of ClassLodarDemo's ClassLoader is sun.misc.Launcher$ExtClassLoader@1b6d3586
The GrandParent of ClassLodarDemo's ClassLoader is null
```

```java
private final ClassLoader parent;
protected Class<?> loadClass(String name, boolean resolve)
        throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // 首先，检查请求的类是否已经被加载过
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    if (parent != null) {//父加载器不为空，调用父加载器loadClass()方法处理
                        c = parent.loadClass(name, false);
                    } else {//父加载器为空，使用启动类加载器 BootstrapClassLoader 加载
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                   //抛出异常说明父类加载器无法完成加载请求
                }

                if (c == null) {
                    long t1 = System.nanoTime();
                    //自己尝试加载
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

```

过程：findInCache -> parent.loadClass -> findClass()
好处：保证了 Java 程序的稳定运行，可以避免类的重复加载

打破双亲委派
重写loadClass()即可



### 参考
- 《深入理解 Java 虚拟机：JVM 高级特性与最佳实践（第三版》
- [https://javaguide.cn/java/jvm/memory-area.html](https://javaguide.cn/java/jvm/memory-area.html)
