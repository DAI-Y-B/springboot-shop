package com.turing.service;

import com.turing.entity.Category;

import java.util.List;

public interface CategoryService {

    /**查询所有的商品一级目录（没有父节点的词条）*/
    List<Category> queryRootCategory();

    /**根据目录的名字查询商品是否存在*/
    Category queryProCateByName(Category category);

    /** 添加商品目录 */
    int addProduct(Category category);

    /**查询商品目录的二级目录*/
    List<Category> queryProCateByParenId(Category category);

    /**  根据商品目录Id查询信息 */
    Category queryProCateById(Category category);

    /** 修改商品目录 */
    int updateCategory(Category category);

    /**删除一级目录时将二级目录删除*/
    int deleteByParentId(Integer cParentId);

    /**删除目录*/
    int deleteById(Category category);

}
