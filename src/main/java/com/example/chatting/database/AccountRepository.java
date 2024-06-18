package com.example.chatting.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<User, Integer> {
    @Query(nativeQuery = true, value = "select *from account where id= :id")
    User findId(int id);
    @Query(nativeQuery = true, value = "select count(*) > 0 from account where email= :email")
    boolean existsByEmail(@Param("email") String email);

    @Query(nativeQuery = true, value = "select count(*)>0 from account where user_name= :user_name")
    Boolean existsByUserName(@Param("user_name") String user_name);

    @Query(nativeQuery = true,value = "select *from account where email= :email")
    User finAccount(@Param("email") String email);

    @Query(nativeQuery = true, value = "select *from account where emmail= :email")
    User findAccountByEmail(@Param("email") String email);
}