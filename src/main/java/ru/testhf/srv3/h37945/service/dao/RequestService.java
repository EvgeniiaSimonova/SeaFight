package ru.testhf.srv3.h37945.service.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import ru.testhf.srv3.h37945.domain.Request;

import java.util.List;

public interface RequestService {
    public void addRequest(Request request) throws MySQLIntegrityConstraintViolationException;

    public List<Request> requestList();

    public Request getRequestById(int id) throws MySQLIntegrityConstraintViolationException;

    public void updateRequest(int id, int state, int idGame);

    public List<Request> requestsForUser(String login);
}
