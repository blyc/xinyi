package behind.service.impl;


import behind.bean.CategorySecond;
import behind.bean.Page;
import behind.dao.CategorySecondDao;
import behind.dao.Impl.CategorySecondDaoImpl;
import behind.service.CategorySecondService;

import java.util.List;

public class CategorySecondServiceImpl implements CategorySecondService {
    private CategorySecondDao categorySecondDao = new CategorySecondDaoImpl();

    @Override
    public boolean selectByName(CategorySecond categorysecond) {
        return categorySecondDao.select(categorysecond).size()>0;
    }

    @Override
    public boolean insert(CategorySecond categorysecond) {
        return categorySecondDao.insert(categorysecond)>0;
    }

    @Override
    public List<CategorySecond> select(Page page) {
        return categorySecondDao.select(page);
    }

    @Override
    public boolean del(CategorySecond categorysecond) {
        return categorySecondDao.del(categorysecond);
    }

    @Override
    public Page getPage() {
        return new Page(categorySecondDao.select(new CategorySecond()).size());
    }

    @Override
    public boolean edit(CategorySecond categorysecond) {
        return categorySecondDao.edit(categorysecond);
    }
}
