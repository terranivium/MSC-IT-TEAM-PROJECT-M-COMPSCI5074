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
  		
  		.playerCard0 {
  			border:1px solid black;
  			display:none;
			background:linear-gradient(to bottom, #ffffe6 5%, #fbfbd1 100%);
  			background-color:#ffffe6;
			border-radius:10px;
  		}
  		
  		.playerCard1 {
  			border:1px solid black;
  			display:none;
			background:linear-gradient(to bottom, #ffffe6 5%, #fbfbd1 100%);
  			background-color:#ffffe6;
			border-radius:10px;
  		}
  		
  		.playerCard2 {
  			border:1px solid black;
  			display:none;
			background:linear-gradient(to bottom, #ffffe6 5%, #fbfbd1 100%);
  			background-color:#ffffe6;
			border-radius:10px;
  		}
  		
  		.playerCard3 {
  			border:1px solid black;
  			display:none;
			background:linear-gradient(to bottom, #ffffe6 5%, #fbfbd1 100%);
  			background-color:#ffffe6;
			border-radius:10px;
  		}
  		
  		.playerCard4 {
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
		
		<div class="playerCard0">
		
			<div class="cardHeader0">
				<p id="playerName0">Player Name</p>
				<p id="playerDesc0">Card Description</p>
				<picture>
				<img src="" alt="Dogs Pic goes here" style="width:100%;height:140px;"" id="playerPic0">
				</picture>
			</div>
				
			<div class ="cardContainer">
				<p id="playerSize0">Category 1</p>
				<p id="playerRare0">Category 2</p>
				<p id="playerTemp0">Category 3</p>
				<p id="playerIntel0">Category 4</p>
				<p id="playerCute0">Category 5</p>
			</div>
	
		</div>
			
		<div class="playerCard1">
		
			<div class="cardHeader1">
				<p id="playerName1">Player Name</p>
				<p id="playerDesc1">Card Description</p>
				<picture>
				<img src="" alt="Dogs Pic goes here" style="width:100%;height:140px;"" id="playerPic1">
				</picture>
			</div>
		
			<div class ="cardContainer">
				<p id="playerSize1">Category 1</p>
				<p id="playerRare1">Category 2</p>
				<p id="playerTemp1">Category 3</p>
				<p id="playerIntel1">Category 4</p>
				<p id="playerCute1">Category 5</p>
			</div>
	
		</div>
			
		<div class="playerCard2">
	
			<div class="cardHeader">
				<p id="playerName2">Player Name</p>
				<p id="playerDesc2">Card Description</p>
				<picture>
				<img src="" alt="Dogs Pic goes here" style="width:100%;height:140px;"" id="playerPic2">
				</picture>
			</div>
		
			<div class ="cardContainer">
				<p id="playerSize2">Category 1</p>
				<p id="playerRare2">Category 2</p>
				<p id="playerTemp2">Category 3</p>
				<p id="playerIntel2">Category 4</p>
				<p id="playerCute2">Category 5</p>
			</div>
	
		</div>
			
		<div class="gap"></div>
			
		<div class="gap"></div>
			
		<div class="playerCard3">
	
			<div class="cardHeader">
				<p id="playerName3">Player Name</p>
				<p id="playerDesc3">Card Description</p>
				<picture>
				<img src="" alt="Dogs Pic goes here" style="width:100%;height:140px;"" id="playerPic3">
				</picture>
			</div>
		
			<div class ="cardContainer">
				<p id="playerSize3">Category 1</p>
				<p id="playerRare3">Category 2</p>
				<p id="playerTemp3">Category 3</p>
				<p id="playerIntel3">Category 4</p>
				<p id="playerCute3">Category 5</p>
			</div>
	
		</div>
			
		<div class="playerCard4">
	
			<div class="cardHeader">
				<p id="playerName4">Player Name</p>
				<p id="playerDesc4">Card Description</p>
				<picture>
				<img src="" alt="Dogs Pic goes here" style="width:100%;height:140px;"" id="playerPic4">
				</picture>
			</div>
		
			<div class ="cardContainer">
				<p id="playerSize4">Category 1</p>
				<p id="playerRare4">Category 2</p>
				<p id="playerTemp4">Category 3</p>
				<p id="playerIntel4">Category 4</p>
				<p id="playerCute4">Category 5</p>
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

					
					for(i=0;i<playerCount;i++){
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
 					alert(playerCount);
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