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
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body style="background-image: url(${pageContext.request.contextPath}/images/sigIn.jpg);background-size: cover;">

<%--    <c:choose>--%>
<%--        <c:when test="${not empty requestScope.error}">--%>
<%--            <p>${requestScope.error}</p>--%>
<%--            <a href="${pageContext.request.contextPath}/controller?command=SHOW_LOFIN_PAGE">Try again</a>--%>
<%--        </c:when>--%>
<%--        <c:otherwise>--%>
<%--            <form action="${pageContext.request.contextPath}/controller?command=LOGIN" method="post">--%>
<%--                <label for="loginField">Login: </label>--%>
<%--                <input type="text" id="loginField" name="login">--%>
<%--                <br>--%>
<%--                <label for="passwordField">Password: </label>--%>
<%--                <input type="password" id="passwordField" name="password">--%>
<%--                <br>--%>
<%--                <input type="submit" value="Log In">--%>
<%--            </form>--%>
<%--        </c:otherwise>--%>
<%--    </c:choose>--%>

    <div id="login">
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="library" method="post">
                            <h3 class="text-center text-light"><fmt:message key="sign_in_page.please_sign_in"/></h3>
                            <div class="form-group">
                                <label for="username" class="text-light"><fmt:message key="sign_in_page.login"/></label><br>
                                <input type="text" name="username" id="username" class="form-control">
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
                            <span class="error" style="color:#ff340a">${errorMessage}</span>

                            <div id="register-link" class="text-right">
                                <br/>
                                <a href="library?command=SHOW_REGISTER_PAGE" class="text-light">
                                    <fmt:message key="sign_in_page.register_here"/>
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
