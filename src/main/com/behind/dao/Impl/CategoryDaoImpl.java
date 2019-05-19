package behind.dao.Impl;

import behind.bean.Category;
import behind.bean.Page;
import behind.dao.CategoryDao;
import behind.util.Dbmanger;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    @Override
    public List<Category> select(Category category) {
        List<Category> categoryList = null;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "select * from category where 1=1";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();

        List<Object> params =new ArrayList<>();

        String cname = category.getCname();
        if (cname != null && cname.trim().length() > 0) {
            sql += " and cname = ?";
            params.add(cname);
        }

        try {
            categoryList = queryRunner.query(conn,sql,new BeanListHandler<>(Category.class),params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return categoryList;
    }

    @Override
    public List<Category> select(Page page) {
        List<Category> categoryList = null;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "select * from category limit ?,?";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();

        Object[] params ={page.getPageSize()*(page.getCurPage()-1),page.getPageSize()};

        try {
            categoryList = queryRunner.query(conn,sql,new BeanListHandler<>(Category.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return categoryList;
    }

    @Override
    public int insert(Category category) {
        int isUpdate = -1;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "insert into category(cname) Values(?)";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();
        Object[] params ={category.getCname()};
        try {
            isUpdate = queryRunner.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isUpdate;
    }

    @Override
    public boolean del(Category category) {
        int isDel = -1;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "delete from category where cid = ?";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();
        Object[] params ={category.getCid()};
        try {
            isDel = queryRunner.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isDel>0;
    }

    @Override
    public boolean edit(Category category) {
        int isEdit = -1;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "update category SET cname=? where cid=?";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();
        Object[] params ={category.getCname(),category.getCid()};
        try {
            isEdit = queryRunner.update(conn,sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isEdit>0;
    }
}