package behind.service;


import behind.bean.CategorySecond;
import behind.bean.Page;

import java.util.List;

public interface CategorySecondService {
    Page getPage();
    boolean selectByName(CategorySecond categorySecond);
    List<CategorySecond> select(Page page);
    boolean insert(CategorySecond categorySecond);
    boolean del(CategorySecond categorySecond);
    boolean edit(CategorySecond categorySecond);
}
