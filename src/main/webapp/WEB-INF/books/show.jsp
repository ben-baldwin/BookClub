<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="/books">back to the shelves</a>
<h1> <c:out value="${ oneBook.title }"></c:out></h1>
<h3><c:out value="${ oneBook.creator.userName } read ${ oneBook.title } by ${ oneBook.authorName }"></c:out></h3>
<h3>Here are <c:out value="${ oneBook.creator.userName }" ></c:out>'s thoughts:</h3>
<hr>
<c:out value="${ oneBook.thoughts }"></c:out>
<hr>
<c:if test="${user_id == oneBook.creator.id}">
<a href="/books/${oneBook.id}/edit">edit</a>
<a href="/books/${oneBook.id}/delete">delete</a>
</c:if>

</body>
</html>