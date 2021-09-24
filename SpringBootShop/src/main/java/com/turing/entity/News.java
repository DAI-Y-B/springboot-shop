package com.turing.entity;

import java.util.Date;

public class News {
    private int nId;
    private String nTitle;
    private String nContent;
    private Date nCreatetime;

    public int getnId() {
        return nId;
    }

    public void setnId(int nId) {
        this.nId = nId;
    }

    public String getnTitle() {
        return nTitle;
    }

    public void setnTitle(String nTitle) {
        this.nTitle = nTitle;
    }

    public String getnContent() {
        return nContent;
    }

    public void setnContent(String nContent) {
        this.nContent = nContent;
    }

    public Date getnCreatetime() {
        return nCreatetime;
    }

    public void setnCreatetime(Date nCreatetime) {
        this.nCreatetime = nCreatetime;
    }

    @Override
    public String toString() {
        return "News [nId=" + nId + ", nTitle=" + nTitle + ", nContent=" + nContent + ", nCreatetime=" + nCreatetime
                + "]";
    }

}
// n_id int not null primary key auto_increment,/* 新闻Id */
// n_title varchar(30) not null,/* 新闻标题 */
// n_content varchar(1000) not null,/* 新闻内容 */
// n_createtime date not null/* 创建时间 */
//
//
// nId int not null primary key autoIncrement,/* 新闻id */
// nTitle varchar(30) not null,/* 新闻标题 */
// nContent varchar(1000) not null,/* 新闻内容 */
// nCreatetime date not null/* 创建时间 */