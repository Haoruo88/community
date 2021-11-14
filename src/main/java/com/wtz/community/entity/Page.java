package com.wtz.community.entity;

/**
 * Description：
 * Date:2021/11/13 15:44
 **/
public class Page {    //分页信息
    //当前页码: 浏览器设定
    private int current = 1;
    //每页贴子上限：服务器设定
    private int limit = 10;
    //数据总数(用于页数的计算):数据库获取
    private int rows;
    //每页访问路径：服务器设定为
    private String path;

    /**
     * 获取当前页的起始行以查询数据库
     * @return
     */
    public int getOffset() {
        return (current - 1)* limit;
    }
    /**
     * 获取总页数
     * @return
     */
    public int getTotal() {
        return rows % limit == 0 ? rows / limit : rows / limit + 1;
    }
    /**
     * 页数范围不全部展示出来，只展示离当前页近的页
     * 现实的起始页
     * @return
     */
    public int getFrom() {
        int from = current - 2;
        return from >= 1 ? from : current;
    }
    /**
     * 页数范围不全部展示出来，只展示离当前页近的页
     * 显示的结束页
     * @return
     */
    public int getTo() {
        int to = current + 2;
        int total = getTotal();
        return Math.min(to, total);
    }

    public int getCurrent() {
        return current;
    }
    public void setCurrent(int current) {
        if (current > 0) {
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 100)
        this.limit = limit;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
