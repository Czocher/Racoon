<#import "../master.template.ftl" as m>
<@m.template>
	<h1>Product #${product.id} <a class="delete" href="/${productDeletePath}?id=${product.id}"><span class="glyphicon glyphicon-trash"></span> </a></h1>
	<ul>
		<li>Name: ${product.name}</li>
	</ul>
</@m.template>