package com.yangze.boot.service;

import com.yangze.boot.bean.UserBean;

public interface UserService {

    UserBean loginIn(String name, String password);
}
