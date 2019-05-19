package behind.service.impl;


import behind.bean.Category;
import behind.bean.Page;
import behind.dao.CategoryDao;
import behind.dao.Impl.CategoryDaoImpl;
import behind.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public boolean selectByName(Category category) {
        return categoryDao.select(category).size()>0;
    }

    @Override
    public boolean insert(Category category) {
        return categoryDao.insert(category)>0;
    }

    @Override
    public List<Category> select(Page page) {
        return categoryDao.select(page);
    }

    @Override
    public boolean del(Category category) {
        return categoryDao.del(category);
    }

    @Override
    public Page getPage() {
        return new Page(categoryDao.select(new Category()).size());
    }

    @Override
    public boolean edit(Category category) {
        return categoryDao.edit(category);
    }
}
