package behind.controller;

import behind.bean.Page;
import behind.bean.User;
import behind.service.UserService;
import behind.service.impl.UserServiceImpl;
import behind.util.BeanUtil;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet_UserMg",urlPatterns = "/Servlet_UserMg")
public class Servlet_UserMg extends HttpServlet {
    private UserService service = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        JSONObject jsonObject = new JSONObject();

        if (("select").equals(request.getParameter("type"))){
            Page page = (Page) BeanUtil.RequestToBeanUtil(request, new Page());
            jsonObject.put("totalPage",service.getPage().getTotalPage());
            jsonObject.put("userList",service.select(page));
        }else if(("del").equals(request.getParameter("type"))){
            User user = (User) BeanUtil.RequestToBeanUtil(request, new User());
            jsonObject.put("isDel",service.del(user));
        }else if(("edit").equals(request.getParameter("type"))){
            User user = (User) BeanUtil.RequestToBeanUtil(request, new User());
            jsonObject.put("isEdit",service.edit(user));
        }else if(("insert").equals(request.getParameter("type"))){
            User user = (User) BeanUtil.RequestToBeanUtil(request, new User());
            jsonObject.put("isInsert",service.insert(user));
        }
        writer.println(jsonObject);
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
