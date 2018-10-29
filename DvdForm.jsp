<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>DVDStore</title>
    <script type="text/javascript" src="/dvdstore/resources/DvdStoreScripts.js"></script>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<div id="dvdFormDiv" style="text-align:center">
<form:form id="dvdForm" modelAttribute="dvd" method="POST" onsubmit="return checkType() && checkCategories();">
    <c:if test="${null != dvd.id}">
    <b>Enter DVD Details to Update :</b><br>
    </c:if>
    <c:if test="${null == dvd.id}">
    <b>Enter DVD Details :</b><br>
    </c:if>
    <table align="center" cellspacing="10">
        <c:if test="${null != dvd.id}">
        <tr>
            <th style="text-align:right">DVD ID :</th>
            <td><form:input path="id" value="${dvd.id}" readOnly="readOnly" style="width:200px"/></td>
        </tr>
        </c:if>
        <tr>
            <th style="text-align:right">Title :</th>
            <td><form:input path="title" maxlength="30" value="${dvd.title}" 
                style="width:200px" autofocus="autofocus" required="required" /></td>
        </tr>
        <tr>
            <th style="text-align:right">Language :</th>
            <td><form:input path="language" pattern="[A-Za-z]{1,30}" maxlength="30" value="${dvd.language}" 
                style="width:200px" required="required" /></td>
        </tr>
        <tr>
            <th style="text-align:right">Copies :</th>
            <td><form:input type="number" path="copies" min="1" max="250" value="${dvd.copies}" 
                style="width:200px" required="required" /></td>
        </tr>
        <tr>
            <th style="text-align:right">Rating :</th>
            <td><form:input type="number" path="rating" min="1" max="10" step="0.1" value="${dvd.rating}" 
                style="width:200px" required="required" /></td>
        </tr>
        </tr>
        <tr>
            <th style="text-align:right">Price :</th>
            <td><form:input type="number" path="price" min="10" max="750" step="0.50" value="${dvd.price}" 
                style="width:200px" required="required" /></td>
        </tr>
        <tr>
            <th style="text-align:right">Release Date :</th>
            <td><form:input type="date" path="releaseDate" max="${today}" value="${dvd.releaseDate}" 
                style="width:200px" required="required" /></td>
        </tr>
        <tr>
            <th style="text-align:right">Type :</th>
            <td>
                <label><input type="radio" name="type" value="720P HD READY" 
                    <c:if test="${'720P HD READY' == dvd.type}">checked</c:if> />720P HD READY</label>
                <label><input type="radio" name="type" value="1080P FULL HD"
                    <c:if test="${'1080P FULL HD' == dvd.type}">checked</c:if> />1080P FULL HD</label>
                <label><input type="radio" name="type" value="4K ULTRA HD"
                    <c:if test="${'4K ULTRA HD' == dvd.type}">checked</c:if> />4K ULTRA HD</label>
            </td>
        </tr>
        <tr>
            <th style="text-align:right" valign="top">Category :</th>
            <td>
                <c:forEach var="category" items="${categories}">
                    <input type="checkbox" path="category" name="category" value="${category.id}" 
                        <c:if test="${(dvd.categories).contains(category)}">checked</c:if> />
                    ${category.name}<br>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td><br></tr>
        </tr>
    </table>
</form:form>
<div>
    <form id="back" method="GET" action="/dvdstore/dvd/displayAllDvds"></form>
    <c:if test="${null != dvd.id}">
        <button form="dvdForm" type="submit" style="margin-right:30px" formaction="/dvdstore/dvd/update"
            onclick="return confirm('Are you sure want to Update?')">Update Dvd</button></td>
        <button form="back" type="submit" style="margin-right:30px">Back</button></a>
    </c:if>
    <c:if test="${null == dvd.id}">
        <button form="dvdForm" type="reset" style="margin-right:30px" onclick="return confirm('Are you sure want to Reset?')">
            Reset</button></td>
        <button form="dvdForm" type="submit" style="margin-right:30px" formaction="/dvdstore/dvd/create" 
            onclick="return confirm('Are you sure want to Create?')">Create Dvd</button></td>
        <button form="back" type="submit" style="margin-right:30px" 
            onclick="return confirm('Are you sure want to Cancel?')">Cancel</button>
    </c:if>
</div>
</div>
</body>
</html>
