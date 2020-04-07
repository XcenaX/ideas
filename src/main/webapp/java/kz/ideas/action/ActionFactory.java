package kz.ideas.action;

import kz.ideas.action.Action;
import kz.ideas.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static kz.ideas.util.AppConstant.*;


public class ActionFactory {
    private static final Map<String, Action> PAGES = new HashMap<>();

    public ActionFactory() {
        init();
    }

    private void init(){
        PAGES.put("GET/", new AuthorizationAction());
        PAGES.put("GET/login", new AuthorizationAction());
        PAGES.put("GET/registration", new RegistrationAction());
        PAGES.put("GET/profile", new ProfileAction());
        PAGES.put("GET/profile/edit", new EditProfileAction());
        PAGES.put("GET/logout", new LogoutAction());
        PAGES.put("GET/ideas", new MainAction());
        PAGES.put("GET/create-idea", new CreateIdeaAction());

        PAGES.put("POST/profile/edit", new EditProfileAction());
        PAGES.put("POST/delete", new DeleteAction());
        PAGES.put("POST/registration", new RegistrationAction());
        PAGES.put("POST/authorization", new LoginAction());

    }

    public Action getAction(HttpServletRequest request, HttpServletResponse response){
        return PAGES.get(request.getMethod() + request.getPathInfo());
    }
}
