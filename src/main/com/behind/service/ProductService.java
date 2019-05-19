package behind.service;


import behind.bean.Page;
import behind.bean.Product;

import java.util.List;

public interface ProductService {
    Page getPage();
    List<Product> selectBy(Product product);
    List<Product> select(Page page);
    boolean insert(Product product);
    boolean del(Product product);
    boolean edit(Product product);
}
