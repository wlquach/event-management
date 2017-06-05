package io.wquach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

import io.wquach.dao.Dao;
import io.wquach.domain.Invitation;
import io.wquach.domain.User;

/**
 * Created by wquach on 6/4/17.
 */
@Component
@Qualifier("invitedUser")
public class InvitedUsersService extends AbstractService<User> {

    @Autowired
    @Qualifier("invitedUser")
    Dao<User> invitedUserDao;

    @Override
    protected Dao<User> getDao() {
        return invitedUserDao;
    }

    @Override
    public List<User> getSubset(int filter) {
        return invitedUserDao.getSubset(filter);
    }
}
