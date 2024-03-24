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
					<div class="w-full flex flex-col">
						<h1 class="font-semibold text-xl text-blue-500">Rechercher Etudiant</h1>
						
						<div class="w-11/12 self-center" >
							<label class=" text-lg text-blue-500">Le Nom complet</label>
							<div class="w-11/12 flex flex-row items-center justify-between ">
								<input 
									type="text" 
									placeholder="entrer le nom complet.."
									id="search" 
									class="py-3 px-4 block w-10/12 border border-black rounded-lg text-sm     " 
								/>
								<button id="searchButton" class="px-5 py-3 bg-blue-500 hover:bg-blue-700 rounded rounded-lg">Rechercher</button>
							</div>
						</div>
						
						<c:if test="${requestScope.item!=null }">
							<h1 class="font-semibold text-lg text-blue-500">Resultat</h1>
							<div class="w-11/12 self-center">
								<h1 class="font-semibold text-sm text-blue-500">CEN :</h1>
								<p>${requestScope.item.CNE }</p>
								<h1 class="font-semibold text-sm text-blue-500">Nom :</h1>
								<p>${requestScope.item.nom }</p>
								<h1 class="font-semibold text-sm text-blue-500">Prenom :</h1>
								<p>${requestScope.item.prenom }</p>
								<h1 class="font-semibold text-sm text-blue-500">Departement :</h1>
								<p>${requestScope.item.departement }</p>
								<h1 class="font-semibold text-sm text-blue-500">Filiere :</h1>
								<p>${requestScope.item.filiere }</p>
								<h1 class="font-semibold text-sm text-blue-500">Telephone :</h1>
								<p>${requestScope.item.telephone }</p>
							</div>
						</c:if>
						
					</div>
		    
		    	</div>
		    </section>
    	</main>
    </section>
    <script defer src="https://use.fontawesome.com/releases/v5.14.0/js/all.js"></script>
	<script src="https://cdn.tailwindcss.com"></script>
	<script type="text/javascript">
		const searchInput=document.getElementById('search');
		const searchButton=document.getElementById('searchButton');
		
		searchButton.addEventListener("click",(e)=>{
			var value=searchInput.value;
			var regex=/^[a-zA-Z]+(?: [a-zA-Z]+)*$/
			var isValid = regex.test(value);
			if(isValid){
				window.location.href = '/annuaire_ens/etudiants/rechercher?param='+value;
			}
			else{
				alert("valeur invalide !\n entrer le nom complet")
			}
		});
	</script>
</body>
</html>
