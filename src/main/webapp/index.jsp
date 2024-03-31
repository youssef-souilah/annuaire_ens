<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"  %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>| Annuaire de l'ENS |</title>
    <meta name="description" content="annuaire ens">
    <style>
        ::-webkit-scrollbar{
            display: none;
        }
    </style>
</head>
<body class="bg-gray-800   flex flex-row items-center justify-center h-full">
    <main class="self-center min-w-min  w-96 mt-28 " >
  <div class="flex flex-col items-center">
  	<div class="flex flex-row items-center justify-between w-8/12">
  		<img class="object-contain h-full w-full hover:bg-gray-700 block" src="/annuaire_ens/assets/imgs/logo.png"
            alt="open-source" />
  	</div>
    <div class="flex flex-row items-center justify-between">
    	<h1 class="font-bold text-white text-3xl ">Annuaire de l'ENS</h1>
    	
    </div>
    <form class="w-11/12 flex flex-col " action="/annuaire_ens/bienvenue" method="POST">
        <div class="flex flex-col items-start w-full">
	        <div class="relative h-10 w-full min-w-[200px]">
			  <select
			  	name="direction"
			    class="peer h-full w-full rounded-[7px] border border-blue-gray-200 border-t-transparent bg-gray-900 px-3 py-2.5 font-sans text-sm font-normal text-white outline outline-0 transition-all placeholder-shown:border placeholder-shown:border-blue-gray-200 placeholder-shown:border-t-blue-gray-200 empty:!bg-gray-900 focus:border-2 focus:border-gray-900 focus:border-t-transparent focus:outline-0 disabled:border-0 disabled:bg-blue-gray-50">
			    <option value="">--- Choisi le menu ---</option>
			    <option value="administration">administration</option>
			    <option value="utilisation">utilisation</option>
			  </select>
			  <label
			    class="before:content[' '] after:content[' '] pointer-events-none absolute left-0 -top-1.5 flex h-full w-full select-none text-[11px] font-normal leading-tight text-white transition-all before:pointer-events-none before:mt-[6.5px] before:mr-1 before:box-border before:block before:h-1.5 before:w-2.5 before:rounded-tl-md before:border-t before:border-l before:border-blue-gray-200 before:transition-all after:pointer-events-none after:mt-[6.5px] after:ml-1 after:box-border after:block after:h-1.5 after:w-2.5 after:flex-grow after:rounded-tr-md after:border-t after:border-r after:border-blue-gray-200 after:transition-all peer-placeholder-shown:text-sm peer-placeholder-shown:leading-[3.75] peer-placeholder-shown:text-white peer-placeholder-shown:before:border-transparent peer-placeholder-shown:after:border-transparent peer-focus:text-[11px] peer-focus:leading-tight peer-focus:text-white peer-focus:before:border-t-2 peer-focus:before:border-l-2 peer-focus:before:border-gray-900 peer-focus:after:border-t-2 peer-focus:after:border-r-2 peer-focus:after:border-gray-900 peer-disabled:text-transparent peer-disabled:before:border-transparent peer-disabled:after:border-transparent peer-disabled:peer-placeholder-shown:text-white">
			    Menus
			  </label>
			</div>
        </div>
        
        <button type="submit" class="my-5 w-full font-bold h-9 bg-gradient-to-r from-indigo-500 via-purple-500 to-pink-500 rounded-lg">
            Suivant
        </button>
    </form>
  </div>
</main>
<script src="https://cdn.tailwindcss.com"></script>
</body>
</html>
