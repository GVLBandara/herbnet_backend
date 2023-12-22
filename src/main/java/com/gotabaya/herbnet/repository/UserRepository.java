package com.gotabaya.herbnet.repository;

import com.gotabaya.herbnet.model.User;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Long> {
}