package de.bencoepp.kommod.daemon.test;

import de.bencoepp.entity.test.Test;
import de.bencoepp.kommod.daemon.repository.ScheduledTestRepository;

public class TestExecutor {

    public ScheduledTestRepository scheduledTestRepository;
    public Test test;

    public TestExecutor(ScheduledTestRepository scheduledTestRepository, Test test) {
        this.scheduledTestRepository = scheduledTestRepository;
        this.test = test;
    }

    public void prepare(){

    }

    public void execute(){

    }
}
