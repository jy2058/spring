package kr.or.ddit.ioc.type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringDateConverter implements Converter<String, Date>{
	
	private String datePattern;

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}
	
	@Override
	public Date convert(String source) {
		// source = "2018-08-08"; / 문자열
		// source = "08-08-2018"; // MM-dd-yyyy
		
		// 문자열을 Date 포맷으로 변환
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		Date convertedDate = null;
		
		try {
			convertedDate = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return convertedDate;
	}
}
