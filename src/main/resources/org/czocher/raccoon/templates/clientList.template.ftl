<#import "master.template.ftl" as m>
<@m.template>
			<h1>List of clients:</h1>
			<#if clientList?has_content>
				<table class="table table-hover table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>Name</th>
						</tr>
					</thead>
					<tbody>
						<#list clientList as item>
							<tr data-type="link" href="/${clientPath}?id=${item.id}">
								<td>${item.id}</td>
								<td>${item.name}</td>
							</tr>
						</#list>
					</tbody>
				</table>
				<script>
					$(document).ready(function(){
						$("[data-type='link']").click(function(){
							document.location.href = $(this).attr('href');
						});
					});
				</script>
			<#else>
				<div class="list-group-item">No clients specified.</div>
			</#if>
		
			<a href="/${newClientPath}" class="btn btn-primary btn-block"><span class="glyphicon glyphicon-plus"></span> Add client</a>
</@m.template>