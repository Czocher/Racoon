<#import "master.template.ftl" as m>
<@m.template>
    <div class="row vertical-center-row">
				<div class="col-md-4 center">
					<a class="btn btn-primary btn-lg btn-block" href="${clientListPath}">
						<span class="glyphicon glyphicon-user "></span> Show client list
					</a>
				</div>
				<div class="col-md-4 center">
					<a class="btn btn-primary btn-lg btn-block" href="${productListPath}">
						<span class="glyphicon glyphicon-folder-open"></span> Show product
						list
					</a>
				</div>
				<div class="col-md-4 center">
					<a class="btn btn-primary btn-lg btn-block" href="${orderListPath}">
						<span class="glyphicon glyphicon-shopping-cart"></span> Show order
						list
					</a>
				</div>
			</div>
</@m.template>