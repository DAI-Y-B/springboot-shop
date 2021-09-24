package com.turing.mapper;

import com.turing.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    /**查询所有的留言信息*/
    @Select("select * from easybuy_comment")
    List<Comment> getAllComments();

    /** 分页查询相关留言信息 */
    @Select("select * from easybuy_comment limit #{pageNum},#{pageSize}")
    List<Comment> selectProductByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    /**插入留言*/
    @Insert("insert into easybuy_comment values(null,#{title},#{content},#{reply},#{createTime},#{replyTime},#{nickName})")
    @Options(useGeneratedKeys = true)
    // 自动增长列
    int addSomeOne(Comment comment);

    /**根据Id查询留言信息*/
    @Select("select * from easybuy_comment where id=#{id}")
    Comment selectCommentById(Comment comment);

    /**回复留言*/
    @Update("update easybuy_comment set title=#{title}, content=#{content}, reply=#{reply}, create_time=#{createTime}, "
            + "reply_time=#{replyTime}, nick_name=#{nickName} where id=#{id}")
    int replyComment(Comment comment);

    /**删除留言*/
    @Delete("delete from easybuy_comment where id=#{id}")
    int delReplyById(Comment comment);
}
// easybuy_comment
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