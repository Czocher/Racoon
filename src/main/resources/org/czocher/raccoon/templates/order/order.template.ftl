<#import "../master.template.ftl" as m> <@m.template>
	<h1>Order #${order.id} <a class="operation" href="/${orderDeletePath}?id=${order.id}"><span class="glyphicon glyphicon-trash"></span> </a><a class="operation" href="/${orderEditPath}?id=${order.id}"><span class="glyphicon glyphicon-pencil"></span> </a></h1>
	<ul>
		<li>Client: <a href="/${clientPath}?id=${order.client.id}">${order.client.name}</a></li>
		<li>Timestamp: ${order.timestamp}</li>
	</ul>
	<h1>List order items:</h1>
	<#if order.orderItems?has_content>
	<table class="table table-hover table-striped">
		<thead>
			<tr>
				<th>#</th>
				<th>Product name</th>
				<th>Quantity</th>
			</tr>
		</thead>
		<tbody>
			<#list order.orderItems as item>
			<tr data-type="link" href="/${orderItemPath}?id=${item.id}">
				<td>${item.id}</td>
				<td>${item.product.name}</td>
				<td>${item.quantity}</td>
			</tr>
			</#list>
		</tbody>
	</table>
	<script>
		$(document).ready(function() {
			$("[data-type='link']").click(function() {
				document.location.href = $(this).attr('href');
			});
		});
	</script>
	<#else>
	<div class="list-group-item">No items specified.</div>
	</#if>
	
	<a href="/${orderItemCreatePath}?orderId=${order.id}" class="btn btn-primary btn-block"><span
		class="glyphicon glyphicon-plus"></span> Add item</a>
</@m.template>
