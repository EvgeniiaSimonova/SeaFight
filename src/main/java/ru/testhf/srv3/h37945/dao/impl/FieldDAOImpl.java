package ru.testhf.srv3.h37945.dao.impl;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.testhf.srv3.h37945.dao.FieldDAO;
import ru.testhf.srv3.h37945.domain.Field;
import ru.testhf.srv3.h37945.domain.Game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class FieldDAOImpl implements FieldDAO {

    private static final Logger logger = LoggerFactory.getLogger(FieldDAOImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addField(Field field) {
        sessionFactory.getCurrentSession().save(field);
    }

    @Override
    public Field getFieldById(int id) {
        List<Field> fields = sessionFactory.getCurrentSession().
                createQuery("from Field where id = ?").setInteger(0, id).list();
        return fields.get(0);
    }

    @Override
    public List<Field> fieldList() {
        return sessionFactory.getCurrentSession().createQuery("from Field").list();
    }

    @Override
    public void setShips(int id, String ships) {
        Field field = getFieldById(id);
        field.setShips(ships);
        sessionFactory.getCurrentSession().update(field);
    }

    @Override
    public void addShot(int id, int cell) {
        Field field = getFieldById(id);
        field.setShots(field.getShots() + cell + ",");
        if (isKilled(id)) {
            field.setKilled(true);
        }
        sessionFactory.getCurrentSession().update(field);
    }

    public boolean isKilled(int id) {
        Field field = getFieldById(id);
        String[] ships = field.getShips().split(",");
        String[] shots = field.getShots().split(",");

        List<String> list = new ArrayList<String>();

        for (int i = 0; i < ships.length; i++) {
            if (!ships[i].equals("")) {
                list.add(ships[i]);
            }
        }

        for (int i = 0; i < shots.length; i++) {
            list.remove(shots[i]);
            logger.info("list "+ list.toString());
        }
        if (list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
