package de.bencoepp.kommod.daemon.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ScheduledTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String path;
    private Integer status;
    public static Integer STATUS_SCHEDULED = 0;

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
}
