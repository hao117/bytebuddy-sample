# 样例009
拦截构造函数，不能获取非静态的类成员属性  ，即`Advice`中不能使用注解`@Advice.FieldValue`获取非静态的属性
在`MainTest009`的`vm`上添加
`-javaagent:your-dir/packages/agent006.jar`
运行`MainTest009`类
