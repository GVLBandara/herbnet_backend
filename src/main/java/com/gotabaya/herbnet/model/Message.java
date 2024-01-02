package com.gotabaya.herbnet.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "messageid")
	private Long messageId;

	@ManyToOne
	@JoinColumn(name = "senderid", referencedColumnName = "userid")
	private User sender;

	@ManyToOne
	@JoinColumn(name = "receiverid", referencedColumnName = "userid")
	private User receiver;

	@ManyToOne
	@JoinColumn(name = "productid", referencedColumnName = "productid")
	private Product product;

	@Column(name = "messagecontent")
	private String messageContent;

	private LocalDateTime timestamp;

	@Column(name = "isread")
	private boolean isRead;
}
