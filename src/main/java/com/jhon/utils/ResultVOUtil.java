package com.jhon.utils;

import com.jhon.VO.ResultVO;

/**
 * <p>功能描述</br> 前端返回数据工具类 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName ResultVOUtil
 * @date 2017/9/14 17:39
 */
public class ResultVOUtil {

	/**
	 * 处理成功
	 * @param obj
	 * @return
	 */
	public static ResultVO success(Object obj){
		ResultVO resultVO = new ResultVO();
		resultVO.setData(obj);
		resultVO.setCode(0);
		resultVO.setMsg("成功");
		return resultVO;
	}

	/**
	 * 成功处理
	 * @return
	 */
	public static ResultVO success(){
		return success(null);
	}

	/**
	 * 错误处理
	 * @param code
	 * @param msg
	 * @return
	 */
	public static ResultVO error(Integer code, String msg){
		ResultVO resultVO = new ResultVO();
		resultVO.setCode(code);
		resultVO.setMsg(msg);
		return resultVO;
	}

}
