package de.bencoepp.kommod.daemon.test.network;

import de.bencoepp.entity.test.Report;
import de.bencoepp.entity.test.Stage;
import de.bencoepp.entity.test.Step;
import de.bencoepp.entity.test.Test;
import de.bencoepp.kommod.daemon.entity.ScheduledTest;
import de.bencoepp.kommod.daemon.repository.ScheduledTestRepository;
import de.bencoepp.kommod.daemon.test.TestExecutor;
import org.apache.logging.log4j.core.time.Instant;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        try {
            stages.add(checkNetworkConnection());
            stages.add(checkNetworkSpeed());
            stages.add(checkNetworkConnections());
        }catch (Exception e){
            System.out.println(e);
        }


        report.setStages(stages);
        report.setEnd(new Timestamp(System.currentTimeMillis()).toString());
        report.setOk(true);
        for (Stage stage : report.getStages()) {
            if(stage.getOk() == false){
                report.setOk(false);
            }
        }

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

        Step stepDockerHub = new Step();
        stepDockerHub.setTitle("hub.docker.com");
        stepDockerHub.setDescription("Connection to Docker Hub, will be true if connection was successful to Docker Hub");
        try {
            final URL url = new URL("https://hub.docker.com/");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            stepDockerHub.setOk(true);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            stepDockerHub.setOk(false);
        }
        steps.add(stepDockerHub);

        stage.setSteps(steps);
        stage.setOk(true);
        for (Step step : steps) {
            if(step.getOk() == false){
                stage.setOk(false);
            }
        }
        return stage;
    }

    private Stage checkNetworkSpeed() throws IOException {

        Stage stage = new Stage();
        stage.setTitle("Network Speed");
        stage.setDescription("Check the download and upload speed");

        Long start = System.nanoTime();
        BufferedInputStream in = new BufferedInputStream(new URL("https://file-examples.com/storage/fe2879c03363c669a9ef954/2017/10/file_example_JPG_1MB.jpg").openStream());
        Long end = System.nanoTime();
        double downloadSpeed = (double) (end -start) / 1_000_000_000;

        List<Step> steps = new ArrayList<>();

        Step stepDownload = new Step();
        stepDownload.setTitle("Download Speed");
        stepDownload.setDescription("1mb was downloaded in the following time: 1em/" + downloadSpeed);
        stepDownload.setOk(true);
        steps.add(stepDownload);

        Step stepUpload = new Step();
        stepUpload.setTitle("Upload Speed");
        stepUpload.setOk(true);
        //TODO add upload speed check
        steps.add(stepUpload);
        stage.setSteps(steps);
        stage.setOk(true);
        return stage;
    }

    private Stage checkNetworkConnections() throws UnknownHostException {
        Stage stage = new Stage();
        stage.setTitle("Network Connections");
        stage.setDescription("Check different connection types on the local system");

        List<Step> steps = new ArrayList<>();

        Step stepIP4 = new Step();
        stepIP4.setTitle("Ip4 Address");
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            stepIP4.setDescription(socket.getLocalAddress().getHostAddress());
            stepIP4.setOk(true);
        } catch (SocketException | UnknownHostException e) {
            System.out.println(e);
            stepIP4.setDescription(e.toString());
            stepIP4.setOk(false);
        }
        steps.add(stepIP4);

        stage.setSteps(steps);
        return stage;
    }

    private Inet6Address getIPv6Addresses(InetAddress[] addresses) {
        for (InetAddress addr : addresses) {
            if (addr instanceof Inet6Address) {
                return (Inet6Address) addr;
            }
        }
        return null;
    }
}
