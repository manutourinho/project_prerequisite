package habsida.spring.boot_security.demo.repository;

import habsida.spring.boot_security.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email = :email")
    User findByUsername(@Param("email") String email);

//    User findById(long id);

}
