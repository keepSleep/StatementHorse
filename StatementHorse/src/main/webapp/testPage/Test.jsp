<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/bootstrap.min.css">
<script src="${pageContext.servletContext.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/jquery-ui.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/js/moment.js"></script>
<script src ="${pageContext.servletContext.contextPath}/js/datetimepicker.js"></script>


</head>
<body>

<div class="container">
    <div class="row">
        <div class='col-sm-6'>
            <div class="form-group">
                <div class='input-group date' id='datetimepicker5' style="cursor: pointer">
                    <input type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
        </div>
        <script type="text/javascript">
        
            $(function () {

			
            	
                $('#datetimepicker5').datetimepicker({
//                 	 minView: "month", //选择日期后，不会再跳转去选择时分秒 
//                 	    language:  'zh-CN',
//                 	    format: 'yyyy-mm-dd',
//                 	    todayBtn:  1,
//                 	    autoclose: 1
  
//                 	 format: 'yyyy-MM-dd'
//                 	 todayBtn:  true,
//                 	 autoclose: true
                
                	defaultDate: "11/1/2013",
                    disabledDates: [
                        //moment("12/25/2013"),
                        new Date(2013, 11 - 1, 21),
                        "11/22/2013 00:53"
                    ]
                });
            });
            
            DateTimePicker.Format(yyyy/MM/dd);
        </script>
    </div>
</div>



</body>
</html>