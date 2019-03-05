<%@page import="kr.or.ddit.prod.model.ProdVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
          <h1 class="page-header">전체 제품 리스트</h1>
             <!-- userList 정보를 화면에 출력하는 로직 작성 -->
             <% List<ProdVo> prodList =  (List<ProdVo>)request.getAttribute("prodVo"); %>
             <div class="table-responsive">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>제품 아이디</th>
                  <th>제품명</th>
                  <th>바이어 아이디</th>
                  <th>제품 가격</th>
                </tr>
              </thead>
              <tbody>
              
              <% for(int i = 0; i < prodList.size(); i++){
            	out.print("<td>" + prodList.get(i).getProd_id() + "</td>");
            	out.print("<td>" + prodList.get(i).getProd_name() + "</td>");
            	out.print("<td>" + prodList.get(i).getProd_buyer() + "</td>");
            	out.print("<td>" + prodList.get(i).getProd_cost() + "</td>");
            	out.print("</tr>");
              }
           	  %>
              </tbody>
            </table>
          </div>
             
             
        </div>
      </div>
    </div>
        
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  </body>
</html>
    