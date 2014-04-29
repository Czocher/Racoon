<#import "master.template.ftl" as m> <@m.template>
	<h1>Order #${order.id}</h1>
	<ul>
		<li>Timestamp: ${order.timestamp}</li>
	</ul>
	<h1>List of items in this order:</h1>
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
			<tr data-type="link" href="/${productPath}?id=${item.id}">
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
	<div class="list-group-item">No products specified.</div>
	</#if>
	
	<a href="/add/order" class="btn btn-primary btn-block"><span
		class="glyphicon glyphicon-plus"></span> Add item</a>
</@m.template>
