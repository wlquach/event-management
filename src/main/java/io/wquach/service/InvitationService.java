package io.wquach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

import io.wquach.dao.Dao;
import io.wquach.domain.Event;
import io.wquach.domain.Invitation;
import io.wquach.domain.User;

/**
 * Created by wquach on 6/4/17.
 */
@Component
public class InvitationService extends AbstractService<Invitation>{
    @Autowired
    Dao<Invitation> invitationDao;

    @Autowired
    @Qualifier("invitedUser")
    Dao<User> invitedUserDao;

    @Override
    protected Dao<Invitation> getDao() {
        return invitationDao;
    }

    @Override
    public List<Invitation> getSubset(int filter) {
        throw new UnsupportedOperationException();
    }
}
