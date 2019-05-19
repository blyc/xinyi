package behind.bean;

import java.io.Serializable;

public class Adminuser implements Serializable  {
    private  int uid;
    private  String username;
    private  String password;

    public Adminuser() {
    }

    public Adminuser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Adminuser(int uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Adminuser{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
