package io.wquach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;

import io.wquach.dao.Dao;
import io.wquach.dao.jdbc.JdbcInvitationDao;
import io.wquach.domain.Event;
import io.wquach.domain.Invitation;
import io.wquach.domain.InvitationBuilder;
import io.wquach.domain.User;

/**
 * Created by wquach on 6/4/17.
 */
@Component
public class InvitationService extends AbstractService<Invitation>{
    @Autowired
    JdbcInvitationDao invitationDao;

    @Override
    protected Dao<Invitation> getDao() {
        return invitationDao;
    }

    public Invitation getAllInvitations(int eventId) {
        return invitationDao.getInvitationsByEvent(eventId);
    }
}
