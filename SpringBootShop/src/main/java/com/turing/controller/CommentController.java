package com.turing.controller;

import com.turing.entity.Comment;
import com.turing.service.CommentService;
import com.turing.util.Pager;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 分页显示留言数据
    @RequestMapping(value = "/getCommentByPage")
    public void getCommentByPage(@RequestParam("pageNum") String pageNum, HttpServletRequest request,
                                 HttpServletResponse response, HttpSession session) throws Exception {
        List<Comment> totalNum = commentService.getAllComments();
        for (Comment comment : totalNum) {
            System.out.println("totalNum---------comment" + comment);
        }
        int size = 4;
        int totalPage = totalNum.size() % size == 0 ? totalNum.size() / size : totalNum.size() / size + 1;
        int pageNum1 = 1;
        if (pageNum != null) {
            pageNum1 = Integer.parseInt(pageNum);
            pageNum1 = pageNum1 <= 1 ? 1 : pageNum1;
            pageNum1 = pageNum1 >= totalPage ? totalPage : pageNum1;
            System.out.println("看看到底是第几页" + pageNum1);
        }
        Pager<Comment> pager;
        pager = commentService.getCommentByPage(pageNum1);
        List<Comment> comments = new ArrayList<Comment>();
        comments = pager.getData();
        for (Comment comment : comments) {
            System.out.println("getCommentByPage-----------------------+comment" + comment);
        }
        PrintWriter out = response.getWriter();
        String json = JSONObject.fromObject(pager).toString();
        out.write(json);
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/commentAdd")
    public String getCommentByPage(@RequestParam("guestTitle") String guestTitle,
                                   @RequestParam("guestContent") String guestContent, @RequestParam("guestName") String guestName,
                                   HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        Comment oneProple = new Comment();
        oneProple.setNickName(guestName);
        oneProple.setContent(guestContent);
        oneProple.setTitle(guestTitle);
        oneProple.setCreateTime(new Date());
        commentService.addSomeOne(oneProple);
        return "user/guestbook";
    }

    @RequestMapping(value = "/commentFind")
    public void commentFind(@RequestParam("id") Integer id, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Comment commentInfo = new Comment();
        commentInfo.setId(id);
        commentInfo = commentService.selectCommentById(commentInfo);
        PrintWriter out = response.getWriter();
        out.write(JSONObject.fromObject(commentInfo).toString());
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/commentEdit")
    public String commentEdit(@RequestParam("orderId") Integer orderId, @RequestParam("replyContent") String replyContent,
                              HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Comment commentInfo = new Comment();
        commentInfo.setId(orderId);
        commentInfo = commentService.selectCommentById(commentInfo);
        commentInfo.setReply(replyContent);
        commentInfo.setReplyTime(new Date());
        commentService.replyComment(commentInfo);
        return "manage/guestbook";
    }

    @RequestMapping(value = "/commentDel")
    public String commentDel(@RequestParam("id") Integer id, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        Comment delCommentInfo = new Comment();
        delCommentInfo.setId(id);
        commentService.delReplyById(delCommentInfo);
        return "manage/guestbook";
    }
}

/*
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
