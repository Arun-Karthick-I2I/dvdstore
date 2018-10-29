<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix = "form"%>

<html>
    <head>
        <title>DvdStore</title>
        <script type="text/javascript" src="/dvdstore/resources/DvdStoreScripts.js"></script>
    </head>
    <body>
    <c:if test="${null == completeRegistration}">
    <c:if test="${null == register}">
        <div id="loginDiv" style="text-align:center; display:block">
    </c:if>
    <c:if test="${null != register}">
        <div id="loginDiv" style="text-align:center; display:none">
    </c:if>
            <h1> Welcome to DVD Store </h1>
            <h2 style="margin-top:100px;">Login</h2>
            <form method="POST" action="/dvdstore/user/login">
                <select name="role" style="margin-top:30px; width:250px">
                    <option value="CUSTOMER">Customer</option>
                    <option value="ADMIN">Admin</option>
                </select><br>
                <input type="text" maxlength=30 pattern="[a-zA-Z]{1}[a-zA-Z_]{1,29}" 
                    title="Username should contain only alphabets and underscore" placeholder="UserName" 
                        style="margin-top:20px; width:250px" name="userName" required><br>
                <input type="password" name="password" placeholder="Password" 
                        style="margin-top:20px; width:250px" minlength=8 maxlength=32 required><br>
                <button type="submit" style="margin-top:20px; width:250px"> Login </button><br>
            </form>
            <p style="margin-top:50px"> Not a Customer Yet ? </p>	
            <button type="button" style="width:250px"  onclick="switchLoginForm()">Register</button><br>
        </div>

    <c:if test="${null != register}">
        <div id="registerDiv" style="text-align:center; display:block">
    </c:if>
    <c:if test="${null == register}">
        <div id="registerDiv" style="text-align:center; display:none">
    </c:if>
            <h2 style="margin-top:100px;">Register</h2>
            <form method="POST" action="/dvdstore/user/checkUserNameAvailability">
                <input type="text" maxlength=30 pattern="[a-zA-Z]{1}[a-zA-Z_]{1,29}" 
                    title="Username should contain only alphabets and underscore" placeholder="UserName" 
                        style="margin-top:20px; width:250px" name="userName" required> <br>
                <button type="submit" name="operation" value="checkUserNameAvailability" style="margin-top:20px; width:250px"> 
                        Check Username Availability </button>
            </form>
            <p style="margin-top:100px"> Already a Customer ? </p>	
            <button type="button" style="width:250px" onclick="switchLoginForm()">Login</button><br>
        </div>
    </c:if>

    <c:if test="${true == completeRegistration}">
        <div style="text-align:center">
            <form method="GET" action="/dvdstore/user/registrationForm">
                <button type="submit" style="margin-top:10px; width:250px">Go Back</button>
            </form>
            <h2 style="margin-top:100px;">Complete Registration</h2>
            <form method="POST" action="/dvdstore/user/register" onsubmit="return confirmPassword()">
                <input type="text" name="userName" value="${userName}" style="margin-top:20px; width:250px" readonly><br>
                <input type="password" id="reg_pass" name="password" placeholder="Password" 
                        style="margin-top:20px; width:250px" minlength=8 maxlength=32 required><br>
                <input type="password" id="reg_confirm_pass" name="confirmpassword" placeholder="Re-Enter Password" 
                        style="margin-top:20px; width:250px" minlength=8 maxlength=32 required><br>
                <button type="submit" name="operation" value="register" style="margin-top:20px; width:250px"> Register </button>
            </form>
        </div>
    </c:if>

    </body>
    <c:if test="${null != message}">
        <script> alert("${message}") </script>
        <c:remove var="message" />
    </c:if>
</html>
