package dnt.backend.repository;

import dnt.backend.model.entity.Message;
import dnt.backend.repository.custom.MessageRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>, MessageRepositoryCustom {
    List<Message> findAllBySenderAndRecipientOrderByTimeDesc(String sender, String recipient);
}
