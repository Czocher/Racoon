<#import "master.template.ftl" as m>
<@m.template>
	<form role="form" action="/${newClientPath}" method="post">
		<div class="form-group">
			<label class="sr-only" for="name">Email address</label>
			<input type="text" class="form-control" id="name" name="name"
				placeholder="Enter client name">
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
</@m.template>
