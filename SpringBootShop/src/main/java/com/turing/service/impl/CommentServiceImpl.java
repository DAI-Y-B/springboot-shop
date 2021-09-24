package com.turing.service.impl;

import com.turing.entity.Comment;
import com.turing.mapper.CommentMapper;
import com.turing.service.CommentService;
import com.turing.util.Pager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    // 日志
    Logger logger = Logger.getLogger(CommentServiceImpl.class);
    @Autowired
    CommentMapper commentMapper;

    public List<Comment> getAllComments() {
        logger.info("调用CommentServiceImpl类的getAllComments方法,获取全部的留言信息");
        List<Comment> commentInfo = commentMapper.getAllComments();
        return commentInfo;
    }

    public Pager<Comment> getCommentByPage(Integer pageNum) {
        logger.info("调用CommentServiceImpl类的getCommentByPage方法,进行分页计算");
        Pager<Comment> pager = new Pager<Comment>();
        pager.setPageNum(pageNum);
        Integer totalCount = commentMapper.getAllComments().size();
        if (totalCount == 0) {
            totalCount = 0;
        }
        pager.setTotalPage(totalCount, 4);
        Integer pageSize = 4;
        Integer countEnd = (pageNum - 1) * pageSize;
        List<Comment> comments = commentMapper.selectProductByPage(countEnd, pageSize);
        pager.setData(comments);
        return pager;
    }

    public int addSomeOne(Comment comment) {
        logger.info("调用CommentServiceImpl类的addSomeOne方法,添加留言");
        return commentMapper.addSomeOne(comment);
    }

    public Comment selectCommentById(Comment comment) {
        logger.info("调用CommentServiceImpl类的selectCommentById方法,查询留言");
        return commentMapper.selectCommentById(comment);
    }

    public int replyComment(Comment comment) {
        logger.info("调用CommentServiceImpl类的replyComment方法,回复留言");
        return commentMapper.replyComment(comment);
    }

    public int delReplyById(Comment comment) {
        logger.info("调用CommentServiceImpl类的delReply方法,删除留言");
        return commentMapper.delReplyById(comment);
    }


}
