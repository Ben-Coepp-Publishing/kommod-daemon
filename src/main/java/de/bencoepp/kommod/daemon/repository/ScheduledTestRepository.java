package de.bencoepp.kommod.daemon.repository;

import de.bencoepp.kommod.daemon.entity.ScheduledTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScheduledTestRepository extends JpaRepository<ScheduledTest, Long> {
    List<ScheduledTest> findAllByStatus(Integer statusScheduled);
}
