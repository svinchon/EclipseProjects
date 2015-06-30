<%
java.util.ResourceBundle rb ;
rb = java.util.ResourceBundle.getBundle("xBank");
String cn = rb.getString("CompanyName");
String ln = rb.getString("LogoName");
%>
<!DOCTYPE html><!--  needed for html 5 needed by bootstrap -->
<html lang="en">
	<head>
		<!-- meta -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!-- meta name="description" content="">
		<meta name="author" content="" -->
		<!-- needed for mobile devices for bootstrap-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- link favicon -->
		<!-- link rel="icon" href="../../favicon.ico"-->
		<!-- link (external css styles) -->
		<link href="bootstrap-3.3.5-dist/css/bootstrap.css" rel="stylesheet">
		<!-- styles -->
		<style type="text/css">
			/* tags */
			/* same as sfn */
			html{
				/* to center vertically login */
				/*position: relative;*/
				height: 100%;
			}
			body {
				/* to center vertically login */
				height: 100%;
				/* background-color: #eee;*/
			}
			body > .container {
				padding: 60px 15px 0;
			}
			/* added */
			input {
				margin-top: 2px;
				margin-bottom: 2px;
			}
			label {
				margin-top: 2px;
				margin-bottom: 2px;
			}
			/* class */
			/* same as test in sfn */
			.login-container {
				/*padding-top: 60px;*/
				/* to center vertically login */
  				/*min-height: calc(100vh - 40px);
  				height: calc(100% - 40px);
  				display:flex;
				align-items: center;*/
			}
			/*.navbar-logo {
				padding: 0;
			}*/
			.local-navbar {
				background-color: #E7E7E7;
			}
			.accounts-table {
				background-color: #E7E7E7;
			}
			/* id */
			#login-wrapper {
				/*margin-top: 50px;*/
				text-align: center;
				width: 100%;
			}
			#login-box {
				/* so it behaves like text */
				display: inline-block;
				/* width */
				width:225px;
				/* padding */
				padding: 15px;
				/* bg color */
				background-color: white;
				/* corners */
				-webkit-border-radius: 15px;
				-moz-border-radius: 15px;
				-ms-border-radius: 15px;
				-o-border-radius: 15px;
				border-radius: 15px;
				/* shadows */
				-webkit-box-shadow: 0 0 8px rgba(0, 0, 0, 0.4);
				-moz-box-shadow: 0 0 8px rgba(0, 0, 0, 0.4);
				box-shadow: 0 0 8px rgba(0, 0, 0, 0.4);
			}
			#login-header {
				font-size:18pt;
				margin-bottom: 5px;
			}
			#inlineCheckbox1 {
				margin-top: 2.5px;
			}
			#footer {
				/* fixed height */
				height: 40px;
				/* stick to bottom of page */
				position: absolute;
				bottom: 0;
				/* bg color */
				background-color: #E7E7E7;
				width: 100%;
				text-align: center;
				/*vertical-align: center;*/
			}
		</style>
	    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
	    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
	    <script src="old/ie-emulation-modes-warning.js"></script>
	</head>
	<body>
    <!-- Fixed navbar -->
		<nav class="navbar navbar-default navbar-fixed-top local-navbar"><!-- id="navbar"  -->
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#"><%= cn %></a>
					<!--  a class="navbar-brand navbar-logo" href="#">
						<img src="images/<%= ln %>" width="50%" heighth"50%" alt="">
					</a>-->
				</div>
				<div id="navbar" class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Home</a></li>
						<li><a href="#about">About</a></li>
						<li><a href="#contact">Contact</a></li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li role="separator" class="divider"></li>
								<li class="dropdown-header">Nav header</li>
								<li><a href="#">Separated link</a></li>
								<li><a href="#">One more separated link</a></li>
							</ul>
						</li>
						<li><a href="Logout">Logout</a></li>
					</ul>
				</div><!--/.nav-collapse -->
			</div>
		</nav>
	    <div class="container login-container">
			<!-- needed by bootstrap for responsive
			<!-- use container-fluid for full width -->
		<div data-ng-app="myApp" data-ng-controller="myCtrl">

			<div class="container accounts-table">
	  			<h2>Your Accounts</h2>
	  			<table class="table table-striped">
				    <thead>
				    	<tr>
				        	<th>Account Number</th>
				        	<th>Client Name</th>
				        	<th>Date</th>
				        	<th>Show Details</th>
				        	<th>Statement #</th>
				        	<th>View Data</th>
				        	<th>View PDF</th>
				      	</tr>
				    </thead>
				    <tbody>
				    	<tr data-ng-repeat-start="x in aiu">
