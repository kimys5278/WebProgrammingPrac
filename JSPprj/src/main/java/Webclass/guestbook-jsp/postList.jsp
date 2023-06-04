<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<title>방명록 목록</title>
</head>
   <body>
   <div class="container w-75 mt-5 mx-auto">
    <h2>방명록 목록</h2>
    <hr>
    <table class="table">
	  <thead class="thead-light">
	    <tr>
	      <th scope="col">#</th>
	      <th scope="col">작성자</th>
	      <th scope="col">이메일</th>
	      <th scope="col">작성일</th>
	      <th scope="col">제목</th>
	    </tr>
	  </thead>
	  <tbody>
	  	<c:forEach items="${post }" var="p">
		    <tr>
		      <th scope="row">${p.aid }</th>
		      <td>${p.username }</td>
		      <td>${p.email }</td>
		      <td>${p.date }</td>
		      <td>
		      	<a href="post.nhn?action=getPost&aid=${p.aid}" class="link-offset-2 link-underline link-underline-opacity-0 link-underline-opacity-0-hover">${p.title }</a>
		      </td>
		    </tr>
		</c:forEach>
	  </tbody>
	</table>
	<div class="row justify-content-center">
		<a class="btn btn-info btn-lg m-2" href="guestbook/newPost.jsp" role="button">등록</a>
	</div>
</div>
</body>
</html>