<#import "../master.template.ftl" as m>
<@m.template>
	<h1>Client #${client.id} <a class="delete" href="/${clientDeletePath}?id=${client.id}"><span class="glyphicon glyphicon-trash"></span> </a></h1>
	<ul>
		<li>Name: ${client.name}</li>
	</ul>
	<h4>List of orders:</h4>
	<#if client.orders?has_content>
		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>Order date</th>
					<th>Client name</th>
				</tr>
			</thead>
			<tbody>
				<#list client.orders as item>
					<tr data-type="link" href="/${orderPath}?id=${item.id}">
						<td>${item.id}</td>
						<td>${item.timestamp}</td>
						<td>${item.client.name}</td>
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
		<div class="list-group-item">No orders specified.</div>
	</#if>
		
	<a href="/${orderCreatePath}?clientId=${client.id}" class="btn btn-primary btn-block"><span class="glyphicon glyphicon-plus"></span> Add order</a>
</@m.template>