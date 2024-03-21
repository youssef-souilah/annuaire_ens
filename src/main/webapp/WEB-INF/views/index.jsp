<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Annuaire de l’ENS </title>

</head>
<body>

	<h1>Annuaire de l’ENS</h1>
	<a href="/annuaire_ens?direction=administration">Administration</a>
	
	<c:if test="${not empty requestScope.error}">
	    <p>${requestScope.error}</p>
	</c:if>
	
	
	
	
	<c:if test="${requestScope.role eq 'admin'}">
	    <!-- Hide this element for admin -->
	    <div>
	       <p>adminnnnn</p>
	    </div>
	</c:if>

	<%
		
		//String hh=(String)request.getParameter("direction");
		//out.println(hh);
	%>
</body>
</html>