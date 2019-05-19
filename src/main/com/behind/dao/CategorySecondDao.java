package behind.dao;

import behind.bean.CategorySecond;
import behind.bean.Page;

import java.util.List;

public interface CategorySecondDao {
    List<CategorySecond> select(CategorySecond categorysecond);
    List<CategorySecond> select(Page page);
    int insert(CategorySecond categorysecond);
    boolean del(CategorySecond categorysecond);
    boolean edit(CategorySecond categorysecond);
}
