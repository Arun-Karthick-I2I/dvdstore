<%@ page import="com.ideas2it.dvdstore.utils.DateUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>DVDStore</title>
        <script type="text/javascript" src="/dvdstore/resources/DvdStoreScripts.js"></script>
    </head>
    <body>
        <jsp:include page="Header.jsp"></jsp:include>
        <div align="center">
            <form method="GET" action="/dvdstore/user/home">
                <button type="submit" style="width:250px">Go Back Home</button>
            </form>
        </div><br>
        <div style="margin-top:30px; width:100%">
            <div style="float:left">
                <form method="GET">
                    <button type="submit" formaction="/dvdstore/dvd/newDvdForm" style="width:200px">Create Dvd</button>
                    <c:if test="${true == status}">
                    <button type="submit" formaction="/dvdstore/dvd/displayDeletedDvds" 
                        style="width:200px">Display Deleted Dvds</button>
                    </c:if>
                    <c:if test="${false == status}">
                    <button type="submit" formaction="/dvdstore/dvd/displayAllDvds" 
                        style="width:200px">Display Available Dvds</button>
                    </c:if>
                    <input type='number' id="ID_Getter" placeholder="Enter DVD ID" min=1 name='dvdId' 
                        style="margin-left:20px"/>
                    <button type="submit" formaction="/dvdstore/dvd/displayDvdById" 
                        style="width:200px" onclick="return checkSearchID()">Search By ID</button>
                    <input type='search' id="Title_Getter" placeholder="Enter DVD Title" maxlength=30 name='title'
                        style="margin-left:20px"/>
                    <button type="submit" formaction="/dvdstore/dvd/displayDvdByTitle" style="width:200px"
                        onclick="return checkSearchTitle()">Search By Title</button>
                </form>
            </div>
            <c:if test="${true == resetSearch}">
                <div style="margin-left:20px; float:left">
                    <form method="GET" action="/dvdstore/dvd/displayAllDvds"> 
                        <button type="submit" style="width:200px" >Reset Search</button>
                    </form>
                </div>
            </c:if>
        </div>
        <div style="height:500px; width:100%; overflow-y:auto" >
        <table width=100% style="border-spacing: 15px;">
            <tr>
                <th>DVD ID</th>
                <th>Title</th>
                <th>Language</th>
                <th>Rating</th>
                <th>Type</th>
                <th>Released</th>
                <th>Copies</th>
                <th>Price</th>
                <th>Categories</th>
                <th>Action</th>
            </tr>
            <c:forEach var="dvd" items="${dvds}">
            <tr align="center">
                <td>${dvd.id}</td>
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
                <form method="POST">
                <c:if test="${true == status}">
                <td>
                    <input type="hidden" name="dvdId" value="${dvd.id}" />
                    <button type="submit" formaction="/dvdstore/dvd/edit">Edit</button>
                    <button type="submit" formaction="/dvdstore/dvd/delete"  
                         onclick="return confirm('Are you sure want to Delete ? DVD ID:${dvd.id}')">Delete</button>
                </td>
                </c:if>
                <c:if test="${false == status}">
                <td>
                    <input type="hidden" name="dvdId" value="${dvd.id}" />
                    <button type="submit" formaction="/dvdstore/dvd/restore"
                        onclick="return confirm('Are you sure want to Restore ? DVD ID:${dvd.id}')">Restore</button>
                </td>
                </c:if>
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
