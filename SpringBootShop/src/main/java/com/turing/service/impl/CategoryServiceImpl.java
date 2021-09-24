package com.turing.service.impl;

import com.turing.entity.Category;
import com.turing.mapper.CategoryMapper;
import com.turing.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    // 日志
    Logger logger = Logger.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryRootCategory() {
        logger.info("调用CategoryServiceImpl类的queryRootCategory方法,查询所有的商品一级目录（没有父节点的词条）");
        List<Category> produdCategories = categoryMapper.selectRootCategory();
        return produdCategories;
    }

    public Category queryProCateByName(Category category) {
        logger.info("调用CategoryServiceImpl类的queryProCateByName方法,根据目录的名字查询商品是否存在");
        Category proCategory = categoryMapper.selectProCateByName(category);
        return proCategory;
    }

    public int addProduct(Category category) {
        logger.info("调用CategoryServiceImpl类的addProduct方法,添加商品目录");
        return categoryMapper.addProduct(category);
    }

    public List<Category> queryProCateByParenId(Category category) {
        logger.info("调用CategoryServiceImpl类的queryProCateByParenId方法,查询商品目录的二级目录");
        List<Category> productCategories = categoryMapper.selectProCateByParenId(category);
        return productCategories;
    }

    public Category queryProCateById(Category category) {
        logger.info("调用CategoryServiceImpl类的queryProCateById方法,根据目录Id查询商品目录");
        Category proInfo = categoryMapper.selectProCateById(category);
        return proInfo;
    }

    public int updateCategory(Category category) {
        logger.info("调用CategoryServiceImpl类的updateCategory方法,根据目录Id修改商品目录信息");
        return categoryMapper.updateCategory(category);
    }

    public int deleteByParentId(Integer cParentId) {
        logger.info("调用CategoryServiceImpl类的deleteByParentId方法,删除一级目录时将二级目录删除");
        return categoryMapper.deleteByParentId(cParentId);
    }

    public int deleteById(Category category) {
        logger.info("调用CategoryServiceImpl类的deleteById方法,删除目录");
        return categoryMapper.deleteById(category);
    }

}
