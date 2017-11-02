package com.jhon.controller;

import com.jhon.entity.ProductCategoryDO;
import com.jhon.entity.ProductInfoDO;
import com.jhon.form.ProductForm;
import com.jhon.service.ProductCategoryService;
import com.jhon.service.ProductInfoService;
import com.jhon.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>功能描述</br> 卖家端商品 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SellerProductController
 * @date 2017/9/15 10:29
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

	@Autowired
	private ProductInfoService productInfoService;

	@Autowired
	private ProductCategoryService productCategoryService;

	/**
	 * 商品信息列表
	 *
	 * @param page 当前页码
	 * @param size 每页大小
	 * @param map  返回的参数
	 * @return
	 */
	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
	                         @RequestParam(value = "size", defaultValue = "10") Integer size,
	                         Map<String, Object> map) {
		PageRequest pageRequest = new PageRequest(page - 1, size);
		Page<ProductInfoDO> productInfoPage = productInfoService.findAll(pageRequest);
		map.put("productInfoPage", productInfoPage);
		map.put("currentPage", page);
		map.put("size", size);
		return new ModelAndView("product/list", map);
	}

	/**
	 * 上架
	 *
	 * @param productId 商品Id
	 * @param map       返回结果
	 * @return
	 */
	@RequestMapping("/on_sale")
	public ModelAndView onSale(@RequestParam("productId") String productId,
	                           Map<String, Object> map) {
		try {
			productInfoService.onSale(productId);
		} catch (Exception e) {
			map.put("msg", e.getMessage());
			map.put("url", "/seller/product/list");
			return new ModelAndView("common/error", map);
		}
		map.put("url", "/seller/product/list");
		return new ModelAndView("common/success", map);
	}


	/**
	 * 下架
	 *
	 * @param productId 商品Id
	 * @param map       返回结果
	 * @return
	 */
	@RequestMapping("/off_sale")
	public ModelAndView offSale(@RequestParam("productId") String productId,
	                            Map<String, Object> map) {
		try {
			productInfoService.offSale(productId);
		} catch (Exception e) {
			map.put("msg", e.getMessage());
			map.put("url", "/seller/product/list");
			return new ModelAndView("common/error", map);
		}
		map.put("url", "/seller/product/list");
		return new ModelAndView("common/success", map);
	}

	/**
	 * 查询商品信息
	 * @param productId 产品Id
	 * @param map 返回信息
	 * @return
	 */
	@GetMapping("/index")
	public ModelAndView index(@RequestParam(value="productId",required = false) String productId,
	                          Map<String,Object> map){
		if(!StringUtils.isEmpty(productId)){
			ProductInfoDO productInfoDO = productInfoService.findOne(productId);
			map.put("productInfo",productInfoDO);
		}

		/** 查询所有的类目 **/
		List<ProductCategoryDO> categoryList = productCategoryService.findAll();
		map.put("categoryList",categoryList);

		return new ModelAndView("product/index",map);
	}

	/**
	 * 创建或更新商品信息
	 * @param form 商品提交的表单信息
	 * @param bindingResult 绑定的处理结果
	 * @param map 返回的参数容器
	 * @return
	 */
	@PostMapping("/save")
	public ModelAndView save(@Valid ProductForm form,
	                         BindingResult bindingResult,
	                         Map<String,Object> map){
		if(bindingResult.hasErrors()){
			map.put("msg",bindingResult.getFieldError().getDefaultMessage());
			map.put("url","/seller/product/list");
			return new ModelAndView("common/error",map);
		}
		ProductInfoDO productInfoDO = new ProductInfoDO();
		try{
			if(!StringUtils.isEmpty(form.getProductId())){
				productInfoDO = productInfoService.findOne(form.getProductId());
			}else{
				form.setProductId(KeyUtil.getUniqueKey());
			}
			BeanUtils.copyProperties(form,productInfoDO);
			productInfoService.save(productInfoDO);
		}catch(Exception e){
			map.put("msg",e.getMessage());
			map.put("url","/seller/product/list");
			return new ModelAndView("common/error",map);
		}
		map.put("url","/seller/product/list");
		return new ModelAndView("common/success",map);
	}
}
