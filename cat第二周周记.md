## 生活随记

**4.4**

真是不好的一天，但是上帝给了我补救的机会，不知道能不能抓住

## 学习笔记

1. 学到的新东西：

   loadclass()和class.forname()的区别：

   - loadclass可以加载类且不初始化其中的静态方法/变量。

   - class.forname初始化其中的静态方法，加载到jvm中。这样可以通过反射实例化

   JarURLConnection

   - 是URLConnection的一个实现类。


   - JarURLConnection的实例可以引用一个JAR的压缩包或者这种包里的某个文件。

   Enumeration

   - 枚举接口，有hasMoreElements，nextElement两个方法。

   - 现在很多已被iterator替代，但是还有一些方法的返回值会使用这个接口。

   ClassLoader

   - 作为类的容器，它起到类隔离的作用

   - ClassLoader一共有三个，BootstrapClassLoader、ExtensionClassLoader 和 AppClassLoader。

   - BootstrapClassLoader 负责加载 JVM 运行时核心类，这些类位于 JAVA_HOME/lib/rt.jar 文件中，我们常用内置库 java.xxx.* 都在里面。称之为根加载器。

   - ExtensionClassLoader 负责加载 JVM 扩展类，比如 swing 系列、内置的 js 引擎、xml 解析器 等等，这些库名通常以 javax 开头，它们的 jar 包位于 JAVA_HOME/lib/ext/*.jar 中，有很多 jar 包。

   - AppClassLoader 才是直接面向我们用户的加载器，它会加载 Classpath 环境变量里定义的路径中的 jar 包和目录。我们自己编写的代码以及使用的第三方 jar 包通常都是由它来加载的。

     **所以我们的项目加载我们的框架时要使用AppClassLoader。可以通过getContextClassLoader**

     1. 如果没有人工去设置，那么所有的线程的 contextClassLoader 都是 AppClassLoader。
     2. 它可以做到跨线程共享类，只要它们共享同一个 contextClassLoader。父子线程之间会自动传递 contextClassLoader，所以共享起来将是自动化的。
     3. 如果我们不去定制 contextClassLoader，那么所有的线程将会默认使用 AppClassLoader，所有的类都将会是共享的。

     **那么为什么不用普通的classLoader呢？不是也是获取面向用户的加载器AppClassLoader吗？**

     1. getClassLoader()是当前类加载器,而getContextClassLoader是当前线程的类加载器
     2. getClassLoader是使用双亲委派模型来加载类的,而getContextClassLoader就是为了避开双亲委派模型的加载方式的,也就是说它不是用这种方式来加载类。我的理解就是说在使用getContextClassLoader时AppClassLoader会先自己加载，不行再让他的parent加载。

   getClassLoader().getResources

   - 获取url。

   - url.getProtocol()获取协议。网络协议http...文件协议file，jar，一般在路径前面。

     例如 file:/E:/Database/bin/com/test/image.gif



## 一周总结

反思：我真的太蠢了，再来一次我可以只选一个工作室。

## 存在问题

之前没搞懂校园上牌是什么意思。现在知道了已经快写完了

## 下周规划

无，能过一轮的话，二轮写个简易框架
