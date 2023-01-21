package de.bencoepp.kommod.daemon.test.network;

import de.bencoepp.entity.test.Report;
import de.bencoepp.entity.test.Stage;
import de.bencoepp.entity.test.Step;
import de.bencoepp.entity.test.Test;
import de.bencoepp.kommod.daemon.entity.ScheduledTest;
import de.bencoepp.kommod.daemon.repository.ScheduledTestRepository;
import de.bencoepp.kommod.daemon.test.TestExecutor;
import org.apache.logging.log4j.core.time.Instant;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BaseNetworkTest extends TestExecutor {

    public BaseNetworkTest(Test test, ScheduledTestRepository scheduledTestRepository, ScheduledTest scheduledTest) {
        super(test, scheduledTestRepository, scheduledTest);
    }

    @Override
    public void execute() {
        Report report = new Report();
        report.setTest(test);
        report.setTitle("Base Network Report");
        report.setStart(new Timestamp(System.currentTimeMillis()).toString());


        //Stages here
        List<Stage> stages = new ArrayList<>();
        stages.add(checkNetworkConnection());

        report.setStages(stages);
        report.setEnd(new Timestamp(System.currentTimeMillis()).toString());

        System.out.println(report.toString());
    }

    private Stage checkNetworkConnection(){
        Stage stage = new Stage();
        stage.setTitle("Check Network Connection");
        stage.setDescription("Test Network connections to different base services that are necessary all of the time");

        //Steps here
        List<Step> steps = new ArrayList<>();
        Step stepGoogle = new Step();
        stepGoogle.setTitle("google.com");
        stepGoogle.setDescription("Checking Connection to google.com, is true when the connection was successful");
        try {
            final URL url = new URL("https://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            stepGoogle.setOk(true);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            stepGoogle.setOk(false);
        }
        steps.add(stepGoogle);

        stage.setSteps(steps);
        return stage;
    }
}
