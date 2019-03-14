<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<spring:message code="hello"/> <br>
	
	<!-- 
		----기존 jsp----
		select box를 변경(change)하면 해당 언어로 locale 변경
		<fmt:setLacle "en"
		
		----변경 spring----
		spring interceptor, localeResolver/>
	 -->
	 
	 <form>
		<select id="langSelect" name="language">
			<option value="ko"> 한국어 </option>
			<option value="en"> 영어 </option>
			<option value="ja"> 일본어 </option>
		</select>	<br>
		<input type="submit" value="전송">
	</form>
</body>
</html>