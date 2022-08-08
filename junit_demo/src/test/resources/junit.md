```plantuml
@startmindmap
* junit 核心概念
**_ 测试类 (TestCase)
**_ 测试集 (Suite)
**_ 测试运行器 (Runner)
@endmindmap
```
//关系图


```plantuml
@startmindmap
* junit 核心对象
**_ Assert 
***_ 让你定义你要去测试的条件，当条件成立时，assert保持沉默，但条件不成立抛出异常
**_ Test
***_ 一个以 @Test 注释的方法定义了一个测试。为了运行这个方法，Junit会创建一个包含类的新实例，然后在调用这个被注释的方法
**_ TestCase
***_ 一个测试类是 @Test 方法的容器
**_ Suite
***_ Suite 允许将测试类归为一组
**_ Runner
***_ Runner 类用来运行测试
@endmindmap
```