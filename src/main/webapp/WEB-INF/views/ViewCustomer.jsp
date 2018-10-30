<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>DVDStore</title>
    <script type="text/javascript" src="/dvdstore/resources/js/DvdStoreScripts.js"></script>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<c:if test="${true == viewAll}">
    <div align="center">
        <form method="GET" action="/dvdstore/user/home">
            <button type="submit" style="width:250px">Go Back Home</button>
        </form>
    </div><br>
    <div style="width:100%">
        <c:if test="${true == resetSearch}">
            <div style="float:right">
                <form method="GET" action="/dvdstore/customer/displayAllCustomers"> 
                    <button type="submit" style="width:100px" >Reset Search</button>
                </form>
            </div>
        </c:if>
        <div style="float:right; margin-right:10px">
            <form method="GET" action="/dvdstore/customer/searchCustomer"> 
                <input type="text" pattern="[A-Za-z]{1,30}" maxlength=30 placeholder="Enter Customer Name" 
                    name="customerName" required>
                <button type="submit" style="width:100px" >Search</button>
            </form>
        </div>
    </div>
    <table width="100%" style="text-align:center">
        <tr>
            <th>Customer ID</th>
            <th>Customer Name</th>
            <th>Mobile Number</th>
            <th>Email ID</th>
            <th>Action</th>
        </tr>
        <c:forEach var="customer" items="${customers}">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.mobileNumber}</td>
            <td>${customer.emailId}</td>
            <td>
                <form method="GET" action="/dvdstore/customer/viewCustomer">
                    <input hidden name="customerId" value="${customer.id}">
                    <button type="submit">View</button>
                </form>
            </td>
        </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${false == viewAll}">
    <div align="center">
        <form method="GET" action="/dvdstore/customer/displayAllCustomers">
            <button type="submit" style="width:250px">Go Back</button>
        </form>
    </div><br>
    <table> 
        <tr>
            <th>Customer Name<th>
            <td>${customer.name}<td>
        </tr>
        <tr>
            <th>Mobile Number<th>
            <td>${customer.mobileNumber}<td>
        </tr>
        <tr>
            <th>Email ID<th>
            <td>${customer.emailId}<td>    
        </tr>
    </table>
    <button onclick="showAddress()" style="width:250px" >View Addresses</button>
    <button onclick="showOrders()" style="width:250px" >View Orders</button>
    <div id="addressDiv" style="display:none">
        <table width=100% style="text-align:center">
            <tr>
                <th>Address ID</th>
                <th>Address Line</th>
                <th>City</th>
                <th>State</th>
                <th>Country</th>
                <th>Pin Code</th>
            </tr>
        <c:forEach var="address" items="${customer.addresses}">
            <tr>
                <td>${address.id}</td>
                <td>${address.addressLine}</td>
                <td>${address.city}</td>
                <td>${address.state}</td>
                <td>${address.country}</td>
                <td>${address.pinCode}</td>
            </tr>
        </c:forEach>
        </table>
    </div>    
    <div id="ordersDiv" style="display:none">
        <table width=100% style="text-align:center">
            <tr>
                <th>Order ID</th>
                <th>DVD Title</th>
                <th>DVD Language</th>
                <th>DVD Type</th>
                <th>DVD Price</th>    
                <th>Order Date</th>
            </tr>
        <c:forEach var="order" items="${customer.orders}">
            <tr>
                <td>${order.id}</td>
                <td>${(order.dvd).title}</td>
                <td>${(order.dvd).language}</td>
                <td>${(order.dvd).type}</td>
                <td>${(order.dvd).price}</td>
                <td>${order.orderDate}</td>
            </tr>
        </c:forEach>
        </table>
    </div>
</c:if>
</body>
<c:if test="${null != message}">
    <script> alert("${message}") </script>
</c:if>
</html>
