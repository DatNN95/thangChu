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
	<!-- End vung header -->

	<!-- Begin vung input-->
	<c:choose>
		<c:when test="${userInfor.userId > 0}">
			<c:set var="setType"
				value="EditUserValidate.do?action=edit&id=${userInfor.userId}"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="setType" value="AddUserValidate.do?action=add"></c:set>
		</c:otherwise>
	</c:choose>

	<form action="${setType }" method="post" name="inputform">
		<table class="tbl_input" border="0" width="75%" cellpadding="0"
			cellspacing="0">
			<tr>
				<th align="left">
					<div style="padding-left: 100px;">会員情報編集</div>
				</th>
			</tr>
			<c:forEach items="${lsErr}" var="error">
				<tr>
					<td class="errMsg" style="color: red;">
						<div style="padding-left: 120px">${error}</div>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td align="left">
					<div style="padding-left: 100px;">
						<table border="0" width="100%" class="tbl_input" cellpadding="4"
							cellspacing="0">

							<tr>
								<c:if test="${userInfor.userId != 0}">
									<c:set var="type" value="readonly" />
								</c:if>
								<td class="lbl_left"><font color="red">*</font> アカウント名:</td>
								<td align="left"><input class="txBox" type="text"
									autofocus="autofocus" name="loginName"
									value="${fn:escapeXml(userInfor.loginName)}" size="15"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" ${type } /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> グループ:</td>
								<td align="left"><select name="groupId">
										<option value="0">選択してください</option>
										<c:forEach items="${groupEntities}" var="group">
											<option value="${group.groupId}" ${userInfor.groupId == group.groupId?"selected":"" }>${(group.groupName)}</option>
										</c:forEach>
								</select> <span>&nbsp;&nbsp;&nbsp;</span></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="fullName"
									value="${fn:escapeXml(userInfor.fullName)}" size="30"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left">カタカナ氏名:</td>
								<td align="left"><input class="txBox" type="text"
									name="fullNameKata"
									value="${fn:escapeXml(userInfor.fullNameKana)}"
									onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> 生年月日:</td>
								<td align="left"><select name="namSinh">
										<c:forEach items="${lsYear}" var="year">
											<option value="${year}" ${userInfor.lsBirthday[0] == year?"selected":"" }>${year}</option>

										</c:forEach>
								</select>年 <select name="thangSinh">
										<c:forEach items="${lsMonth}" var="month">
											<option value="${month}"
												${userInfor.lsBirthday[1] == month?"selected":"" }>${month}</option>
										</c:forEach>
								</select>月 <select name="ngaySinh">
										<c:forEach items="${lsDay}" var="day">
											<option value="${day}" ${userInfor.lsBirthday[2] == day?"selected":"" }>${day}</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font> メールアドレス:</td>
								<td align="left"><input class="txBox" type="text"
									name="email" value="${fn:escapeXml(userInfor.email)}"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<tr>
								<td class="lbl_left"><font color="red">*</font>電話番号:</td>
								<td align="left"><input class="txBox" type="text"
									name="tel" value="${fn:escapeXml(userInfor.tel)}"
									size="30" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
							</tr>
							<c:if test="${userInfor.getUserId() == 0}">
								<tr>

									<td class="lbl_left"><font color="red">*</font> パスワード:</td>
									<td align="left"><input class="txBox" type="password"
										name="password" value="${fn:escapeXml(userInfor.pass)}"
										size="30" onfocus="this.style.borderColor='#0066ff';"
										onblur="this.style.borderColor='#aaaaaa';" /></td>
								</tr>
								<tr>
									<td class="lbl_left">パスワード（確認）:</td>
									<td align="left"><input class="txBox" type="password"
										name="confirmPass"
										value="${fn:escapeXml(userInfor.confirmPass)}" size="30"
										onfocus="this.style.borderColor='#0066ff';"
										onblur="this.style.borderColor='#aaaaaa';" /></td>
								</tr>
							</c:if>


							<!-- vung hien thi trinh do tieng nhat -->
							<tr>
								<th align="left" colspan="2"><a href="#"
									onclick="toggle();">日本語能力</a></th>
							</tr>
							<tr id="quali" style="display: none;">
								<td class="lbl_left">資格:</td>
								<td align="left"><select name="nameLevel">
										<option value="">選択してください</option>
										<c:forEach items="${japanEntities}" var="level">
	
											<option value="${level.codeLevel}" ${userInfor.codeLevel == level.codeLevel?"selected":"" }>${fn:escapeXml(level.nameLevel)}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr id="start" style="display: none;">
								<td class="lbl_left">資格交付日:</td>
								<td align="left"><select name="yearStartDate">
										<c:forEach items="${lsYear}" var="year">
											<option value="${year}"
												${userInfor.lsStartDay[0] == year?"selected":"" }>${year}</option>
										</c:forEach>
								</select>年 <select name="monthStartDate">

										<c:forEach items="${lsMonth}" var="month">
											<option value="${month}"
												${userInfor.lsStartDay[1] == month?"selected":"" }>${month}</option>
										</c:forEach>
								</select>月 <select name="dayStartDate">
										<c:forEach items="${lsDay}" var="day">
											<option value="${day}" ${userInfor.lsStartDay[2] == day?"selected":"" }>${day}</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr id="end" style="display: none;">
								<td class="lbl_left">失効日:</td>
								<td align="left"><select name="yearEndDate">
										<c:forEach items="${lsEndYear}" var="year">
											<option value="${year}" ${userInfor.lsEndDay[0] == year?"selected":"" }>${year}</option>
										</c:forEach>
								</select>年 <select name="monthEndDate">
										<c:forEach items="${lsMonth}" var="month">
											<option value="${month}"
												${userInfor.lsEndDay[1]  == month?"selected":"" }>${month}</option>
										</c:forEach>
								</select>月 <select name="dayEndDate">
										<c:forEach items="${lsDay}" var="day">
											<option value="${day}" ${userInfor.lsEndDay[2] == day?"selected":"" }>${day}</option>
										</c:forEach>
								</select>日</td>
							</tr>
							<tr id="total" style="display: none;">
								<td class="lbl_left">点数:</td>
								<td align="left"><input class="txBox" type="text"
									name="total" value="${fn:escapeXml(userInfor.total)}"
									size="5" onfocus="this.style.borderColor='#0066ff';"
									onblur="this.style.borderColor='#aaaaaa';" /></td>
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
					<th width="170px" align="center">&nbsp;</th>
					<td>
					<input class="btn" type="submit" value="確認" /></td>
					<td>
					<c:choose>
						<c:when test="${userInfor.userId > 0}">
							<input class="btn" type="button"
						onclick="location.href='${pageContext.request.contextPath}/ShowUserInfor.do?id=${userInfor.userId}'"
						value="戻る" />
						</c:when>
						<c:otherwise>
						<input class="btn" type="button"
						onclick="location.href='${pageContext.request.contextPath}/ListUser.do?action=back'"
						value="戻る" />
						</c:otherwise>
					</c:choose>
					
					</td>
				</tr>
			</table>
		</div>
		<!-- End vung button -->
	</form>
	<!-- Begin vung footer -->
	<jsp:include page="footer.jsp" />
	<!-- End vung footer -->
</body>

</html>