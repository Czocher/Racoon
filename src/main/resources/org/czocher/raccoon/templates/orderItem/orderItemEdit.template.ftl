<#import "../master.template.ftl" as m>
<@m.template>
	<h1>Edit order item</h1>
	<form role="form" id="form" action="/${orderItemEditPath}" method="post">
		<div class="form-group">
			<label for="productId">Product name</label>
			<select class="form-control" id="productId" name="productId">
				<#list productList as item>
  					<option value="${item.id}">${item.name}</option>
  				</#list>
			</select>
		</div>
		<div class="form-group">
			<label for="quantity">Quantity</label>
			<input type="text" class="form-control" id="quantity" name="quantity"
				placeholder="Enter product quantity" required value="${orderItem.quantity}">
		</div>
		<input type="hidden" id="id" name="id" value="${orderItem.id}" />
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
	<script>
		$(document).ready(new function(){
			$("#form").validate({
				rules: {
					quantity: {
						required: true,
						number: true
					}
				}
			});
		});
	</script>
</@m.template>
