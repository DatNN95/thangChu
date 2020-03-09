<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>ユーザ管理</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/Style.css" />
<script src="js/user.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<form action="ListUser.do" method="get" name="inputform">
		<table class="tbl_input" border="0" width="80%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="center" colspan="2">
					<div style="height: 50px"></div>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">${message}</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<div style="height: 70px"></div>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input class="btn" type="submit"
					value="OK" /></td>
			</tr>
		</table>
	</form>
	<!-- Begin vung footer -->
	<jsp:include page="footer.jsp" />
	<!-- End vung footer -->
</body>
</html>