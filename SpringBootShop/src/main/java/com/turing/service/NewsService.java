package com.turing.service;

import com.turing.entity.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsService {
    /**查询所有新闻消息*/
    List<News> selectAllNews();

    /** 实现分页查询，计算相关的页数位置 */
    List<News> selectAllNewsPerPage(@Param("currentPage") Integer currentPage, @Param("size") Integer size);

    /** 新增新闻 */
    int addNewInfo(News news);

    /**查询所有新闻消息*/
    News selectAllNewsById(News news);

    /**修改新闻信息*/
    int newInfoEdit(News news);

    /**根据新闻id删除对应的新闻*/
    int delNewInfo(News news);

    /**根据标题查询对应的信息*/
    News selectByTitle(News news);
}
