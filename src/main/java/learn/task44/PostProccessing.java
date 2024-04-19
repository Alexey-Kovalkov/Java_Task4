package learn.task44;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Calendar;

@Component
public class PostProccessing implements BeanPostProcessor {
    @Value("${spring.application.pathlog}")
    private String lopPath;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!bean.getClass().isAnnotationPresent(LogTransformation.class)) return bean;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                Object o = method.invoke(bean, args);
                String sSep = " | ";
                String s = "Дата-время: " + Calendar.getInstance().getTime() + sSep;
                s += "Класc: " + obj.getClass().getName() + sSep;
                s += "Вх.: " + Arrays.toString(args).replace("[", "").replace("]", "") + sSep;
                s += "Вых.: " + o.toString() + sSep;
                try {
                    if (lopPath != null) {
                        FileWriter fw = new FileWriter(lopPath, true);
                        fw.write( s + "\n");
                        fw.close();}
                } catch (IOException eio) {
                    throw new RuntimeException(eio);
                }
                // return method.invoke(bean, args);
                return o;
            }
        });
        Constructor cons= bean.getClass().getConstructors()[0];
        Object [] arr=new Object[cons.getParameterCount()];
        return enhancer.create(cons.getParameterTypes(),arr);
    }
}
