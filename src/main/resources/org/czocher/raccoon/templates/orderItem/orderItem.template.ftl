<#import "../master.template.ftl" as m>
<@m.template>
	<h1>Order item #${orderItem.id}</h1>
	<ul>
		<li>Order #: <a href="/${orderPath}?id=${orderItem.order.id}">${orderItem.order.id}</a></li>
		<li>Client: <a href="/${clientPath}?id=${orderItem.order.client.id}">${orderItem.order.client.name}</a></li>
		<li>Product: <a href="/${productPath}?id=${orderItem.product.id}">${orderItem.product.name}</a></li>
		<li>Quantity: ${orderItem.quantity}</li>
	</ul>
</@m.template>