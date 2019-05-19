package behind.dao;

import behind.bean.Adminuser;
import behind.bean.Page;

import java.util.List;

public interface AdminuserDao {
    List<Adminuser> select(Adminuser adminuser);
    List<Adminuser> select(Page page);
    int insert(Adminuser adminuser);
    boolean del(Adminuser adminuser);
    boolean edit(Adminuser adminuser);
}
