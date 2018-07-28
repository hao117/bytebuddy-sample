package net.beeapm.bytebuddy.hello.agent;

import net.beeapm.bytebuddy.hello.interceptor.HttpURLConnectionMethodAdviceInterceptor;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.jar.JarFile;

/**
 * Created by yuan on 2018/4/1.
 */
public class MyAgent {
    public static void agentmain(String arguments, Instrumentation inst) {
        System.out.println("Hey, look: I'm instrumenting a running JVM!");
    }
    public static void premain(String arguments, Instrumentation inst) throws Exception{
        System.out.println("\n----this is an bytebuddy sample -----");


        AgentBuilder agentBuilder = new AgentBuilder.Default().with(new MyListener());
        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder,
                                                    TypeDescription typeDescription,
                                                    ClassLoader classLoader, JavaModule javaModule){
                String className = typeDescription.getCanonicalName();
                System.out.println("++++++++ class name = " + className);
                try {
                    builder = builder.method(ElementMatchers.named("getRequestMethod").or(ElementMatchers.named("getHeaderFields"))).intercept(Advice.to(HttpURLConnectionMethodAdviceInterceptor.class));
                }catch (Exception e){

                }
                return builder;
            }
        };
        //agentBuilder.enableBootstrapInjection(inst,temp);
        agentBuilder = agentBuilder.ignore(ElementMatchers.none().and(ElementMatchers.nameStartsWith("net.beeapm.bytebuddy.hello.agent")))
                .type(ElementMatchers.nameEndsWith(".http.HttpURLConnection"))
                .transform(transformer);

        //agentBuilder.with(AgentBuilder.Listener.StreamWriting.toSystemError()).installOn(inst);
        agentBuilder.installOn(inst);
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


