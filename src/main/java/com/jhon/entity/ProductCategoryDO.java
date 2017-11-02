package com.jhon.entity;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * <p>功能描述</br> 商品类目实体 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ProductCategoryDO
 * @date 2017/9/14 11:22
 */
@Entity
@Data
@DynamicUpdate
@Table(name="t_product_category")
public class ProductCategoryDO {

	/** 类目id. */
	@Id
	@GeneratedValue
	private Integer categoryId;

	/** 类目名字. */
	private String categoryName;

	/** 类目编号. */
	private Integer categoryType;

	private Date createTime;

	private Date updateTime;

	public ProductCategoryDO() {
	}

	public ProductCategoryDO(String categoryName, Integer categoryType) {
		this.categoryName = categoryName;
		this.categoryType = categoryType;
	}
}
