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
		WITH LatestMessages AS (
			SELECT
				IF(senderid = :userid, receiverid, senderid) AS correspondent_id,
				MAX(Timestamp) AS latest_messagetime
			FROM message
			WHERE senderid = :userid OR receiverid = :userid
			GROUP BY
				IF(senderid = :userid, receiverid, senderid)
		)
  
		SELECT * FROM LatestMessages lm
		JOIN message m ON
			(lm.correspondent_id = m.senderid OR lm.correspondent_id = m.receiverid)
			AND  lm.latest_messagetime = m.Timestamp
	""", nativeQuery = true)
	List<Message> findLastMessagesForEachUser(@Param("userid") Long userid);

	@Query("""
		SELECT m FROM Message m
		WHERE (m.sender = :sender AND m.receiver = :receiver)
		OR (m.sender = :receiver AND m.receiver = :sender)
	""")
	List<Message> findAllBySender(@Param("sender") User sender, @Param("receiver") User receiver);
}
