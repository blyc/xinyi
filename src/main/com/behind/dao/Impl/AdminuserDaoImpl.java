package behind.dao.Impl;

import behind.bean.Adminuser;
import behind.bean.Page;
import behind.dao.AdminuserDao;
import behind.util.Dbmanger;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminuserDaoImpl implements AdminuserDao {

    @Override
    public List<Adminuser> select(Adminuser adminuser) {
        List<Adminuser> userList = null;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "select * from adminuser where 1=1";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();

        List<Object> params =new ArrayList<>();

        String username = adminuser.getUsername();
        if (username != null && username.trim().length() > 0) {
            sql += " and username = ?";
            params.add(username);
        }
        String password = adminuser.getPassword();
        if (password != null && password.trim().length() > 0) {
            sql += " and password = ?";
            params.add(password);
        }

        try {
            userList = queryRunner.query(conn,sql,new BeanListHandler<>(Adminuser.class),params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    @Override
    public List<Adminuser> select(Page page) {
        List<Adminuser> userList = null;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "select * from adminuser limit ?,?";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();

        Object[] params ={page.getPageSize()*(page.getCurPage()-1),page.getPageSize()};

        try {
            userList = queryRunner.query(conn,sql,new BeanListHandler<>(Adminuser.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    @Override
    public int insert(Adminuser adminuser) {
        int isUpdate = -1;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "insert into adminuser(username,password) Values(?,?)";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();
        Object[] params ={adminuser.getUsername(),adminuser.getPassword()};
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
    public boolean del(Adminuser adminuser) {
        int isDel = -1;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "delete from adminuser where uid = ?";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();
        Object[] params ={adminuser.getUid()};
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
    public boolean edit(Adminuser adminuser) {
        int isEdit = -1;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "update adminuser SET username=?,password=? where uid=?";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();
        Object[] params ={adminuser.getUsername(),adminuser.getPassword(),adminuser.getUid()};
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