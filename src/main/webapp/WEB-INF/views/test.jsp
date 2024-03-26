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
	    <jsp:include page="components/aside.jsp" />
	    
	    <main class="sm:h-full flex-1 flex flex-col min-h-0 min-w-0 overflow-auto">
		    <!-- Include navigation menu -->
		    <jsp:include page="components/nav.jsp" />
		    <section class="flex-1 pt-3 p-6 lg:mb-0 lg:min-h-0 lg:min-w-0">
	      		<div class="flex flex-col  h-full w-full items-center ">
	      		
	      		
	      		
				    <!-- Include main content -->
				    
				    
				   

				    
				    
				    
					<div class="flex flex-col w-full h-full">
  <div class="-m-1.5 overflow-x-auto">
    <div class="p-1.5 min-w-full inline-block align-middle">
      <div class="flex flex-col">
        <div class="-m-1.5 overflow-x-auto">
          <div class="p-1.5 min-w-full inline-block align-middle">
            <div class="border rounded-lg divide-y divide-gray-200"> 
              <div class="py-3 px-4 w-full flex flex-row item-center justify-between   ">
              
                <div class="relative max-w-xs  ">
                  <label for="hs-table-search" class="sr-only">Search</label>
                  <input type="text" name="hs-table-search" id="hs-table-search" class="py-2 px-3 ps-9 block w-full border-black shadow-sm rounded-lg text-sm focus:z-10 focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50 disabled:pointer-events-none" placeholder="Search for items">
                  <div class="absolute inset-y-0 start-0 flex items-center pointer-events-none ps-3">
                    <svg class="size-4 text-gray-400" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="11" cy="11" r="8"/><path d="m21 21-4.3-4.3"/></svg>
                  </div>
                </div>
                <a href="new" class="self-end">
                <button type="button" class=" py-3 px-4 inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-transparent bg-blue-600 text-white hover:bg-blue-700 disabled:opacity-50 disabled:pointer-events-none ">
				  Solid
				</button>
                </a>
                
              </div>
              <div class="overflow-hidden">
                <table class="min-w-full divide-y divide-gray-200">
                  <thead class="bg-gray-50">
                    <tr>
                      
                      <th scope="col" class="px-6 py-3 text-start text-xs font-medium text-gray-500 uppercase">Name</th>
                      <th scope="col" class="px-6 py-3 text-start text-xs font-medium text-gray-500 uppercase">Age</th>
                      <th scope="col" class="px-6 py-3 text-start text-xs font-medium text-gray-500 uppercase">Address</th>
                      <th scope="col" class="px-6 py-3 text-center text-xs font-medium text-gray-500 uppercase">Actions</th>
                    </tr>
                  </thead>
                  <tbody class="divide-y divide-gray-200">
                    <tr>
                      
                      <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-800">John Brown</td>
                      <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-800">45</td>
                      <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-800">New York No. 1 Lake Park</td>
                      <td class="px-6 py-4 whitespace-nowrap text-center text-sm font-medium">
                      	<a href="edit"  class="inline-flex items-center gap-x-4 text-lg font-semibold rounded-lg border border-transparent text-blue-600 hover:text-blue-800 disabled:opacity-50 disabled:pointer-events-none"><i class="fas fa-pencil-alt"></i></a>
                        <a href="delete"  class="inline-flex items-center gap-x-4 text-lg font-semibold rounded-lg border border-transparent text-red-600 hover:text-red-800 disabled:opacity-50 disabled:pointer-events-none"><i class="fas fa-trash-alt"></i></a>
                      </td>
                    </tr>
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
				    
		    
		    	</div>
		    </section>
    	</main>
    </section>
    <script defer src="https://use.fontawesome.com/releases/v5.14.0/js/all.js"></script>
	<script src="https://cdn.tailwindcss.com"></script>
	<script type="text/javascript">
  function toggleModal(modalID){
    document.getElementById(modalID).classList.toggle("hidden");
    document.getElementById(modalID + "-backdrop").classList.toggle("hidden");
    document.getElementById(modalID).classList.toggle("flex");
    document.getElementById(modalID + "-backdrop").classList.toggle("flex");
  }
</script>
	
</body>
</html>