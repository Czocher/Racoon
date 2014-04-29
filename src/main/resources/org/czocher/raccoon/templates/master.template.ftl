<#macro template>
<!doctype html>

<html lang="en">
<head>
<meta charset="utf-8">

<title>Racoon Shop</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="/static/css/bootstrap.css">
<link rel="stylesheet" href="/static/css/style.css">
<script src="/static/js/jquery-1.11.0.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
</head>

<body>

	<div class="container">
		<div class="contain">
			<div class="row">
				<div class="col-md-12">
					<div class="jumbotron center">
						<h1 class="logo-text"><a href="/index">Raccoon Shop</a></h1>
						<img src="/static/logo.png" class="logo" />
					</div>
				</div>
			</div>
		</div>
		<div class="contain">
			<#nested>
		</div>
	</div>
</body>
</html>
</#macro>