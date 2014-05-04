<#import "../master.template.ftl" as m>
<@m.template>
	<h1>New product</h1>
	<form role="form" id="form" action="/${productCreatePath}" method="post">
		<div class="form-group">
			<label for="name">Product name</label>
			<input type="text" class="form-control" id="name" name="name"
				placeholder="Enter product name" required>
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
	<script>
		$(document).ready(new function(){
			$("#form").validate();
		});
	</script>
</@m.template>
