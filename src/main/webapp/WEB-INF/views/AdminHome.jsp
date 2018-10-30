<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>DVDStore</title></head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<div id="adminView" style="text-align:center">
    <form method="GET">
        <h2 style="margin-top:100px"> Admin Home </h2>
        <button type="submit" formaction="/dvdstore/dvd/displayAllDvds"
            style="width:250px; margin-top:30px">Dvd</button><br>
        <button type="submit" formaction="/dvdstore/category/displayAllCategories" 
            style="width:250px; margin-top:30px" >Category</button><br>
        <button type="submit" formaction="/dvdstore/customer/displayAllCustomers"
            style="width:250px; margin-top:30px" >Customers</button><br>
        <button type="submit" formaction="/dvdstore/order/display" 
            style="width:250px; margin-top:30px" >Orders</button>
    </form>
</div>
</body>
<c:if test="${null != message}">
    <script> alert("${message}") </script>
    <c:remove var="message" />
</c:if>
</html>
