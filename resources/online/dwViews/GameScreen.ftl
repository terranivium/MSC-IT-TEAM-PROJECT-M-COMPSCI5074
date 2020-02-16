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

	
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				startGame();
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
			var headerNames;
			var playersRem;
			var topCards;
			var activePlayer;
			var roundNum;
			var handSizes;
			var playerCount;
			
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
 					headerNames = JSON.parse(xhr.response); // the text of the response
 					document.getElementById("selectButton1").value="Select: " + headerNames[1];
 					document.getElementById("selectButton2").value="Select: " + headerNames[2];
 					document.getElementById("selectButton3").value="Select: " + headerNames[3];
 					document.getElementById("selectButton4").value="Select: " + headerNames[4];
 					document.getElementById("selectButton5").value="Select: " + headerNames[5];
					playersLeft();
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			function buildRoundCards() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/buildRoundCards"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					topCards = JSON.parse(xhr.response);
					
					for(var i=0;i<playerCount;i++){
						document.getElementById("playerName"+i).innerHTML= playersRem[i];
						document.getElementById("playerDesc"+i).innerHTML= topCards[i].description + " (" + handSizes[i] + ")";
						document.getElementById("playerSize"+i).innerHTML= headerNames[1] + ": " + topCards[i].categoryOne;
						document.getElementById("playerRare"+i).innerHTML= headerNames[2] + ": " + topCards[i].categoryTwo;
						document.getElementById("playerTemp"+i).innerHTML= headerNames[3] + ": " + topCards[i].categoryThree;
						document.getElementById("playerIntel"+i).innerHTML= headerNames[4] + ": " + topCards[i].categoryFour;
						document.getElementById("playerCute"+i).innerHTML= headerNames[5] + ": " + topCards[i].categoryFive;
						document.getElementById("playerPic"+i).src = "../assets/" + topCards[i].description + ".jpg";
					}
					selectPlayer().call();
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
					document.getElementById("mainButton").value = "Next: Category Selection";
				
					var x = document.getElementsByClassName("activePlayerWrapper");
					x[0].style.display = "block";
					
					for(var i = 0;i<playerCount;i++)
					{
					if(playersRem[i] === "Player1")
					{
					var y = document.getElementsByClassName("playerCard0");
					y[0].style.display = "block";
					}
					}
					
					updateRoundCounter();
					updateActivePlayer();
					
 					var isBot = JSON.parse(xhr.response); // the text of the response
					if(isBot == "true")
						{
						document.getElementById("mainButton").onclick = function() { getBotChoice() };
						}
					else if(isBot == "false")
						{
						document.getElementById("mainButton").onclick = function() { showCardButtons() };
						}
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			function showCardButtons() {
					var x = document.getElementsByClassName("selectButtonsWrapper");
					x[0].style.display = "block";
					
					var y = document.getElementsByClassName("mainButtonWrapper");
					y[0].style.display = "none";
					
					var z = document.getElementsById("gameHeader").innerHTML = roundNum + ": Waiting on " + activePlayer + "(You) to select a category ";	
			}
			
			function updateSelectionHeader(stat) {
				var choice = stat;
 				
 				var a = document.getElementById("gameHeader");
				a.innerHTML = roundNum + ": " + activePlayer + " selected " + headerNames[choice];
				
				var b = document.getElementById("mainButton");
				b.onclick = function() { playCards(choice).call(); };
				b.value = "Show Winner";
				
				var c = document.getElementsByClassName("mainButtonWrapper");
				c[0].style.display = "block";
					
				var d = document.getElementsByClassName("selectButtonsWrapper");
				d[0].style.display = "none";
				
				for(i=0;i<playerCount;i++)
				{
				var e = document.getElementsByClassName("playerCard"+i);
				e[0].style.display = "block";
				}
			}
			
			function playCards(stat) {
			var choice = stat;
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
					
					for(i=0;i<playerCount;i++)
					{
 					var x = document.getElementsByClassName("playerCard"+i);
					x[0].style.display = "none";
					}
					
					var x = document.getElementsByClassName("activePlayerWrapper");
					x[0].style.display = "none"; 
					
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
					document.getElementById("gameHeader").innerHTML = xhr.response;
					var y = document.getElementById("mainButton");
					y.value = "Next Round"; 
					y.onclick = function() { hasWon().call(); };
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
					var gameState = JSON.parse(xhr.response);
					if(gameState == false)
					{
					resetRound();
					}
					else if(gameState == true)
					{
					document.getElementById("gameHeader").innerHTML = "GAME OVER: " + activePlayer + " has won the game";
					resetGame();
					}
				}
				
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
 					 activePlayer = JSON.parse(xhr.response);
					document.getElementById("activePlayer").innerHTML = JSON.parse(xhr.response) + " is currently the active player.";
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
 					 roundNum = JSON.parse(xhr.response);
					document.getElementById("gameHeader").innerHTML = JSON.parse(xhr.response) + ": Players have drawn their cards." ;
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
					var botChoice = JSON.parse(xhr.response);
					updateSelectionHeader(botChoice);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			function getHandSizes() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playerHandSizes"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					handSizes = JSON.parse(xhr.response);
 					buildRoundCards();
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			function playersLeft() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playersLeft"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					playersRem = JSON.parse(xhr.response);
 					playerCount = Object.keys(playersRem).length;
 					
 					getHandSizes().call();
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
			
			function resetRound() {
				document.getElementById("mainButton").value= "Next: Category Selection";
				var x = document.getElementsByClassName("activePlayerWrapper");
				x[0].style.display = "block";	
				
				playersLeft();
			}
			
			function resetGame() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/setNewGameState"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					alert("Returning to main menu...");
 					window.history.go(-1);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}

		</script>
		
		</body>
</html>