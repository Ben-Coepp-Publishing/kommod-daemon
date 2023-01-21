package de.bencoepp.kommod.daemon.scheduler;

import de.bencoepp.entity.test.Test;
import de.bencoepp.kommod.daemon.entity.ScheduledTest;
import de.bencoepp.kommod.daemon.repository.ScheduledTestRepository;
import de.bencoepp.kommod.daemon.utils.TestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Component
public class Scheduler {

    @Autowired
    private ScheduledTestRepository scheduledTestRepository;

    @Scheduled(fixedRate = 60000)
    public void executeScheduler() throws InterruptedException, IOException {

        List<ScheduledTest> scheduledTests = scheduledTestRepository.findAllByStatus(ScheduledTest.STATUS_SCHEDULED);
        for (ScheduledTest scheduledTest : scheduledTests) {
            Test test = TestHandler.getTest(scheduledTest.getPath());

            new Thread(() -> {
                Class<?> clazz = null;
                try {
                    clazz = Class.forName(test.getExecutor());
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                Constructor<?> constructor = null;
                try {
                    constructor = clazz.getConstructor(Test.class, ScheduledTestRepository.class, ScheduledTest.class);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                Object instance = null;
                try {
                    instance = constructor.newInstance(test, scheduledTestRepository, scheduledTest);
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
}