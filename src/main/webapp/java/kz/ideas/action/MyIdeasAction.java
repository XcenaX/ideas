package kz.ideas.action;

import kz.ideas.dao.IdeaDao;
import kz.ideas.entity.Idea;
import kz.ideas.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static kz.ideas.util.AppConstant.*;

public class MyIdeasAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");

        IdeaDao ideaDao = new IdeaDao();
        List<Idea> ideas = ideaDao.findByUser(user.getId());

        String title = null;

        try{
            title = request.getParameter("title");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        if(title != null){
            ideas = ideaDao.findByTitleAndUser(user.getId(), title);
            request.setAttribute(TITLE, title);
        }

        request.setAttribute(IDEAS, ideas);
        request.getRequestDispatcher(URL_MY_IDEAS_PAGE).forward(request, response);
    }
}
