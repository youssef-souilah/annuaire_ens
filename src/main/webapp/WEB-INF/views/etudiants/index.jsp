<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>etudiensd </h1>
<% //out.print(request.getServletPath() ); %>
<% out.print(request.getAttribute("d") ); %>
<form action="insert" method="post">
		<input type="submit"  value="ggg" />
	</form>
	
<c:forEach var="etudiant" items="${requestScope.list}">
    <!-- Access properties of each etudiant using the var attribute -->
    <p>Etudiant ID: ${etudiant.id}</p>
    <p>Nom: ${etudiant.nom}</p>
    <p>Prénom: ${etudiant.prenom}</p>
    <!-- Add more HTML markup as needed -->
</c:forEach></body>
</html>