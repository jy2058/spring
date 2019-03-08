<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

				<h1 class="page-header">전체 사용자 리스트(Ajax Tiles)</h1>
				<c:set var="userList" value="${userList }" scope="request"/>
				
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>사용자 아이디</th>
								<th>사용자 이름</th>
								<th>별명</th>
								<th>등록 일시</th>
							</tr>
						</thead>
						<tbody id="userListTbody">
						
								
						</tbody>
					</table>
					
					<form action="${cp }/user/userForm" method="get">
						<button type="submit" class="btn_btn-default">사용자 등록</button>
					</form>

				</div>

				
				<nav style="text-align:center;">
						<ul id="pagination" class="pagination">
							
						</ul>
				</nav>
					
		<script>
		
		// 사용자가 배열을 이용하여 사용자 리스트 HTML을 생성
		function makeUserList(userList){
			var html ="";
			for(var i = 0; i < userList.length; i++){
				var user = userList[i];
				html += "<tr class='userTr' data-userid='" + user.userId + "'>";
				html += "	<td></td>";	
				html += "	<td>" + user.userId + "</td>";
				html += "	<td>" + user.userNm + "</td>";
				html += "	<td></td>";
				html += "	<td>" + user.reg_dt_fmt + "</td>";
				html += "</tr>";
			}
			$("#userListTbody").html(html);
		}
		
		function makePagination(userCnt, pageSize, page){
			
			var lastPage = parseInt(userCnt/pageSize) + (userCnt % pageSize > 0 ? 1 : 0);
			
			var html = "";
			
			if(page == 1){
				html += "<li class='disabled'>";
				html += "	<a aria-label='Previous'>";
				html += "	<span aria-hidden='true'>&laquo;</span>";
				html += "	</a>";
				html += "</li>";
				
			}else{
				html += "<li>";
				//html += "	<a href='${cp }/user/userPagingList' aria-label='Previous'>";
				html += "	<a href='javascript:getUserPageList(1)' aria-label='Previous'>";
				html += "		<span aria-hidden='true'>&laquo;</span>";
				html += "	</a>";
				html += "</li>";
			}
			
			for(var i = 1; i <= lastPage; i++){
				var active = "";
				if(i == page){
					active = "active";
				}
				html += "<li class='" + active + "'>";
				html += "	<a href='javascript:getUserPageList(" + i + ")'>" + i + "</a>";
// 				html += "	<a href='${cp }/user/userPagingList?page=" + i + "'>" + i + "</a>";
				html += "</li>";
			}
 			
			if(page == lastPage){
				html += "<li class='disabled'>";
				html += "	<a aria-label='Next'>";
				html += "		<span aria-hidden='true'>&raquo;</span>";
				html += "	</a>";
				html += "</li>";
			}else{
				html += "<li>";
				//html += "	<a href='${cp }/user/userPagingList' aria-label='Previous'>";
				html += "	<a href='javascript:getUserPageList(" + lastPage + ")' aria-label='Next'>";
				html += "		<span aria-hidden='true'>&raquo;</span>";
				html += "	</a>";
				html += "</li>";
				} 
			//
			$("#pagination").html(html);

		}
		
		// 1. 일반적인 Ajax 사용 로직 (jsonView 이용)
		function getUserPageList(page){
			$.ajax({
				url : "${cp}/user/userPagingListAjax",
				data : "page=" + page,
				success : function(data){ //json 타입으로 된 데이터
					makeUserList(data.userList);
					makePagination(data.userCnt, data.pageSize, data.page);
				}
			});
		}
		
		// 2. html(.jsp)로 받아오기
		function getUserPageListHtml(page){
			$.ajax({
				url : "${cp}/user/userPagingListAjaxHtml",
				data : "page=" + page,
				success : function(data){ // html 타입으로 된 데이터
					
					/*
						data :
							
							사용자 리스트 html........
							============seperator===========
							페이지네이션 html..........
					*/
					var htmlArr = data.split("====================seperator====================");
				
					$("#userListTbody").html(htmlArr[0]);
					$("#pagination").html(htmlArr[1]);
					
					//$("#userListTbody").html(data);
				}
			});
		}
		
		//문서 로딩이 완료된 이후 이벤트 등록
		$(document).ready(function() {
			console.log("document ready");
			
			//getUserPageList(1, 10);
			getUserPageListHtml(1);
			
			//msg 속성이 존재하면 alert, 존재하지 않으면 넘어가기
			<c:if test="${msg != null}">
				alert("${msg}");
				<% session.removeAttribute("msg"); %>
			</c:if>

			// 사용자 tr 태그 클릭시 이벤트 핸들러
			/* $(".userTr").click(function(){
			   
			}); */
			$(".userTr").on("click", function() {
				console.log("userTr click");
				/* // 클릭한 userTr태그의 userId 값을 출력
				var userId = $(this).children()[1].innerText;
				console.log(userId); */

				var userId = $(this).data("userid");

				// /user
				// 1. document 방식
				// document.location = "/user?userId=" + userId;

				// 2. form 방식
				$("#userId").val(userId);
				//           $("#frm").attr("action", "/userAllList");
				$("#frm").submit();

			});
		});
	</script>

	<form id="frm" action="${cp }/user/user" method="get">
		<input type="hidden" id="userId" name="userId" />
	</form>
