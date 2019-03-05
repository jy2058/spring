<%@page import="kr.or.ddit.prod.model.LprodVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="${cp}/css/dashboard.css" rel="stylesheet">

  </head>
  <body>
  
	<%@ include file="/WEB-INF/views/module/header.jsp" %>

	<div class="container-fluid">
      <div class="row">
       <%@ include file="/WEB-INF/views/module/left.jsp" %>
       
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">전체 카테고리 리스트</h1>
             <!-- userList 정보를 화면에 출력하는 로직 작성 -->
             <%-- <% List<LprodVo> lprodList =  (List<LprodVo>)request.getAttribute("lprodList"); %> --%> 
             <c:set var="lprodList" value="${lprodList }" scope="request"/>
             
             <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>#</th>
                  <th>카테고리 코드</th>
                  <th>카테고리 이름</th>
                </tr>
              </thead>
              <tbody>
              
<%--               <% for(int i = 0; i < lprodList.size(); i++){
            	out.print("<tr class=\"lprodTr\" data-lprod_gu = " + lprodList.get(i).getLprod_gu() + ">");
            	out.print("<td>" + lprodList.get(i).getLprod_id() + "</td>");
            	out.print("<td>" + lprodList.get(i).getLprod_gu() + "</td>");
            	out.print("<td>" + lprodList.get(i).getLprod_nm() + "</td>");
            	out.print("</tr>");
              }
           	  %> --%>
              </tbody>
              
              <c:forEach items="${lprodList }" var="vo">
              	<tr class="lprodTr" data-lprod_gu = ${vo.lprod_gu }>
              		<td> ${vo.lprod_id }</td>
              		<td> ${vo.lprod_gu }</td>
              		<td> ${vo.lprod_nm }</td>
           		</tr>
              </c:forEach>
            </table>
            
<!--             lprodCnt -->

<%-- 			<%
				int lprodCnt =  (int)request.getAttribute("lprodCnt");
				int pageSize = (int)request.getAttribute("pageSize");
				int cpage = (int)request.getAttribute("page");
				int lastPage = lprodCnt / pageSize + (lprodCnt % pageSize > 0 ? 1 : 0);
				String cp = request.getContextPath();
				%> 
--%>
				<c:set var="lastPage" value="${Integer(lprodCnt / pageSize + (lprodCnt % pageSize > 0 ? 1 : 0)) }"/>
			
          </div>
          
          
          
<%--           <nav style="text-align: center;">
					<ul class="pagination">
						<%if(cpage == 1){ %>
							<li class="disabled">
								<a href="<%=cp%>/lprodPagingList" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
							</li>
						<%}else{ %>
							<li>
								<a href="<%=cp%>/lprodPagingList" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
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
								><a href="<%=cp%>/lprodPagingList?page=<%=i%>"><%=i%></a></li>
						<%
							}
						%>
						
						<%if(cpage == lastPage){ %>

						<li class="disabled">
							<a href="<%=cp%>/lprodPagingList?page=<%=lastPage %>" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
						</li>
						<%}else{ %>
							<li>
							<a href="<%=cp%>/lprodPagingList?page=<%=lastPage %>" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
						<%} %>
					</ul>
				</nav> 
				
--%>
		           <nav style="text-align: center;">
						<ul class="pagination">
						<c:choose>
							<c:when test="${page == 1 }">
								<li class="disabled">
									<a href="${pageContext.servletContext.contextPath }/lprodPagingList" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
								</li>
							</c:when>
							<c:otherwise>
								<li>
									<a href="${pageContext.servletContext.contextPath }/lprodPagingList" aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
								</li>
							</c:otherwise>
						</c:choose>
							
						<c:forEach begin="1" end="${lastPage }" var="i">
							<c:set var="active" value=""/>
							<c:if test="${i == page }">
								<c:set var="active" value="active"/>
							</c:if>	
							<li class="${active }">
								<a href="${pageContext.servletContext.contextPath }/lprodPagingList?page=${i}">${i }</a>
							</li>
						</c:forEach>
						<c:choose>
							<c:when test="${page == lastPage }">
								<li class="disabled">
								<a href="${pageContext.servletContext.contextPath }/lprodPagingList?page=${lastPage}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
								</li>
							</c:when>
							<c:otherwise>
								<li>
								<a href="${pageContext.servletContext.contextPath }/lprodPagingList?page=${lastPage}" aria-label="Next"> <span aria-hidden="true">&raquo;</span></a>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  <script>
      //문서 로딩이 완료된 이후 이벤트 등록
      $(document).ready(function(){
         console.log("document ready");
         
         // 사용자 tr 태그 클릭시 이벤트 핸들러
         /* $(".userTr").click(function(){
            
         }); */
         $(".lprodTr").on("click",function(){
            console.log("lprodTr click"); 
            /* // 클릭한 userTr태그의 userId 값을 출력
            var userId = $(this).children()[1].innerText;
            console.log(userId); */
            
          var lprod_gu = $(this).data("lprod_gu");
            
          // /user
          // 1. document 방식
          // document.location = "/user?userId=" + userId;
          
          // 2. form 방식
          $("#lprod_gu").val(lprod_gu);
//           $("#frm").attr("action", "/userAllList");
          $("#frm").submit();
          
         });
      });
   </script>
   
   <form id="frm" action="${pageContext.servletContext.contextPath }/prod" method="get">
   		<input type="hidden" id="lprod_gu" name="lprod_gu"/>
   </form>
  </body>
</html>
    