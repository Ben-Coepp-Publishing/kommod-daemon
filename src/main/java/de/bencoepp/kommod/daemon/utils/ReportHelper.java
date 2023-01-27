package de.bencoepp.kommod.daemon.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.bencoepp.entity.test.Report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This Helper is all for handling and using reports throughout the daemon. The reports are saved locally
 * meaning that the file access depends mainly on the way the user hardware is set up.
 */
public class ReportHelper {

    /**This method will return all locally saved reports. If there are no reports the list will be empty
     * if there are reports they are read from YAML files.
     *
     * Depending on the amount of reports this might take quite a while
     * @return List<Report>
     * @throws IOException
     */
    public static List<Report> getAllReports() throws IOException {
        ArrayList<Report> reports = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        List<File> files = new ArrayList<>();
        try{
            files = List.of(new File("/report").listFiles());
        }catch (NullPointerException e){
            System.out.println(e);
        }
        for (File file : files) {
            reports.add(mapper.readValue(file, Report.class));
        }
        return reports;
    }
}
