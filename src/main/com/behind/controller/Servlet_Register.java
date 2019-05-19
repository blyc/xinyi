package behind.controller;

import behind.bean.Adminuser;
import behind.service.AdminuserService;
import behind.service.impl.AdminuserServiceImpl;
import behind.util.BeanUtil;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet_Register",urlPatterns ="/Servlet_Register")
public class Servlet_Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        Adminuser adminuser = (Adminuser) BeanUtil.RequestToBeanUtil(request, new Adminuser());
        AdminuserService service = new AdminuserServiceImpl();
        PrintWriter writer = response.getWriter();

        JSONObject jsonObject = new JSONObject();
        if("select".equals(request.getParameter("type"))){
            boolean isRepetition = service.selectByName(adminuser);
            jsonObject.put("isRepetition",isRepetition);
        }else if("register".equals(request.getParameter("type"))){
            if (service.register(adminuser)){
                request.getRequestDispatcher("/Servlet_LoginIn").forward(request,response);
            }else{
                response.sendRedirect("Register.jsp");
            }
        }else if("insert".equals(request.getParameter("type"))){
            boolean isInsert = service.register(adminuser);
            jsonObject.put("isInsert",isInsert);
        }
        writer.println(jsonObject);
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
