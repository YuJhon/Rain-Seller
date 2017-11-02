package com.jhon.controller;

import com.jhon.VO.ProductInfoVO;
import com.jhon.VO.ProductVO;
import com.jhon.VO.ResultVO;
import com.jhon.entity.ProductCategoryDO;
import com.jhon.entity.ProductInfoDO;
import com.jhon.service.ProductCategoryService;
import com.jhon.service.ProductInfoService;
import com.jhon.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>功能描述</br> 买家商品控制器 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName BuyerProductController
 * @date 2017/9/15 8:55
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

	@Autowired
	private ProductInfoService productInfoService;

	@Autowired
	private ProductCategoryService productCategoryService;

	@GetMapping("/list")
	public ResultVO<?> list() {

		/** 1.查询所有上架的商品 **/
		List<ProductInfoDO> productInfoList = productInfoService.findUpAll();

		/** 2.获取类目的类型Ids **/
		List<Integer> categoryTypeList = productInfoList.stream()
				.map(e -> e.getCategoryType())
				.collect(Collectors.toList());
		List<ProductCategoryDO> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

		/** 3.数据的封装 **/
		List<ProductVO> productVOList = new ArrayList<>();

		productCategoryList.forEach(productCategory -> {
			ProductVO productVO = new ProductVO();
			productVO.setCategoryType(productCategory.getCategoryType());
			productVO.setCategoryName(productCategory.getCategoryName());

			List<ProductInfoVO> productInfoVoList = new ArrayList<>();

			productInfoList.forEach(productInfo ->{
				if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
					ProductInfoVO productInfoVO = new ProductInfoVO();
					BeanUtils.copyProperties(productInfo,productInfoVO);
					productInfoVoList.add(productInfoVO);
				}
			});

			/*for(ProductInfoDO productInfo:productInfoList){
				if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
					ProductInfoVO productInfoVO = new ProductInfoVO();
					BeanUtils.copyProperties(productInfo,productInfoVO);
					productInfoVoList.add(productInfoVO);
				}
			}*/

			productVO.setProductInfoVoList(productInfoVoList);
			productVOList.add(productVO);
		});

		/*
		for (ProductCategoryDO productCategory:productCategoryList) {
			ProductVO productVO = new ProductVO();
			productVO.setCategoryType(productCategory.getCategoryType());
			productVO.setCategoryName(productCategory.getCategoryName());

			List<ProductInfoVO> productInfoVoList = new ArrayList<>();
			for(ProductInfoDO productInfo:productInfoList){
				if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
					ProductInfoVO productInfoVO = new ProductInfoVO();
					BeanUtils.copyProperties(productInfo,productInfoVO);
					productInfoVoList.add(productInfoVO);
				}
			}

			productVO.setProductInfoVoList(productInfoVoList);
			productVOList.add(productVO);
		}
		*/

		return ResultVOUtil.success(productVOList);
	}
}
