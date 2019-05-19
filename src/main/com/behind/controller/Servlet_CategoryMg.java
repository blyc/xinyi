package behind.controller;

import behind.bean.Category;
import behind.bean.Page;
import behind.service.CategoryService;
import behind.service.impl.CategoryServiceImpl;
import behind.util.BeanUtil;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet_CategoryMg",urlPatterns = "/Servlet_CategoryMg")
public class Servlet_CategoryMg extends HttpServlet {
    private CategoryService service = new CategoryServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        JSONObject jsonObject = new JSONObject();

        Page page = (Page) BeanUtil.RequestToBeanUtil(request, new Page());
        Category category = (Category) BeanUtil.RequestToBeanUtil(request, new Category());

        if (("select").equals(request.getParameter("type"))){
            jsonObject.put("totalPage",service.getPage().getTotalPage());
            jsonObject.put("categoryList",service.select(page));
        }else if(("del").equals(request.getParameter("type"))){
            jsonObject.put("isDel",service.del(category));
        }else if(("edit").equals(request.getParameter("type"))){
            jsonObject.put("isEdit",service.edit(category));
        }else if(("insert").equals(request.getParameter("type"))){
            jsonObject.put("isInsert",service.insert(category));
        }
        writer.println(jsonObject);
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
