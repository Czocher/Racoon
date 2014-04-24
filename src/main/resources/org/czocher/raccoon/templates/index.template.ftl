<#import "master.template.ftl" as m>
<@m.template>
    <div class="row vertical-center-row">
				<div class="col-md-6 center">
					<a class="btn btn-primary btn-lg btn-block" href="${clients}">
						<span class="glyphicon glyphicon-user "></span> Show client list
					</a>
				</div>
				<div class="col-md-6 center">
					<a class="btn btn-primary btn-lg btn-block" href="${orders}">
						<span class="glyphicon glyphicon-shopping-cart"></span> Show order
						list
					</a>
				</div>
			</div>
</@m.template>