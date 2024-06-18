package com.example.chatting.database;

import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue()
    @Column(name = "id")
    int id;
    @Column(name = "user_name")
    String name;
    @Column(name = "email")
    String email;
    @Column(name = "pass_word")
    String pass_word;

    @Column(name = "created_at")
    Timestamp created_at;

    @Column(name="otpValue")
    String otp;

}