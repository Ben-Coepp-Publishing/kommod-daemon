package de.bencoepp.kommod.daemon.repository;

import de.bencoepp.kommod.daemon.entity.ScheduledTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledTestRepository extends JpaRepository<ScheduledTest, Integer> {

}
