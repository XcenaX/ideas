package kz.ideas.dao;
import kz.ideas.entity.Idea;
import kz.ideas.pool.ConnectionPool;
import kz.ideas.entity.Idea;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IdeaDao extends AbstractDao<Idea> {
    private Logger logger = Logger.getLogger(IdeaDao.class);


    private static final String SQL_SELECT_ALL = "select * from public.ideas";
    private static final String SQL_SELECT_BY_ID = "select * from public.ideas where id=?";
    private static final String SQL_SELECT_BY_USER_ID = "select * from public.ideas where user_id=?";
    private static final String SQL_SELECT_BY_TITLE = "select * from public.ideas where title like '%?%'";
    private static final String SQL_SELECT_BY_TITLE_AND_USER = "select * from public.ideas where title like '%?%' and user_id=?";
    private static final String SQL_SELECT_NOT_DELETED = "select * from public.ideas where is_deleted=false";
    private static final String INSERT = "insert into public.ideas (title, description, user_id, pub_date, is_deleted) values(?, ?, ?, ?, false)";
    private static final String DELETE_ID = "UPDATE public.ideas set is_deleted=true where id=?";
    @Override
    public boolean delete(int id) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ID)) {
            preparedStatement.setInt(1, id);
            Idea idea = new Idea();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
            }
            return true;
        } catch (SQLException e){
            logger.error(e.getMessage());
            return false;
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }

    }

    @Override
    public boolean delete(Idea idea) {
        return false;
    }

    @Override
    public boolean insert(Idea idea) {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Connection connection = kz.ideas.pool.ConnectionPool.getConnectionPool().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
            preparedStatement.setString(1,idea.getTitle());
            preparedStatement.setString(2,idea.getDescription());
            preparedStatement.setInt(3,idea.getUserId());
            preparedStatement.setDate(4, idea.getPubDate());
            preparedStatement.executeQuery();
        } catch (SQLException e){
            logger.error(e.getMessage());
        } finally {
            kz.ideas.pool.ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return true;
    }

    @Override
    public boolean update(Idea idea) {
        return false;
    }

    @Override
    public List<Idea> findAll() {
        List<Idea> ideas = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try(Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)){
            while (resultSet.next()){
                Idea idea = new Idea();
                idea.setId(resultSet.getInt("id"));
                idea.setDescription(resultSet.getString("description"));
                idea.setTitle(resultSet.getString("title"));
                idea.setDeleted(resultSet.getBoolean("deleted"));
                idea.setUserId(resultSet.getInt("user_id"));
                idea.setPubDate(resultSet.getDate("pub_date"));
                ideas.add(idea);
            }
        } catch (SQLException e){
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return ideas;
    }

    @Override
    public Idea findById(int id) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            Idea idea = new Idea();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    idea = setIdeaParameters(resultSet);
                }
            }
            return idea;
        } catch (SQLException e){
            logger.error(e.getMessage());
            return null;
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
    }

    public List<Idea> findByUser(int userId) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        List<Idea> ideas = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_USER_ID)) {
            preparedStatement.setInt(1, userId);
            Idea idea = new Idea();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    idea = setIdeaParameters(resultSet);
                    ideas.add(idea);
                }
            }
        } catch (SQLException e){
            logger.error(e.getMessage());
            return null;
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
            return ideas;
        }
    }

    public List<Idea> findByTitleAndUser(int userId, String title) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        List<Idea> ideas = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_TITLE_AND_USER)) {
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, userId);
            Idea idea = new Idea();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    idea = setIdeaParameters(resultSet);
                    ideas.add(idea);
                }
            }
        } catch (SQLException e){
            logger.error(e.getMessage());
            return null;
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
            return ideas;
        }
    }


    private Idea setIdeaParameters(ResultSet resultSet){
        Idea idea = new Idea();
        try {
            idea.setId(resultSet.getInt("id"));
            idea.setDescription(resultSet.getString("description"));
            idea.setTitle(resultSet.getString("title"));
            idea.setDeleted(resultSet.getBoolean("deleted"));
            idea.setPubDate(resultSet.getDate("pub_date"));
            idea.setUserId(resultSet.getInt("user_id"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return idea;
    }

    public List<Idea> findByTitle(String title) {
        Connection connection = kz.ideas.pool.ConnectionPool.getConnectionPool().getConnection();
        List<Idea> ideas = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_TITLE)) {
            preparedStatement.setString(1, title);
            Idea idea = new Idea();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    idea = setIdeaParameters(resultSet);
                    ideas.add(idea);
                }
            }
        } catch (SQLException e){
            logger.error(e.getMessage());
        } finally {
            kz.ideas.pool.ConnectionPool.getConnectionPool().releaseConnection(connection);
            return ideas;
        }
    }
}
