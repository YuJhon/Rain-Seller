package com.jhon.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>功能描述</br> 卖家信息  </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SellerInfoDO
 * @date 2017/9/14 11:31
 */
@Data
@Entity
@Table(name = "t_seller_info")
public class SellerInfoDO {

	@Id
	private String sellerId;

	private String username;

	private String password;

	private String openid;
}
