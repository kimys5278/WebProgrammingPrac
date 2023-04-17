<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>
	1. JSP 주석 
	<!-- HTML 주석 -->
	<%--JSP 주석 --%>
	</h3>
	<%!
		String[] members = {"사과","포도","배","감"};
		int num1 = 10;
		int calc(int num2){
			return num1+num2;
		}
	%>
	<hr>
	<h3>
	2.calc(10) 메서드 실행 결과: 
	<%= calc(10) %>
	</h3>
	<hr>
	
	<h3>
	3. include: hello.JSP
	<%@ include file = "../WebProgramming/hello.jsp" %>
	</h3>
	<hr>
	
	<h3>
	4.스크립트 (배열 데이터 출력)
		<ul>
		<%
			for(String name :members){		
		%>
			<li><%= name %></li>
			<%
			}
			%>
			
		</ul>
	</h3>
</body>
</html>