package dnt.backend.repository;

import dnt.backend.model.entity.User;
import dnt.backend.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    List<User> findAllByUsernameIn(List<String> usernames);
}
