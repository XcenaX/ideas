package kz.ideas.controller;

import kz.ideas.dao.UserDao;
import kz.ideas.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        if(user == null)return;

        String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        Part filePart = request.getPart("avatar"); // Retrieves <input type="file" name="file">
        InputStream inputStream = filePart.getInputStream();

        UserDao userDao = new UserDao();
        userDao.updatePhoto(inputStream, user.getId());

        session.setAttribute("currentUser", userDao.findById(user.getId()));

        request.setAttribute("hasImage", true);
        response.sendRedirect("/fs/profile/edit");
    }


    public static byte [] ImageToByte(File file) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
        }
        byte[] bytes = bos.toByteArray();
        return bytes;
    }
}
