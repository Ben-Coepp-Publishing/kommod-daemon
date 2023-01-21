package de.bencoepp.kommod.daemon.entity;


import jakarta.persistence.*;

@Entity
public class ScheduledTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String path;
    private Integer status;
    public static final Integer STATUS_SCHEDULED = 0;
    public static final Integer STATUS_RUNNING = 1;
    public static final Integer STATUS_FINISHED = 2;
    public static final Integer STATUS_CANCEL= 3;
    private String report;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
