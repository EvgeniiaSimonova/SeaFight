package ru.testhf.srv3.h37945.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.testhf.srv3.h37945.dao.RequestDAO;
import ru.testhf.srv3.h37945.domain.Request;

import java.util.List;

@Repository
public class RequestDAOImpl implements RequestDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addRequest(Request request) {
        sessionFactory.getCurrentSession().save(request);
    }

    @Override
    public List<Request> requestList() {
        return sessionFactory.getCurrentSession().createQuery("from Request").list();
    }

    @Override
    public Request getRequestById(int id) {
        List<Request> requests = sessionFactory.getCurrentSession().
                createQuery("from Request where id = ?").setInteger(0, id).list();
        return requests.get(0);
    }

    @Override
    public void updateRequest(int id, int state, int idGame) {
        Request request = (Request) sessionFactory.getCurrentSession().load(Request.class, id);
        request.setState(state);
        request.setIdGame(idGame);
        if (null != request) {
            sessionFactory.getCurrentSession().update(request);
        }
    }

    @Override
    public List<Request> requestsForUser(String login) {
        return sessionFactory.getCurrentSession().createQuery("from Request where secondLogin = ? AND state = ?").
                setString(0, login).setInteger(1, 0).list();
    }
}
