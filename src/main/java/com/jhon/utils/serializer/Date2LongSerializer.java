package com.jhon.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * <p>功能描述</br> Json序列化的时候，将时间转换为数字 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName Date2LongSerializer
 * @date 2017/9/14 14:46
 */
public class Date2LongSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
		jsonGenerator.writeNumber(date.getTime()/1000);
	}
}
