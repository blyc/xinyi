package behind.dao.Impl;

import behind.bean.CategorySecond;
import behind.bean.Page;
import behind.dao.CategorySecondDao;
import behind.util.Dbmanger;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorySecondDaoImpl implements CategorySecondDao {

    @Override
    public List<CategorySecond> select(CategorySecond categorySecond) {
        List<CategorySecond> categorySecondList = null;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "SELECT a.csid,a.csname,b.cname,a.cid FROM categorySecond a ,category b WHERE a.cid = b.cid ";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();

        List<Object> params =new ArrayList<>();

        String csname = categorySecond.getCsname();
        if (csname != null && csname.trim().length() > 0) {
            sql += " and a.csname = ?";
            params.add(csname);
        }

        try {
            categorySecondList = queryRunner.query(conn,sql,new BeanListHandler<>(CategorySecond.class),params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return categorySecondList;
    }

    @Override
    public List<CategorySecond> select(Page page) {
        List<CategorySecond> categorySecondList = null;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "SELECT a.csid,a.csname,b.cname,a.cid FROM categorySecond a ,category b WHERE a.cid = b.cid limit ?,?";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();

        Object[] params ={page.getPageSize()*(page.getCurPage()-1),page.getPageSize()};

        try {
            categorySecondList = queryRunner.query(conn,sql,new BeanListHandler<>(CategorySecond.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return categorySecondList;
    }

    @Override
    public int insert(CategorySecond categorySecond) {
        int isUpdate = -1;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "insert into categorySecond(csname,cid) Values(?,?)";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();
        Object[] params ={categorySecond.getCsname(),categorySecond.getCid()};
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
    public boolean del(CategorySecond categorySecond) {
        int isDel = -1;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "delete from categorySecond where csid = ?";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();
        Object[] params ={categorySecond.getCsid()};
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
    public boolean edit(CategorySecond categorySecond) {
        int isEdit = -1;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "update categorySecond SET csname=?,cid=? where csid=?";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();
        Object[] params ={categorySecond.getCsname(),categorySecond.getCid(),categorySecond.getCsid()};
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