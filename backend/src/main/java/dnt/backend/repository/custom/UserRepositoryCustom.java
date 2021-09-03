package dnt.backend.repository.custom;

import dnt.backend.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {
    List<String> getFriends(String me);
}
