package behind.dao;

import behind.bean.Category;
import behind.bean.Page;

import java.util.List;

public interface CategoryDao {
    List<Category> select(Category category);
    List<Category> select(Page page);
    int insert(Category category);
    boolean del(Category category);
    boolean edit(Category category);
}
