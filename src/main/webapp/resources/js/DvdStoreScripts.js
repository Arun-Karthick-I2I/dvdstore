function getCategoryName(form) {
    var categoryName = prompt("Enter the New Category Name");
    if (null != categoryName) {
        var pattern = /^[A-Za-z]+$/;
        if ('' != categoryName && categoryName.match(pattern)) {
            form.categoryName.value = categoryName;
            return true;
        } else {
            alert("Invalid Category Name (i.e should contain only letters)");
        }
    }
    return false; 
}

function checkCategories() {
    var checkboxes = document.getElementsByName("category");
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            return true;
        }
    }
    alert("Please Select atleast one Category");
    return false;
}

function checkType() {
    var radioButtons = document.getElementsByName("type");
    for (var i = 0; i < radioButtons.length; i++) {
        if (radioButtons[i].checked) {
            return true;
        }
    }
    alert("Please Select the Type of Dvd Content");
    return false;
}

function showAddress() {
    document.getElementById("ordersDiv").style.display = "none";
    document.getElementById("addressDiv").style.display = "block";
}

function showOrders(size) {
    if (0 != size) {
        document.getElementById("ordersDiv").style.display = "block";
        document.getElementById("addressDiv").style.display = "none";
    } else {
        alert("You have no orders so far!!");
    }
}

function checkSearchID() {
    if ("" != document.getElementById("ID_Getter").value) {
        return true;
    } else {
        alert('Please Enter an ID to search');
        return false;
    }
} 

function checkSearchTitle() {
    var searchBox = document.getElementById("Title_Getter").value;
    if ("" != searchBox && " " != searchBox) {
        return true;
    } else {
        alert('Please Enter a Title to search');
        return false;
    }
} 

function checkDvds() {
    var checkboxes = document.getElementsByName("dvdId");
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            return true;
        }
    }
    alert("Please Select atleast one Dvd to Purchase");
    return false;
}

function purchase() {
    var addressBox = document.getElementById("addressBox");
    if (checkDvds()) {
        addressBox.style.display="block";
        return true;
    } 
    return false;
}

function checkDeliveryAddress() {
    var radioButtons = document.getElementsByName("addressId");
    for (var i = 0; i < radioButtons.length; i++) {
        if (radioButtons[i].checked) {
            return confirm('Are you sure want to Purchase');
        }
    }
    alert("Please select a delivery Address");
    return false;
}

function closeAddressBox() {
    document.getElementById("addressBox").style.display="none";
}

function confirmPassword() {
    var password = document.getElementById("reg_pass").value;
    var reEnteredPassword = document.getElementById("reg_confirm_pass").value;
    if (password == reEnteredPassword) {
        return true;
    } else {
        alert('Passwords do not match!');
        return false;
    }
}

function switchLoginForm() {
    var loginDiv = document.getElementById("loginDiv");
    var registerDiv = document.getElementById("registerDiv");
    if ("none" == loginDiv.style.display) {
        loginDiv.style.display="block";
        registerDiv.style.display="none";
    } else {
        loginDiv.style.display="none";
        registerDiv.style.display="block";
    }
}

