package learn.task44;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DBLoginRepo extends JpaRepository<Login, Integer> {
}
