<#import "../master.template.ftl" as m>
<@m.template>
			<h1>List of orders:</h1>
			<#if orderList?has_content>
				<table class="table table-hover table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>Order date</th>
							<th>Client name</th>
						</tr>
					</thead>
					<tbody>
						<#list orderList as item>
							<tr data-type="link" href="/${orderPath}?id=${item.id}">
								<td>${item.id}</td>
								<td>${item.timestamp}</td>
								<td>${item.getClient().getName()}</td>
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
				
			<a href="/${orderCreatePath}" class="btn btn-primary btn-block"><span class="glyphicon glyphicon-plus"></span> Add order</a>
			
</@m.template>