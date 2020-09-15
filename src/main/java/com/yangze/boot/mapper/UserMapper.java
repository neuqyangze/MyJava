package com.yangze.boot.mapper;

import com.yangze.boot.bean.UserBean;

public interface UserMapper {

    UserBean getInfo(String name, String password);
}
