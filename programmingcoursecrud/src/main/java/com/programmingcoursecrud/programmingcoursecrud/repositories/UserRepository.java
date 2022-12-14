package com.programmingcoursecrud.programmingcoursecrud.repositories;

import com.programmingcoursecrud.programmingcoursecrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
