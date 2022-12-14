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
<h1>Welcome, <c:out value="${ user.userName }"/></h1>
	<a href="/logout">Logout</a>
	</br>
	<a href="/books/new">+Add a book to my shelf!</a>
	<div class="container">
		<h3>Books from everyone's shelves:</h3>
		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Author Name</th>
					<th>Posted By</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${allBooks}">
					<tr>
						<td><c:out value="${book.id}" /></td>
						<td><a href="/books/${book.id}"><c:out value="${book.title}" /></a></td>
						<td><c:out value="${book.authorName}" /></td>
						<td><c:out value="${book.creator.userName}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>