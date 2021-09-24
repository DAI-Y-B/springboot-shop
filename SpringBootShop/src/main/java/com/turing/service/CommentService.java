package com.turing.service;

import com.turing.entity.Comment;
import com.turing.util.Pager;

import java.util.List;

public interface CommentService {
    /**查询所有的留言信息*/
    List<Comment> getAllComments();

    /**获取展示分页信息*/
    Pager<Comment> getCommentByPage(Integer pageNum);

    /**插入留言*/
    int addSomeOne(Comment comment);

    /**根据Id查询留言信息*/
    Comment selectCommentById(Comment comment);

    /**回复留言*/
    int replyComment(Comment comment);

    /**删除留言*/
    int delReplyById(Comment comment);
}
