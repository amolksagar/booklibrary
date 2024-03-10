package au.gov.nsw.revenue.booklibrary.repository;

import au.gov.nsw.revenue.booklibrary.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findByDescription(String description);
}
