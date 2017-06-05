package io.wquach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

import io.wquach.dao.Dao;
import io.wquach.domain.User;

/**
 * Created by wquach on 6/4/17.
 */
@Component
@Qualifier("user")
public class UserService extends AbstractService<User>{
    @Autowired
    @Qualifier("user")
    Dao<User> userDao;

    @Override
    protected Dao<User> getDao() {
        return userDao;
    }
}
