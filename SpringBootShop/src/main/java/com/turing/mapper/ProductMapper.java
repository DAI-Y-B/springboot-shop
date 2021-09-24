package com.turing.mapper;

import com.turing.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    /** 根据商品名查询是否存在相同商品 */
    @Select("select * from product where name = #{name}")
    // @Results(id = "replyAndUsers", value = { @Result(column = "reply_id",
    // property = "replyId"),
    // @Result(column = "eu_user_id", property = "euUserId"),
    // @Result(column = "eu_user_name", property = "euUserName"),
    // @Result(column = "eu_name", property = "euName"),
    // @Result(column = "eu_password", property = "euPassword"),
    // @Result(column = "eu_sex", property = "euSex"),
    // @Result(column = "eu_identity_code", property = "euIdentityCode"),
    // @Result(column = "eu_mobile", property = "euMobile"),
    // @Result(column = "eu_address", property = "euAddress"),
    // @Result(column = "eu_money", property = "euMoney"),
    // @Result(column = "eu_status", property = "euStatus"),
    // @Result(column = "eu_status", property = "roles", many = @Many(fetchType
    // = FetchType.LAZY, select =
    // "com.turing.mapper.RoleMapper.selectByUserStatus")), })
    Product selectByProductName(Product product);

    /** 添加商品 */
    @Insert("insert into product values(null,#{name},#{description},#{price},#{stock},#{brand},#{barcode},#{cId},#{cChildId},#{fileName},#{goodnum})")
    @Options(useGeneratedKeys = true)
    // 自动增长列
    int addSomeOne(Product product);

    /** 查询商品信息 */
    @Select("select * from product")
    List<Product> getAllProducts();

    /** 分页查询相关商品信息 */
    @Select("select * from product limit #{pageNum},#{pageSize}")
    List<Product> selectProductByPage(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    /** 根据商品Id查询商品信息 */
    @Select("select * from product where id = #{id}")
    Product selectProductInfoById(Product product);

    /** 根据商品优惠信息查询是否是优惠产品 */
    @Select("select * from product where goodnum = 1")
    List<Product> selectProductInfoByGoodNum();

    /** 根据商品Id删除商品信息 */
    @Delete("delete from product where id =#{id}")
    int delProductInfoById(Product product);

    /** 根据商品Id修改商品信息 */
    @Update("update product set name=#{name}, description=#{description}, price=#{price}, stock=#{stock}, brand=#{brand}, "
            + "barcode=#{barcode}, c_id=#{cId}, c_child_id=#{cChildId}, file_name=#{fileName}, goodnum=#{goodnum} where id=#{id}")
    int updateProduct(Product product);

    /** 根据商品的二级目录删除商品信息（管理员删除一个二级目录时要将二级目录下的所有物品删除） */
    @Delete("delete from product where c_child_id =#{cChildId}")
    int delProductByChildId(Product product);

    /** 根据商品的父节点删除商品信息（管理员删除一级目录时要将二级目录和一级目录下所有的武平删除） */
    @Delete("delete from product where c_id =#{cId}")
    int delProductByCId(Product product);

    //动态sql的编写
    @Select("<script>"
            + "select * from product where 1 = 1"
            + "<if test=\"cId != null\">"
            + "		and c_id = #{cId}"
            + "</if>"
            + "<if test=\"ccId != null\">"
            + "		and c_child_id=#{ccId}"
            + "</if>"
            + "</script>")
//	@Results(id = "pageProduct", value = {
//        @Result(property = "cId",  column = "c_id"),
//        @Result(property = "ccId", column = "c_child_id"),
//	})
    List<Product> countProductByCond(@Param("cId") Integer cId, @Param("ccId") Integer ccId);

    //动态sql的编写
    @Select("<script>"
            + "select * from product where 1 = 1"
            + "<if test=\"cid != null\">"
            + "		and c_id = #{cid}"
            + "</if>"
            + "<if test=\"pid != null\">"
            + "		and c_child_id=#{pid}"
            + "</if>"
            + " order by id desc "
            + " limit #{pageNo} , #{pageSize} "
            + "</script>")
//	@ResultMap(value = "pageProduct")
    List<Product> selectProductByCond(@Param("pageNo") Integer pageNo, @Param("pageSize") int pageSize, @Param("cid") Integer cid, @Param("pid") Integer pid);

    /**检查库存是否充足*/
    @Select("select * from product where stock < 5 ")
    List<Product> checkStockProducts();
}

// product
// id int not null primary key auto_increment,/* 商品编号 */
// name varchar(100) not null,/* 商品名称 */
// description varchar(100),/* 商品描述 */
// price decimal(12,2) not null,/* 商品价格 */
// stock int not null,/* 库存 */
// brand varchar(20) not null,/* 品牌 */
// barcode varchar(30)not null,/* 商品编码 */
// c_id int,/* 一级分类 */
// c_child_id int,/* 二级分类 */
// file_name varchar(200)/* 文件名称（上传图片） */
//
// id int not null primary key autoIncrement,/* 商品编号 */
// name varchar(100) not null,/* 商品名称 */
// description varchar(100),/* 商品描述 */
// price decimal(12,2) not null,/* 商品价格 */
// stock int not null,/* 库存 */
// brand varchar(20) not null,/* 品牌 */
// barcode varchar(30)not null,/* 商品编码 */
// cId int,/* 一级分类 */
// cChildId int,/* 二级分类 */
// fileName varchar(200)/* 文件名称（上传图片） */
