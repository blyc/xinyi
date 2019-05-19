package behind.service;


import behind.bean.Page;
import behind.bean.User;

import java.util.List;

public interface UserService {
    Page getPage();
    boolean login(User user);
    boolean selectByName(User user);
    List<User> select(Page page);
    boolean insert(User user);
    boolean del(User user);
    boolean edit(User user);
}
