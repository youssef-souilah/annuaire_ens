<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

<script defer src="https://use.fontawesome.com/releases/v5.14.0/js/all.js"></script>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<section class="h-screen w-screen bg-white flex flex-col-reverse sm:flex-row min-h-0 min-w-0 overflow-hidden">
  <aside class="sm:h-full sm:w-20 w-full h-14 bg-gray-800 text-gray-200">
    <ul class="text-center flex flex-row sm:flex-col w-full">
      <li class="h-14 border-b border-gray-900 hidden sm:block">
        <a id="page-icon" href="/" class="h-full w-full hover:bg-gray-700 block p-3">
          <img class="object-contain h-full w-full hover:bg-gray-700 block" src="assets/imgs/logo.png"
            alt="open-source" />
        </a>
      </li>
      <li class="sm:border-b border-gray-900 flex-1 sm:w-full" title="Etudiant">
        <a id="page-icon" href="etudiants" class="h-full w-full hover:bg-gray-700 block p-3">
          <i class="fas fa-users fill-current"></i>
          <p class="sm:hidden text-xs">Etudiants</p>
        </a>
        <p class="sm:block text-xs">Etudiants</p>
      </li>
      <li class="sm:border-b border-gray-900 flex-1 sm:w-full" title="Departements">
        <a id="page-icon" href="/" class="h-full w-full hover:bg-gray-700 block p-3">
         <i class="fas fa-suitcase"></i>
         <p class="sm:hidden text-xs">Departements</p>
        </a>
        <p class="sm:block text-xs">Departements</p>
      </li>
      <li class="sm:border-b border-gray-900 flex-1 sm:w-full" title="Filieres">
        <a id="page-icon" href="/" class="h-full  w-full hover:bg-gray-700 block p-3">
          <i class="fas fa-th-list"></i>
          <p class="sm:hidden text-xs">Filieres</p>
        </a>
        <p class="sm:block text-xs">Filieres</p>
      </li>
    </ul>
  </aside>
  <main class="sm:h-full flex-1 flex flex-col min-h-0 min-w-0 overflow-auto">
    <nav class="border-b bg-white px-6 py-2 flex items-center min-w-0 h-14">
      <h1 class="font-semibold text-lg"></h1>
      <span class="flex-1"></span>
      <span class="mr-2">
        <input type="text" placeholder="Search"
          class="w-full border-2 px-2 py-1 border border-gray-300 rounded-sm focus:outline-none focus:ring-2 focus:ring-blue-600 focus:border-transparent bg-gray-300 focus:bg-gray-100" />
      </span>
      <button
        class="ml-auto border rounded-full ml-2 w-10 h-10 text-center leading-none text-gray-200 bg-gray-400 focus:outline-none focus:ring-2 focus:ring-blue-600 focus:border-transparent">
        <i class="fas fa-user fill-current"></i>
      </button>
    </nav>
    <section class="flex-1 pt-3 md:p-6 lg:mb-0 lg:min-h-0 lg:min-w-0">
      <div class="flex flex-col lg:flex-row h-full w-full">
        
			-----
			<div class="flex flex-col">
  <div class="-m-1.5 overflow-x-auto">
    <div class="p-1.5 min-w-full inline-block align-middle">
      <div class="flex flex-col">
        <div class="-m-1.5 overflow-x-auto">
          <div class="p-1.5 min-w-full inline-block align-middle">
            <div class="border rounded-lg divide-y divide-gray-200"> 
              <div class="py-3 px-4">
                <div class="relative max-w-xs">
                  <label for="hs-table-search" class="sr-only">Search</label>
                  <input type="text" name="hs-table-search" id="hs-table-search" class="py-2 px-3 ps-9 block w-full border-gray-200 shadow-sm rounded-lg text-sm focus:z-10 focus:border-blue-500 focus:ring-blue-500 disabled:opacity-50 disabled:pointer-events-none" placeholder="Search for items">
                  <div class="absolute inset-y-0 start-0 flex items-center pointer-events-none ps-3">
                    <svg class="size-4 text-gray-400" xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="11" cy="11" r="8"/><path d="m21 21-4.3-4.3"/></svg>
                  </div>
                </div>
              </div>
              <div class="overflow-hidden">
                <table class="min-w-full divide-y divide-gray-200">
                  <thead class="bg-gray-50">
                    <tr>
                      <th scope="col" class="py-3 px-4 pe-0">
                        <div class="flex items-center h-5">
                          <input id="hs-table-search-checkbox-all" type="checkbox" class="border-gray-200 rounded text-blue-600 focus:ring-blue-500">
                          <label for="hs-table-search-checkbox-all" class="sr-only">Checkbox</label>
                        </div>
                      </th>
                      <th scope="col" class="px-6 py-3 text-start text-xs font-medium text-gray-500 uppercase">Name</th>
                      <th scope="col" class="px-6 py-3 text-start text-xs font-medium text-gray-500 uppercase">Age</th>
                      <th scope="col" class="px-6 py-3 text-start text-xs font-medium text-gray-500 uppercase">Address</th>
                      <th scope="col" class="px-6 py-3 text-end text-xs font-medium">Action</th>
                    </tr>
                  </thead>
                  <tbody class="divide-y divide-gray-200">
                    <tr>
                      <td class="py-3 ps-4">
                        <div class="flex items-center h-5">
                          <input id="hs-table-search-checkbox-1" type="checkbox" class="border-gray-200 rounded text-blue-600 focus:ring-blue-500">
                          <label for="hs-table-search-checkbox-1" class="sr-only">Checkbox</label>
                        </div>
                      </td>
                      <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-800">John Brown</td>
                      <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-800">45</td>
                      <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-800">New York No. 1 Lake Park</td>
                      <td class="px-6 py-4 whitespace-nowrap text-end text-sm font-medium">
                        <button type="button" class="inline-flex items-center gap-x-2 text-sm font-semibold rounded-lg border border-transparent text-blue-600 hover:text-blue-800 disabled:opacity-50 disabled:pointer-events-none">Delete</button>
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
			
			-----

      </div>
    </section>
    
  </main>
</section>


</body>
</html>