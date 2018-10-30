<%@ page import="com.ideas2it.dvdstore.utils.DateUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>DVDStore</title>
        <script type="text/javascript" src="/dvdstore/resources/js/DvdStoreScripts.js"></script>
        <link rel="stylesheet" href="/dvdstore/resources/css/modal.css">
    </head>
    <body>
        <jsp:include page="Header.jsp"></jsp:include>
        <form method="GET" action="customer">
            <div style="text-align:center"><button formaction="/dvdstore/customer/display" 
                style="width:250px">Go Back</button></div>
        </form>
        <div style="width:100%">
            <c:if test="${true == resetSearch}">
                <div style="float:right">
                    <form method="GET" action="/dvdstore/customer/displayDvds"> 
                        <button type="submit" style="width:100px" >Reset Search</button>
                    </form>
                </div>
            </c:if>
            <div id="searchBox" style="margin-right:10px; float:right; width:250px">
                <form method="GET"> 
                    <input type="search" placeholder="Enter Title" maxlength="30" name="title" required/>
                    <button type="submit" formaction="/dvdstore/customer/displayDvdByTitle">Search</button>
                </form>
            </div>
            <div id="categorySelect" style="float:left; width:500px">
                <form method="GET" action="customer"> 
                    <select name="categoryId"> 
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.id}" <c:if test="${category.id == categoryId}">selected</c:if> >
                                 ${category.name}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" formaction="/dvdstore/customer/displayDvdsByCategory">View DVDs By Category</button>
                </form>
            </div>
        </div>
        <div style="height:600px;overflow:auto;width:100%">
        <form id="purchaseForm" method="POST" action="customer">
            <table width=100% style="border-spacing: 15px;">
                <tr>
                    <th>Title</th>
                    <th>Language</th>
                    <th>Rating</th>
                    <th>Type</th>
                    <th>Released</th>
                    <th>Copies</th>
                    <th>Price</th>
                    <th>Categories</th>
                    <th>Select Dvd to Purchase</th>
                </tr>
                <c:forEach var="dvd" items="${dvds}">
                <tr align="center">
                    <td>${dvd.title}</td>
                    <td>${dvd.language}</td>
                    <td>${dvd.rating}</td>
                    <td>${dvd.type}</td>
                    <td>${DateUtils.calculateDaysDifference(dvd.releaseDate.toLocalDate())}</td>
                    <td>${dvd.copies}</td>
                    <td>${dvd.price}</td> 
                    <td>
                        <c:forEach var="category" items="${dvd.categories}">
                            ${category.name}
                        </c:forEach>
                    </td>
                    <td>
                        <c:if test="${0 != dvd.copies}">
                            <input type="checkbox" name="dvdId" value="${dvd.id}" />
                        </c:if>
                        <c:if test="${0 == dvd.copies}">
                            Out of Stock
                        </c:if>
                    </td>
                </tr>
                </c:forEach>
            </table>
            <div id="addressBox" class="modal">
                <div class="modal-content">
                    <span class="close" onclick="closeAddressBox();">&times</span>
                    <h2>Select a delivery address:</h2>
                    <c:forEach var="address" items="${customer.addresses}">
                        <label><input type="radio" name="addressId" value="${address.id}">${address}</input></label><br><br>
                    </c:forEach>
                    <button type="submit" formaction="/dvdstore/customer/purchaseDvd" style="width:200px; float:right"
                        onclick="return checkDeliveryAddress()">Place Order</button>
                </div>
            </div>
        </form> 
        </div><br>
        <div style="text-align:center">
            <button type="button" style="width:250px; height:30px" onclick="purchase()">Purchase
            </button>
        </div>
    </body>
    <c:if test="${null != message}">
        <script> alert("${message}") </script>
        <c:remove var="message" scope="session" />
    </c:if>
</html>   
