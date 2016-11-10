<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="3;url=${pageContext.servletContext.contextPath}/MessageIndex.jsp" />
<title>CorrectPage</title>
</head>
<body>
	<jsp:include page="/top/Top.jsp" />
	<table align=center>
		<tr>
			<td><div class="col-lg-12"
					style="margin: 0 auto; width: 800px; height: 650px">
					<table align=center style="width: 550px">
						<tr>
							<td style="padding:200px 0 0px 0"><h1 style="color:#FFBB00">更新密碼成功 畫面於3秒後轉跳</h1></td>
						</tr>
						<tr>
							<td><h2 style="color:#99FF33">如果畫面未轉跳<a href="${pageContext.servletContext.contextPath}/MessageIndex.jsp">請點選這裡</a></h2></td>
						</tr>
					</table>
				</div></td>
		</tr>
	</table>
</body>
</html>