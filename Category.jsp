<%@ page import="com.ideas2it.dvdstore.utils.DateUtils" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>DVDStore</title>
        <script type="text/javascript" src="/dvdstore/resources/DvdStoreScripts.js"></script>
    </head>
    <body>
        <jsp:include page="Header.jsp"></jsp:include>
        <c:if test="${true == viewAll}">
        <div align="center">
            <form method="GET" action="/dvdstore/user/home">
                <button type="submit" style="width:250px" >Go Back Home</button>
            </form>
        </div><br>
        <form method="GET">
        <input hidden name="categoryName" />
        <div align="center"><button style="width:250px" type="submit" formmethod="POST" 
            formaction="/dvdstore/category/create" onclick="return getCategoryName(this.form)">Add New Category</button>
            <c:if test="${true == status}">
            <button style="width:250px" type="submit" formaction="/dvdstore/category/displayDeletedCategories">
            View Deleted Categories</button>
            </c:if>
            <c:if test="${false == status}">
            <button style="width:250px" type="submit" formaction="/dvdstore/category/displayAllCategories">
            View Available Categories</button>
            </c:if>
         </div><br>
        </form>
        <div style='height:500px; overflow-y:auto' >
        <table width=100% style="border-spacing: 15px;">
            <tr>
                <th>Category ID</th>
                <th>Category Name</th>
                <th>Action</th>         
            </tr>
            <c:forEach var="existingCategory" items="${categories}">
            <tr align="center">
                <td>${existingCategory.id}</td>
                <td>${existingCategory.name}</td>
                <form method="POST" action="category">
                <c:if test="${true == status}">
                <td>
                    <input type="hidden" name="categoryId" value="${existingCategory.id}" />
                    <input type="hidden" name="categoryName" />
                    <button type="submit" formaction="/dvdstore/category/display" formmethod="GET" onclick=>View</button>
                    <button type="submit" formaction="/dvdstore/category/update"
                         onclick="return getCategoryName(this.form)">Edit</button>
                    <button type="submit" formaction="/dvdstore/category/delete"
                         onclick="return confirm('Are you sure want to Delete Category: ${existingCategory.id} ?')">Delete</button>
                </td>
                </c:if>
                <c:if test="${false == status}">
                <td>
                    <input type="hidden" name="categoryId" value="${existingCategory.id}" />
                    <button type="submit" formaction="/dvdstore/category/restore"
                        onclick="return confirm('Are you sure want to Restore Category: ${existingCategory.id} ?')">Restore</button>
                </td>
                </c:if>
                </form>
            </tr>
            </c:forEach>
        </table>
        </div>
        </c:if>
        <c:if test="${false == viewAll}">
        <div align="center"><form method="GET" action="/dvdstore/category/displayAllCategories" >
            <button type="submit">Go Back</button></form></div>
        <div>
            <p><b>Category ID :</b> ${category.id}</p>
            <p><b>Category Name:</b> ${category.name}</p>
        </div>
        <div style='height:500px; overflow-y:auto' >
            <table width=100% style="border-spacing: 15px;">
                <caption>List of Dvds Available in this Category</caption>
                <tr>
                    <th>DVD ID</th>
                    <th>Title</th>
                    <th>Language</th>
                    <th>Rating</th>
                    <th>Type</th>
                    <th>Released</th>
                    <th>Copies</th>
                    <th>Price</th>
                    <th>Action</th>
                </tr>
            <c:forEach var="dvd" items="${category.dvds}">
                <tr align="center">
                    <td>${dvd.id}</td>
                    <td>${dvd.title}</td>
                    <td>${dvd.language}</td>
                    <td>${dvd.rating}</td>
                    <td>${dvd.type}</td>
                    <td>${DateUtils.calculateDaysDifference(dvd.releaseDate.toLocalDate())}</td>
                    <td>${dvd.copies}</td>
                    <td>${dvd.price}</td>
                    <form method="POST" action="category">
                    <td>
                        <input type="hidden" name="dvdId" value="${dvd.id}" />
                        <input type="hidden" name="categoryId" value="${category.id}" />
                        <button type="submit" formaction="/dvdstore/category/removeDvd" 
                            onclick="return confirm('Are you sure want to remove the Dvd from the Category ? DVD ID:35')">
                            Remove</button>
                    </td>
                    </form>
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
