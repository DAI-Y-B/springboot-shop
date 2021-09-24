package com.turing.mapper;

import com.turing.entity.News;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NewsMapper {
    /**查询所有新闻消息*/
    @Select("select * from easybuy_news")
    List<News> selectAllNews();

    /** 实现分页查询，计算相关的页数位置 */
    @Select("select * from easybuy_news limit #{currentPage},#{size}")
    List<News> selectAllNewsPerPage(@Param("currentPage") Integer currentPage, @Param("size") Integer size);

    /** 新增新闻 */
    @Insert("insert into easybuy_news values(null,#{nTitle},#{nContent},#{nCreatetime})")
    @Options(useGeneratedKeys = true)
    // 自动增长列
    int addNewInfo(News news);

    /**查询所有新闻消息*/
    @Select("select * from easybuy_news where n_id = #{nId}")
    News selectAllNewsById(News news);

    /**修改新闻信息*/
    @Update("update easybuy_news set n_title=#{nTitle} ,  n_content=#{nContent} ,n_createtime= #{nCreatetime} where n_id = #{nId}")
    int newInfoEdit(News news);

    /**根据新闻id删除对应的新闻*/
    @Delete("delete from easybuy_news where n_id = #{nId}")
    int delNewInfo(News news);

    /**根据标题查询对应的信息*/
    @Select("select * from easybuy_news where n_title = #{nTitle}")
    News selectByTitle(News news);
}

//n_id int not null primary key auto_increment,/* 新闻Id */
//n_title varchar(30) not null,/* 新闻标题 */
//n_content varchar(1000) not null,/* 新闻内容 */
//n_createtime date not null/* 创建时间 */
//
//
//nId int not null primary key autoIncrement,/* 新闻id */
//nTitle varchar(30) not null,/* 新闻标题 */
//nContent varchar(1000) not null,/* 新闻内容 */
//nCreatetime date not null/* 创建时间 */
