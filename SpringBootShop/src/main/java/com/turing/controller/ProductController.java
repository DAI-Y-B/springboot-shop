package com.turing.controller;

import com.turing.entity.Category;
import com.turing.entity.Product;
import com.turing.service.CarService;
import com.turing.service.CategoryService;
import com.turing.service.ProductService;
import com.turing.util.Pager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
    Logger logger = Logger.getLogger(ProductController.class);

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/cateNameCheck")
    public void cateNameCheck(@RequestParam("className") String className, HttpServletResponse response,
                              HttpSession session) throws Exception {
        logger.info("调用ProductController类的cateNameCheck方法,查询是否存在相同的商品名字");
        Category categoryInfo = new Category();
        categoryInfo.setcName(className);
        categoryInfo = categoryService.queryProCateByName(categoryInfo);
        PrintWriter out = response.getWriter();
        out.write(JSONObject.fromObject(categoryInfo).toString());
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/product")
    public void product(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        List<Product> products = productService.getAllProducts();
        PrintWriter out = response.getWriter();
        out.write(JSONArray.fromObject(products).toString());
        out.flush();
        out.close();
    }

    // 今日特价
    @RequestMapping(value = "/product1")
    public void product1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        List<Product> products = productService.selectProductInfoByGoodNum();
        PrintWriter out = response.getWriter();
        out.write(JSONArray.fromObject(products).toString());
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/rootCategoryQuery")
    public void rootCategoryQuery(HttpServletResponse response, HttpServletRequest request, HttpSession session)
            throws IOException {
        System.out.println("rootCategoryQuery---------------------------------------");
        logger.info("调用rootCategoryQuery方法,查询所有的商品一级目录（没有父节点的词条）");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        List<Category> proCategories = categoryService.queryRootCategory();
        PrintWriter out = response.getWriter();
        out.write(JSONArray.fromObject(proCategories).toString());
        System.out.println("rootCategoryQuery---------------------------------------");
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/productCategoryAdd")
    public String productCategoryAdd(@RequestParam("className") String className,
                                     @RequestParam("parentId") String parentId, HttpServletRequest request, HttpServletResponse response,
                                     HttpSession session) {
        logger.info("调用productCategoryAdd方法,添加商品目录");
        Category proCategory = new Category();
        proCategory.setcName(className);
        proCategory.setcParentId(Integer.parseInt(parentId));
        if (categoryService.queryProCateByName(proCategory) != null) {
            session.setAttribute("resultCode", "商品种类已存在");
            return "manage/productClass-add";
        }
        if (categoryService.addProduct(proCategory) == 1) {
            return "manage/productClass";
        } else {
            session.setAttribute("resultCode", "商品种类添加失败");
            return "manage/productClass-add";
        }
    }

    @RequestMapping(value = "/proCateQueryByParenId")
    public void proCateQueryByParenId(@RequestParam("parentId") String parentId, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        System.out.println("proCateQueryByParenId-----------------------------------------------------------");
        logger.info("调用proCateQueryByParenId方法,查询对应父目录下的商品分类");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Category proSomeCategory = new Category();
        proSomeCategory.setcParentId(Integer.parseInt(parentId));
        List<Category> productCategories = categoryService.queryProCateByParenId(proSomeCategory);
        PrintWriter out = response.getWriter();
        out.write(JSONArray.fromObject(productCategories).toString());
        System.out.println("proCateQueryByParenId-----------------------------------------------------------");
        out.flush();
        out.close();
    }

    // 查找到相对应的内容，显示出来
    @RequestMapping(value = "/productCategoryFind")
    public void productCategoryFind(@RequestParam("cId") String cId, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        System.out.println("productCategoryFind-----------------------------------------------------------");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        Category idCategory = new Category();
        idCategory.setcId(Integer.parseInt(cId));
        idCategory = categoryService.queryProCateById(idCategory);
        System.out.println("productCategoryFind-------------idCategory" + idCategory);
        PrintWriter out = response.getWriter();
        out.write(JSONObject.fromObject(idCategory).toString());
        out.flush();
        out.close();
    }

    // 修改对应的商品目录
    @RequestMapping(value = "/productCategoryEdit")
    public String productCategoryEdit(@RequestParam("cId") String cId, @RequestParam("className") String className,
                                      @RequestParam("parentId") String parentId, HttpServletRequest request, HttpServletResponse response,
                                      HttpSession session) throws Exception {
        logger.info("调用productCategoryEdit方法,修改对应的商品目录");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        Category cateInfo = new Category();
        cateInfo.setcId(Integer.parseInt(cId));
        cateInfo.setcName(className);
        cateInfo.setcParentId(Integer.parseInt(parentId));
        int i = categoryService.updateCategory(cateInfo);
        if (i == 1) {
            return "manage/productClass";
        } else {
            session.setAttribute("resultCode", "修改商品种类失败");
            return "manage/productClass-modify";
        }
    }

    // 删除库存管理中的商品目录
    @RequestMapping(value = "/productCategoryDelete")
    public String productCategoryDelete(@RequestParam("id") Integer id, HttpServletRequest request,
                                        HttpServletResponse response, HttpSession session) throws Exception {
        Product product = new Product();
        Category oldInfoCategory = new Category();
        oldInfoCategory.setcId(id);
        oldInfoCategory = categoryService.queryProCateById(oldInfoCategory);
        System.out.println("productCategoryDelete--------------------------oldInfoCategory" + oldInfoCategory);
        // 当ParentId!=0时说明这是一个二级目录，删除时要删除对应目录下的商品
        if (oldInfoCategory.getcParentId() != 0) {
            categoryService.deleteById(oldInfoCategory);
            product.setcChildId(id);
            productService.delProductByChildId(product);
            return "manage/productClass";
        }
        // 当ParentId==0时，说明这是一个一级目录，要把所有的信息都删除
        else if (oldInfoCategory.getcParentId() == 0) {
            System.out.println(
                    "============================================================================================================");
            System.out.println("oldInfoCategory.getcParentId()" + oldInfoCategory.getcParentId());
            Category parIdCategory = new Category();
            parIdCategory.setcId(id);
            System.out.println("parIdCategory" + parIdCategory);
            System.out.println("id" + id);
            System.out.println(
                    "============================================================================================================");
            categoryService.deleteById(parIdCategory);
            categoryService.deleteByParentId(parIdCategory.getcId());
            product.setcId(oldInfoCategory.getcId());
            productService.delProductByCId(product);
            return "manage/productClass";
        }
        return "manage/productClass";
    }

    // 查找出对应的全部商品，并显示出来
    @RequestMapping(value = "/queryProductByName") // 可以没有返回值，此处只是ajax的验证存不存在相同商品
    public void queryProductByName(@RequestParam("productName") String productName, HttpServletRequest request,
                                   HttpServletResponse response, HttpSession session) throws IOException, ServletException {
        logger.info("调用queryProductByName方法,查看是否存在相同商品名称的商品");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        Product proInfo = new Product();
        proInfo.setName(productName);
        Product product = productService.selectByProductName(proInfo);
        System.out.println(product.getId());
        PrintWriter out = response.getWriter();
        out.write(JSONObject.fromObject(product).toString());
        out.flush();
        out.close();
    }

    // 添加商品
    @RequestMapping(value = "/productAdd")
    public String productAdd(@RequestParam("productName") String productName, @RequestParam("parentId") String parentId,
                             @RequestParam("photo") MultipartFile photo, @RequestParam("productPrice") Double productPrice,
                             @RequestParam("productBrand") String productBrand, @RequestParam("productStock") String productStock,
                             @RequestParam("productBarcode") String productBarcode, @RequestParam("productDes") String productDes,
                             HttpSession session) throws Exception {
        logger.info("调用productAdd方法,添加商品");

        String name = UUID.randomUUID().toString().replaceAll("-", "");// 防止重名，重新修改名字
        String ext = FilenameUtils.getExtension(photo.getOriginalFilename());// 获取图片格式
        photo.transferTo(new File("D:\\upload\\" + name + "." + ext));// 图片保存路径
        String newFileName = name + "." + ext;

        // 根据外界输入的id值查询存在的目录信息
        Category checkParent = new Category();
        checkParent.setcId(Integer.parseInt(parentId));
        checkParent = categoryService.queryProCateById(checkParent);
        System.out.println("productAdd-------------------------------checkParent" + checkParent);

        Product proInfoProduct = new Product();
        // 将查询到的目录等级信息传到服务器中
        if (checkParent.getcParentId() == 0) {
            proInfoProduct.setcId(Integer.parseInt(parentId));
            proInfoProduct.setcChildId(0);
        } else {
            proInfoProduct.setcId(checkParent.getcParentId());
            proInfoProduct.setcChildId(checkParent.getcId());
        }
        proInfoProduct.setName(productName);
        proInfoProduct.setFileName(newFileName);
        proInfoProduct.setPrice(productPrice);
        proInfoProduct.setBrand(productBrand);
        proInfoProduct.setStock(Integer.parseInt(productStock));
        proInfoProduct.setBarcode(productBarcode);
        proInfoProduct.setDescription(productDes);
        System.out.println("productAdd-------------------------parentId" + parentId);
        System.out.println("productAdd------------------------proInfoProduct" + proInfoProduct);

        int i = productService.addSomeOne(proInfoProduct);
        if (i == 1) {
            session.setAttribute("result", "商品添加成功");
            return "manage/product";
        } else {
            session.setAttribute("result", "商品添加失败");
            return "manage/productClass-add";
        }
    }

    // 查询商品信息
    @RequestMapping(value = "/queryProductByPage")
    public void queryProductByPage(@RequestParam("pageNum") String pageNum, HttpServletRequest request,
                                   HttpServletResponse response, HttpSession session) throws Exception {
        logger.info("调用queryProductByPage方法,查询商品信息");
        List<Product> totalNum = productService.getAllProducts();
        for (Product product : totalNum) {
            System.out.println("totalNum---------product" + product);
        }
        int size = 4;
        int totalPage = totalNum.size() % size == 0 ? totalNum.size() / size : totalNum.size() / size + 1;
        int pageNum1 = 1;
        if (pageNum != null) {
            pageNum1 = Integer.parseInt(pageNum);
            pageNum1 = pageNum1 <= 1 ? 1 : pageNum1;
            pageNum1 = pageNum1 >= totalPage ? totalPage : pageNum1;
            System.out.println("看看到底是第几页" + pageNum1);
        }
        Pager<Product> pager;
        pager = productService.getProductByPage(pageNum1);
        List<Product> products = new ArrayList<Product>();
        products = pager.getData();
        for (Product product : products) {
            System.out.println("queryProductByPage-----------------------+product" + product);
        }
        PrintWriter out = response.getWriter();
        String json = JSONObject.fromObject(pager).toString();
        out.write(json);
        out.flush();
        out.close();
    }

    // 查询相对应的页数变化
    @RequestMapping(value = "/getProductByPage")
    public void getProductByPage(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws Exception {
        logger.info("调用getProductByPage方法,查询商品信息");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        String pageNo = request.getParameter("pageNum");
        String parentId = request.getParameter("id");
        Integer totalNum;
        Integer totalPage;
        Integer pageNum = 1;
        List<Product> countList;
        Integer pId;
        if (parentId != null) {
            pId = Integer.parseInt(parentId);
        } else {
            pId = null;
        }
        Pager<Product> pager = null;
        Boolean f = false;
        Category recCategory = new Category();
        recCategory.setcId(pId);
        Category productCategory = categoryService.queryProCateById(recCategory);
        List<Category> categories = categoryService.queryRootCategory();
        if (productCategory != null) {
            for (Category category : categories) {
                if (category.getcId() == productCategory.getcParentId()) {
                    f = true;
                }
            }
        }
        if (f) {
            countList = productService.countProductByCond(productCategory.getcId(), 0);
            totalNum = countList.size();
            totalPage = (totalNum % 4) == 0 ? totalNum / 4 : (totalNum / 4) + 1;
            if (pageNo != null) {//
                pageNum = Integer.parseInt(pageNo);
                pageNum = pageNum <= 1 ? 1 : pageNum;
                pageNum = pageNum >= totalPage ? totalPage : pageNum;
                if (pageNum == 0) {
                    pageNum = 1;
                }
            }
            pager = productService.queryProductByPageByCond(pageNum, 4, productCategory.getcParentId(), pId);
        }
        PrintWriter out = response.getWriter();
        String json = JSONObject.fromObject(pager).toString();
        out.write(json);
        out.flush();
        out.close();
    }

    // 删除商品信息
    @RequestMapping(value = "/productDelete")
    public String productDelete(@RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response,
                                HttpSession session) {
        logger.info("调用queryProductByPage方法,删除商品信息");
        Product InfoProduct = new Product();
        InfoProduct.setId(id);
        InfoProduct = productService.selectProductInfoById(InfoProduct);
        System.out.println("productDelete----------------------InfoProduct" + InfoProduct);
        if (InfoProduct == null) {
            session.setAttribute("resultCode", "商品不存在");// 删除失败
            return "manage/product";
        } else {
            productService.delProductInfoById(InfoProduct);
            return "manage/product";
        }
    }

    // 修改信息前先查询出相关信息
    String record;

    @RequestMapping(value = "/productFind")
    public void productFind(@RequestParam("id") Integer id, HttpServletRequest request, HttpServletResponse response,
                            HttpSession session) throws Exception {
        logger.info("调用queryProductByPage方法,修改信息前先查询出相关信息");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        System.out.println("productFind---------------------------------id" + id);
        Product product = new Product();
        product.setId(id);
        // 查出相关信息
        product = productService.selectProductInfoById(product);
        System.out.println("productFind---------------------------------product" + product);
        System.out.println("productFind---------------------------------product.getId()" + product.getId());
        if (record == null) {
            record = id.toString();
        } else {
            Boolean f = false;
            String[] temp = record.split(",");
            if (temp.length <= 5) {
                for (int i = 0; i < temp.length; i++) {
                    if (temp[i].equals(id.toString())) {
                        f = true;
                    }
                }
                if (f) {
                    record = id.toString();
                    for (int i = 0; i < temp.length; i++) {
                        if (temp[i].equals(id.toString())) {
                            continue;
                        } else {
                            record += "," + temp[i];
                            System.out.println("record1111111111111111111111111111111111111111111111" + record);
                        }
                    }
                }
            } else {
                for (int i = temp.length - 5; i < temp.length; i++) {
                    if (temp[i].equals(id.toString())) {
                        f = true;
                    }
                }
                if (f) {
                    record = id.toString();
                    for (int i = temp.length - 5; i < temp.length; i++) {
                        if (temp[i].equals(id.toString())) {
                            continue;
                        } else {
                            record += "," + temp[i];
                        }
                    }
                }
            }
            if (!f) {
                record = id.toString() + "," + record;
            }

            System.out.println("f" + f);
        }
        PrintWriter out = response.getWriter();
        out.write(JSONObject.fromObject(product).toString());
        out.flush();
        out.close();
    }

    // 修改商品
    @RequestMapping(value = "/productEdit")
    public String productEdit(@RequestParam("id") Integer id, @RequestParam("productName") String productName,
                              @RequestParam("parentId") String parentId, @RequestParam("photo") MultipartFile photo,
                              @RequestParam("productPrice") Double productPrice, @RequestParam("productBrand") String productBrand,
                              @RequestParam("productStock") String productStock, @RequestParam("productBarcode") String productBarcode,
                              @RequestParam("productDes") String productDes, @RequestParam("goodNum") Integer goodNum,
                              HttpSession session) throws Exception {
        logger.info("调用productAdd方法,添加商品");

        String name = UUID.randomUUID().toString().replaceAll("-", "");// 防止重名，重新修改名字
        String ext = FilenameUtils.getExtension(photo.getOriginalFilename());// 获取图片格式
        photo.transferTo(new File("D:\\upload\\" + name + "." + ext));// 图片保存路径
        String newFileName = name + "." + ext;

        // 查询原始数据
        Product oldInfoProduct = new Product();
        oldInfoProduct.setId(id);
        oldInfoProduct = productService.selectProductInfoById(oldInfoProduct);
        System.out.println("productEdit-------------------------------oldInfoProduct" + oldInfoProduct);

        // 根据外界输入的id值查询存在的目录信息
        Category checkParent = new Category();
        checkParent.setcId(Integer.parseInt(parentId));
        checkParent = categoryService.queryProCateById(checkParent);
        System.out.println("productEdit-------------------------------checkParent" + checkParent);

        Product proInfoProduct = new Product();
        proInfoProduct.setId(id);
        // 将查询到的目录等级信息传到服务器中
        if (checkParent.getcParentId() == 0) {
            proInfoProduct.setcId(Integer.parseInt(parentId));
            proInfoProduct.setcChildId(0);
        } else {
            proInfoProduct.setcId(checkParent.getcParentId());
            proInfoProduct.setcChildId(checkParent.getcId());
        }

        if (photo.isEmpty()) {
            System.out.println("productEdit-------------------------photo1：" + photo);
            proInfoProduct.setFileName(oldInfoProduct.getFileName());
            System.out.println(
                    "productEdit-------------------------oldInfoProduct.getFileName()" + oldInfoProduct.getFileName());
        } else {
            System.out.println("productEdit-------------------------photo2：" + photo);
            proInfoProduct.setFileName(newFileName);
            System.out.println("productEdit-------------------------newFileName：" + newFileName);
        }
        proInfoProduct.setName(productName);
        proInfoProduct.setPrice(productPrice);
        proInfoProduct.setBrand(productBrand);
        proInfoProduct.setStock(Integer.parseInt(productStock));
        proInfoProduct.setBarcode(productBarcode);
        proInfoProduct.setDescription(productDes);
        proInfoProduct.setGoodnum(goodNum);
        System.out.println("productEdit-------------------------parentId" + parentId);
        System.out.println("productEdit------------------------proInfoProduct" + proInfoProduct);

        int i = productService.updateProduct(proInfoProduct);
        if (i == 1) {
            session.setAttribute("result", "商品修改成功");
            return "manage/product";
        } else {
            session.setAttribute("result", "商品修改失败");
            return "manage/product-modify";
        }
    }

    // 检查库存是否充足
    @RequestMapping(value = "/checkStock")
    public String checkStock(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws Exception {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        List<Product> cheStock = productService.checkStockProducts();
        boolean isStock = false;
        for (Product product : cheStock) {
            System.out.println("checkStock---------------------" + product);
            if (product != null) {
                isStock = true;
            }
        }
        if (isStock) {
            out = response.getWriter();
            out.print("<script>alert('管理员商品，存在商品库存数量小于5，请及时添加！');" +
                    // "location.href='user/carInfo'"+
                    "</script>");
            out.flush();
        }
        return "manage/product";
    }
    // //检查库存是否充足
    // @RequestMapping(value =
    // "/checkStock",consumes="application/json;charset=utf-8",
    // produces="application/json;charset=utf-8")
    // @ResponseBody
    // public Mymessage checkStock(HttpSession session) {
    // List<Product> cheStock = productService.checkStockProducts();
    // Mymessage msg =new Mymessage();
    // if(cheStock==null){
    // msg.setMsg("商品数量充足");
    // }else {
    // session.setAttribute("cheStock",cheStock);
    // msg.setObj(cheStock);
    // }
    // return msg;
    // }
}

/*






























 */