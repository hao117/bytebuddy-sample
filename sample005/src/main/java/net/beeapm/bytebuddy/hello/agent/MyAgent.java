package net.beeapm.bytebuddy.hello.agent;

import net.beeapm.bytebuddy.hello.interceptor.HttpURLConnectionMethodInterceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.util.Collections;
import java.util.concurrent.Callable;

/**
 * Created by yuan on 2018/4/1.
 */
public class MyAgent {
    /**
     *
     * -javaagent:H:\workspace\java\packages\agent005.jar
     *
     * @param arguments
     * @param inst
     */
    public static void agentmain(String arguments, Instrumentation inst) {
        System.out.println("Hey, look: I'm instrumenting a running JVM!");
    }
    public static void premain(String arguments, Instrumentation inst) throws Exception{
        System.out.println("\n----this is an bytebuddy sample -----");
//
//      inst.appendToBootstrapClassLoaderSearch(new JarFile(new File("H:\\workspace\\java\\packages\\temp\\jar1.jar")));
        File temp = new File("H:/workspace/java/packages/temp");

        ClassInjector.UsingInstrumentation.of(temp, ClassInjector.UsingInstrumentation.Target.BOOTSTRAP, inst).inject(Collections.singletonMap(
                new TypeDescription.ForLoadedType(HttpURLConnectionMethodInterceptor.class),
                ClassFileLocator.ForClassLoader.read(HttpURLConnectionMethodInterceptor.class).resolve()));

        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder,
                                                    TypeDescription typeDescription,
                                                    ClassLoader classLoader, JavaModule javaModule){
                String className = typeDescription.getCanonicalName();
                System.out.println("++++++++ class name = " + className);
                try {
                    builder = builder.method(ElementMatchers.named("getRequestMethod"))
                            //Interceptor不能用new，方法intercept必须static
                            .intercept(MethodDelegation.to(HttpURLConnectionMethodInterceptor.class));
                }catch (Exception e){

                }
                return builder;
            }
        };
        new AgentBuilder.Default()
                .with(new MyListener())
                .with(AgentBuilder.Listener.StreamWriting.toSystemError())
                .ignore(ElementMatchers.nameStartsWith("net.bytebuddy."))
                .enableBootstrapInjection(inst,temp)
                .type(ElementMatchers.nameEndsWith(".net.HttpURLConnection"))
                .transform(transformer).installOn(inst);
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
            InstrumentDebuggingClass.INSTANCE.log(typeDescription, dynamicType);
        }

        @Override
        public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module,
                              boolean loaded) {
        }

        @Override
        public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded,
                            Throwable throwable) {
        }

        @Override
        public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {
        }
    }

}


