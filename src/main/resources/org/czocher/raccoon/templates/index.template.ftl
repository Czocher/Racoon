<!doctype html>

<html lang="en">
<head>
<meta charset="utf-8">

<title>Racoon Shop</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="static/css/bootstrap.css">
<link rel="stylesheet" href="static/css/style.css">
</head>

<body>

	<div class="container">
		<div class="contain">
			<div class="row">
				<div class="col-md-12">
					<div class="jumbotron center">
						<h1 class="logo-text"><a href="/index">Raccoon Shop</a></h1>
						<img src="static/logo.png" class="logo" />
					</div>
				</div>
			</div>
		</div>
		<div class="contain">
			<div class="row vertical-center-row">
				<div class="col-md-6 center">
					<a class="btn btn-primary btn-lg btn-block" href="${clients}">
						<span class="glyphicon glyphicon-user "></span> Show client list
					</a>
				</div>
				<div class="col-md-6 center">
					<a class="btn btn-primary btn-lg btn-block" href="${orders}">
						<span class="glyphicon glyphicon-shopping-cart"></span> Show order
						list
					</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>