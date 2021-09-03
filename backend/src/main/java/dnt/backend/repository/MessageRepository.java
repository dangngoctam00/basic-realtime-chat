package dnt.backend.repository;

import dnt.backend.model.entity.Message;
import dnt.backend.repository.custom.MessageRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>, MessageRepositoryCustom { }
