package com.jhon.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>功能描述</br> 商品详情 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ProductInfoVO
 * @date 2017/9/15 9:10
 */
@Data
public class ProductInfoVO implements Serializable {

	private static final long serialVersionUID = -8314438954224578605L;

	@JsonProperty("id")
	private String productId;

	@JsonProperty("name")
	private String productName;

	@JsonProperty("price")
	private BigDecimal productPrice;

	@JsonProperty("description")
	private String productDescription;

	@JsonProperty("icon")
	private String productIcon;
}
