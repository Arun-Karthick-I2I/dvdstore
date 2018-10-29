<%@ page isErrorPage="true" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>DVDStore</title></head>
<body>
<c:set var="statusCode" value="<%= response.getStatus() %>" />
<c:if test="${404 == statusCode}">
<div style="text-align:center;margin-top:30px">
<img  src="/dvdstore/resources/404error.jpg" alt="Error 404" width="600" height="400">
<h3> We couldn't find the page you are looking for </h3>
</div>  
</c:if> 
<c:if test="${404 != statusCode}">
<h3 style="color:red;text-align:center">Oops!! An Error Occurred!!! Please Try Again Later</h3>
</c:if> 
<div style="text-align:center;">
<form action="/dvdstore/">
<button type="submit"> Take Me Back Home </button>
</form>
</div> 
</body> 
</html>
