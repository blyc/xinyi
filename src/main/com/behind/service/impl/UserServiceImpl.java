package behind.service.impl;


import behind.bean.Page;
import behind.bean.User;
import behind.dao.Impl.UserDaoImpl;
import behind.dao.UserDao;
import behind.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public boolean login(User user) {
        return userDao.select(user).size()>0;
    }

    @Override
    public boolean selectByName(User user) {
        return userDao.select(user).size()>0;
    }

    @Override
    public boolean insert(User user) {
        return userDao.insert(user)>0;
    }

    @Override
    public List<User> select(Page page) {
        return userDao.select(page);
    }

    @Override
    public boolean del(User user) {
        return userDao.del(user);
    }

    @Override
    public Page getPage() {
        return new Page(userDao.select(new User()).size());
    }

    @Override
    public boolean edit(User user) {
        return userDao.edit(user);
    }
}
