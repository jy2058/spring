<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Dashboard Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<link href="${cp}/css/dashboard.css"
	rel="stylesheet">

</head>
<body>

	<%@ include file="/WEB-INF/views/module/header.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<%@ include file="/WEB-INF/views/module/left.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">전체 사용자 리스트</h1>
				<!-- userList 정보를 화면에 출력하는 로직 작성 -->
<%-- 				
				<%
					List<UserVo> userList = (List<UserVo>)request.getAttribute("userList");
				%>
--%>	
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
						<tbody>
						
<%-- 							<%
								for(int i = 0; i < userList.size(); i++){
					            	out.print("<tr class=\"userTr\" data-userid = " + userList.get(i).getUserId() + ">");
					            	out.print("<td>" + (i+1) + "</td>");
					            	out.print("<td>" + userList.get(i).getUserId() + "</td>");
					            	out.print("<td>" + userList.get(i).getUserNm() + "</td>");
					            	out.print("<td>" + "--" + "</td>");
					            	out.print("<td>" + userList.get(i).getReg_dt_fmt() + "</td>");
					            	out.print("</tr>");
					              }
							%>
 --%>							
							<c:forEach items="${userList }" var="vo">
								<tr class="userTr" data-userid="${vo.userId }">
									<td></td>	<!-- 생략 -->
									<td>${vo.userId }</td><!-- userId -->
									<td>${vo.userNm }</td><!-- userNm -->
									<td></td><!-- 생략 -->
									<td><fmt:formatDate value="${vo.reg_dt }" pattern="yyyy/MM/dd"/></td><!-- reg_tmt -->
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<form action="${cp }/user/userForm" method="get">
						<button type="submit" class="btn_btn-default">사용자 등록</button>
					</form>

<%-- 					<%
						int userCnt = (int)request.getAttribute("userCnt");
		            	int pageSize = (int)request.getAttribute("pageSize");
		            	int cpage = (int)request.getAttribute("page");
		            	int lastPage = userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0);
			           	pageContext.setAttribute("lastPage", lastPage);
			           	 
			           	String cp = request.getContextPath();
					%>
 --%>					
					
					<c:set var="lastPage" value="${Integer(userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0 )) }"/>
					 
	

				</div>

<%-- 				<nav style="text-align: center;">
					<ul class="pagination">
						<%if(cpage == 1){ %>
							<li class="disabled">
								<a href="<%=cp%>/userPagingList" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
							</li>
						<%}else{ %>
							<li>
								<a href="<%=cp%>/userPagingList" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
							</li>
						<%} %>
						<%
							for (int i = 1; i <= lastPage; i++) {
						%>
								<li 
									<%
									if(i == cpage){%>
									class="active"
									<%} %>
								><a href="<%=cp%>/userPagingList?page=<%=i%>"><%=i%></a></li>
						<%
							}
						%>
						
						<%if(cpage == lastPage){ %>

						<li class="disabled">
							<a href="<%=cp%>/userPagingList?page=<%=lastPage %>" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
						</li>
						<%}else{ %>
							<li>
							<a href="<%=cp%>/userPagingList?page=<%=lastPage %>" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
						<%} %>
					</ul>
				</nav> --%>
				
				<nav style="text-align:center;">
						<ul class="pagination">
							<c:choose>
								<c:when test="${page == 1 }">
									<li class="disabled">
										<a aria-label="Previous">
											<span aria-hidden="true">&laquo;</span>
										</a>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<a href="${cp }/user/userPagingList" aria-label="Previous">
											<span aria-hidden="true">&laquo;</span>
										</a>
									</li>									
								</c:otherwise>
							</c:choose>
							
							<c:forEach begin="1" end="${lastPage }" var="i">
								<c:set var="active" value=""/>
								<c:if test="${i == page }">
									<c:set var="active" value="active"/>
								</c:if>
									
								<li class="${active }">
									<a href="${cp }/user/userPagingList?page=${i}">${i}</a>
								</li>
							</c:forEach>
							
							<c:choose>
								<c:when test="${page == lastPage }">
									<li class="disabled">
										<a aria-label="Next">
											<span aria-hidden="true">&raquo;</span>
										</a>
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<a href="${cp }/user/userPagingList?page=${lastPage}" aria-label="Next">
											<span aria-hidden="true">&raquo;</span>
										</a>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
					</nav>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<script>
		//문서 로딩이 완료된 이후 이벤트 등록
		$(document).ready(function() {
			console.log("document ready");
			
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
</body>
</html>
