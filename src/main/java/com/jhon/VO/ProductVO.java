package com.jhon.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>功能描述</br> 商品(包含类目)</p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ProductVO
 * @date 2017/9/15 9:02
 */
@Data
public class ProductVO implements Serializable {

	private static final long serialVersionUID = -8749132370127887501L;

	@JsonProperty("name")
	private String categoryName;

	@JsonProperty("type")
	private Integer categoryType;

	@JsonProperty("foods")
	private List<ProductInfoVO> productInfoVoList;
}
