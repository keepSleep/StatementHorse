<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css" >
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/metisMenu.css" >
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/sb-admin-2.css" >
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/font-awesome.min.css" >
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/MyCSS.css" >

<script
	src="${pageContext.servletContext.contextPath}/js/jquery-3.1.1.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
<style>
  .modal-header, h4, .close {
      background-color: #5cb85c;
      color:white !important;
      text-align: center;
      font-size:30px;
  }
  .modal-footer {
      background-color: #f9f9f9;
  }
  
  table{
  	font-family:微軟正黑體;
  }
</style>

	<table style="background-color: lightblue; height: 70px;width:100%">
		<tr>
			<td ><a href="">
			<img src="${pageContext.servletContext.contextPath}/picture/statementhourse.png" class="img-circle" style="padding: 5px"></a></td>
			<td>
				<table>
					<tr>
						<td class="col-sm-1"> <button type="button" class="btn btn-outline btn-info btn-lg" style="border:0px blue none" >個股資料</button></td>
						<td class="col-sm-1"><button type="button" class="btn btn-outline btn-info btn-lg" style="border:0px blue none" >財報比較</button></td>
						<td class="col-sm-4">
							<div class="input-group custom-search-form">
                                <input type="text" style="width:500px;height:auto" class="form-control" placeholder="輸入股號或股名...">
                                <span class="input-group-btn" >
                                    <button class="btn btn-default" type="button" style="height:140%">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </span>
                            </div>
						</td>			
						<td class="col-sm-1 col-md-offset-8">
						<c:if test="${empty member_id}">
						 <button type="button" class="btn btn-outline btn-warning btn-lg" style="border:0px blue none" id="login"><i class="fa fa-user fa-fw"></i>登入</button>
						</c:if>
						<c:if test="${!empty member_id}">
						 <button type="button" class="btn btn-outline btn-warning btn-lg" style="border:0px blue none " id="logout"><i class="fa fa-user fa-fw"></i>登出</button>
						</c:if>
						<c:if test="${!empty member_id}">
						</td>
						<td class="col-sm-1">
 							<ul class="nav navbar-top-links navbar-right ">
            				    <li class="dropdown ">
               					  <a id="tg" class="dropdown-toggle btn-lg " data-toggle="dropdown" href="" style="width:65px">
                   				     <i class="fa fa-bell "></i> <i class="fa fa-caret-down"></i>
                  				  </a>
                   					 <ul class="dropdown-menu dropdown-messages">
                       					 <li>
                           					        <div class="chat-panel panel panel-default">
						                        <!-- /.panel-heading -->
						                        <div class="panel-body">
						                            <ul class="chat" id="newmessage">
						                            </ul>
						                        </div> 
						                        <div style="display:none" id="messagelength"></div>      
						                        <!-- /.panel-footer -->
						                    	</div>
	                        			 </li>             
                        				 <li>
                        				    <a class="text-center" href="#">
                         				       <strong>Read All Messages</strong>
                          					   <i class="fa fa-angle-right"></i>
                    				        </a>
                 					    </li>
                 					</ul>
                 				</li>
                 			</ul>
						</td>
						<td  class="col-sm-1">
							<ul class="nav navbar-top-links navbar-right ">
							<li class="dropdown">
			                    <a class="dropdown-toggle btn-lg" data-toggle="dropdown" href="" style="width:65px">
			                        <i class="fa fa-gear fa-fw"></i><i class="fa fa-caret-down"></i>
			                    </a>
			                    <ul class="dropdown-menu dropdown-user">
			                        <li><a  href="${pageContext.servletContext.contextPath}/changepassword/changepassword.jsp" style="cursor:pointer"><i class="fa fa-user fa-fw"></i>更改密碼</a>
			                        </li>
			                        <li><a  id="setting" style="cursor:pointer"><i class="fa fa-gear fa-fw"></i>通知設定</a>
			                        </li>
			                        <li class="divider"></li>
			                        <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
			                        </li>
			                    </ul>
			                </li>
			                </ul>
						</td>
						</c:if>
					</tr>
				</table>
			</td>

		</tr>
	</table>
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header" style="padding:35px 50px;">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 ><i class="fa fa-gear" ></i>通知設定</h4>
        </div>
        <div class="modal-body" style="padding:25px 50px;">
          
