package com.gotabaya.herbnet.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gotabaya.herbnet.model.Message;
import com.gotabaya.herbnet.model.Product;
import com.gotabaya.herbnet.model.Species;
import com.gotabaya.herbnet.model.UserProfile;
import com.gotabaya.herbnet.model.dto.SignUpRequest;
import com.gotabaya.herbnet.repository.*;
import com.gotabaya.herbnet.security.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader  implements CommandLineRunner {
	private final ObjectMapper objectMapper;
	private final UserRepository userRepository;
	private final UserService userService;
	private final UserProfileRepository userProfileRepository;
	private final SpeciesRepository speciesRepository;
	private final ProductRepository productRepository;
	private final MessageRepository messageRepository;
	@Override
	public void run(String... args) throws IOException {
		if(userRepository.count()==0){
			try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/user.json")){
				List<SignUpRequest> users = objectMapper.readValue(inputStream, new TypeReference<>() {});
				users.forEach(userService::saveUser);
			}
		}

		if(userProfileRepository.count()==0){
			try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/profile.json")){
				userProfileRepository.saveAll(objectMapper.readValue(inputStream,new TypeReference<List<UserProfile>>(){}));
			}
		}

		if(speciesRepository.count()==0){
			try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/species.json")){
				speciesRepository.saveAll(objectMapper.readValue(inputStream,new TypeReference<List<Species>>(){}));
			}
		}

		if(productRepository.count()==0){
			try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/product.json")){
				productRepository.saveAll(objectMapper.readValue(inputStream,new TypeReference<List<Product>>(){}));
			}
		}

		if(messageRepository.count()==0){
			try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/message.json")){
				List<Message> messages = objectMapper.readValue(inputStream, new TypeReference<>() {
				});
				messages.forEach(message -> {
					if(message.getReceiver().getUserId()%2==0)message.setRead(true);
				});
				messages.stream().map(Message::isRead).forEach(System.out::println);
				messageRepository.saveAll(messages);
			}
		}
	}
}
