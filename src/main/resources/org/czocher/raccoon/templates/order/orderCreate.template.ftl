<#import "../master.template.ftl" as m>
<@m.template>
	<h1>New order</h1>
	<form role="form" id="form" action="/${orderCreatePath}" method="post">
		<div class="form-group">
			<label class="sr-only" for="name">Client name</label>
			<select class="form-control" id="clientId" name="clientId">
				<#list clientList as item>
  					<option value="${item.id}">${item.name}</option>
  				</#list>
			</select>
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
	<script>
		$(document).ready(new function(){
			$("#form").validate();
		});
	</script>
</@m.template>
