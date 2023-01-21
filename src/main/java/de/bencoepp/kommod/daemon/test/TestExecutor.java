package de.bencoepp.kommod.daemon.test;


import de.bencoepp.entity.test.Test;
import de.bencoepp.kommod.daemon.entity.ScheduledTest;
import de.bencoepp.kommod.daemon.repository.ScheduledTestRepository;

import java.io.IOException;

public class TestExecutor {

    public Test test;
    public ScheduledTestRepository scheduledTestRepository;
    public ScheduledTest scheduledTest;
    public TestExecutor(Test test, ScheduledTestRepository scheduledTestRepository, ScheduledTest scheduledTest) {
        this.test = test;
        this.scheduledTestRepository = scheduledTestRepository;
        this.scheduledTest = scheduledTest;
    }

    public void prepare(){

    }

    public void execute(){

    }
}
