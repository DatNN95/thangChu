<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/Style.css" />
<title>ユーザ管理</title>
</head>
<body align="center">
	<form action="Login.do" method="post">
		<center>
			<table class="tbl_input" cellpadding="4" cellspacing="0"
				width="400px">
				<tr>
					<th width="120px">&nbsp;</th>
					<th></th>
				</tr>
				<tr>
					<th colspan="2" align="left">アカウント名およびパスワードを入力してください</th>
				</tr>
				<c:forEach items="${listError}" var="error">
					<tr>
						<td class="errMsg" colspan="2" style="color: red;">${error}</td>
					</tr>
				</c:forEach>
				<tr align="left">
					<td class="lbl_left">アカウント名:</td>
					<td align="left"><input class="txBox" type="text"
						autofocus="autofocus" name="loginName" value="${fn:escapeXml(loginID)}"
						size="20" onfocus="this.style.borderColor='#0066ff';"
						onblur="this.style.borderColor='#aaaaaa';" /></td>
				</tr>
				<tr>
					<td class="lbl_left">パスワード:</td>
					<td align="left"><input class="txBox" type="password"
						name="pass" value="" size="22" /></td>
				</tr>
				<tr>
					<td></td>
					<td align="left"><input class="btn btn_wider" type="submit"
						value="ログイン" /></td>
				</tr>
			</table>
		</center>
	</form>
</body>
</html>