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
			background-color:#e6e6e6;
			font-family:Verdana;
		}

  		.navbar {
  			background-color:#4d4d4d;
			background:linear-gradient(to bottom, #4d4d4d 5%, #747474 100%);
  			padding:2rem;
  		}
  		
  		.gameHeaderWrapper {
			background-color:#ffffe6;
			background:linear-gradient(to bottom, #ffffe6 5%, #fbfbd1 100%);
  			border-bottom:1px solid black;
  			padding-top:0.5rem;
  			padding-left:1rem;
  		}
  		
  		.gridLayout {
  		  	display:grid;
  			grid-template-columns:repeat(4, 1fr);
  			grid-gap:10px;
  			grid-auto-rows:minmax(100px, auto);
  			width:100%;
  			padding:1rem;
			min-height:250px;
  		}
  		
  		.controller {
  			text-align:center;
			background-color:#ffffe6;
			background:linear-gradient(to bottom, #ffffe6 5%, #fbfbd1 100%);
  			margin-bottom:auto;
  			border:1px solid black;
			border-radius:6px;
  		}
  		
  		.myButton {
  		  	width: 100%;
			box-shadow:inset 0px 1px 0px 0px #fce2c1;
			background:linear-gradient(to bottom, #ffc477 5%, #fb9e25 100%);
			background-color:#ffc477;
			border:1px solid #eeb44f;
			border-radius:6px;
			display:inline-block;
			cursor:pointer;
			color:#ffffff;
			font-size:15px;
			font-weight:bold;
			padding:6px 24px;
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
  		
  		.activePlayerWrapper {
  			padding-top:1rem;
  			padding-left:1rem;
  			padding-right:1rem;
  			font-size:15px;
  		}
  
  		.selectButtonsWrapper {
			display:none;
  		}
  		
  		.cardHeader {
  			box-shadow:inset 0px 1px 0px 0px #fff6af;
			background:linear-gradient(to bottom, #ffec64 5%, #ffab23 100%);
			background-color:#ffec64;
  			text-align:center;
  			padding-top:1rem;
			border-radius:10px;
			font-size:15px;
  		}
  		
  		.cardContainer {
  			padding-top:1rem;
  			padding-left:1rem;
			font-size:13px;
  		}
  		
  		.playerOneCard {
  			border:1px solid black;
  			display:none;
			background:linear-gradient(to bottom, #ffffe6 5%, #fbfbd1 100%);
  			background-color:#ffffe6;
			border-radius:10px;
  		}
  		
  		.playerTwoCard {
  			border:1px solid black;
  			display:none;
			background:linear-gradient(to bottom, #ffffe6 5%, #fbfbd1 100%);
  			background-color:#ffffe6;
			border-radius:10px;
  		}
  		
  		.playerThreeCard {
  			border:1px solid black;
  			display:none;
			background:linear-gradient(to bottom, #ffffe6 5%, #fbfbd1 100%);
  			background-color:#ffffe6;
			border-radius:10px;
  		}
  		
  		.playerFourCard {
  			border:1px solid black;
  			display:none;
			background:linear-gradient(to bottom, #ffffe6 5%, #fbfbd1 100%);
  			background-color:#ffffe6;
			border-radius:10px;
  		}
  		
  		.playerFiveCard {
  			border:1px solid black;
  			display:none;
			background:linear-gradient(to bottom, #ffffe6 5%, #fbfbd1 100%);
  			background-color:#ffffe6;
			border-radius:10px;
  		}

  		.footer {
  			position:sticky;
  			width:100%;
  			padding:2rem;
  			background-color:#4d4d4d;
			background:linear-gradient(to bottom, #4d4d4d 5%, #747474 100%);
  			color:#ffffff;
			font-size:12px;
  			text-align:center;
  		}

  		</style>

<!-- game screen HTML -->

	<nav class="navbar navbar-expand-lg navbar-inverse bg-inverse">
		<a class="navbar-brand" href="http://localhost:7777/toptrumps">
        <img src="https://i.pinimg.com/originals/f3/d8/97/f3d897a2e4842b17f52a41154ab8fe62.png" width="400" alt="Doggo Trumps"></a>
   	</nav>
   	
   	<div class="gameHeaderWrapper">
   		<h5 id="gameHeader">GAME HEADER</h5>
   	</div>
   		
   	<div class="gridLayout"> 
   		
   		<div class = "controller">
   		
   			<div class="activePlayerWrapper">
   				<p id="activePlayer">ACTIVE PLAYER</p>
   			</div>
   		
   			<div class="mainButtonWrapper">
				<input class="myButton" onclick="" type="button" value="Next: Category Selection" id="mainButton"></input>
			</div>
		
			<div class="selectButtonsWrapper">
				<input class="myButton" onclick="updateSelectionHeader('1');" type="button" value="Select Option 1" id="selectButton1" ></input>
				<input class="myButton" onclick="updateSelectionHeader('2');" type="button" value="Select Option 2" id="selectButton2"></input>
				<input class="myButton" onclick="updateSelectionHeader('3');" type="button" value="Select Option 3" id="selectButton3"></input>
				<input class="myButton" onclick="updateSelectionHeader('4');" type="button" value="Select Option 4" id="selectButton4"></input>
				<input class="myButton" onclick="updateSelectionHeader('5');" type="button" value="Select Option 5" id="selectButton5"></input>
			</div>
		
		</div>
		
		<div class="playerOneCard">
		
			<div class="cardHeader">
				Player 1
				<p id="playerOneDesc">Card Description</p>
				<picture>
				<img src="" alt="Dogs Pic goes here" style="width:100%;height:140px;"" id="playerOnePic">
				</picture>
			</div>
				
			<div class ="cardContainer">
				<p id="playerOneSize">Category 1</p>
				<p id="playerOneRare">Category 2</p>
				<p id="playerOneTemp">Category 3</p>
				<p id="playerOneIntel">Category 4</p>
				<p id="playerOneCute">Category 5</p>
			</div>
	
		</div>
			
		<div class="playerTwoCard">
		
			<div class="cardHeader">
				Player 2 (AI)
				<p id="playerTwoDesc">Card Description</p>
				<picture>
				<img src="" alt="Dogs Pic goes here" style="width:100%;height:140px;"" id="playerTwoPic">
				</picture>
			</div>
		
			<div class ="cardContainer">
				<p id="playerTwoSize">Category 1</p>
				<p id="playerTwoRare">Category 2</p>
				<p id="playerTwoTemp">Category 3</p>
				<p id="playerTwoIntel">Category 4</p>
				<p id="playerTwoCute">Category 5</p>
			</div>
	
		</div>
			
		<div class="playerThreeCard">
	
			<div class="cardHeader">
				Player 3 (AI)
				<p id="playerThreeDesc">Card Description</p>
				<picture>
				<img src="" alt="Dogs Pic goes here" style="width:100%;height:140px;"" id="playerThreePic">
				</picture>
			</div>
		
			<div class ="cardContainer">
				<p id="playerThreeSize">Category 1</p>
				<p id="playerThreeRare">Category 2</p>
				<p id="playerThreeTemp">Category 3</p>
				<p id="playerThreeIntel">Category 4</p>
				<p id="playerThreeCute">Category 5</p>
			</div>
	
		</div>
			
		<div class="gap"></div>
			
		<div class="gap"></div>
			
		<div class="playerFourCard">
	
			<div class="cardHeader">
				Player 4 (AI)
				<p id="playerFourDesc">Card Description</p>
				<picture>
				<img src="" alt="Dogs Pic goes here" style="width:100%;height:140px;"" id="playerFourPic">
				</picture>
			</div>
		
			<div class ="cardContainer">
				<p id="playerFourSize">Category 1</p>
				<p id="playerFourRare">Category 2</p>
				<p id="playerFourTemp">Category 3</p>
				<p id="playerFourIntel">Category 4</p>
				<p id="playerFourCute">Category 5</p>
			</div>
	
		</div>
			
		<div class="playerFiveCard">
	
			<div class="cardHeader">
				Player 5 (AI)
				<p id="playerFiveDesc">Card Description</p>
				<picture>
				<img src="" alt="Dogs Pic goes here" style="width:100%;height:140px;"" id="playerFivePic">
				</picture>
			</div>
		
			<div class ="cardContainer">
				<p id="playerFiveSize">Category 1</p>
				<p id="playerFiveRare">Category 2</p>
				<p id="playerFiveTemp">Category 3</p>
				<p id="playerFiveIntel">Category 4</p>
				<p id="playerFiveCute">Category 5</p>
			</div>
	
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
			var topCards;
			var activePlayer;
			var roundNum;
			var handSizes;
			
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
					playersRem();
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
					var topCardsSize = Object.keys(topCards).length;
					
					document.getElementById("playerOneDesc").innerHTML= topCards[0].description + "</br>" + handSizes[0] + " cards in hand";
					document.getElementById("playerOneSize").innerHTML= headerNames[1] + ": " + topCards[0].categoryOne;
					document.getElementById("playerOneRare").innerHTML= headerNames[2] + ": " + topCards[0].categoryTwo;
					document.getElementById("playerOneTemp").innerHTML= headerNames[3] + ": " + topCards[0].categoryThree;
					document.getElementById("playerOneIntel").innerHTML= headerNames[4] + ": " + topCards[0].categoryFour;
					document.getElementById("playerOneCute").innerHTML= headerNames[5] + ": " + topCards[0].categoryFive;
					document.getElementById("playerOnePic").src = "../assets/" + topCards[0].description + ".jpg";
					
					document.getElementById("playerTwoDesc").innerHTML= topCards[1].description + "</br>" + handSizes[1] + " cards in hand";
					document.getElementById("playerTwoSize").innerHTML= headerNames[1] + ": " + topCards[1].categoryOne;
					document.getElementById("playerTwoRare").innerHTML= headerNames[2] + ": " + topCards[1].categoryTwo;
					document.getElementById("playerTwoTemp").innerHTML= headerNames[3] + ": " + topCards[1].categoryThree;
					document.getElementById("playerTwoIntel").innerHTML= headerNames[4] + ": " + topCards[1].categoryFour;
					document.getElementById("playerTwoCute").innerHTML= headerNames[5] + ": " + topCards[1].categoryFive;
					document.getElementById("playerTwoPic").src = "../assets/" + topCards[1].description + ".jpg";
					
					document.getElementById("playerThreeDesc").innerHTML= topCards[2].description + "</br>" + handSizes[2] + " cards in hand";
					document.getElementById("playerThreeSize").innerHTML= headerNames[1] + ": " + topCards[2].categoryOne;
					document.getElementById("playerThreeRare").innerHTML= headerNames[2] + ": " + topCards[2].categoryTwo;
					document.getElementById("playerThreeTemp").innerHTML= headerNames[3] + ": " + topCards[2].categoryThree;
					document.getElementById("playerThreeIntel").innerHTML= headerNames[4] + ": " + topCards[2].categoryFour;
					document.getElementById("playerThreeCute").innerHTML= headerNames[5] + ": " + topCards[2].categoryFive;
					document.getElementById("playerThreePic").src = "../assets/" + topCards[2].description + ".jpg";
					
					document.getElementById("playerFourDesc").innerHTML= topCards[3].description + "</br>" + handSizes[3] + " cards in hand";
					document.getElementById("playerFourSize").innerHTML= headerNames[1] + ": " + topCards[3].categoryOne;
					document.getElementById("playerFourRare").innerHTML= headerNames[2] + ": " + topCards[3].categoryTwo;
					document.getElementById("playerFourTemp").innerHTML= headerNames[3] + ": " + topCards[3].categoryThree;
					document.getElementById("playerFourIntel").innerHTML= headerNames[4] + ": " + topCards[3].categoryFour;
					document.getElementById("playerFourCute").innerHTML= headerNames[5] + ": " + topCards[3].categoryFive;
					document.getElementById("playerFourPic").src = "../assets/" + topCards[3].description + ".jpg";
					
					document.getElementById("playerFiveDesc").innerHTML= topCards[4].description + "</br>" + handSizes[4] + " cards in hand";
					document.getElementById("playerFiveSize").innerHTML= headerNames[1] + ": " + topCards[4].categoryOne;
					document.getElementById("playerFiveRare").innerHTML= headerNames[2] + ": " + topCards[4].categoryTwo;
					document.getElementById("playerFiveTemp").innerHTML= headerNames[3] + ": " + topCards[4].categoryThree;
					document.getElementById("playerFiveIntel").innerHTML= headerNames[4] + ": " + topCards[4].categoryFour;
					document.getElementById("playerFiveCute").innerHTML= headerNames[5] + ": " + topCards[4].categoryFive;
					document.getElementById("playerFivePic").src = "../assets/" + topCards[4].description + ".jpg";
					
					var x = document.getElementsByClassName("playerOneCard");
					x[0].style.display = "block";
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
					
					var y = document.getElementsByClassName("playerOneCard");
					y[0].style.display = "block";
					
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
				
				var e = document.getElementsByClassName("playerTwoCard");
				e[0].style.display = "block";
					
				var f= document.getElementsByClassName("playerThreeCard");
				f[0].style.display = "block";
					
				var g = document.getElementsByClassName("playerFourCard");
				g[0].style.display = "block";
					
				var h = document.getElementsByClassName("playerFiveCard");
				h[0].style.display = "block";
	
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
					
 					var x = document.getElementsByClassName("playerOneCard");
					x[0].style.display = "none";
					
					var x = document.getElementsByClassName("playerTwoCard");
					x[0].style.display = "none";
					
					var x = document.getElementsByClassName("playerThreeCard");
					x[0].style.display = "none";
					
					var x = document.getElementsByClassName("playerFourCard");
					x[0].style.display = "none";
					
					var x = document.getElementsByClassName("playerFiveCard");
					x[0].style.display = "none";
					
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
						playersRem().call();
						}
					else if(gameState == true){
						updateDb();
						alert("game over");
						}
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
			
			function playersRem() {
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playersRem"); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var playersLeft = xhr.repsonse;
 					//alert(playersLeft);
 					getHandSizes().call();
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
			
			function updateDb() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					//alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

		</script>
		
		</body>
</html>