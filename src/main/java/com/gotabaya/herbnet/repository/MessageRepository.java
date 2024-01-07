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
		SELECT * FROM (
			SELECT
				IF(senderid = :userid, receiverid, senderid) AS correspondent_id,
				MAX(Timestamp) AS latest_messagetime
			FROM message
			WHERE :userid IN (senderid, receiverid)
			GROUP BY correspondent_id
		) lm JOIN message m
		ON lm.correspondent_id IN (m.senderid, m.receiverid) AND lm.latest_messagetime = m.Timestamp
		ORDER BY IsRead DESC , Timestamp DESC;
	""", nativeQuery = true)
	List<Message> findLastMessagesForEachUser(@Param("userid") Long userid);

	@Query("""
		SELECT m FROM Message m
		WHERE (m.sender = :sender AND m.receiver = :receiver)
		OR (m.sender = :receiver AND m.receiver = :sender)
		ORDER BY m.timestamp DESC
	""")
	List<Message> findAllBySender(@Param("sender") User sender, @Param("receiver") User receiver);
}
