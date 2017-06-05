package io.wquach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import io.wquach.dao.Dao;
import io.wquach.domain.User;

/**
 * Created by wquach on 6/4/17.
 */
@Component
public class UserService implements CrudService<User>{
    @Autowired
    Dao<User> userDao;

    @Override
    public int add(User user) {
        return userDao.insert(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void getAll(Consumer processor) {
        userDao.writeAll(processor);
    }

    @Override
    public User getSingle(int id) {
        return userDao.getSingle(id);
    }

    @Override
    public void deleteSingle(int id) {
        userDao.delete(id);
    }
}
