package com.back.service;

import com.back.entity.Resourse;
import com.back.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> getAllUser();

    User getUserByName(String account);

    int registUser(User user);

    List<Resourse> getResourseByUserName(String userName);

}
