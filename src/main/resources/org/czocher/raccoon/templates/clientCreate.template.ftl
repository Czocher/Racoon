<#import "master.template.ftl" as m>
<@m.template>
	<h1>New client</h1>
	<form role="form" id="form" action="/${clientCreatePath}" method="post">
		<div class="form-group">
			<label class="sr-only" for="name">Email address</label>
			<input type="text" class="form-control" id="name" name="name"
				placeholder="Enter client name" required>
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
	<script>
		$(document).ready(new function(){
			$("#form").validate();
		});
	</script>
</@m.template>
