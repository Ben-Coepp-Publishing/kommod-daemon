package de.bencoepp.kommod.daemon.scheduler;

import org.springframework.stereotype.Component;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class Scheduler {

    //@Scheduled(fixedRate = 60000)
    public void executeScheduler() throws InterruptedException, IOException {
        new Thread(() -> {
            Class<?> clazz = null;
            try {
                clazz = Class.forName("");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Constructor<?> constructor = null;
            try {
                constructor = clazz.getConstructor();
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            Object instance = null;
            try {
                instance = constructor.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            Method method;
            try {
                method = instance.getClass().getMethod("execute");
                method.invoke(instance);
            } catch (Exception e) {
                System.out.println(e);
            }
        }) {{
            start();
        }}.join();
    }
}