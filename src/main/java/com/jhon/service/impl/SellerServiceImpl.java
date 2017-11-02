package com.jhon.service.impl;

import com.jhon.dao.SellerInfoDAO;
import com.jhon.entity.SellerInfoDO;
import com.jhon.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>功能描述</br> 卖家业务逻辑接口实现类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SellerServiceImpl
 * @date 2017/9/15 14:37
 */
@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerInfoDAO sellerInfoDAO;


	@Override
	public SellerInfoDO findSellerInfoByOpenid(String openid) {
		return sellerInfoDAO.findByOpenid(openid);
	}
}
