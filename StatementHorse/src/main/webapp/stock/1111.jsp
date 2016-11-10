<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>損益表</title>
</head>
<body>
	<div class="col-md-6"
				style="width: 800px; position: absolute; left: 300px; top: 0px; display: none"
				id="show_li_mgr">
				<div class="panel with-nav-tabs panel-info">
					<div class="panel-heading" style="background-color: lightblue">
						<ul class="nav nav-tabs"
							style="background-color: lightblue; font-size: x-large">
							<li class="active"><a href="#tab1info" data-toggle="tab"
								id="111">月營收</a></li>
							<li><a href="#tab2info" data-toggle="tab">每股盈餘</a></li>
							<li><a href="#tab3info" data-toggle="tab">股價</a></li>
							<li><a href="#tab4info" data-toggle="tab">股利</a></li>
							<li><a href="#tab5info" data-toggle="tab">本益比</a></li>
						</ul>
					</div>
					<div class="panel-body" style="height: 630px">
						<div class="tab-content" id="container">
							<div class="tab-pane fade in active" id="tab1info"
								style="width: 730px"></div>
							<div class="tab-pane fade" id="tab2info" style="width: 730px"></div>
							<div class="tab-pane fade" id="tab3info" style="width: 730px"></div>
							<div class="tab-pane fade" id="tab4info" style="width: 730px"></div>
							<div class="tab-pane fade" id="tab5info" style="width: 730px"></div>
						</div>
					</div>
				</div>
			</div>
</body>
</html>