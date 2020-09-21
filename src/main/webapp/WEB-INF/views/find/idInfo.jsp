<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>비밀번호 찾기</title>
      <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
      <link href="resources/find.css" rel="stylesheet" type="text/css">
   </head>
   <body>
      <div class="wrapper">
         <header class="header-pw">
         </header>         
         <!-- body -->
         <section class="content content--login">
            <div class="content__body">
               <div class="content__body__page">
                  <h2>아이디 찾기</h2>
                  <section class="login">
                     <article class="notification">
                        <div class="notification_pic">
                           <img src=" //d2h5doj4xlbun0.cloudfront.net/member-0.0.1367/
                           images/member/member_status_img3.jpg">   
                        </div>                        
                        <dl class="notification__txt">
                           <dt></dt>
                           <dt>
                              회원님의 본인인증 정보로 검색된 아이디는
                              <br>
                              <span class="color-blue">      
                              총${fn:length(id)}개
                              </span>
                              입니다.
                           </dt>
                        </dl>   
                     </article>
                     <article class="user-area">   
                     <c:forEach items="${id}" var="dto">
                        <c:if test="${dto.sns_type eq 'normal'}">${dto.id}</c:if>             
                     <br>
                        <c:if test="${dto.sns_type eq 'kakao'}">카카오 계정</c:if>        
                     <br>
                        <c:if test="${dto.sns_type eq 'naver'}">네이버 계정</c:if>    
                     </c:forEach>
                     </article>
                     <br>
                     <button class="button button--base-m button--shape-a" style="top: 70px;" onclick="location.href='${pageContext.request.contextPath}/pwFind'">
                        <span class="button__text">비밀번호 찾기</span>
                     </button>
                     
                     <button class="button button--base-m button--shape-a" style="top: 70px;" onclick="location.href='${pageContext.request.contextPath}/loginForm'">
                        <span class="button__text">로그인 페이지로 이동</span>
                     </button>
                     
                  </section>
               </div>   
            </div>      
         </section>
      </div>
      
    <script type="text/javascript">   
    
         /* $("#pwFind-submit").click(function(e) {
            var id = $("#id").val();
            if(id == null) {
               alert("아이디를 다시 확인해주세요.");
               e.preventDefault();
            }
         });
      
               
         $("#verifyNumber-submit").click(function(e) {
            e.preventDefault();
            alert("인증번호를 입력해주세요.");
            var verifyNumber = $("#verifyNumber").val();
            var authKey = ${authKey};
            if(verifyNumber != authKey) {
               alert("인증번호를 다시 확인해주세요.");
            }
         });  */
      
   </script> 
   </body>
</html>