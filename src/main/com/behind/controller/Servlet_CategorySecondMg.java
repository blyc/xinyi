package behind.controller;

import behind.bean.Category;
import behind.bean.CategorySecond;
import behind.bean.Page;
import behind.dao.CategoryDao;
import behind.dao.Impl.CategoryDaoImpl;
import behind.service.CategorySecondService;
import behind.service.impl.CategorySecondServiceImpl;
import behind.util.BeanUtil;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet_CategorySecondMg",urlPatterns = "/Servlet_CategorySecondMg")
public class Servlet_CategorySecondMg extends HttpServlet {
    CategorySecondService service = new CategorySecondServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        JSONObject jsonObject = new JSONObject();

        Page page = (Page) BeanUtil.RequestToBeanUtil(request, new Page());
        CategorySecond categorySecond = (CategorySecond) BeanUtil.RequestToBeanUtil(request, new CategorySecond());

        if (("select").equals(request.getParameter("type"))){
            jsonObject.put("totalPage",service.getPage().getTotalPage());
            jsonObject.put("categorySecondList",service.select(page));
        }else if(("del").equals(request.getParameter("type"))){
            jsonObject.put("isDel",service.del(categorySecond));
        }else if(("edit").equals(request.getParameter("type"))){
            jsonObject.put("isEdit",service.edit(categorySecond));
        }else if(("insert").equals(request.getParameter("type"))){
            jsonObject.put("isInsert",service.insert(categorySecond));
        }else if(("selectP").equals(request.getParameter("type"))){
            CategoryDao categoryDao = new CategoryDaoImpl();
            jsonObject.put("categoryList",categoryDao.select(new Category()));
        }
        writer.println(jsonObject);
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
