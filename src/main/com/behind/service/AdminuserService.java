package behind.service;


import behind.bean.Adminuser;
import behind.bean.Page;

import java.util.List;

public interface AdminuserService {
    Page getPage();
    boolean login(Adminuser adminuser);
    boolean selectByName(Adminuser adminuser);
    List<Adminuser> select(Page page);
    boolean register(Adminuser adminuser);
    boolean del(Adminuser adminuser);
    boolean edit(Adminuser adminuser);
}
