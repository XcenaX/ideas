package kz.ideas.controller;

import kz.ideas.action.Action;
import kz.ideas.action.ActionFactory;
import kz.ideas.pool.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static kz.ideas.util.AppConstant.URL_AUTHORIZATION_PAGE;

public class ControllerServlet extends HttpServlet {
    ActionFactory actionFactory;
    @Override
    public void init() throws ServletException {
        super.init();
        actionFactory = new ActionFactory();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Action action = actionFactory.getAction(req, resp);
        if(action != null){
            action.service(req,resp);
        } else {
            req.getRequestDispatcher(URL_AUTHORIZATION_PAGE).forward(req, resp);
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.dispose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.destroy();
    }

}
