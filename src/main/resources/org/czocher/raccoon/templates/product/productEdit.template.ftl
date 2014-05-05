<#import "../master.template.ftl" as m>
<@m.template>
	<h1>Edit product ${product.name}</h1>
	<form role="form" id="form" action="/${productEditPath}" method="post">
		<div class="form-group">
			<label for="name">Product name</label>
			<input type="text" class="form-control" id="name" name="name"
				placeholder="Enter product name" required value="${product.name}">
			<input type="hidden" id="id" name="id" required value="${product.id}">
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
	<script>
		$(document).ready(new function(){
			$("#form").validate();
		});
	</script>
</@m.template>
