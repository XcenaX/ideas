package kz.ideas.action;

import kz.ideas.action.Action;
import kz.ideas.dao.IdeaDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ideaIdStr = request.getParameter("ideaId");
        int ideaId = Integer.valueOf(ideaIdStr);

        IdeaDao ideaDao = new IdeaDao();
        ideaDao.delete(ideaId);

        response.sendRedirect("/fs/ideas");
    }
}
