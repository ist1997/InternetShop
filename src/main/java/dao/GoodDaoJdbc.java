package dao;

import db.DatabaseConnector;
import model.Good;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodDaoJdbc implements GoodDao {

    private static final String SAVE_METHOD_QUERY = "INSERT INTO internetshop.goods(name, description, price) VALUES (?, ?, ?)";
    private static final String UPDATE_METHOD_QUERY = "UPDATE internetshop.goods SET name=?,description=?,price=? WHERE id=?";
    private static final String DELETE_METHOD_QUERY = "DELETE FROM internetshop.goods WHERE id=?";
    private static final String GET_ALL_METHOD_QUERY = "SELECT * FROM internetshop.goods";
    private static final String DATABASE_NAME = "internetshop";
    private static final Logger LOGGER = Logger.getLogger(GoodDaoJdbc.class);

    @Override
    public void add(Good good) {
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)) {
            PreparedStatement ps = connection.prepareStatement(SAVE_METHOD_QUERY);
            ps.setString(1, good.getName());
            ps.setString(2, good.getDescription());
            ps.setDouble(3, good.getPrice());
            LOGGER.debug("SQL query for save method: " + SAVE_METHOD_QUERY);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can`t connect to database", e);
        }
    }

    @Override
    public void update(Good Good) {
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)) {
            PreparedStatement ps = connection.prepareStatement(UPDATE_METHOD_QUERY);
            ps.setString(1, Good.getName());
            ps.setString(2, Good.getDescription());
            ps.setDouble(3, Good.getPrice());
            ps.setLong(4, Good.getId());
            LOGGER.debug("SQL query for update method: " + UPDATE_METHOD_QUERY);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can`t connect to database", e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)) {
            PreparedStatement ps = connection.prepareStatement(DELETE_METHOD_QUERY);
            ps.setLong(1, id);
            LOGGER.debug("SQL query for delete method: " + DELETE_METHOD_QUERY);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can`t connect to database", e);
        }
    }

    @Override
    public Good getGoodById(long id) {
        Good returnGood = new Good();
        for (Good good : getAllGoods()) {
            if (good.getId() == id) {
                returnGood = good;
            }
        }
        return returnGood;
    }

    @Override
    public List<Good> getAllGoods() {
        List<Good> goods = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection(DATABASE_NAME)) {
            PreparedStatement ps = connection.prepareStatement(GET_ALL_METHOD_QUERY);
            LOGGER.debug("SQL query for getAllGoods method: " + GET_ALL_METHOD_QUERY);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Good good = new Good(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4)
                );
                goods.add(good);
            }
        } catch (SQLException e) {
            LOGGER.error("Can`t connect to database", e);
        }
        return goods;
    }
}
