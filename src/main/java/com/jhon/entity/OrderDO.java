package com.jhon.entity;

import com.jhon.enums.OrderStatusEnum;
import com.jhon.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>功能描述</br> 主订单信息 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName OrderDO
 * @date 2017/9/14 10:15
 */
@Entity
@Data
@DynamicUpdate
@Table(name = "t_order")
public class OrderDO {

	/** 订单id. */
	@Id
	private String orderId;

	/** 买家名字. */
	private String buyerName;

	/** 买家手机号. */
	private String buyerPhone;

	/** 买家地址. */
	private String buyerAddress;

	/** 买家微信Openid. */
	private String buyerOpenId;

	/** 订单总金额. */
	private BigDecimal orderAmount;

	/** 订单状态, 默认为0新下单. */
	private Integer orderStatus = OrderStatusEnum.NEW.getCode();

	/** 支付状态, 默认为0未支付. */
	private Integer payStatus = PayStatusEnum.WAIT.getCode();

	/** 创建时间. */
	private Date createTime;

	/** 更新时间. */
	private Date updateTime;
}
