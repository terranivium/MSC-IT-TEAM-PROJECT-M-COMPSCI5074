<html>

	<head>
		<!-- Web page title -->
			<title>Doggo Trumps</title>
			<meta name="viewport" content="width=device-width, initial-scale=1">
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
	
<!-- selection screen CSS -->
	
	<style>
		
		body {
			background-color:#e6e6e6;
			font-family:Verdana;
  			text-align:center;	
		}
		
  		.navbar {
  			background-color:#4d4d4d;
			background:linear-gradient(to bottom, #4d4d4d 5%, #747474 100%);
  			padding:2rem;
  		}
  		
  		.menuWrapper {
  			padding-top:5rem;
  			padding-bottom:5rem;
			min-height:300px;
  		}
  		
  		.footer {
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
			box-shadow:inset 0px 1px 0px 0px #fce2c1;
			background:linear-gradient(to bottom, #ffc477 5%, #fb9e25 100%);
			background-color:#ffc477;
			border-radius:6px;
			border:1px solid #eeb44f;
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
		           
	</style>

<!-- selection screen HTML -->
		
	<nav class="navbar navbar-expand-lg navbar-inverse bg-inverse">
		<a class="navbar-doggotrumps" href="http://localhost:7777/toptrumps">
		<img src="https://i.pinimg.com/originals/f3/d8/97/f3d897a2e4842b17f52a41154ab8fe62.png" width="400" alt="Doggo Trumps">
        </a>
   	</nav>
   	
   	<div class = "menuWrapper">
   		<h4><b>Welcome to Doggo Top Trumps!</b></h4>
   		</br>
	    <h5>Would you like to play a new game or see previous game statistics?</h5>
	    </br>
		<p><input class="mybutton" type="button" value="New Game" 
		onclick="window.location.href='toptrumps/game'" />
		<input class="mybutton" type="button" value="Game Statistics" 
		onclick="window.location.href='toptrumps/stats'"/></p>
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
				
				//drawMain();
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
			
			function drawMain() {
				console.log("this got called");
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/drawMain"); // Request type and URL+parameters
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
 					document.getElementById("gametitle").innerHTML = responseText;
 					console.log(responseText);
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
			
			function helloJSONList() {
			
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
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
		</script>
	</body>
</html>