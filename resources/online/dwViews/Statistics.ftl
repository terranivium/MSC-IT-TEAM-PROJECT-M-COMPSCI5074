<html>

	<head>
		<!-- Web page title -->
    	<title>Doggo Trumps - Statistics</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    
<!-- statistics CSS -->
    
    <style>
		
		body {
			background-color:#e6e6e6;
			font-family:Verdana;
  			text-align:center;
			font-size:15px;
			display:flex;
			flex-direction:column;
		}
		
  		.navbar {
  			background-color:#4d4d4d;
			background:linear-gradient(to bottom, #4d4d4d 5%, #747474 100%);
  			padding:2rem;
  		}
  		
  		.maincontainer {
  			padding:2rem;
  			flex: 1 0 auto;
  		}
  		
  		.results {
  			background-color:#ffffe6;
			background:linear-gradient(to bottom, #ffffe6 5%, #fbfbd1 100%);
  			border-left:1px solid black;
  			border-right:1px solid black;
  			border-bottom:1px solid black;
  			width:75%;
  			display:inline-block;
  			padding:1rem;
  		}
  		
  		.footer {
  			flex-shrink:0;
  			right:0;
  			bottom:0;
  			left:0;
  			width:100%;
  			padding:2rem;
  			background-color:#4d4d4d;
			background:linear-gradient(to bottom, #4d4d4d 5%, #747474 100%);
  			color:#ffffff;
			font-size:12px;
  		}
  		
  		.myButton {
  			width:75%;
			box-shadow:inset 0px 1px 0px 0px #fce2c1;
			background:linear-gradient(to bottom, #ffc477 5%, #fb9e25 100%);
			background-color:#ffc477;
  			border-left:1px solid black;
  			border-right:1px solid black;
  			border-top:1px solid black;
			display:inline-block;
			cursor:pointer;
			color:#ffffff;
			font-weight:bold;
			padding:1rem;
			text-decoration:none;
			text-shadow:0px 1px 0px #cc9f52;
		}
		
		.myButton:hover {
			background:linear-gradient(to bottom, #fb9e25 5%, #ffc477 100%);
			background-color:#fb9e25;
		}
		
		.myButton:active {
			position:relative;
			top:1px;
		}
  		
  	</style>
  	
<!-- statistics HTML -->
    	
	<nav class="navbar navbar-expand-lg navbar-inverse bg-inverse">
		<a class="navbar-doggotrumps" href="http://localhost:7777/toptrumps">
		<img src="https://i.pinimg.com/originals/f3/d8/97/f3d897a2e4842b17f52a41154ab8fe62.png" width="400" alt="Doggo Trumps">
        </a>
   	</nav>
    	
    <div class="mainContainer">
    		
    	<div class="buttons">
    		<input class="mybutton" type="button" value="Start New Game" 
			onclick="window.location.href='/toptrumps/game'" />
    	</div>
    		
    	<div class="results">
    	<p id="results"></p>
    	<p id="results1"></p>
    	<p id="results2"></p>
    	<p id="results3"></p>
    	<p id="results4"></p>
    	</div>
    	
	</div>
	
	<div class="footer">
		Made by You Git What You Give</br>Jessica Lavin - Simon Manton Milne - Cyriac Mathew - Daniel Mitchell - Wesley Scott
	</div>
		
	<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				drawDb();
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		
		</script>
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function drawDb() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/drawDb"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var statsText = JSON.parse(xhr.response); // the text of the response
 					console.log(statsText);
					document.getElementById("results").innerHTML="Total Games: " + statsText[0];
					document.getElementById("results1").innerHTML="Human Wins: " + statsText[1];
					document.getElementById("results2").innerHTML="AI Wins: " + statsText[2];
					document.getElementById("results3").innerHTML="Avg. Draws: " + statsText[3];
					document.getElementById("results4").innerHTML="Highest No. of Rounds: " + statsText[4];
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

		</script>
		
		</body>
</html>