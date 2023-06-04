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
<title>방명록 수정</title>
</head>
<script>
	function validateInputs() {
	    var username = document.getElementsByName("username")[0].value;
	    var email = document.getElementsByName("email")[0].value;
	    var title = document.getElementsByName("title")[0].value;
	    var content = document.getElementsByName("content")[0].value;
	    
	    if (username === "" || email === "" || title === "" || content === "") {
	        alert("모든 입력란을 채워주세요.");
	        return false;
	    }
	
	    // 폼 유효성 검사 통과한 경우
	    submitForm(); // 폼 제출
	    return false; // 폼 제출 후 페이지 이동 방지
	}
	
	function submitForm() {
	    // 폼 제출 코드 추가
	    document.querySelector("form").submit();
	}

</script>
<body>
	<div class="container w-75 mt-5 mx-auto">
	
		<h2>방명록 수정</h2>
		<hr />
		<div id="editForm">
			<div class="form-group">
				<form method="post" action="/post.nhn?action=updatePost&aid=${post.aid }">
					<div class="input-group mb-3">
				  		<div class="input-group-prepend">
				    	<span class="input-group-text" id="inputGroup-sizing-default">작성자</span>
				  	</div>
				  		<input type="text" name="username" class="form-control" value="${post.username }" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
					</div>
					<div class="input-group mb-3">
				  		<div class="input-group-prepend">
				    	<span class="input-group-text" id="inputGroup-sizing-default">이메일</span>
				  	</div>
				  		<input type="text" name="email" class="form-control" value="${post.email }" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
					</div>
					<div class="input-group mb-3">
				  		<div class="input-group-prepend">
				    	<span class="input-group-text" id="inputGroup-sizing-default">제목</span>
				  	</div>
				  		<input type="text" name="title" class="form-control" value="${post.title }" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
					</div>
					<div class="form-floating"> 
  						<textarea class="form-control" name="content" id="floatingTextarea2" style="height: 200px">${post.content }</textarea>
					</div>
					<div class="row justify-content-center">
						<button class="btn btn-info btn-lg m-2" type="button" onclick="validateInputs()">수정</button>
						<a class="btn btn-info btn-lg m-2" href="post.nhn?action=deletePost&aid=${post.aid}" role="button">삭제</a>
						<a class="btn btn-info btn-lg m-2" href="javascript:window.history.back()" role="button">목록</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>