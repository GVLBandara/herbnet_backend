package com.gotabaya.herbnet.repository;

import com.gotabaya.herbnet.model.UserProfile;
import org.springframework.data.repository.ListCrudRepository;

public interface UserProfileRepository extends ListCrudRepository<UserProfile, Long> {
}
