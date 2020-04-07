package kz.ideas.dao;

import kz.ideas.entity.*;
import kz.ideas.entity.Role;
import kz.ideas.pool.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends AbstractDao<Role> {
    private Logger logger = Logger.getLogger(RoleDao.class);

    private static final String SQL_SELECT_ALL = "select * from public.roles";
    private static final String SQL_SELECT_BY_ID = "select * from public.roles where id=?";
    private static final String INSERT = "insert into public.roles (name) values(?)";
    private static final String DELETE_ID = "delete from public.roles where id=";
    private static final String DELETE_ROLE = "delete from public.roles where";
    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Role role) {
        return false;
    }

    @Override
    public boolean insert(Role role) {
        return false;
    }

    @Override
    public boolean update(Role role) {
        return false;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = new ArrayList<>();
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try(Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL)){
            while (resultSet.next()){
                Role role = new Role(resultSet.getString("name"));
                role.setId(resultSet.getInt("id"));
                roles.add(role);
            }
        } catch (SQLException e){
            logger.error(e.getMessage());
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
        return roles;
    }

    @Override
    public Role findById(int id) {
        Connection connection = ConnectionPool.getConnectionPool().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            Role role = new Role();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    role = setRoleParameters(resultSet);
                }
            }
            return role;
        } catch (SQLException e){
            logger.error(e.getMessage());
            return null;
        } finally {
            ConnectionPool.getConnectionPool().releaseConnection(connection);
        }
    }

    private Role setRoleParameters(ResultSet resultSet){
        Role role = new Role();
        try {
            role.setId(resultSet.getInt("id"));
            role.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        return role;
    }
}
