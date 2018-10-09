package net.beeapm.bytebuddy.hello.agent;

import net.beeapm.bytebuddy.hello.interceptor.MyAdvice;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.FieldManifestation;
import net.bytebuddy.description.modifier.Ownership;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

/**
 * Created by yuan on 2018/4/1.
 */
public class MyAgent {
    /**
     * @param arguments
     * @param inst
     */
    public static void agentmain(String arguments, Instrumentation inst) {
        System.out.println("Hey, look: I'm instrumenting a running JVM!");
    }
    public static void premain(String arguments, Instrumentation inst) throws Exception{
        System.out.println("\n----this is an bytebuddy sample -----");

        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder,
                                                    TypeDescription typeDescription,
                                                    ClassLoader classLoader, JavaModule javaModule){
                String className = typeDescription.getCanonicalName();
                System.out.println("++++++++ class name = " + className);
                try {

                    builder = builder.visit(Advice.to(MyAdvice.class).on(ElementMatchers.<MethodDescription>named("methodB")))
                            .defineField("body",String.class, Visibility.PUBLIC, Ownership.STATIC, FieldManifestation.FINAL);//添加body属性
                }catch (Exception e){
                    e.printStackTrace();
                }
                return builder;
            }
        };



        AgentBuilder agentBuilder =  new AgentBuilder.Default()
                .with(new MyListener())
                .with(AgentBuilder.Listener.StreamWriting.toSystemError())
                .ignore(ElementMatchers.<TypeDescription>none().and(ElementMatchers.nameStartsWith("net.bytebuddy.")));

        agentBuilder = agentBuilder.type(ElementMatchers.nameStartsWith("net.beeapm.bytebuddy.hello.sample")).transform(transformer).asDecorator();
        agentBuilder.installOn(inst);
        //agentBuilder.installOn(inst);
    }

    private static class MyListener implements AgentBuilder.Listener {
        @Override
        public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

        }

        @Override
        public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module,
                                     boolean loaded, DynamicType dynamicType) {
            //修改后的类输出
            WeavingClassLog.INSTANCE.log(typeDescription, dynamicType);
        }

        @Override
        public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module,
                              boolean loaded) {
        }

        @Override
        public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded,
                            Throwable throwable) {
            throwable.printStackTrace();
        }

        @Override
        public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {
        }
    }

}


