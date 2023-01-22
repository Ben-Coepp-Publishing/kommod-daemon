package de.bencoepp.kommod.daemon.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.bencoepp.entity.test.Report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReportHelper {

    public static List<Report> getAllReports() throws IOException {
        ArrayList<Report> reports = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        List<File> files = List.of(new File("/report").listFiles());
        for (File file : files) {
            reports.add(mapper.readValue(file, Report.class));
        }
        return reports;
    }
}
