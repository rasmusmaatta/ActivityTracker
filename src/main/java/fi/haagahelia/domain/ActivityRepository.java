package fi.haagahelia.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends CrudRepository<Activity, Long> {
    List<Activity> findByCategory(Category category);
    List<Activity> findByTitle(String title);
}
