package com.turing.mapper;

import com.turing.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /** 添加商品目录 */
    @Insert("insert into product_category values(null,#{cName},#{cParentId})")
    @Options(useGeneratedKeys = true)
    // 自动增长列
    int addProduct(Category category);

    /** 查询所有的商品一级目录 */
    @Select("select * from product_category where c_parent_id = 0 ")
    List<Category> selectRootCategory();

    /** 查询所有的商品一级目录 */
    @Select("select * from product_category where c_name = #{cName} ")
    Category selectProCateByName(Category category);

    /** 查询商品的二级目录 */
    @Select("select * from product_category where c_parent_id = #{cParentId} ")
    List<Category> selectProCateByParenId(Category category);

    /** 根据商品目录Id查询信息 */
    @Select("select * from product_category where c_id = #{cId} ")
    Category selectProCateById(Category category);

    /** 修改商品目录 */
    @Update("Update product_category set  c_name = #{cName}, c_parent_id=#{cParentId} where c_id=#{cId} ")
    int updateCategory(Category category);

    /**删除一级目录时将二级目录删除*/
    @Delete("delete from product_category where c_parent_id = #{cParentId}")
    int deleteByParentId(@Param("cParentId") Integer cParentId);

    /**删除目录*/
    @Delete("delete from product_category where c_id = #{cId}")
    int deleteById(Category category);
}
// c_id int not null primary key auto_increment,/* 编号 */
// c_name varchar(20) not null, /* 名字 */
// c_parent_id int /* 父分类 */
//
// cId int not null primary key autoIncrement,/* 编号 */
// cName varchar(20) not null, /* 名字 */
// cParentId int /* 父分类 */