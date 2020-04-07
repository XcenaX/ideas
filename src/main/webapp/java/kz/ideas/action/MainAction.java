package kz.ideas.action;

import kz.ideas.dao.IdeaDao;
import kz.ideas.entity.Idea;
import kz.ideas.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static kz.ideas.util.AppConstant.*;

public class MainAction implements Action{
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IdeaDao ideaDao = new IdeaDao();
        List<Idea> ideas = ideaDao.findAll();

        String title = null;

        try{
            title = request.getParameter("title");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        if(title != null){
            ideas = ideaDao.findByTitle(title);
            request.setAttribute(TITLE, title);
        }

        request.setAttribute(IDEAS, ideas);
        request.getRequestDispatcher(URL_MAIN_PAGE).forward(request, response);
    }
}
