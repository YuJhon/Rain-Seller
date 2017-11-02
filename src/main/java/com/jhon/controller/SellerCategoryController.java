package com.jhon.controller;

import com.jhon.entity.ProductCategoryDO;
import com.jhon.form.CategoryForm;
import com.jhon.service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * <p>功能描述</br> 卖家类目控制器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SellerCategoryController
 * @date 2017/9/15 9:49
 */
@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

	@Autowired
	private ProductCategoryService categoryService;

	/**
	 * 查询所有的类目列表
	 * @param map
	 * @return
	 */
	@GetMapping("/list")
	public ModelAndView list(Map<String,Object> map){
		List<ProductCategoryDO> categoryList = categoryService.findAll();
		map.put("categoryList",categoryList);
		return new ModelAndView("/category/list",map);
	}

	/**
	 * 通过Id查询对应的类目
	 * @param categoryId 类目Id
	 * @param map 返回的参数
	 * @return 视图和数据
	 */
	@GetMapping("/index")
	public ModelAndView index(@RequestParam(value="categoryId",required = false) Integer categoryId,
	                          Map<String,Object> map){
		if(categoryId != null){
			ProductCategoryDO productCategory = categoryService.findOne(categoryId);
			map.put("category",productCategory);
		}
		return new ModelAndView("/category/index",map);
	}

	/**
	 * 保存/更新类目
	 * @param form 类目的表单
	 * @param bindingResult 绑定结果
	 * @param map 参数信息
	 * @return
	 */
	@PostMapping("/save")
	public ModelAndView save(@Valid CategoryForm form,
	                         BindingResult bindingResult,
	                         Map<String,Object> map){
		if(bindingResult.hasErrors()){
			map.put("msg",bindingResult.getFieldError().getDefaultMessage());
			map.put("url","/seller/category/index");
			return new ModelAndView("common/error",map);
		}
		ProductCategoryDO productCategoryDO = new ProductCategoryDO();
		try{
			if(form.getCategoryId() != null){
				productCategoryDO = categoryService.findOne(form.getCategoryId());
			}
			BeanUtils.copyProperties(form,productCategoryDO);
			categoryService.save(productCategoryDO);
		}catch(Exception e){
			map.put("msg",e.getMessage());
			map.put("url","/seller/category/index");
			return new ModelAndView("common/success",map);
		}

		map.put("url", "/seller/category/list");
		return new ModelAndView("common/success", map);
	}
}
