package lipatov.lab.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import lipatov.lab.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByLoginIgnoreCase(String login);
}