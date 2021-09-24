package com.turing.service.impl;

import com.turing.entity.News;
import com.turing.mapper.NewsMapper;
import com.turing.service.NewsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    // 日志
    Logger logger = Logger.getLogger(NewsServiceImpl.class);
    @Autowired
    private NewsMapper newsMapper;

    public List<News> selectAllNews() {
        logger.info("调用NewsServiceImpl类selectAllNews方法,查询全部新闻消息");
        List<News> allNews = newsMapper.selectAllNews();
        return allNews;
    }

    public List<News> selectAllNewsPerPage(Integer currentPage, Integer size) {
        logger.info("调用NewsServiceImpl类的selectAllNewsPerPage方法,查询所有新闻,实现分页效果");
        List<News> allNewsPage = newsMapper.selectAllNewsPerPage(currentPage, size);
        return allNewsPage;
    }

    public int addNewInfo(News news) {
        logger.info("调用NewsServiceImpl类的addOrder方法,实现添加新闻");
        return newsMapper.addNewInfo(news);
    }

    public News selectAllNewsById(News news) {
        logger.info("调用NewsServiceImpl类的selectAllNewsById方法,根据id查询一条新闻");
        return newsMapper.selectAllNewsById(news);
    }

    public int newInfoEdit(News news) {
        logger.info("调用NewsServiceImpl类的newInfoEdit方法,根据id查询一条新闻");
        return newsMapper.newInfoEdit(news);
    }

    public int delNewInfo(News news) {
        logger.info("调用NewsServiceImpl类的delNewInfo方法,根据id删除一条新闻");
        return newsMapper.delNewInfo(news);
    }

    public News selectByTitle(News news) {
        logger.info("调用NewsServiceImpl类的delNewInfo方法,根据title查询一条新闻");
        return newsMapper.selectByTitle(news);
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
 */
