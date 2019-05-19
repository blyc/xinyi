package behind.bean;

public class CategorySecond {

  private int csid;
  private String csname;
  private int cid;
  private String cname;

  public CategorySecond() {
  }

    public CategorySecond(int csid, String csname, int cid, String cname) {
        this.csid = csid;
        this.csname = csname;
        this.cid = cid;
        this.cname = cname;
    }

    public int getCsid() {
        return csid;
    }

    public void setCsid(int csid) {
        this.csid = csid;
    }

    public String getCsname() {
        return csname;
    }

    public void setCsname(String csname) {
        this.csname = csname;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
