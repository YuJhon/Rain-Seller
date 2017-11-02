package com.jhon.form;

import lombok.Data;

/**
 * <p>功能描述</br> 商品类目表单对象 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName CategoryForm
 * @date 2017/9/15 10:00
 */
@Data
public class CategoryForm {

	/** 类目Id **/
	private Integer categoryId;

	/** 类目名称 **/
	private String categoryName;

	/** 类目编号 **/
	private Integer categoryType;
}
