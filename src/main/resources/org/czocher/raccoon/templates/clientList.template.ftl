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
			<h1>List of clients:</h1>
			<div class="list-group">
				<#if clientList?has_content>
					<#list clientList as item>
						<a href="/client?id=${item.id}" class="list-group-item">${item.id}. ${item.name}</a>
					</#list>
				<#else>
					<div class="list-group-item">No clients specified.</div>
				</#if>
				<a href="/add/client" class="list-group-item active center"><span class="glyphicon glyphicon-plus"></span> Add client</a>
			</div>
		</div>
	</div>
</body>
</html>