<html>

	<head>
		<!-- Web page title -->
    	<title>Doggo Trumps</title>
    	
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

    <body onload="initalize()"> 

    <!-- game screen CSS -->

	<style>

		body {
			background-color: #e6e6e6;
		}

  		.navbar {
  			padding: 2rem;
  			background-color: #4d4d4d;
  		}

  		.container {
  			text-align: center;

  		}

  		.footer {
  			position: absolute;
  			right: 0;
  			bottom: 0;
  			left: 0;
  			width: 100%;
  			padding: 2rem;
  			background-color: #4d4d4d;
  			color: #ffffff;
			font-family: Arial;
			font-size: 14px;
  		}

  		</style>

  		<!-- game screen HTML -->

    	<nav class="navbar navbar-expand-lg navbar-inverse bg-inverse">
        	<a class="navbar-brand" href="http://localhost:7777/toptrumps">
            <img src="https://i.pinimg.com/originals/ec/7e/79/ec7e79072d498c26433d9658d83d4a8b.png" width="250" height="100" alt="Doggo Trumps">
        </a>
   		</nav>

    	<div class="container">
   			<h1 id="cardList">players top card</h1>
			<h2 id="roundCounter">round counter</h2>
			<h2 id="activePlayer">active player</h2>
			<h2 id="roundWinner">winner goes here</h2>
			<input onclick="showCard();updateActivePlayer();updateRoundCounter()" type="button" value="Show Player Card" id="myButton1"></input>
			<input onclick="playCards('1')" type="button" value="Select Option 1" id="selectButton1"></input>
			<input onclick="playCards('2')" type="button" value="Select Option 2" id="selectButton1"></input>
			<input onclick="playCards('3');" type="button" value="Select Option 3" id="selectButton1"></input>
			<input onclick="playCards('4');" type="button" value="Select Option 4" id="selectButton1"></input>
			<input onclick="playCards('5');" type="button" value="Select Option 5" id="selectButton1"></input>
			<input onclick="setNewGameStates();startGame();selectPlayer()" type="button" value="Reset" id="resetButton"></input>

		</div>

		<div class="footer">
			Made by Git What You Give</br>Jessica Lavin - Daniel Mitchell - Simon Manton Milne - Wesley Scott
		</div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				initializeGame();
				
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
			
			function initializeGame() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/initializeGame"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert("Welcome"); // lets produce an alert
					updateRoundCounter();
					updateActivePlayer();
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			function startGame() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/startGame"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert("New Game Started"); // lets produce an alert
					
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			function selectPlayer() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/selectPlayer"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var player = xhr.response; // the text of the response
					//alert("Active Player Selected"); // lets produce an alert
					if(this.player != "Player1"){
					getBotChoice().call();
					}
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			function showCard() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showCard"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					 // the text of the response
					document.getElementById("cardList").innerHTML = xhr.response;
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}

			function playCards(stat) {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playCards?Stat="+stat); // Request type and URL+parameters

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
 					 // the text of the response
					document.getElementById("roundCounter").innerHTML = xhr.response;
					alert("cards played");
					selectWinners().call();
				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
			}
			
			function selectWinners() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/selectWinners"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					 // the text of the response
					document.getElementById("roundWinner").innerHTML = xhr.response;
					hasWon().call();
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			function updateActivePlayer() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/updateActivePlayer"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					 // the text of the response
					document.getElementById("activePlayer").innerHTML = xhr.response;
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			function updateRoundCounter() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/updateRoundCounter"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					 // the text of the response
					document.getElementById("roundCounter").innerHTML = xhr.response;
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			function getBotChoice() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getBotChoice"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					 // the text of the response
					var choice = xhr.response;
					playCards.call(choice);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			function hasWon() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/hasWon"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					 // the text of the response
					var choice = xhr.response;
					playCards.call(choice);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

		</script>
		
		</body>
</html>