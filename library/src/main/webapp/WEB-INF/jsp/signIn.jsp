<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title><fmt:message key="sign_in_page.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sign_in.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>

<body>
    <div class="layout">
        <c:import url="header.jsp"/>
        <div class="layout-body"
             style="background-image: url(${pageContext.request.contextPath}/images/sigIn.jpg);background-size: cover;">

            <c:choose>
                <c:when test="${not empty requestScope.successRegister}">
                    <p>${requestScope.error}</p>
                    <a href="${pageContext.request.contextPath}/controller?command=SHOW_LOGIN_PAGE">Try again</a>
                </c:when>
                <c:otherwise>
                    <c:if test="${sessionScope.successRegister == true}">
                        <div class = "container p-3">
                            <div class="alert alert-success alert-dismissible">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                <fmt:message key="alert.success_registration"/>

                            </div>
                            <%session.setAttribute("successRegister", false);%>
                        </div>
                    </c:if>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${not empty requestScope.wrongData}">
                    <div class = "container p-3">
                        <div class="alert alert-danger alert-dismissible">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <fmt:message key="alert.wrong_data_login"/>
                        </div>
                    </div>
                </c:when>
            </c:choose>

            <div id="login">
                <div class="container">
                    <div id="login-row" class="row justify-content-center align-items-center">
                        <div id="login-column" class="col-md-6">
                            <div id="login-box" class="col-md-12">
                                <form id="login-form" class="form" action="library" method="post">
                                    <h3 class="text-center text-light"><fmt:message key="sign_in_page.please_sign_in"/></h3>
                                    <div class="form-group">
                                        <label for="login" class="text-light"><fmt:message key="sign_in_page.login"/></label><br>
                                        <input type="text" name="login" id="login" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="text-light"><fmt:message key="sign_in_page.password"/></label><br>
                                        <input type="password" name="password" id="password" class="form-control">
                                    </div>
                                    <div class="form-group">
                                        <input type="hidden" name="command" value="LOGIN"/>
                                        <input type="submit" class="btn btn-primary btn-md" value=
                                        <fmt:message key="sign_in_page.sign_in"/>
                                        >
                                    </div>
                                    <div id="register-link" class="text-right">
                                        <br/>
                                        <a href="library?command=SHOW_REGISTRATION_PAGE" class="text-light">
                                            <fmt:message key="sign_in_page.register_here"/>
                                        </a>
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

</html>
