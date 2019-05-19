package behind.service;


import behind.bean.Category;
import behind.bean.Page;

import java.util.List;

public interface CategoryService {
    Page getPage();
    boolean selectByName(Category category);
    List<Category> select(Page page);
    boolean insert(Category category);
    boolean del(Category category);
    boolean edit(Category category);
}
