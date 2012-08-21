package ru.testhf.srv3.h37945.domain.lists;

import ru.testhf.srv3.h37945.domain.Request;

import java.util.Iterator;
import java.util.List;

public class RequestList {

    private List<Request> requests;

    public RequestList(List<Request> requests) {
        this.requests = requests;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public boolean isIdIncludeList(int id) {
        Iterator<Request> iterator = requests.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                return true;
            }
        }
        return false;
    }
}
