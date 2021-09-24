package com.turing.entity;

import java.util.Date;

public class Comment {
    private int id;
    private String title;
    private String content;
    private String reply;
    private Date createTime;
    private Date replyTime;
    private String nickName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Comment [id=" + id + ", title=" + title + ", content=" + content + ", reply=" + reply + ", createTime="
                + createTime + ", replyTime=" + replyTime + ", nickName=" + nickName + "]";
    }

}
// id int not null primary key auto_increment,/* 编号 */
// title varchar(50) not null,/* 留言标题 */
// content varchar(200) not null,/* 留言内容 */
// reply varchar(200),/* 回复 */
// create_time Datetime not null,/* */
// reply_time Datetime,/* 回复时间 */
// nick_name varchar(10) not null/* 昵称 */
//
// id int not null primary key autoIncrement,/* 编号 */
// title varchar(50) not null,/* 留言标题 */
// content varchar(200) not null,/* 留言内容 */
// reply varchar(200),/* 回复 */
// createTime datetime not null,/* */
// replyTime datetime,/* 回复时间 */
// nickName varchar(10) not null/* 昵称 */