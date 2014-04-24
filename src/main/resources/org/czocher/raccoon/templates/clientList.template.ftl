<#import "master.template.ftl" as m>
<@m.template>
			<h1>List of clients:</h1>
			<div class="list-group">
				<#if clientList?has_content>
					<#list clientList as item>
						<a href="/client?id=${item.id}" class="list-group-item">${item.id}. ${item.name}</a>
					</#list>
				<#else>
					<div class="list-group-item">No clients specified.</div>
				</#if>
				<a href="/add/client" class="list-group-item active center"><span class="glyphicon glyphicon-plus"></span> Add client</a>
			</div>
</@m.template>