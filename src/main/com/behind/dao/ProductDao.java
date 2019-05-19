package behind.dao;

import behind.bean.Page;
import behind.bean.Product;

import java.util.List;

public interface ProductDao {
    List<Product> select(Product product);
    List<Product> select(Page page);
    int insert(Product product);
    boolean del(Product product);
    boolean edit(Product product);
}
