<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>StatementHorse</title>
</head>
<body>
	<jsp:include page="/top/Top.jsp" />

	<table align=center>
	<tr><td>
		<div class="col-lg-12"
			style="margin:0 auto;width: 800px;height:650px">
			<table align=center style="width: 500px">
				<tr>
					<td><h1>修改密碼</h1></td>
				</tr>
			</table>
			<table align=center style="width: 500px"
				class="table table-condensed">
				<tr>
					<td style="margin: 0px; padding: 0px"></td>
				</tr>
			</table>
			<form method="post" action="ChangePassword">
				<table align=center style="width: 500px">
					<tr>
					</tr>
					<tr>
						<td class="form-group" ><label><h3>
									<i class="fa fa-unlock-alt" style="color: orange"></i>請輸入您的密碼
								</h3></label><input type="password" class="form-control" placeholder="輸入舊的密碼" name="oldpassword"><p style="color:red">${ErrorMsg.erroroldMsg} ${ErrorMsg.oldpassword}</p></td>
					</tr>
					<br>
					<tr>
						<td class="form-group"  style="padding: 10px 0px 5px 0px"><label><h3>
									<i class="fa fa-unlock-alt" style="color: blue"></i>新密碼
								</h3></label> <input type="password" class="form-control" placeholder="輸入新的密碼" name="newpassword1"><p style="color:red">${ErrorMsg.newpassword1null} ${ErrorMsg.errornewMsg}</p></td>
					</tr>
					<tr>
						<td class="form-group"  style="padding: 5px 0px 5px 0px"><label><h3>
									<i class="fa fa-unlock-alt" style="color: blue"></i>再次輸入新密碼
								</h3></label> <input type="password" class="form-control" placeholder="再次輸入新的密碼" name="newpassword2"><p style="color:red">${ErrorMsg.newpassword2null} ${ErrorMsg.errornewMsg}</p></td>
					</tr>
					<tr>
						<td style="padding: 8px 0px"><input type=submit
							style="border-color: gray"
							class="form-control btn btn-outline btn-primary" value="更新">
						</td>
					</tr>
				</table>
			</form>
		</div>
		</td></tr>
	</table>
</body>
</html>