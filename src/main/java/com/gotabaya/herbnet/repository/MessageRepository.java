package com.gotabaya.herbnet.repository;

import com.gotabaya.herbnet.model.Message;
import com.gotabaya.herbnet.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

//TODO Modify to be used with Principles
public interface MessageRepository extends ListCrudRepository<Message, Long> {
	@Query(value = """
		SELECT m FROM Message m
		WHERE m.timestamp = (SELECT MAX(m2.timestamp) FROM Message m2 WHERE m2.sender = m.sender)
		ORDER BY m.isRead ASC , m.timestamp DESC
	""")
	List<Message> findLastMessagesForEachUser();

	@Query("SELECT m FROM Message m WHERE m.sender = :sender OR m.receiver = :sender")
	List<Message> findAllBySender(@Param("sender") User sender);
}
