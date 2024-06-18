package com.example.chatting.service;

import com.example.chatting.database.AccountRepository;
import com.example.chatting.database.User;
import com.example.chatting.model.AccountRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private JavaMailSender javaMailSender;

    public List<User> getAllAccount() {
        return accountRepository.findAll();
    }

    public User getAccount(int id) {
        return accountRepository.findId(id);
    }

    public String saveAccount(AccountRequest acc) {
        User user = new User();
        if (accountRepository.existsByEmail(acc.getEmail()) || accountRepository.existsByUserName(acc.getUser_name())) {
            return "Email này đã được đăng ký, hãy đăng ký bằng email khác";
        } else {
            user.setName(acc.getUser_name());
            user.setEmail(acc.getEmail());
            user.setPass_word(acc.getPass_word());
            Timestamp currentTime = (Timestamp.valueOf(LocalDateTime.now()));
            user.setCreated_at(currentTime);
            accountRepository.save(user);
        }
        return "Đã lưu tài khoản thành công";
    }

    public String updateAccount(String email) {
        {
            String value = randomPass(8);
            User user = accountRepository.finAccount(email);
            user.setPass_word(value);
            accountRepository.save(user);
            return "New pass_word send your email";
        }
    }

    public void sendResetPasswordEmail(String email){
        String otp = generateOTP(6);
        User user = accountRepository.findAccountByEmail(email);
        user.setOtp(otp);
        accountRepository.save(user);
        sendEmail(email,"Reset Password", "Your OTP: " + otp);
    }

    private void sendEmail(String to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    public String generateOTP(int len){
        String OTP ="1234567890";
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        int OTPIndex;
        char OTPChar;
        for(int j=0; j<len;j++){
           OTPIndex= random.nextInt(OTP.length());
           OTPChar=OTP.charAt(OTPIndex);
           builder.append(OTPChar);
        }
        return builder.toString();
    }
    public String randomPass(int len) {
        String newPassWord = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        int randomValueGen;
        char randomChar;
        for (int i = 0; i < len; i++) {
            randomValueGen = random.nextInt(newPassWord.length());
            randomChar = newPassWord.charAt(randomValueGen);
            builder.append(randomChar);
        }
        return builder.toString();
    }

    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }
}
