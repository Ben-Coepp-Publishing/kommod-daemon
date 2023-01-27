package de.bencoepp.kommod.daemon.test.system;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.bencoepp.entity.test.Report;
import de.bencoepp.entity.test.Stage;
import de.bencoepp.entity.test.Test;
import de.bencoepp.kommod.daemon.entity.ScheduledTest;
import de.bencoepp.kommod.daemon.repository.ScheduledTestRepository;
import de.bencoepp.kommod.daemon.test.TestExecutor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LanguageFrameworkTest extends TestExecutor {

    public LanguageFrameworkTest(Test test, ScheduledTestRepository scheduledTestRepository, ScheduledTest scheduledTest) {
        super(test, scheduledTestRepository, scheduledTest);
    }

    @Override
    public void execute() {
        scheduledTest.setStatus(ScheduledTest.STATUS_RUNNING);
        scheduledTestRepository.save(scheduledTest);
        Report report = new Report();
        report.setTest(test);
        report.setTitle("Language and Framework Test");
        report.setStart(new Timestamp(System.currentTimeMillis()).toString());
        report.setId(scheduledTest.getReport());

        //Stages here
        List<Stage> stages = new ArrayList<>();
        //TODO write test
        report.setStages(stages);

        report.setEnd(new Timestamp(System.currentTimeMillis()).toString());
        report.setOk(true);
        for (Stage stage : report.getStages()) {
            if(stage.getOk() == false){
                report.setOk(false);
            }
        }
        try {
            ObjectMapper mapper =  new ObjectMapper(new YAMLFactory());
            File file = new File( System.getProperty("user.dir") + "/report/" + report.getId() + ".yaml");
            Files.createDirectories(Paths.get( System.getProperty("user.dir") + "/report"));
            Files.createFile(Path.of(file.toURI()));
            mapper.writeValue(file, report);
        }catch (StreamWriteException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scheduledTest.setStatus(ScheduledTest.STATUS_FINISHED);
        scheduledTestRepository.save(scheduledTest);
    }
}
