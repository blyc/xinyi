package behind.dao.Impl;

import behind.bean.Page;
import behind.bean.User;
import behind.dao.UserDao;
import behind.util.Dbmanger;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<User> select(User user) {
        List<User> userList = null;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "select * from user where 1=1";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();

        List<Object> params =new ArrayList<>();

        String username = user.getUsername();
        if (username != null && username.trim().length() > 0) {
            sql += " and username = ?";
            params.add(username);
        }
        String password = user.getPassword();
        if (password != null && password.trim().length() > 0) {
            sql += " and password = ?";
            params.add(password);
        }

        try {
            userList = queryRunner.query(conn,sql,new BeanListHandler<>(User.class),params.toArray());
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
    public List<User> select(Page page) {
        List<User> userList = null;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "select * from user limit ?,?";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();

        Object[] params ={page.getPageSize()*(page.getCurPage()-1),page.getPageSize()};

        try {
            userList = queryRunner.query(conn,sql,new BeanListHandler<>(User.class),params);
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
    public int insert(User user) {
        int isUpdate = -1;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "insert into user(username,password,name,email,phone,addr,state) Values(?,?,?,?,?,?,?)";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();
        Object[] params ={user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getPhone(),user.getAddr(),user.getState()};
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
    public boolean del(User user) {
        int isDel = -1;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "delete from user where uid = ?";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();
        Object[] params ={user.getUid()};
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
    public boolean edit(User user) {
        int isEdit = -1;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "update user SET username=?,password=?,name=?,email=?,phone=?,addr=?,state=? where uid=?";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();
        Object[] params ={user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getPhone(),user.getAddr(),user.getState(),user.getUid()};
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