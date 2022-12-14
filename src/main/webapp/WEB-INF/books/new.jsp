<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Book</title>
</head>
<body>
<a href="/books">Back to the shelves</a>
<h1>Add a Book to Your Shelf!</h1>
	<div class="container">
		<form:form method="POST" action="/books/new" modelAttribute="bookObj">
			<form:input type="hidden" path="creator" value="${user_id}" />
			<p>
				Title:
				<form:input path="title" />
				<form:errors path="title" />
			</p>
			<p>
				Author:
				<form:input path="authorName"/>
				<form:errors path="authorName" />
			</p>
			<p>
				My thoughts:
				<form:textarea value="text" path="thoughts"/>
				<form:errors path="thoughts" />
			</p>
			<button>Submit</button>
		</form:form>
	</div>
</body>
</html>