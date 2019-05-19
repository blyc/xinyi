package behind.dao.Impl;

import behind.bean.Page;
import behind.bean.Product;
import behind.dao.ProductDao;
import behind.util.Dbmanger;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public List<Product> select(Product product) {
        List<Product> productList = null;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "SELECT a.*,b.csname FROM product a ,categorysecond b WHERE a.csid = b.csid ";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();

        List<Object> params =new ArrayList<>();

        String pname = product.getPname();
        if (pname != null && pname.trim().length() > 0) {
            sql += " and a.pname = ?";
            params.add(pname);
        }

        String csname = product.getCsname();
        if (csname != null && csname.trim().length() > 0) {
            sql += " and b.csname = ?";
            params.add(csname);
        }

        int pid = product.getPid();
        if (pid > 0) {
            sql += " and a.pid = ?";
            params.add(pid);
        }

        try {
            productList = queryRunner.query(conn,sql,new BeanListHandler<>(Product.class),params.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return productList;
    }

    @Override
    public List<Product> select(Page page) {
        List<Product> productList = null;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "SELECT a.*,b.csname FROM product a ,categorysecond b WHERE a.csid = b.csid limit ?,?";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();

        Object[] params ={page.getPageSize()*(page.getCurPage()-1),page.getPageSize()};

        try {
            productList = queryRunner.query(conn,sql,new BeanListHandler<>(Product.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return productList;
    }

    @Override
    public int insert(Product product) {
        int isUpdate = -1;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "insert into product(pname,marketprice,shopprice,pdesc,ishot,pdate,csid,image) Values(?,?,?,?,?,?,?,?)";
        Object[] params ={product.getPname(),product.getMarketprice(),product.getShopprice(),product.getPdesc(),product.getIshot(),product.getPdate(),product.getCsid(),product.getImage()};
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();

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
    public boolean del(Product product) {
        int isDel = -1;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = "delete from product where pid = ?";
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();
        Object[] params ={product.getPid()};
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
    public boolean edit(Product product) {
        int isEdit = -1;
        //获取连接
        Connection conn = Dbmanger.getConn();
        //构建SQL语句
        String sql = null;
        String image = product.getImage();
        List<Object> params =new ArrayList<>();
        params.add(product.getPname());
        params.add(product.getMarketprice());
        params.add(product.getShopprice());
        params.add(product.getPdesc());
        params.add(product.getIshot());
        params.add(product.getCsid());
        if (!("undefined").equals(image)) {
            sql = "update product SET pname=?,marketprice=?,shopprice=?,pdesc=?,ishot=?,csid=?,image=? where pid=?";
            params.add(product.getImage());
        }else{
            sql = "update product SET pname=?,marketprice=?,shopprice=?,pdesc=?,ishot=?,csid=? where pid=?";
        }
        params.add(product.getPid());
        //创建QueryRunner类对象
        QueryRunner queryRunner = new QueryRunner();
        try {
            isEdit = queryRunner.update(conn,sql,params.toArray());
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