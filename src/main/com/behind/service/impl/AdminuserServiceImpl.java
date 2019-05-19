package behind.service.impl;


import behind.bean.Adminuser;
import behind.bean.Page;
import behind.dao.AdminuserDao;
import behind.dao.Impl.AdminuserDaoImpl;
import behind.service.AdminuserService;

import java.util.List;

public class AdminuserServiceImpl implements AdminuserService {
    AdminuserDao adminuserDao = new AdminuserDaoImpl();

    @Override
    public boolean login(Adminuser adminuser) {
        return adminuserDao.select(adminuser).size()>0;
    }

    @Override
    public boolean selectByName(Adminuser adminuser) {
        return adminuserDao.select(adminuser).size()>0;
    }

    @Override
    public boolean register(Adminuser adminuser) {
        return adminuserDao.insert(adminuser)>0;
    }

    @Override
    public List<Adminuser> select(Page page) {
        return adminuserDao.select(page);
    }

    @Override
    public boolean del(Adminuser adminuser) {
        return adminuserDao.del(adminuser);
    }

    @Override
    public Page getPage() {
        return new Page(adminuserDao.select(new Adminuser()).size());
    }

    @Override
    public boolean edit(Adminuser adminuser) {
        return adminuserDao.edit(adminuser);
    }
}
