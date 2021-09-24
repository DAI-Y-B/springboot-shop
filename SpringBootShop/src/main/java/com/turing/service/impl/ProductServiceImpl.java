package com.turing.service.impl;

import com.turing.entity.Product;
import com.turing.mapper.ProductMapper;
import com.turing.service.ProductService;
import com.turing.util.Pager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    // 日志
    Logger logger = Logger.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductMapper productMapper;

    public Product selectByProductName(Product product) {
        logger.info("调用ProductServiceImpl类的selectByProductName方法,查询商品信息，查看是否存在重复的商品");
        Product proInfoByName = productMapper.selectByProductName(product);
        return proInfoByName;
    }

    public int addSomeOne(Product product) {
        logger.info("调用ProductServiceImpl类的addSomeOne方法,添加商品");
        return productMapper.addSomeOne(product);
    }

    public List<Product> getAllProducts() {
        logger.info("调用ProductServiceImpl类的getAllProducts方法,获取全部的商品信息");
        List<Product> productInfo = productMapper.getAllProducts();
        return productInfo;
    }

    public Pager<Product> getProductByPage(Integer pageNum) {
        logger.info("调用ProductServiceImpl类的getProductByPage方法,进行分页计算");
        Pager<Product> pager = new Pager<Product>();
        pager.setPageNum(pageNum);
        Integer totalCount = productMapper.getAllProducts().size();
        if (totalCount == 0) {
            totalCount = 0;
        }
        pager.setTotalPage(totalCount, 4);
        Integer pageSize = 4;
        Integer countEnd = (pageNum - 1) * pageSize;
        List<Product> products = productMapper.selectProductByPage(countEnd, pageSize);
        pager.setData(products);
        return pager;
    }

    public Product selectProductInfoById(Product product) {
        logger.info("调用ProductServiceImpl类的selectProductInfoById方法,根据商品的id查询商品信息");
        Product proInfo = productMapper.selectProductInfoById(product);
        return proInfo;
    }

    public List<Product> selectProductInfoByGoodNum() {
        logger.info("调用ProductServiceImpl类的selectProductInfoById方法,根据商品优惠信息查询是否是优惠产品");
        List<Product> proInfo = productMapper.selectProductInfoByGoodNum();
        return proInfo;
    }

    public int delProductInfoById(Product product) {
        logger.info("调用ProductServiceImpl类的delProductInfoById方法,根据商品的id删除商品信息");
        return productMapper.delProductInfoById(product);
    }

    public int updateProduct(Product product) {
        logger.info("调用ProductServiceImpl类的updateUser方法,根据商品的id修改商品信息");
        return productMapper.updateProduct(product);
    }

    public int delProductByChildId(Product product) {
        logger.info("调用ProductServiceImpl类的delProductByChildId方法,管理员删除一个二级目录时要将二级目录下的所有物品删除");
        return productMapper.delProductByChildId(product);
    }

    public int delProductByCId(Product product) {
        logger.info("调用ProductServiceImpl类的delProductByCId方法,管理员删除一级目录时要将二级目录和一级目录下所有的武平删除");
        return productMapper.delProductByCId(product);
    }

    public List<Product> countProductByCond(Integer cId, Integer ccId) {
        logger.info("调用ProductServiceImpl类的countProductByCond方法,查询对应节点的信息");
        List<Product> countList = productMapper.countProductByCond(cId, ccId);
        return countList;
    }

    public Pager<Product> queryProductByPageByCond(Integer pageNo, int pageSize, Integer cid, Integer ccid) {
        logger.info("调用ProductServiceImpl类的queryProductByPageByCond方法");
        Pager<Product> pager = new Pager<Product>();
        List<Product> countList;
        countList = productMapper.countProductByCond(cid, ccid);
        pager.setPageNum(pageNo);
        Integer totalCount = countList.size();
        if (totalCount.equals(null)) {
            totalCount = 0;
        }
        pager.setTotalPage(totalCount, 4);
        pageNo = (pageNo - 1) * pageSize;
        List<Product> product = productMapper.selectProductByCond(pageNo, pageSize, cid, ccid);
        pager.setData(product);
        return pager;
    }

    public List<Product> checkStockProducts() {
        logger.info("调用ProductServiceImpl类的checkStockProducts方法,检查库存是否充足");
        List<Product> idStock = productMapper.checkStockProducts();
        return idStock;
    }

}
