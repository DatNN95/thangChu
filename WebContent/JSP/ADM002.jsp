<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/CSS/Style.css" />
	<title>ユーザ管理</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<form action="ListUser.do" method="get" name="mainform" style="margin-bottom : 0px" >
		<table class="tbl_input" border="0" width="90%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>会員名称で会員を検索します。検索条件無しの場合は全て表示されます。</td>
			</tr>
			<!-- End vung dieu kien tim kiem -->
			<tr>
				<td width="100%">
					<table class="tbl_input" cellpadding="4" cellspacing="0">
						<tr>
							<td class="lbl_left">氏名:</td>
							<td align="left"><input class="txBox" type="text" autofocus="autofocus"
								name="name" value="${fn:escapeXml(fullName)}" size="20"
								onfocus="this.style.borderColor='#0066ff';"
								onblur="this.style.borderColor='#aaaaaa';" /></td>
							<td></td>
						</tr>
						<tr>
							<td class="lbl_left">グループ:</td>
							<td align="left" width="80px"><select name="group_id">
									<option value="0">全て</option>
									<c:forEach items="${groupEntities}" var="group">
										<c:if test="${groupId == group.groupId}">
											<option value="${group.groupId}" selected="selected">${fn:escapeXml(group.groupName)}</option>
										</c:if>
										<c:if test="${groupId != group.groupId}">
											<option value="${group.groupId}">${fn:escapeXml(group.groupName)}</option>
										</c:if>
									</c:forEach>
							</select></td>
<!-- 	button tim kiem -->
							<td align="left"><input class="btn" type="submit" value="検索" />
							
								<input class="btn" type="button"
								onclick="location.href='${pageContext.request.contextPath}/AddUserInput.do'"
								value="新規追加" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	<!-- vung Kết quả tìm -->
	<c:url value="ListUser.do" var="url_sort">
		<c:param name="action" value="sort"></c:param>
		<c:param name="name" value="${fullName}"></c:param>
		<c:param name="group_id" value="${groupId}"></c:param>
	</c:url>
	<table class="tbl_list" border="1" cellpadding="4" cellspacing="0" width="80%">
		<tr class="tr2">
			<th align="center" width="35px">ID</th>
			<th align="left">氏名
			<c:choose>
				<c:when test="${sortByFullName == 'ASC'}">
					<a href="${url_sort}&sortType=full_name&sortValue=DESC">▲▽</a>
				</c:when>
				<c:otherwise>
					<a href="${url_sort}&sortType=full_name&sortValue=ASC">△▼</a>
				</c:otherwise>
			</c:choose>
			</th>
			<th align="left">生年月日</th>
			<th align="left">グループ</th>
			<th align="left">メールアドレス</th>
			<th align="left" width="70px">電話番号</th>
			<th align="left">日本語能力 
			
			<c:choose>
				<c:when test="${sortByCodeLevel == 'ASC'}">
					<a href="${url_sort}&sortType=code_level&sortValue=DESC">▲▽</a>
				</c:when>
				<c:otherwise>
					<a href="${url_sort}&sortType=code_level&sortValue=ASC">△▼</a>
				</c:otherwise>
			</c:choose>

			</th>
			<th align="left">失効日
			<c:choose>
				<c:when test="${sortByEndDate == 'DESC'}">
					<a href="${url_sort}&sortType=end_date&sortValue=ASC">△▼</a>
				</c:when>
				<c:otherwise>
					<a href="${url_sort}&sortType=end_date&sortValue=DESC">▲▽</a>
				</c:otherwise>
			</c:choose>
			</th>
			<th align="left" width="40">点数</th>
		</tr>
		<c:if test="${totalUser eq 0}">
			<td  colspan="9" style="color: black;">${MSG005}</td>
		</c:if>
		<c:if test="${totalUser gt 0}">
			<c:forEach items="${lisUserInfors}" var="ls">
				<tr>
					<td align="right"><a href="ShowUserInfor.do?id=${ls.userId}">${ls.userId}</a></td>
					<td >${fn:escapeXml(ls.fullName)}</td>
					<td>${ls.birthday}</td>
					<td>${fn:escapeXml(ls.groupName)}</td>
					<td>${fn:escapeXml(ls.email)}</td>
					<td>${fn:escapeXml(ls.tel)}</td>
					<td>${ls.nameLevel}</td>
					<td align="center">${ls.endDate}</td>
					<td align="right">${ls.total}</td>

				</tr>
			</c:forEach>
		</c:if>
	</table>
<!-- Begin vung paging -->
	<c:url value="ListUser.do" var="url_paging">
		<c:param name="action" value="paging"></c:param>
		<c:param name="name" value="${fullName}"></c:param>
		<c:param name="group_id" value="${groupId}"></c:param>
		<c:param name="sortByFullName" value="${sortByFullName}"></c:param>
		<c:param name="sortByCodeLevel" value="${sortByCodeLevel}"></c:param>
		<c:param name="sortByEndDate" value="${sortByEndDate}"></c:param>
		<c:param name="sortType" value="${sortType}"></c:param>
	</c:url>
	<table>
		<tr>
			<td class="lbl_paging" colspan="4"><c:if
					test="${listPaging ne null}">
					<c:if test="${totalPage > 1}">
						<c:if test="${currentPage gt 3}">
							<a href="${url_paging}&page=${listPaging[0]-limitPage}">
								&#60;&#60; </a>
						</c:if>
						<c:forEach items="${listPaging}" var="page">
							<c:if test="${page == currentPage}">
								<a href="${url_paging }&page=${page}"
									style="text-decoration: none;">${page}</a>&nbsp;
							</c:if>
							<c:if test="${page != currentPage}">
								<a href="${url_paging}&page=${page}">${page}</a>&nbsp;
							</c:if>
						</c:forEach>
						<c:if test="${totalPage gt (listPaging[0] + limitPage - 1)}">
							<a
								href="${url_paging}&page=${listPaging[0] + limitPage}">
								>> </a>
						</c:if>
					</c:if>
				</c:if></td>
		</tr>




	</table>
	<!-- End vung paging -->

	<jsp:include page="footer.jsp" />
</body>
</html>
