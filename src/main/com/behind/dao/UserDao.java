package behind.dao;

import behind.bean.Page;
import behind.bean.User;

import java.util.List;

public interface UserDao {
    List<User> select(User user);
    List<User> select(Page page);
    int insert(User user);
    boolean del(User user);
    boolean edit(User user);
}
