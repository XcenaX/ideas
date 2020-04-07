package kz.ideas.dao;

import kz.ideas.entity.User;
import kz.ideas.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class UserDao extends AbstractDao<User> {
    private Logger logger = Logger.getLogger(UserDao.class);

    private static final String SQL_SELECT_USERS_ALL = "SELECT * FROM public.users";
    private static final String SQL_INSERT_USER = "insert into public.users (login, password, name) values(?, ?, ?)";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM public.users where id=?";
    private static final String SQL_UPDATE_USER = "UPDATE public.users set login=?, password=?, name=?, date_of_birth=? where id=?";
    private static final String SQL_UPDATE_USER_PROFILE = "UPDATE public.users set name=?, date_of_birth=?where id=?";
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM public.users where id=?";
    private static final String SQL_SELECT_USER_BY_LOGIN_PASSWORD = "SELECT * FROM public.users where login=? and password=?";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM public.users where login=?";
    private static final String SQL_UPDATE_AVATAR = "update public.users set avatar=? where id=?";

    public User findByLoginAndPassword(String login, String password){
        User user = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_PASSWORD)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = setUserParameters(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return user;
    }

    public User findByLogin(String login){
        User user = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = setUserParameters(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return user;
    }

    @Override
    public boolean delete(User entity) {
        boolean deleted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.execute();
            deleted = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return deleted;
    }

    @Override
    public User findById(int id) {
        User user = null;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user = setUserParameters(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return user;
    }

    @Override
    public boolean update(User entity) {
        boolean updated = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getName());
            preparedStatement.setDate(4, entity.getDateOfBirth());
            preparedStatement.setInt(5, entity.getId());
            preparedStatement.executeUpdate();
            updated = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return updated;
    }

    public boolean updateProfile(User user) throws FileNotFoundException {
        boolean updated = false;

        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_PROFILE)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setDate(2, user.getDateOfBirth());
            preparedStatement.setInt(3, user.getId());
            preparedStatement.executeUpdate();
            updated = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return updated;
    }

    @Override
    public boolean delete(int id) {
        boolean deleted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            deleted = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return deleted;
    }

    @Override
    public boolean insert(User entity) {
        boolean inserted = false;
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER)) {
            preparedStatement.setString(1, entity.getLogin());
            preparedStatement.setString(2, entity.getPassword());
            preparedStatement.setString(3, entity.getName());
            preparedStatement.executeUpdate();
            inserted = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return inserted;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_SELECT_USERS_ALL)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setDateOfBirth(resultSet.getDate("date_of_birth"));
                user.setRole(resultSet.getInt("role"));

                byte[] avatar = resultSet.getBytes("avatar");
                user.setImage(avatar);
                user.setBase64image(getBase64Image(avatar));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return users;
    }

    public String getBase64Image(byte[] avatar){
        String base64Image = "";
        try{
            base64Image = Base64.getEncoder().encodeToString(avatar);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return base64Image;
    }

    private User setUserParameters(ResultSet resultSet){
        User user = new User();
        try {
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));

            user.setDateOfBirth(resultSet.getDate("date_of_birth"));
            user.setRole(resultSet.getInt("role"));
            byte[] avatar = resultSet.getBytes("avatar");
            user.setImage(avatar);
            user.setBase64image(getBase64Image(avatar));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return user;
    }

    public boolean updatePhoto(InputStream inputStream, int id) throws FileNotFoundException {
        boolean updated = false;

        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_AVATAR)) {
            int length = inputStream.available();
            preparedStatement.setBinaryStream(1, inputStream, length);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            updated = true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (IOException e){
            logger.error(e.getMessage());
        }
        finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return updated;
    }
}
