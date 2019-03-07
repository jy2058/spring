<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${cp }/js/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function(){
		console.log("ajaxView.jsp");
		
		// jsonData 요청
		$("#jsonReqBtn").on("click", function(){
			requestBody();
		});
	});
	
	function requestBody(){
		var data = { userId : "brown", userNm : "브라운" };
		$.ajax({
			url : "${cp}/ajax/requestBody",
			method : "post",
			//data : "userId=brown&userNm=브라운",
			//data : $("#frm").serialize(), 	// form 태그에 있는 것들을 문자열로 만들어 줌
			data : JSON.stringify(data),	// data를 json 문자열로 전송한다.
			dataType : "json",	// server에게 희망하는 리턴 타입을 명시
			contentType : "application/json; charset=utf-8", 	// client가 전송하는 데이터 타입
			success : function(data){ // 성공했을 때 서버에서 보낸 data가 data에 저장 됨
				// data.rangerList => array
				
				$("#jsonRecvTbody").html("<tr><td>" + data.userId + "</td></tr>");
				
				/* var html = "";
				for(var i = 0; i < data.length; i++){
					html += "<tr><td>" + data[i] + "</td></tr>";
				}
				$("#jsonRecvTbody").append(html); */
			}
		});
	}
	
	function responseBody(){
		$.ajax({
			url : "${cp}/ajax/responseBody",
			mathod : "post",
			dataType : "json",	// server에게 희망하는 리턴 타입을 명시
			success : function(data){ // 성공했을 때 서버에서 보낸 data가 data에 저장 됨
				// data.rangerList => array
				var html = "";
				for(var i = 0; i < data.length; i++){
					html += "<tr><td>" + data[i] + "</td></tr>";
				}
				$("#jsonRecvTbody").append(html);
			}
		});
	}
	
	function jsonView(){
		$.ajax({
			url : "${cp}/ajax/jsonView",
			mathod : "post",
			success : function(data){ // 성공했을 때 서버에서 보낸 data가 data에 저장 됨
				// data.rangerList => array
				console.log("data : " + data);
	
				var html = "";
				for(var i = 0; i < data.rangerList.length; i++){
					html += "<tr><td>" + data.rangerList[i] + "</td></tr>";
				}
				$("#jsonRecvTbody").append(html);
	
	//				$.each(data.rangerList, function(i, d){
	//					html += "<tr><td>" + d + "</td></tr>";
	//				});
	//				$("#jsonRecvTbody").append(html);
				}
			});
	}
</script>
</head>
<body>
	<form id="frm">
		<input type="text" name="userId" value="brown">
		<input type="text" name="userNm" value="브라운">
	</form>

	<h2>ajaxView.jsp</h2>
	<h3>json 수신</h3>
	<div>
		<button id="jsonReqBtn">jsonData 요청</button>
		<div id="jsonRecv">
			<table>
				<thead>
					<tr>
						<th>이름</th>
					</tr>
				</thead>
				<tbody id="jsonRecvTbody">
					
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>