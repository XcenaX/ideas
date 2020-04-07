package kz.ideas.action;

import kz.ideas.action.Action;
import kz.ideas.dao.UserDao;
import kz.ideas.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

import static kz.ideas.util.AppConstant.PROFILE_EDITED;
import static kz.ideas.util.AppConstant.URL_PROFILE_EDIT_PAGE;

@MultipartConfig
public class EditProfileAction implements Action {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        if(request.getMethod().equals("GET")){
            if(user.getImage() != null)request.setAttribute("hasImage", true);
            else request.setAttribute("hasImage", false);

            request.getRequestDispatcher(URL_PROFILE_EDIT_PAGE).forward(request, response);
        } else if(request.getMethod().equals("POST")){


            String name = request.getParameter("name");

            String dateOfBirth = request.getParameter("date_of_birth");
            Date date = Date.valueOf(dateOfBirth);



            user.setName(name);
            user.setDateOfBirth(date);

            UserDao userDao = new UserDao();
            userDao.updateProfile(user);

            request.setAttribute(PROFILE_EDITED, true);
            request.getRequestDispatcher(URL_PROFILE_EDIT_PAGE).forward(request, response);
        }
    }
}
