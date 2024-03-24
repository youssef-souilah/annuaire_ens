<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Annuaire de l’ENS</title>
<style>
  
  
 

body {
  font-family: "Nunito", sans-serif;
}

main {
  font-size: clamp(0.9rem, 3vw, 1rem);
}

#page-icon img {
  -webkit-animation: cssfilter 3s infinite;
}


@-webkit-keyframes cssfilter {
  0%, 100%  {  
    filter: invert(75%) drop-shadow(0px 0px 2px blue) 
  }
  
  50% { 
    filter: invert(0%) drop-shadow(0px 0px 1px teal); 	
  }
}
</style>
</head>
<body>
	<section class="h-screen w-screen bg-white flex flex-col-reverse sm:flex-row min-h-0 min-w-0 overflow-hidden">
		<!-- Include header -->
	    <jsp:include page="../components/aside.jsp" />
	    
	    <main class="sm:h-full flex-1 flex flex-col min-h-0 min-w-0 overflow-auto">
		    <!-- Include navigation menu -->
		    <jsp:include page="../components/nav.jsp" />
		    <section class="flex-1 pt-3 p-6 lg:mb-0 lg:min-h-0 lg:min-w-0">
	      		<div class="flex flex-col  h-full w-full items-center ">
				    <!-- Include main content -->

		    
		    	</div>
		    </section>
    	</main>
    </section>
    <script defer src="https://use.fontawesome.com/releases/v5.14.0/js/all.js"></script>
	<script src="https://cdn.tailwindcss.com"></script>
</body>
</html>
