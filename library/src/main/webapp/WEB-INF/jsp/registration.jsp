<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${not empty language}">
        <fmt:setLocale value="${sessionScope.language}"/>
    </c:when>
    <c:when test="${empty language}">
        <fmt:setLocale value="en"/>
    </c:when>
</c:choose>

<fmt:setBundle basename="language"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <title><fmt:message key="registration_page.title"/></title>
</head>
<body>
<div class="layout">
    <c:import url="header.jsp"/>
    <div class="layout-body" style="background-image: url(${pageContext.request.contextPath}/images/registration.jpg);background-size: cover;">
        <div class="container">
            <div class="row centered-form">
                <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <fmt:message key="registration_page.please_registration"/></h3>
                        </div>
                        <div class="panel-body">
                            <form action="library?command=REGISTRATION" method="post">
                                <div class="row">
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <input type="text" name="firstName" id="firstName" class="form-control input-sm"
                                                   placeholder=<fmt:message key="registration_page.name"/> required pattern="^[a-zA-Z]{3,20}$"
                                                   oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_name"/>')"
                                                   onchange="this.setCustomValidity('')" value="${registrationData['firstName']}"/>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <input type="text" name="secondName" id="secondName" class="form-control input-sm"
                                                   placeholder=
                                                   <fmt:message key="registration_page.surname"/> required pattern="^[a-zA-Z]{3,20}$"
                                                   oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_surname"/>')"
                                                   onchange="this.setCustomValidity('')" value="${registrationData['secondName']}"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="phone" id="phone" class="form-control input-sm"
                                           placeholder="+375(xx)xxx-xx-xx" required
        <%--                                   pattern="^(\+375\([\d]{2}\)[\d]{3}\-[\d]{2}\-[\d]{2})$"--%>
                                           oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_phone"/>')"
                                           onchange="this.setCustomValidity('')" value="${registrationData['phone']}"/>
                                </div>
                                <div class="form-group">
                                    <input type="text" name="login" id="login" class="form-control input-sm"
                                           placeholder=
                                           <fmt:message key="registration_page.login"/> required pattern="^[a-zA-Z0-9_]{3,20}$"
                                           oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_login"/>')"
                                           onchange="this.setCustomValidity('')" value="${registrationData['login']}"/>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <input type="password" name="password" id="password" class="form-control input-sm"
                                                   placeholder=
                                                   <fmt:message key="registration_page.password"/> required pattern="^.{3,20}$"
                                                   oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_password"/>')"
                                                   onchange="this.setCustomValidity('')" value="${registrationData['password']}"/>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <input type="password" name="password_confirmation" id="password_confirmation"
                                                   class="form-control input-sm" placeholder=
                                                   <fmt:message key="registration_page.password_confirmation"/> required pattern="^.{3,20}$"
                                                   oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_password"/>')"
                                                   onchange="this.setCustomValidity('')" value="${registrationData['password_confirmation']}"/>
                                        </div>
                                    </div>
                                </div>
        <%--                        <div class="someDiv">--%>
        <%--                            <input type="hidden" name="command" value="REGISTRATION"/>--%>
        <%--                            <input type="submit" class="btn btn-info btn-block" value=--%>
        <%--                            <fmt:message key="registration_page.registration"/>>--%>
        <%--                            <p class="message">--%>
        <%--                                <fmt:message key="registration_page.return_to_sign_in"/>--%>
        <%--                                <a href="library?command=SHOW_LOGIN_PAGE">--%>
        <%--                                    <fmt:message key="registration_page.sign_in"/>--%>
        <%--                                </a>--%>
        <%--                            </p>--%>
        <%--                        </div>--%>

        <%--                        <input type="submit" class="btn btn-info btn-block"--%>
        <%--                               value=<fmt:message key="registration_page.registration"/>>--%>

                                <div class="controls">
                                    <button class="btn btn-success"><fmt:message key="registration_page.registration"/></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