<!-- 							<td> -->
<!-- 								<table> -->
<!-- 									<tr> -->
										<td>{{ x.AccountNumber }}</td>
							    		<td>{{ x.ClientName }}</td>
							    		<td>{{ x.Date }}</td>
							    		<td>{{ x.showDetails }}</td>
							    		<td>{{ x.itemIndex }}</td>
							    		<td><a
							    			href="#"
							    			data-ng-click="showDetailsFrame(x, 'Data');"
							    		>Data</a></td>
							    		<td><a
							    			href="#"
							    			data-ng-click="showDetailsFrame(x, 'PDF');"
							    		>PDF</a></td>
									</tr>
							      	<tr data-ng-repeat-end data-ng-show="x.showDetails">
							    		<td colspan="7">
							    			<iframe id="view-frame-{{x.itemIndex}}" width="100%" height="300" src=""></iframe>
							    		</td>
							      	</tr>
<!-- 				      			</table> -->
<!-- 							</td> -->
<!-- 						</tr> -->
				    </tbody>
			  	</table>
			</div>
		</div>
		</div>
		<footer id="footer" class="footer navbar-fixed-bottom">
			<div class="container">
				<p class="text-muted">Place sticky footer content here.</p>
				<!-- Powered by xPression &amp; InfoArchive -->
			</div>
		</footer>
		<!-- external scripts (js) -->
		<!-- jquery -->
		<script src="jquery-1.11.3/jquery.js"></script>
		<!-- bootstrap -->
    	<script src="bootstrap-3.3.5-dist/js/bootstrap.js"></script>
		<!-- custom js -->
    	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    	<script src="old/ie10-viewport-bug-workaround.js"></script>
        <script src="backstretch/backstretch.min.js"></script>
		<!-- script src="sfn/backstretch.min.js"></script -->
		<!-- script -->
		<script src="angular/angular.1.3.16.min.js"></script>
		<!-- for moving background image -->
	    <script>
		    /* triggers background image */
// 		    jQuery(document).ready(
// 		    	function($) {
// 		    		$.backstretch(
// 		    			[
// 			      			"images/kruger1.jpg", 
// 			      			"images/kruger2.jpg"
// 		      			],
// 		      			{duration: 3000, fade: 750}
// 		    		);
				
// 				}
// 		    );
		</script>
		<script>
		var app = angular.module('myApp', []);
		app.controller(
			'myCtrl',
			function($scope, $http) {
		    	$http.get("getDataFromIAAsJSON?accountnumber=1271857")
		    	.success(
		    		function(response) {	
		    			//console.log($scope.aiu);
		    			//console.log(typeof $scope.aiu);
		    			if( Object.prototype.toString.call( response.root.aiu ) === '[object Array]' ) {
		    				$scope.aiu = response.root.aiu;
		    				var index;
		    				for (index = 0; index < $scope.aiu.length; ++index) {
		    					$scope.aiu[index].showDetails = false;
		    					$scope.aiu[index].itemIndex = index;
		    				}
		    			} else {
		    				$scope.aiu = [ response.root.aiu ];
		    				$scope.aiu[1].showDetails = false;
		    				$scope.aiu[1].itemIndex = 0;
		    			}
		    		}
		    	);
		    	$scope.showDetailsFrame = function(item, format) {
		    		//alert("coucpu dos");
					console.log("showDetails: "+item.showDetails);
					console.log("Date: "+item.Date);
					console.log("format: "+format);
					console.log("item.format: "+item.format);
					lastShowDetails = item.showDetails;
					console.log("lastShowDetails: "+lastShowDetails);
					console.log("item.showDetails before: "+item.showDetails);
					console.log("item.itemIndex: "+item.itemIndex);
					var index;
					if (
							(format==item.format)
							||
							(item.format == null)
							||
							((format!=item.format) && (lastShowDetails==false))
							||
							((format==item.format ) && (lastShowDetails==true))
					){
						for (index = 0; index < $scope.aiu.length; ++index) {
							if (index!=item.itemIndex) {
								$scope.aiu[index].showDetails = false;	
							}
						}
						item.showDetails = !item.showDetails;
						console.log("item.showDetails changed: "+item.showDetails);
					}
					if (item.showDetails) {
						var URL = "http://www.google.com"
						if (format=="PDF")  {
							item.format="PDF";
							URL = "getContent?contentId=" + item.content_file['ns1:cid'] + ":" +item.start_page + ":" + item.page_count;
						} else {
							item.format="Data";
							URL = "getDataFromIAAsJSON?accountnumber=1271857";
						}
						loadIframe(
							'view-frame-'+item.itemIndex,
							URL
							//
						);
					}
				}
   			}
		);
		</script>
		<script>
		function loadIframe(iframeName, url) {
		    var $iframe = $('#' + iframeName);
		    if ( $iframe.length ) {
		        $iframe.attr('src',url);   
		        return false;
		    }
		    return true;
		}

		</script>
	</body>
</html>