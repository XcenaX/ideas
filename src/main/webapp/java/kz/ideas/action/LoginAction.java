package kz.ideas.action;


import kz.ideas.action.Action;
import kz.ideas.dao.IdeaDao;
import kz.ideas.dao.UserDao;
import kz.ideas.entity.Idea;
import kz.ideas.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static kz.ideas.util.AppConstant.*;

public class LoginAction implements Action {
    private Logger logger = Logger.getLogger(LoginAction.class);
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = userDao.findByLoginAndPassword(login, password);

        if(user != null){
            IdeaDao ideaDao = new IdeaDao();
            List<Idea> ideas = ideaDao.findAll();

            request.setAttribute(IDEAS, ideas);

            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);


            response.sendRedirect("/fs/ideas");
            //request.getRequestDispatcher(URL_HI_PAGE).forward(request, response);
        }
        else{
            logger.info("Неверный логин или пароль! (" + login + " | " + password + ")");
            request.setAttribute(ERROR_LOGIN, "Неверный логин или пароль!");
            request.getRequestDispatcher(URL_AUTHORIZATION_PAGE).forward(request, response);

        }

    }
}
