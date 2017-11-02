package com.jhon.service;

import com.jhon.entity.SellerInfoDO;

/**
 * <p>功能描述</br> 卖家登录的处理 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SellerService
 * @date 2017/9/15 14:33
 */
public interface SellerService {

	/**
	 * 通过openid查询卖家信息
	 * @param openid
	 * @return
	 */
	SellerInfoDO findSellerInfoByOpenid(String openid);
}
