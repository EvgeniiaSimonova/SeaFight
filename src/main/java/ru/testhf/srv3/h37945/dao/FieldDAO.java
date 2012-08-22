package ru.testhf.srv3.h37945.dao;

import ru.testhf.srv3.h37945.domain.Field;

import java.util.List;

public interface FieldDAO {
    public void addField(Field field);

    public Field getFieldById(int id);

    public List<Field> fieldList();

    public void setShips(int id, String ships);

    public void addShot(int id, int cell);
}
