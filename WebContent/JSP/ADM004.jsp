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
<!-- Begin vung header -->
</head>
<body>
	<jsp:include page="header.jsp" />
	<!-- Begin vung input-->
	<c:choose>
		<c:when test="${userInfor.userId != 0}">
			<c:set var="setType"
				value="EditUserOK.do"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="setType" value="AddUserOK.do"></c:set>
		</c:otherwise>
	</c:choose>
	<form action="${setType}" method="post" name="inputform">
	<input type="hidden" name="keySession" value="${keySession}"/>
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">
						情報確認 <br> 入力された情報をＯＫボタンクリックでＤＢへ保存してください 
					</div>
					<div style="padding-left: 100px;">&nbsp;</div>
				</th>
			</tr>
			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="1" width="70%" class="tbl_input" cellpadding="4"
							cellspacing="0">
							<tr>
								<td class="lbl_left">アカウント名:</td>
								<td align="left">${userInfor.loginName }</td>
							</tr>
							<tr>
								<td class="lbl_left">グループ:</td>
								<td align="left">${userInfor.groupName }</td>
							</tr>
							<tr>
								<td class="lbl_left">氏名:</td>
								<td align="left" style="word-break: break-all;">${fn:escapeXml(userInfor.fullName)}</td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left" style="word-break: break-all;">${userInfor.fullNameKana }</td>
							</tr>
							<tr>
								<td class="lbl_left">生年月日:</td>
								<td align="left">${userInfor.birthday }</td>
							</tr>
							<tr>
								<td class="lbl_left">メールアドレス:</td>
								<td align="left" style="word-break: break-all;">${fn:escapeXml(userInfor.email) }</td>
							</tr>
							<tr>
								<td class="lbl_left">電話番号:</td>
								<td align="left">${userInfor.tel }</td>
							</tr>
							<tr>
								<th colspan="2"><a href="#" onclick="toggle();">日本語能力</a></th>
							</tr>

							<tr id="quali" style="display: none;">
								<td class="lbl_left">資格:</td>
								<td align="left">${userInfor.nameLevel }</td>
							</tr>
							<tr id="start" style="display: none;">
								<td class="lbl_left">資格交付日:</td>
								<td align="left">${fn:length(userInfor.nameLevel) != 0 ?userInfor.startDate: "" }</td>
							</tr>
							<tr id="end" style="display: none;">
								<td class="lbl_left">失効日:</td>
								<td align="left">${fn:length(userInfor.nameLevel) != 0 ? userInfor.endDate: "" }</td>
							</tr>
							<tr id="total" style="display: none;">
								<td class="lbl_left">点数:</td>
								<td align="left">${fn:length(userInfor.nameLevel) != 0 ?userInfor.total:"" }</td>
							</tr>

						</table>
					</div>
				</td>
			</tr>
		</table>
		<div style="padding-left: 100px;">&nbsp;</div>
		<!-- Begin vung button -->
		<div style="padding-left: 45px;">
			<table border="0" cellpadding="4" cellspacing="0" width="300px">
				<tr>
					<th width="175px" align="center">&nbsp;</th>
					<td><input class="btn" type="submit" value="OK" /></td>
				
					<td><input class="btn" type="button"
						onclick="location.href='${pageContext.request.contextPath}/AddUserInput.do?action=back&key=${keySession}'"
						value="戻る" /></td>
				</tr>
			</table>
			<!-- End vung button -->
	</form>
	<!-- End vung input -->
	<!-- Begin vung footer -->
	<jsp:include page="footer.jsp" />
	<!-- End vung footer -->
</body>

</html>