package com.kh.myproject.repository;

import com.kh.myproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {


    @Query("SELECT u FROM User u WHERE u.user_id = :user_id")
    User findByUser_id(@Param("user_id") String user_id);


}
