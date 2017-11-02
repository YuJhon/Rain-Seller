package com.jhon.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jhon.utils.EnumUtil;
import com.jhon.enums.ProductStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>功能描述</br> 商品信息</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ProductInfoDO
 * @date 2017/9/14 11:25
 */
@Entity
@Data
@DynamicUpdate
@Table(name = "t_product_info")
public class ProductInfoDO implements Serializable{

	private static final long serialVersionUID = 5810667988713590461L;
	@Id
	private String productId;

	/** 名字. */
	private String productName;

	/** 单价. */
	private BigDecimal productPrice;

	/** 库存. */
	private Integer productStock;

	/** 描述. */
	private String productDescription;

	/** 小图. */
	private String productIcon;

	/** 状态, 0正常1下架. */
	private Integer productStatus = ProductStatusEnum.UP.getCode();

	/** 类目编号. */
	private Integer categoryType;

	private Date createTime;

	private Date updateTime;

	@JsonIgnore
	public ProductStatusEnum getProductStatusEnum(){
		return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);
	}

}
