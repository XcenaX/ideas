package kz.ideas.action;

import kz.ideas.action.Action;
import kz.ideas.dao.IdeaDao;
import kz.ideas.dao.RatedIdeaDao;
import kz.ideas.entity.Idea;
import kz.ideas.entity.RatedIdea;
import kz.ideas.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static kz.ideas.util.AppConstant.*;

public class ProfileAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        RatedIdeaDao ratedIdeaDao = new RatedIdeaDao();
        IdeaDao ideaDao = new IdeaDao();

        List<RatedIdea> ratedIdeas = ratedIdeaDao.findByUserId(user.getId());
        List<Idea> ideas = new ArrayList<>();

        for (int i = 0; i < ratedIdeas.size(); i++) {
            ideas.add(ideaDao.findById(ratedIdeas.get(i).getIdeaId()));
        }
        if(user.getImage() != null)request.setAttribute("hasImage", true);
        else request.setAttribute("hasImage", false);
        request.setAttribute(USER_IDEAS, ideas);

        request.getRequestDispatcher(URL_PROFILE_PAGE).forward(request, response);
    }
}
