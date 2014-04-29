<#import "master.template.ftl" as m>
<@m.template>
	<h1>Product #${product.id}</h1>
	<ul>
		<li>Name: ${product.name}</li>
	</ul>
</@m.template>