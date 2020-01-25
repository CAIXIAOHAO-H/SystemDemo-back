package com.back.service;

import com.back.entity.Resourse;
import com.back.entity.Role;

import java.util.List;

public interface SystemService {

    List<Resourse> getAllResourse();

    List<Role> getAllRoles();

}
