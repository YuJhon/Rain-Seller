package com.jhon.form;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>功能描述</br> 商品类目表单 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ProductForm
 * @date 2017/9/15 11:14
 */
@Data
public class ProductForm {

	private String productId;

	/** 名字 **/
	private String productName;

	/** 单价 **/
	private BigDecimal productPrice;

	/** 库存 **/
	private Integer productStock;

	/** 描述 **/
	private String productDescription;

	/** 小图 **/
	private String productIcon;

	/** 类目编号 **/
	private Integer categoryType;
}