<!--           <form method="post" role="form" action="MsgIUDServlet"> -->
         	 <table class="table table-condensed">
					<caption>個人通知設定</caption>
					<thead>
						<tr>
							<th>股號</th>
							<th>月營收通知</th>
							<th>財報更新通知</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="stock" items="${memberlistno}" varStatus="count">
						<tr>
							<td>${stock[0]}</td>
							<td><input type="checkbox" id="${stock[0]}1" name="${stock[0]}1" ${(stock[1]==true)? 'checked' : ''}/></td>
							<td><input type="checkbox" id="${stock[0]}2" name="${stock[0]}2" ${(stock[2]==true)? 'checked' : ''}/></td>
						</tr>
						<div style="display:none" id="${count.count}">${stock[0]}</div>
						<div style="display:none" id="${count.last}">${count.count}</div>
					</c:forEach>
					
					</tbody>
		 		</table>
              <button type="button" class="btn btn-success btn-block" style="border:0px;" id="updatemessage"><span  class="glyphicon glyphicon-floppy-disk"></span> 完成更新</button>
 			       
<!--           </form> -->
          
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
          <p>Not a member? <a href="#">Sign Up</a></p>
          <p>Forgot <a href="#">Password?</a></p>
        </div>
      </div>
      
    </div>
  </div>
	<script>


	
	$(document).ready(function(){
		var last=$("#true").text();
// 		setInterval("refreshnews()",5000);
		$("#tg").click(function(){
			$("#tg").attr("style","color:#337ab7")
		})
// 		for (var i=1; i<=last; i++) {
// 			var stockno=$("#"+i).text();
// 			stockcheck1+=$("#"+i).text()+","+$("#"+stockno+"1").prop("checked")+","+$("#"+stockno+"2").prop("checked")+";"
// 		}
		
		
	    $("#setting").click(function(){
	        $("#myModal").modal();
	    });
	    $("#updatemessage").click(function(){
	    	var stockcheck1="";
	    	for (var i=1; i<=last; i++) {
				var stockno=$("#"+i).text();
				stockcheck1+=$("#"+i).text()+","+$("#"+stockno+"1").prop("checked")+","+$("#"+stockno+"2").prop("checked")+";"
			}
	    	console.log(stockcheck1)
	    	$.get("MsgIUDServlet",{"action":"jquery_check","stockcheck1":stockcheck1},function(data){
 	    		console.log(data)
 	    		
	    			var data1=data.split("]");
 	    		
// 	    			console.log(data1)
	    		for(var i=0;i<(data1.length-2);i++){
	    			var data2=data1[i].split(",");
	    			console.log(data2)
	    			
	    			if(i==0){
	    			var stock=data2[0].substring(2);
	    			
	    			var check1=data2[1].substring(1)
	    			
	    			var check2=data2[2].substring(1)
	    			
	    			}else{
	    			var stock=data2[1].substring(2);
	    			console.log(stock)
	    			var check1=data2[2].substring(1)
	    			console.log(check1)
	    			var check2=data2[3].substring(1)
	    			console.log(check2)
	    			}
	    			//console.log(main)
// 	    			main[i+]
	    			
// 	    			$("#"+main[i*3]+"1").prop("checked",main[(1+i*3)])
// 	    			$("#"+main[i*3]+"2").prop("checked",main[(2+i*3)])
// 	    			console.log($("#"+main[i*3]+"2").prop("checked",main[(2+i*3)]))
	    		}
	    		
	    	})
	    	
	    })

	
	
	});
	function refreshnews()
	{
	$.getJSON("GetNewsMsgServlet",function(data){
		console.log(data)
		$("#newmessage").empty();
		if($("#messagelength").val()!=data.length){
			$("#tg").attr("style","color:#c13353");
			}
		$("#messagelength").val(data.length);
		$.each(data,function(i,v){		
		$("#newmessage").append("<li class='clearfix'><a style='cursor:pointer'><div class='chat-body clearfix'><div class='header'><small class='pull-right text-muted'></small></div><p>"+v+"</p></div></a></li>");
		})
		
	})
		
		
	}
</script>
	
	
	


