<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <body>
        <div id="addressFormDiv" style="text-align:center">
        <form:form id="addressForm" method="POST" modelAttribute="address">
            <c:if test="${null != address.id}">
            <b>Enter Address Details to Update :</b><br>
            <form:hidden path="id" />
            </c:if>
            <c:if test="${null == address.id}">
            <b>Enter Address Details :</b><br>
            </c:if>
            <table align="center" cellspacing="10">
                <tr>
                    <th style="text-align:right">Address Line :</th>
                    <td><form:input path="addressLine" maxlength="50" autofocus="autofocus" required="required" /></td>
                </tr>
                <tr>
                    <th style="text-align:right">City :</th>
                    <td><form:input path="city" maxlength="30" required="required" /></td>
                </tr>
                <tr>
                    <th style="text-align:right">State :</th>
                    <td><form:input path="state" maxlength="30" required="required" /></td>
                </tr>
                <tr>
                    <th style="text-align:right">Country :</th>
                    <td><form:input path="country" maxlength="30" required="required" /></td>
                </tr>
                <tr>
                    <th style="text-align:right">PinCode :</th>
                    <td><form:input path="pinCode" maxlength="6" required="required" /></td>
                </tr>
            </table>
        </form:form>
        <div>
            <form id="backtoDisplay" method="GET" action="/dvdstore/customer/display"></form>
            <c:if test="${null != address.id}">
                <button form="addressForm" type="submit" style="margin-right:30px" formaction="/dvdstore/customer/updateAddress"
                    onclick="return confirm('Are you sure want to Update?')">Update Address</button></td>
            </c:if>
            <c:if test="${null == address.id}">
                <button form="addressForm" type="reset" style="margin-right:30px" 
                    onclick="return confirm('Are you sure want to Reset?')">Reset</button></td>
                <button form="addressForm" type="submit" style="margin-right:30px" formaction="/dvdstore/customer/addAddress"  
                    onclick="return confirm('Are you sure want to Add Address?')">Add Address</button></td>
            </c:if>
            <button style="margin-right:30px" form="backtoDisplay">Back</button></a>
        </div>
        </div>
    </body>
</html>
