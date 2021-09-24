package com.turing.service;

import com.turing.entity.Product;
import com.turing.util.Pager;

import java.util.List;

public interface ProductService {
    /** 根据商品名查询是否存在相同商品 */
    Product selectByProductName(Product product);

    /**添加商品*/
    int addSomeOne(Product product);

    /**查询商品信息*/
    List<Product> getAllProducts();

    /**获取展示分页信息*/
    Pager<Product> getProductByPage(Integer pageNum);

    /**根据商品Id查询商品信息*/
    Product selectProductInfoById(Product product);

    /** 根据商品优惠信息查询是否是优惠产品 */
    List<Product> selectProductInfoByGoodNum();

    /**根据商品Id删除商品信息*/
    int delProductInfoById(Product product);

    /** 根据商品Id修改商品信息 */
    int updateProduct(Product product);

    /** 根据商品的二级目录删除商品信息（管理员删除一个二级目录时要将二级目录下的所有物品删除） */
    int delProductByChildId(Product product);

    /** 根据商品的父节点删除商品信息（管理员删除一级目录时要将二级目录和一级目录下所有的武平删除） */
    int delProductByCId(Product product);

    /**根据当前节点查询对应节点的信息*/
    List<Product> countProductByCond(Integer cId, Integer ccId);

    /**计算分页*/
    Pager<Product> queryProductByPageByCond(Integer pageNo, int pageSize, Integer cid, Integer ccid);

    /**检查库存是否充足*/
    List<Product> checkStockProducts();
}
