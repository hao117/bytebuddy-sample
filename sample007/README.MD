# 样例007
使用`Advice`拦截器，获取原方法的异常信息
获取异常，需要添加onThrowable = Throwable.class 和@Advice.Thrown Throwable t，如果没有异常t==null
在`MainTest007`的`vm`上添加
`-javaagent:your-dir/packages/agent006.jar`
运行`MainTest007`类
