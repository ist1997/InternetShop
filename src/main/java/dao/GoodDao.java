package dao;

import model.Good;

import java.util.List;

public interface GoodDao {

    void add(Good good);

    void delete(int id);

    void update(Good good);

    Good getGoodById(long id);

    List<Good> getAllGoods();

}
