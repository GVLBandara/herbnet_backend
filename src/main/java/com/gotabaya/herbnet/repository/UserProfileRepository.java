package com.gotabaya.herbnet.repository;

import com.gotabaya.herbnet.model.User;
import com.gotabaya.herbnet.model.UserProfile;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserProfileRepository extends ListCrudRepository<UserProfile, Long> {
	Optional<UserProfile> findUserProfileByUser(User user);
}
