package kz.ideas.action;

import kz.ideas.action.Action;
import kz.ideas.dao.UserDao;
import kz.ideas.entity.User;
import kz.ideas.exception.CheckException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static kz.ideas.util.AppConstant.*;

public class RegistrationAction implements Action{
    private String login;
    private String password;
    private String retypePassword;

    private String name;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getMethod().equals("GET")){
            request.getRequestDispatcher(URL_REGISTRATION_PAGE).forward(request, response);
        } else if(request.getMethod().equals("POST")){
            try{
                UserDao userDao = new UserDao();
                fillData(request);
                checkField();

                User user = new User();
                user = setUser();
                User testUser = userDao.findByLogin(user.getLogin());
                if(testUser != null)throw new CheckException("Такой пользователь уже существует!","login");
                if(userDao.insert(user)){
                    request.getRequestDispatcher(URL_AUTHORIZATION_PAGE).forward(request, response);
                } else{
                    request.setAttribute(ERROR_LOGIN, "Что-то пошло не так! Попробуйте еще раз!");
                    request.getRequestDispatcher(URL_REGISTRATION_PAGE).forward(request, response);
                }
            } catch (CheckException e){
                request.setAttribute(ERROR_LOGIN, e.getMessage());
                request.getRequestDispatcher(URL_REGISTRATION_PAGE).forward(request, response);
            }


        }

    }

    private void checkField() throws CheckException{
        if(login.length() < 3)throw new CheckException("Логин должен иметь не менее 3 символов", "login");
        if(password.length() < 5)throw new CheckException("Пароль должен иметь не менее 5 символов", "password");
        if(!password.equals(retypePassword))throw new CheckException("Пароли не совпадают!","password");
    }

    private User setUser(){
        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        return user;
    }

    private void fillData(HttpServletRequest request){
        login = request.getParameter("login");
        password = request.getParameter("password");
        name = request.getParameter("name");
        retypePassword = request.getParameter("retypePassword");
    }
}
