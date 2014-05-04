<#import "../master.template.ftl" as m>
<@m.template>
	<h1>Edit client ${client.name}</h1>
	<form role="form" id="form" action="/${clientEditPath}" method="post">
		<div class="form-group">
			<label for="name">Client name</label>
			<input type="text" class="form-control" id="name" name="name"
				placeholder="Enter client name" required value="${client.name}">
			<input type="hidden" id="id" name="id" required value="${client.id}">
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
	<script>
		$(document).ready(new function(){
			$("#form").validate();
		});
	</script>
</@m.template>
