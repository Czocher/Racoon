<#import "master.template.ftl" as m>
<@m.template>
	<h1>Client #${client.getId()}</h1>
	<ul>
		<li>Name: ${client.getName()}</li>
	</ul>
	<h4>List of orders:</h4>
	<#if client.getOrders()?has_content>
		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th>#</th>
					<th>Product</th>
					<th>Quantity</th>
					<th>Client name</th>
				</tr>
			</thead>
			<tbody>
				<#list client.getOrders() as item>
					<tr data-type="link" href="/${orderPath}?id=${item.id}">
						<td>${item.id}</td>
						<td>${item.product}</td>
						<td>${item.quantity}</td>
						<td>${item.getClient().name}</td>
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
		
	<a href="/add/order" class="btn btn-primary btn-block"><span class="glyphicon glyphicon-plus"></span> Add order</a>
</@m.template>