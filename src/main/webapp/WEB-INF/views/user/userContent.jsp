<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

          <h1 class="page-header">사용자 정보조회(Tiles)</h1>
			
          		<form action="/user/userModifyForm" class="form-horizontal" role="form">
					<% UserVo user = (UserVo)request.getAttribute("userVo"); %>
					
					<input type="hidden" name="userId" value="${userVo.userId }"> 
					
					<div class="form-group">
						<label for="userNm" class="col-sm-3 control-label">사진</label>
						<div class="col-sm-9">
							<img src="${cp }/user/profileImg?userId=${userVo.userId }">
							<%-- <c:choose>
								<c:when test="${userVo.filename == null}">
									<img src="${cp }/upload/noimg.png"/>
								</c:when>
								<c:otherwise>
									<img src="${cp }/upload/${userVo.filename }"/>
								</c:otherwise>
							</c:choose> --%>
						</div>
					</div>
					
					<div class="form-group">
						<label for="userNm" class="col-sm-3 control-label">사용자 아이디</label>
						<div class="col-sm-9">
							<label class="control-label">${userVo.userId}</label>
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-3 control-label">사용자 이름</label>
						<div class="col-sm-9">
							<label class="control-label">${userVo.userNm}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-3 control-label">별명</label>
						<div class="col-sm-9">
							<label class="control-label">${userVo.alias}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="pass" class="col-sm-3 control-label">주소1</label>
						<div class="col-sm-9">
							<label class="control-label">${userVo.addr1}</label>
						</div>
					</div>
					<div class="form-group">
						<label for="pass" class="col-sm-3 control-label">주소2</label>
						<div class="col-sm-9">
							<label class="control-label">${userVo.addr2}</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="pass" class="col-sm-3 control-label">등록일자</label>
						<div class="col-sm-9">
							<label class="control-label"><fmt:formatDate value="${userVo.reg_dt }" pattern="yyyy-MM-dd"/></label>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-9">
							<button type="submit" class="btn btn-default">사용자 수정</button>
						</div>
					</div>
					
				</form>
             
  <script>
    <c:if test="${msg != null}">
		alert("${msg}");
	</c:if>
  
  </script>
