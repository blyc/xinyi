package behind.controller;

import behind.bean.Adminuser;
import behind.bean.Page;
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

@WebServlet(name = "Servlet_AdminMg",urlPatterns = "/Servlet_AdminMg")
public class Servlet_AdminMg extends HttpServlet {
    private AdminuserService service = new AdminuserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        JSONObject jsonObject = new JSONObject();

        if (("select").equals(request.getParameter("type"))){
            Page page = (Page) BeanUtil.RequestToBeanUtil(request, new Page());
            jsonObject.put("totalPage",service.getPage().getTotalPage());
            jsonObject.put("adminuserList",service.select(page));
        }else if(("del").equals(request.getParameter("type"))){
            Adminuser adminuser = (Adminuser) BeanUtil.RequestToBeanUtil(request, new Adminuser());
            jsonObject.put("isDel",service.del(adminuser));
        }else if(("edit").equals(request.getParameter("type"))){
            Adminuser adminuser = (Adminuser) BeanUtil.RequestToBeanUtil(request, new Adminuser());
            jsonObject.put("isEdit",service.edit(adminuser));
        }
        writer.println(jsonObject);
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
