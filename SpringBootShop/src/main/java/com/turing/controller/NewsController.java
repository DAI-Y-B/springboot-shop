package com.turing.controller;

import com.turing.entity.News;
import com.turing.service.NewsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/news")
public class NewsController {
    Logger logger = Logger.getLogger(NewsController.class);
    @Autowired
    private NewsService newsService;

    // 添加新闻
    @RequestMapping(value = "/newsAdd")
    public String newsAdd(@RequestParam("title") String title, @RequestParam("content") String content,
                          HttpSession session) {
        News addNews = new News();
        addNews.setnTitle(title);
        addNews.setnContent(content);
        addNews.setnCreatetime(new Date());
        newsService.addNewInfo(addNews);
        return "redirect:/news/newsQueryByPage.do?currentPage=1";
    }

    // 新闻的分页查询
    @RequestMapping(value = "/newsQueryByPage")
    public String newsQueryByPage(@RequestParam("currentPage") String currentPage, HttpSession session) {
        List<News> news = null;
        news = newsService.selectAllNews();
        List<News> fenyeNews = null;
        int size = 3;
        int totalCount = news.size() % size == 0 ? news.size() / size : news.size() / size + 1;
        if (null != currentPage) {
            fenyeNews = newsService.selectAllNewsPerPage((Integer.parseInt(currentPage) - 1) * size, size);
            session.setAttribute("currentPage", Integer.parseInt(currentPage));
        } else {
            fenyeNews = newsService.selectAllNewsPerPage(1, size);
            session.setAttribute("currentPage", Integer.parseInt(currentPage));
        }
        session.setAttribute("newsList", fenyeNews);
        session.setAttribute("totalCount", totalCount);
        session.setAttribute("total", news.size());
        return "manage/news";
    }

    // 修改新闻前先将新闻信息传到修改表中
    @RequestMapping(value = "/newsFind")
    public String newsFind(@RequestParam("id") Integer id, HttpSession session) {
        News newInfo = new News();
        newInfo.setnId(id);
        newInfo = newsService.selectAllNewsById(newInfo);
        session.setAttribute("news", newInfo);
        return "manage/news-modify";
    }

    // 根绝id修改新闻
    @RequestMapping(value = "/newsEdit")
    public String newsFind(@RequestParam("id") Integer id, @RequestParam("title") String title,
                           @RequestParam("content") String content, HttpSession session) {
        News oneNews = (News) session.getAttribute("news");
        oneNews.setnId(id);
        oneNews.setnTitle(title);
        oneNews.setnContent(content);
        newsService.newInfoEdit(oneNews);
        return "redirect:/news/newsQueryByPage.do?currentPage=1";
    }

    //根据id删除新闻
    @RequestMapping(value = "/newsDel")
    public String newsDel(@RequestParam("id") Integer id, HttpSession session) {
        News oldNews = new News();
        oldNews.setnId(id);
        newsService.delNewInfo(oldNews);
        return "redirect:/news/newsQueryByPage.do?currentPage=1";
    }

    //商品主页显示新闻内容
    @RequestMapping(value = "/findNewsByTitle")
    public String findNewsByTitle(@RequestParam("title") String title, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        List<News> list = newsService.selectAllNews();
        session.setAttribute("newsList", list);
        News newContent = new News();
        newContent.setnTitle(title);
        newContent = newsService.selectByTitle(newContent);
        session.setAttribute("news", newContent);
        return "user/news-view";
    }
}

/**
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
 * */
