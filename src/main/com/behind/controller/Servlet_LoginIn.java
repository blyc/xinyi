package behind.controller;

import behind.bean.Adminuser;
import behind.service.AdminuserService;
import behind.service.impl.AdminuserServiceImpl;
import behind.util.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet_LoginIn",urlPatterns = "/Servlet_LoginIn")
public class Servlet_LoginIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        Adminuser adminuser = (Adminuser) BeanUtil.RequestToBeanUtil(request, new Adminuser());
        AdminuserService service = new AdminuserServiceImpl();
        boolean login = service.login(adminuser);

        if(login){
            request.getSession().setAttribute("adminuser", adminuser);
            request.getSession().setMaxInactiveInterval(60*60);

            Cookie username_c = new Cookie("adminUsername",adminuser.getUsername());
            username_c.setMaxAge(7*24*60*60);
            Cookie password_c = new Cookie("adminPassword",adminuser.getPassword());
            password_c.setMaxAge(7*24*60*60);
            username_c.setPath("/");
            password_c.setPath("/");
            response.addCookie(username_c);
            response.addCookie(password_c);

            response.sendRedirect("/behind/view/Main.jsp");
        } else{
            response.sendRedirect("/behind/Login.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String username = (String)request.getAttribute("username");
        String password = (String)request.getAttribute("password");
        AdminuserService service = new AdminuserServiceImpl();
        Adminuser adminuser = new Adminuser(username,password);
        boolean login = service.login(adminuser);
        if(login){
            request.getSession().setAttribute("adminuser", adminuser);
            request.getSession().setMaxInactiveInterval(60*60);

            Cookie username_c = new Cookie("adminUsername",adminuser.getUsername());
            username_c.setMaxAge(7*24*60*60);
            Cookie password_c = new Cookie("adminPassword",adminuser.getPassword());
            password_c.setMaxAge(7*24*60*60);
            username_c.setPath("/");
            password_c.setPath("/");
            response.addCookie(username_c);
            response.addCookie(password_c);

            response.sendRedirect("/behind/view/Main.jsp");
        } else{
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
            response.sendRedirect("/behind/Login.jsp");
        }
    }
}
