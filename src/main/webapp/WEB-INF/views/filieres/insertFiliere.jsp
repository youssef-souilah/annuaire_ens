<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Annuaire de l’ENS</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/creativetimofficial/tailwind-starter-kit/compiled-tailwind.css">	

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
		    <c:if test="${ not empty cookie.message  }">
			    <div class="text-white px-6 py-4 border-0 rounded relative mb-4 bg-emerald-500">
				  
				  <span class="inline-block align-middle mr-8">
				    <b class="capitalize">Message</b> ${cookie.message.value } !
				  </span>
				  <button class="absolute bg-transparent text-2xl font-semibold leading-none right-0 top-0 mt-4 mr-6 outline-none focus:outline-none">
				    <span>×</span>
				  </button>
				</div>
		    </c:if>
		    <c:if test="${not empty cookie.erreur  }">
			    <div class="text-white px-6 py-4 border-0 rounded relative mb-4 bg-red-500">
				  <span class="text-xl inline-block mr-5 align-middle">
				    
				  </span>
				  <span class="inline-block align-middle mr-8">
				    <b class="capitalize">Message</b>  ${ cookie.erreur.value} !
				  </span>
				  <button class="absolute bg-transparent text-2xl font-semibold leading-none right-0 top-0 mt-4 mr-6 outline-none focus:outline-none">
				    <span>×</span>
				  </button>
				</div>
		    </c:if>
	      		<div class="flex flex-col  h-full w-full items-center ">
				    <!-- Include main content -->
				    
				   
				    
				    
					
  <div class=" overflow-x-auto w-full">
    <div class="p-1.5 min-w-full inline-block align-middle">
    <h1 class="font-semibold text-xl text-blue-500">Filieres</h1>
    
      <div class="flex flex-col">
        <div class="-m-1.5 overflow-x-auto">
          <div class="p-1.5 min-w-full inline-block align-middle">
            <div class="border rounded-lg divide-y divide-gray-200"> 
              <div class="py-3 px-4 w-full flex flex-row item-center justify-between   ">
                <form action="filieres" class="relative max-w-xs  flex items-center " method="get">
                  <input type="text" name="search" id="hs-table-search" class="py-2 px-3 ps-9 block w-full border-black shadow-sm rounded-lg text-sm focus:z-10 focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50 disabled:pointer-events-none" placeholder="Search for items">
                  <div class="absolute inset-y-0 start-0 flex items-center pointer-events-none ps-3">
                    <svg class="size-4 text-gray-400" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="11" cy="11" r="8"/><path d="m21 21-4.3-4.3"/></svg>
                  </div>
                  <input type="submit"  class=" hidden"/>
                </form>
       			<div class ="flex flex-row items-center ">
	                <%@include  file="insertFiliere.html" %>
                </div>
              </div>
              <div class="mb-3 pt-0 w-11/12 mx-5">
        			<label class="text-lg font-semibold inline-block py-1 px-2  rounded text-blue-600  last:mr-0 mr-1">Lister par departements</label>
				  	<select
				  		id="departement_id"
					  	name="departement_id"
					    class="peer h-full w-full rounded-[7px] border border-blue-gray-200 border-t-transparent bg-transparent px-3 py-2.5 font-sans text-sm font-normal text-blue-gray-700 outline outline-0 transition-all placeholder-shown:border placeholder-shown:border-blue-gray-200 placeholder-shown:border-t-blue-gray-200 empty:!bg-gray-900 focus:border-2 focus:border-gray-900 focus:border-t-transparent focus:outline-0 disabled:border-0 disabled:bg-blue-gray-50"
					>
				    	<option value="">--- departements ---</option>
					    <c:forEach var="departement" items="${requestScope.departements}">
					    <c:choose>
					        <c:when test="${ departement.id eq param.departement_id}">
					            <option value="${departement.id}" selected="selected">${departement.nom}</option>
					        </c:when>
					        <c:otherwise>
					            <option value="${departement.id}">${departement.nom}</option>
					        </c:otherwise>
					    </c:choose>
					    </c:forEach>
				  	</select>
				</div>
              <div class="overflow-hidden">
                <table class="min-w-full divide-y divide-gray-200">
                  <thead class="bg-gray-50">
                    <tr>
                      <th scope="col" class="px-6 py-3 text-start text-xs font-medium text-gray-500 uppercase">id</th>
                      <th scope="col" class="px-6 py-3 text-start text-xs font-medium text-gray-500 uppercase">Nom</th>
                      <th scope="col" class="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase">Actions</th>
                    </tr>
                  </thead>
                  <tbody class="divide-y divide-gray-200">
                  	<c:forEach var="filiere" items="${requestScope.list}">
	                  	<tr>
	                  		<td class="px-6 py-4 whitespace-nowrap text-sm text-gray-800">${filiere.id}</td>
	                  		<td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-800">${filiere.nom}</td>
	                      	<td class="px-6 py-4 whitespace-nowrap text-center text-sm font-medium">
	                      		<a href="filieres/modifier?id=${filiere.id}"  class="inline-flex items-center gap-x-4 text-lg font-semibold rounded-lg border border-transparent text-blue-600 hover:text-blue-800 disabled:opacity-50 disabled:pointer-events-none"><i class="fas fa-pencil-alt"></i></a>
	                        	<a href="filieres/supprimer?id=${filiere.id}"  class="inline-flex items-center gap-x-4 text-lg font-semibold rounded-lg border border-transparent text-red-600 hover:text-red-800 disabled:opacity-50 disabled:pointer-events-none"><i class="fas fa-trash-alt"></i></a>
	                      	</td>
	                    </tr>
					</c:forEach>
                    
                    <!-- Additional table rows here -->
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>




				    
		    
		    	</div>
		    </section>
    	</main>
    </section>
    <script defer src="https://use.fontawesome.com/releases/v5.14.0/js/all.js"></script>
	<script src="https://cdn.tailwindcss.com"></script>
	<script type="text/javascript">
		const departement=document.getElementById("departement_id");
		departement.addEventListener('change',(e)=>{
			if(departement.value){
				window.location.href = `/annuaire_ens/filieres?departement_id=`+departement.value;
			}
		})
	  	function toggleModal(modalID){
		    document.getElementById(modalID).classList.toggle("hidden");
		    document.getElementById(modalID + "-backdrop").classList.toggle("hidden");
		    document.getElementById(modalID).classList.toggle("flex");
		    document.getElementById(modalID + "-backdrop").classList.toggle("flex");
	  	}
		document.getElementById("close-modal").addEventListener('click',(e)=>{
			window.location.href = '/annuaire_ens/filieres';
		})
  
</script>
</body>
</html>
