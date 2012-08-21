package ru.testhf.srv3.h37945.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testhf.srv3.h37945.dao.FieldDAO;
import ru.testhf.srv3.h37945.domain.Field;
import ru.testhf.srv3.h37945.service.dao.FieldService;

import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {

    @Autowired
    private FieldDAO fieldDAO;

    @Transactional
    public void addField(Field field) {
        fieldDAO.addField(field);
    }

    @Transactional
    public Field getFieldById(int id) {
        return fieldDAO.getFieldById(id);
    }

    @Transactional
    public List<Field> fieldList() {
        return fieldDAO.fieldList();
    }

    @Transactional
    public void setShips(int id, String ships) {
        fieldDAO.setShips(id, ships);
    }

    @Transactional
    public void addShot(int id, int cell) {
        fieldDAO.addShot(id, cell);
    }
}
