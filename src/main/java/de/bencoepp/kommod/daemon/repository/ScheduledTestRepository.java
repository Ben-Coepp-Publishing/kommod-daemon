package de.bencoepp.kommod.daemon.repository;

import de.bencoepp.kommod.daemon.entity.ScheduledTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduledTestRepository extends JpaRepository<ScheduledTest, Long> {
    List<ScheduledTest> findAllByStatus(Integer statusScheduled);
}
