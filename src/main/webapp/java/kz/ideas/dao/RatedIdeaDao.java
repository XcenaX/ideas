package kz.ideas.dao;
import kz.ideas.entity.Idea;
import kz.ideas.entity.RatedIdea;
import kz.ideas.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatedIdeaDao extends AbstractDao<RatedIdea> {
    private Logger logger = Logger.getLogger(RatedIdeaDao.class);

    private static final String SQL_SELECT_ALL = "select * from public.rated_ideas";
    private static final String SQL_SELECT_BY_ID = "select * from public.rated_ideas where id=?";
    private static final String SQL_SELECT_BY_USER_ID = "select * from public.rated_ideas where user_id=?";
    private static final String INSERT = "insert into public.rated_ideas (user_id, idea_id) values(?, ?)";
    private static final String DELETE_ID = "delete from public.rated_ideas where id=?";
    private static final String DELETE_COURCE_ID = "delete from public.rated_ideas where idea_id=?";
    private static final String DELETE_ROLE = "delete from public.rated_ideas where";
    @Override
    public boolean delete(int ideaId) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURCE_ID)) {
            preparedStatement.setInt(1, ideaId);
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
    public boolean delete(RatedIdea idea) {
        return false;
    }

    @Override
    public boolean insert(RatedIdea idea) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT)){
            preparedStatement.setInt(1,idea.getUserId());
            preparedStatement.setInt(2,idea.getIdeaId());
            preparedStatement.executeQuery();
        } catch (SQLException e){
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return false;
    }

    @Override
    public boolean update(RatedIdea idea) {
        return false;
    }

    @Override
    public List<RatedIdea> findAll() {
        List<RatedIdea> ratedIdeas = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try(Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)){
            while (resultSet.next()){
                RatedIdea idea = new RatedIdea();
                idea.setUserId(resultSet.getInt("user_id"));
                idea.setIdeaId(resultSet.getInt("idea_id"));
                ratedIdeas.add(idea);
            }
        } catch (SQLException e){
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return ratedIdeas;
    }

    @Override
    public RatedIdea findById(int id) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            RatedIdea idea = new RatedIdea();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    idea = setRatedIdeaParameters(resultSet);
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

    public List<RatedIdea> findByUserId(int id) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        List<RatedIdea> ratedIdeas = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_USER_ID)) {
            preparedStatement.setInt(1, id);
            RatedIdea idea = new RatedIdea();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    idea = setRatedIdeaParameters(resultSet);
                    ratedIdeas.add(idea);
                }
            }
        } catch (SQLException e){
            logger.error(e.getMessage());
            return null;
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return ratedIdeas;
    }

    private RatedIdea setRatedIdeaParameters(ResultSet resultSet){
        RatedIdea idea = new RatedIdea();
        try {
            idea.setUserId(resultSet.getInt("user_id"));
            idea.setIdeaId(resultSet.getInt("idea_id"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return idea;
    }
}
