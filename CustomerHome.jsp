<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>DVDStore</title>
        <script type="text/javascript" src="/dvdstore/resources/DvdStoreScripts.js"></script>
    </head>
    <body>
        <jsp:include page="Header.jsp"></jsp:include>
        <table> 
            <tr>
                <th style="text-align:right">Customer Name:<th>
                <td>${customer.name}<td>
            </tr>
            <tr>
                <th style="text-align:right">Mobile Number:<th>
                <td>${customer.mobileNumber}<td>
            </tr>
            <tr>
                <th style="text-align:right">Email ID:<th>
                <td>${customer.emailId}<td>    
            </tr>
        </table>
        <form id="profile" method="GET" action="customer"></form>
        <button style="width:250px" form="profile" formaction="/dvdstore/customer/edit">Update Profile</button>
        <button style="width:250px" form="profile" formaction="/dvdstore/customer/newAddressForm">Add Address</button>
        <button onclick="showAddress()" style="width:250px" >View Addresses</button>
        <button onclick="showOrders(${customer.orders.size()})" style="width:250px" >View Orders</button>
        <button type="submit" style="width:250px" form="profile" formaction="/dvdstore/customer/displayDvds" >View Dvds</button>
        <div id="addressDiv" style="display:none">
            <table width=100% style="text-align:center;" cellspacing=15>
                <tr>
                    <th>Address Line</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Country</th>
                    <th>Pin Code</th>
                    <th>Action</th>
                </tr>
            <c:forEach var="address" items="${customer.addresses}">
                <tr>
                    <td>${address.addressLine}</td>
                    <td>${address.city}</td>
                    <td>${address.state}</td>
                    <td>${address.country}</td>
                    <td>${address.pinCode}</td>
                    <td>
                        <form method="POST">
                            <input hidden name="customerId" value="${customer.id}" />
                            <input hidden name="addressId" value="${address.id}" />
                            <button type="submit" formaction="/dvdstore/customer/editAddress" >Update</button> 
                            <c:if test="${1 != (customer.addresses).size()}">
                                <button type="submit" formaction="/dvdstore/customer/deleteAddress" >Delete</button>
                            </c:if>
                        </form> 
                    </td>
                </tr>
            </c:forEach>
            </table>
        </div>    
        <div id="ordersDiv" style="display:none">
            <table width=100% style="text-align:center;" cellspacing=15>
                <tr>
                    <th>Order ID</th>
                    <th>DVD Title</th>
                    <th>DVD Language</th>
                    <th>DVD Type</th>
                    <th>DVD Price</th>    
                    <th>Address</th>
                    <th>Order Date</th>
                </tr>
            <c:forEach var="order" items="${customer.orders}">
                <tr>
                    <form method="POST">
                        <td>${order.id}</td>
                        <td>${(order.dvd).title}</td>
                        <td>${(order.dvd).language}</td>
                        <td>${(order.dvd).type}</td>
                        <td>${(order.dvd).price}</td>
                        <td>${order.address}</td>
                        <td>${order.orderDate}</td>
                        <td>
                            <input hidden name="orderId" value="${order.id}">
                            <c:if test="${order.orderDate == today}">
                                <button type="submit" formaction="/dvdstore/customer/cancelOrder" 
                                    onclick="return confirm('Are you sure want to Cancel Order?')" >Cancel Order
                                </button>
                            </c:if>
                        </td> 
                    </form>
                </tr>
            </c:forEach>
            </table>
        </div>
    </body>
    <c:if test="${null != message}">
        <script> alert("${message}") </script>
        <c:remove var="message" />
    </c:if>
</html>
