package behind.bean;

public class Page {
    private int curPage = 1;//当前页码
    private int total;//总记录数
    private int totalPage;//总页数
    private int pageSize = 10;//每页显示条数

    public Page() {
    }

    public Page(int total) {
        this.total = total;
        double i = total / 1.0 / pageSize;
        if(i==0){
            this.totalPage = 1;
        }else{
            this.totalPage = (int)Math.ceil(i);
        }
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "curPage=" + curPage +
                ", total=" + total +
                ", totalPage=" + totalPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
