package behind.bean;

import java.sql.Timestamp;

public class Product {

  private int pid;
  private String pname;
  private double marketprice;
  private double shopprice;
  private String image;
  private String pdesc;
  private int ishot;
  private java.sql.Timestamp pdate;
  private int csid;
  private String csname;

    public Product() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public double getMarketprice() {
        return marketprice;
    }

    public void setMarketprice(double marketprice) {
        this.marketprice = marketprice;
    }

    public double getShopprice() {
        return shopprice;
    }

    public void setShopprice(double shopprice) {
        this.shopprice = shopprice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPdesc() {
        return pdesc;
    }

    public void setPdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public int getIshot() {
        return ishot;
    }

    public void setIshot(int ishot) {
        this.ishot = ishot;
    }

    public Timestamp getPdate() {
        return pdate;
    }

    public void setPdate(Timestamp pdate) {
        this.pdate = pdate;
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
}
