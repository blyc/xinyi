package behind.service.impl;


import behind.bean.Page;
import behind.bean.Product;
import behind.dao.Impl.ProductDaoImpl;
import behind.dao.ProductDao;
import behind.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<Product> selectBy(Product product) {
        return productDao.select(product);
    }

    @Override
    public boolean insert(Product product) {
        return productDao.insert(product)>0;
    }

    @Override
    public List<Product> select(Page page) {
        return productDao.select(page);
    }

    @Override
    public boolean del(Product product) {
        return productDao.del(product);
    }

    @Override
    public Page getPage() {
        return new Page(productDao.select(new Product()).size());
    }

    @Override
    public boolean edit(Product product) {
        return productDao.edit(product);
    }
}
