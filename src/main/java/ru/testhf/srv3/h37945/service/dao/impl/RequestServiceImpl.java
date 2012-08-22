package ru.testhf.srv3.h37945.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testhf.srv3.h37945.dao.RequestDAO;
import ru.testhf.srv3.h37945.domain.Request;
import ru.testhf.srv3.h37945.service.dao.RequestService;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestDAO requestDAO;

    @Transactional
    public void addRequest(Request request) {
        requestDAO.addRequest(request);
    }

    @Transactional
    public List<Request> requestList() {
        return requestDAO.requestList();
    }

    @Transactional
    public Request getRequestById(int id) {
        return requestDAO.getRequestById(id);
    }

    @Transactional
    public void updateRequest(int id, int state, int idGame) {
        requestDAO.updateRequest(id, state, idGame);
    }

    @Transactional
    public List<Request> requestsForUser(String login) {
        return requestDAO.requestsForUser(login);
    }
}
