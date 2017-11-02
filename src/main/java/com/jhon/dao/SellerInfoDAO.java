package com.jhon.dao;

import com.jhon.entity.SellerInfoDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>功能描述</br> 卖家信息数据访问层 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName SellerInfoDAO
 * @date 2017/9/14 12:10
 */
public interface SellerInfoDAO extends JpaRepository<SellerInfoDO,String> {

	/**
	 * 根据OpenId查询卖家信息 
	 * @param openid
	 * @return
	 */
	SellerInfoDO findByOpenid(String openid);
}
