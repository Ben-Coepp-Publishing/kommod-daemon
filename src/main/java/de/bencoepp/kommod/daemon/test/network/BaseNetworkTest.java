package de.bencoepp.kommod.daemon.test.network;

import de.bencoepp.entity.test.Test;
import de.bencoepp.kommod.daemon.repository.ScheduledTestRepository;
import de.bencoepp.kommod.daemon.test.TestExecutor;

public class BaseNetworkTest extends TestExecutor {

    public BaseNetworkTest(ScheduledTestRepository scheduledTestRepository, Test test) {
        super(scheduledTestRepository, test);
    }

    @Override
    public void execute() {
        System.out.println("test test test");
    }
}
