package com.kh.myproject.repository;

import com.kh.myproject.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {


    //    @Query("SELECT u FROM User u WHERE u.userId = :user_id")
    // camel case -> snake case auto change
    User findByUserId(@Param("user_id") String user_id);

    User findByUserIdAndUserPassword(@Param("user_id") String user_id,
                                     @Param("user_password") String user_password);


}