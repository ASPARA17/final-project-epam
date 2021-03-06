<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<c:choose>
    <c:when test="${not empty language}">
        <fmt:setLocale value="${sessionScope.language}"/>
    </c:when>
    <c:when test="${empty language}">
        <fmt:setLocale value="en"/>
    </c:when>
</c:choose>
<fmt:setBundle basename="language"/>
<head>
    <title><fmt:message key="registration_page.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addBook.css">
</head>
<body>
<div class="layout">
    <c:import url="header.jsp"/>
    <div class="layout-body"
         style="background-image: url(${pageContext.request.contextPath}/images/main.jpg);">

        <c:choose>
            <c:when test="${not empty requestScope.error}">
                <div class = "container p-3">
                    <div class="alert alert-danger alert-dismissible" style="width: 500px; margin:auto">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        ${error}
                    </div>
                </div>
            </c:when>
        </c:choose>

        <div id="login">
            <div class="container">
                <div id="login-row" class="row justify-content-center align-items-center">
                    <div id="login-column" >

                        <div id="login-box">
                            <form id="login-form" class="form" action="library" method="post" style="width: 400px">
                                <h3 class="text-center text-light"><fmt:message key="registration_page.please_registration"/></h3>

                                <div class="row">
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <label for="firstName" class="text-light"><fmt:message key="registration_page.name"/></label><br>
                                            <input type="text" name="firstName" id="firstName" class="form-control"
                                                   required pattern="^[a-zA-Z]{3,20}$"
                                                   oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_name"/>')"
                                                   onchange="this.setCustomValidity('')" value="${registrationData['firstName']}"/>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <label for="secondName" class="text-light"> <fmt:message key="registration_page.surname"/></label><br>
                                            <input type="text" name="secondName" id="secondName" class="form-control"
                                                   required pattern="^[a-zA-Z]{3,20}$"
                                                   oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_surname"/>')"
                                                   onchange="this.setCustomValidity('')" value="${registrationData['secondName']}"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="phone" class="text-light"><fmt:message key="registration_page.phone"/></label><br>
                                    <input type="text" name="phone" id="phone" class="form-control"
                                           required pattern="^\+375(25|44|29|33)\d{7}$"
                                           oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_phone"/>')"
                                           onchange="this.setCustomValidity('')" value="${registrationData['phone']}"/>
                                </div>

                                <div class="form-group">
                                    <label for="subscriptionId" class="text-light"><fmt:message key="registration_page.subscription"/></label><br>

                                    <input type="text" name="subscriptionId" id="subscriptionId" class="form-control"
                                           pattern="^[\d+]{6}$"
                                           oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_subscription"/>')"
                                           onchange="this.setCustomValidity('')" value="${registrationData['subscriptionId']}"/>
                                </div>

                                <div class="form-group">
                                    <label for="login" class="text-light"><fmt:message key="registration_page.login"/></label><br>
                                    <input type="text" name="login" id="login" class="form-control"
                                           required pattern="^[a-zA-Z0-9_]{3,20}$"
                                           oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_login"/>')"
                                           onchange="this.setCustomValidity('')" value="${registrationData['login']}"/>
                                </div>

                                <div class="row"    >
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <label for="password" class="text-light"><fmt:message key="registration_page.password"/></label><br>
                                            <input type="password" name="password" id="password" class="form-control"
                                                   required pattern="^.{3,20}$"
                                                   oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_password"/>')"
                                                   onchange="this.setCustomValidity('')" value="${registrationData['password']}"/>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <label for="confirmPassword" class="text-light"><fmt:message key="registration_page.password_confirmation"/></label><br>
                                            <input type="password" name="confirmPassword" id="confirmPassword" class="form-control"
                                                   required pattern="^.{3,20}$"
                                                   oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_password"/>')"
                                                   onchange="this.setCustomValidity('')" value="${registrationData['confirmPassword']}"/>
                                        </div>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <input type="hidden" name="command" value="REGISTRATION"/>
                                    <input type="submit" class="btn btn-primary btn-md" value=
                                    <fmt:message key="registration_page.registration"/>
                                    >
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</html>
