<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head><title>DVDStore</title></head>
    <body> 
        <jsp:include page="Header.jsp"></jsp:include>
        <div align="center">
            <form method="GET" action="/dvdstore/user/home">
                <button type="submit" style="width:250px">Go Back Home</button>
            </form>
        </div><br>
        <div style="width:100%">
            <c:if test="${true == resetSearch}">
                <div style="float:right">
                    <form method="GET" action="/dvdstore/order/display"> 
                        <button type="submit" style="width:100px" >Reset Search</button>
                    </form>
                </div>
            </c:if>
            <div style="float:right; margin-right:10px">
                <form method="GET" action="/dvdstore/order/search"> 
                    <input type="number" min=1 placeholder="Enter Order ID" name="orderId" required>
                    <button type="submit" style="width:100px" >Search</button>
                </form>
            </div>
        </div>
        <div style="width:100%; height:600px; overflow:auto">
        <table width=100%>
            <tr>
                <th>Order ID</th>
                <th>Customer ID</th>
                <th>DVD ID</th>
                <th>DVD Title</th>
                <th>DVD Language</th>
                <th>DVD Type</th>
                <th>DVD Price</th>
                <th>Address</th>
                <th>Order Date</th>
            </tr>
            <c:forEach var="order" items="${orders}">
            <tr align="center" style="height:30px">
                <td>${order.id}</td>
                <td>${order.customerId}</td>
                <td>${(order.dvd).id}</td>
                <td>${(order.dvd).title}</td>
                <td>${(order.dvd).language}</td>
                <td>${(order.dvd).type}</td>
                <td>${(order.dvd).price}</td>
                <td>${order.address}</td>
                <td>${order.orderDate}</td>
            </tr>
            </c:forEach>
        </table>
        </div>
    </body>
    <c:if test="${null != message}">
        <script> alert("${message}") </script>
    </c:if>
</html>
