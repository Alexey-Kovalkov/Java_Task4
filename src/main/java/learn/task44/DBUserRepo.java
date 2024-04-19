package learn.task44;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

interface DBUserRepo extends JpaRepository<User, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET fio = :userfio WHERE id = :id", nativeQuery = true)
    int updateUserSetNameForIdNative(@Param("userfio") String userfio, @Param("id") Integer id);
}



