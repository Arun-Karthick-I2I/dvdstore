<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head><title>DVDStore</title></head>
<body>
    <jsp:include page="Header.jsp"></jsp:include>
    <div id="customerFormDiv" style="text-align:center">
    <form:form id="customerForm" method="POST" modelAttribute="customer">
        <c:if test="${null != customer.id}">
        <b>Enter Customer Details to Update :</b><br>
        <form:hidden path="id" />
        </c:if>
        <c:if test="${null == customer.id}">
        <b>Enter Customer Details :</b><br>
        </c:if>
        <table align="center" cellspacing="10">
            <tr>
                <th style="text-align:right">Customer Name :</th>
                <td><form:input path="name" pattern="[A-Za-z]{1,30}" maxlength="30" 
                    autofocus="autofocus" required="required" /></td>
            </tr>
            <tr>
                <th style="text-align:right">Mobile Number :</th>
                <td><form:input path="mobileNumber" pattern="[9876]{1}[0-9]{9}" maxlength="10" 
                    required="required" /></td>
            </tr>
            <tr>
                <th style="text-align:right">Email ID :</th>
                <td><form:input type="email" path="emailId" maxlength="50" required="required" /></td>
            </tr>
           <c:if test="${null == customer.id}">
            <tr>
                <th style="text-align:right">Address Line :</th>
                <td><form:input path="addresses[0].addressLine" maxlength="50" required="required" /></td>
            </tr>
            <tr>
                <th style="text-align:right">City :</th>
                <td><form:input path="addresses[0].city" pattern="[A-Za-z ]{1,30}" maxlength="30" required="required" /></td>
            </tr>
            <tr>
                <th style="text-align:right">State :</th>
                <td><form:input path="addresses[0].state" pattern="[A-Za-z ]{1,30}" maxlength="30" required="required" /></td>
            </tr>
            <tr>
                <th style="text-align:right">Country :</th>
                <td><form:input path="addresses[0].country" pattern="[A-Za-z ]{1,30}" maxlength="30" required="required" /></td>
            </tr>
            <tr>
                <th style="text-align:right">PinCode :</th>
                <td><form:input path="addresses[0].pinCode" pattern="[0-9]{6}" maxlength="6" required="required" /></td>
            </tr>
            </c:if>
        </table>
    </form:form>
    <div>
        <c:if test="${null != customer.id}">
            <form id="backtoDisplay" method="GET" action="/dvdstore/customer/display">
            </form>
            <button form="customerForm" type="submit" style="margin-right:30px" formaction="/dvdstore/customer/update"
                onclick="return confirm('Are you sure want to Update?')">Update</button></td>
            <button style="margin-right:30px" form="backtoDisplay" type="submit" >
                Back</button>
        </c:if>
        <c:if test="${null == customer.id}">
            <button form="customerForm" type="reset" style="margin-right:30px" 
                onclick="return confirm('Are you sure want to Reset?')">Reset</button></td>
            <button form="customerForm" type="submit" style="margin-right:30px" formaction="/dvdstore/customer/register"  
                onclick="return confirm('Are you sure want to Register?')">Register</button></td>
        </c:if>
    </div>
    </div>
</body>
</html>
