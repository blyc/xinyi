package behind.controller;

import behind.bean.CategorySecond;
import behind.bean.Page;
import behind.bean.Product;
import behind.dao.CategorySecondDao;
import behind.dao.Impl.CategorySecondDaoImpl;
import behind.service.ProductService;
import behind.service.impl.ProductServiceImpl;
import behind.util.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Servlet_ProductMg",urlPatterns = "/Servlet_ProductMg")
public class Servlet_ProductMg extends HttpServlet {
    private ProductService service = new ProductServiceImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        JSONObject jsonObject = new JSONObject();

        if(isMultipartContent(request)){
            DiskFileItemFactory factory = new DiskFileItemFactory();//工厂
            ServletFileUpload upload = new ServletFileUpload(factory);//解析器
            List<FileItem> fileItems = null;
            try {
                fileItems = upload.parseRequest(request);//解析request
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()) {//isFormField() 文件返回false
                    request.setAttribute(fileItem.getFieldName(),fileItem.getName());
                    //文件
                    InputStream inputStream = fileItem.getInputStream();
                    int len;
                    byte[] bf = new byte[1024];
                    String realPath = request.getSession().getServletContext().getRealPath("images\\upload");
                    FileOutputStream fileOutputStream = new FileOutputStream(realPath+"\\" + fileItem.getName());
                    while ((len = inputStream.read(bf)) > 0) {
                        fileOutputStream.write(bf, 0, len);
                    }
                    fileOutputStream.close();
                    inputStream.close();
                }else{
                    request.setAttribute(fileItem.getFieldName(),fileItem.getString());
                }
            }
            Product product =(Product)BeanUtil.RequestToBeanUtil2(request, new Product());
            if(("edit").equals(request.getAttribute("type"))){
                jsonObject.put("isEdit",service.edit(product));
            }else if(("insert").equals(request.getAttribute("type"))){
                jsonObject.put("isInsert",service.insert(product));
            }
        }else{
            Page page = (Page) BeanUtil.RequestToBeanUtil(request, new Page());
            Product product = (Product) BeanUtil.RequestToBeanUtil(request, new Product());

            if (("select").equals(request.getParameter("type"))){
                jsonObject.put("totalPage",service.getPage().getTotalPage());
                jsonObject.put("productList",service.select(page));
            }else if(("del").equals(request.getParameter("type"))){
                jsonObject.put("isDel",service.del(product));
            }else if(("selectSP").equals(request.getParameter("type"))){
                CategorySecondDao categorySecondDao = new CategorySecondDaoImpl();
                jsonObject.put("categorySecondList",categorySecondDao.select(new CategorySecond()));
            }else if(("selectO").equals(request.getParameter("type"))){
                jsonObject.put("productList",service.selectBy(product));
            }
        }
        writer.println(jsonObject);
        writer.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * 判断是否是multipart/form-data请求
     * @param request
     * @return boolean
     */
    private static boolean isMultipartContent(HttpServletRequest request) {
        String contentType = request.getContentType();	//获取Content-Type
        return (contentType != null) && (contentType.toLowerCase().startsWith("multipart/"));
    }
}
