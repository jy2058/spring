<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

				<h1 class="page-header">전체 사용자 리스트(Tiles)</h1>
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

					
					<c:set var="lastPage" value="${Integer(userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0 )) }"/>
					 
	

				</div>

				
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
