<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Library</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addBook.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<div class="layout">
    <c:import url="header.jsp"/>
    <div class="layout-body"
         style="background-image: url(${pageContext.request.contextPath}/images/main.jpg);">


<%--        <div class="sidenav">--%>
<%--            <a href="#about">О Нас</a>--%>
<%--            <a href="#services">Услуги</a>--%>
<%--            <a href="#clients">Клинты</a>--%>
<%--            <a href="#contact">Контакт</a>--%>
<%--            <a href="#contact">Поиск</a>--%>
<%--        </div>--%>

    <p>There are several browsers available, such as <a href="https://www.mozilla.org/en-US/firefox/">Mozilla
        Firefox</a>, <a href="https://www.google.com/chrome/index.html">Google Chrome</a>, and
        <a href="https://www.microsoft.com/en-us/windows/microsoft-edge">Microsoft Edge</a>.</p>


        <div id="login">
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" >
                    <div id="login-box">
                        <form id="login-form" class="form" action="library" method="post">
                            <h3 class="text-center text-light">Add book</h3>

                            <div class="form-group">
                                <label for="login" class="text-light"><fmt:message key="sign_in_page.login"/></label><br>
                                <input type="text" name="login" id="login" class="form-control">
                            </div>

                            <div class="form-group">
                                <label for="login" class="text-light"><fmt:message key="sign_in_page.login"/></label><br>
                                <input type="text" name="login" id="login" class="form-control">
                            </div>

                            <div class="form-group">
                                <label for="login" class="text-light"><fmt:message key="sign_in_page.login"/></label><br>
                                <input type="text" name="login" id="login" class="form-control">
                            </div>

                            <div class="row">
                                <div class="col-xs-6 col-sm-6 col-md-6">
                                    <div class="form-group">
                                        <label for="login" class="text-light"><fmt:message key="sign_in_page.login"/></label><br>
                                        <input type="text" name="login" id="login" class="form-control">
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6">
                                    <div class="form-group">
                                        <label for="password" class="text-light"><fmt:message key="sign_in_page.password"/></label><br>
                                        <input type="password" name="password" id="password" class="form-control">
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-xs-6 col-sm-6 col-md-6">
                                    <div class="form-group">
                                        <label for="login" class="text-light"><fmt:message key="sign_in_page.login"/></label><br>
                                        <input type="text" name="login" id="login" class="form-control">
                                    </div>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6">
                                    <div class="form-group">
                                        <label for="password" class="text-light"><fmt:message key="sign_in_page.password"/></label><br>
                                        <input type="password" name="password" id="password" class="form-control">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <input type="hidden" name="command" value="LOGIN"/>
                                <input type="submit" class="btn btn-primary btn-md" value=
                                <fmt:message key="sign_in_page.sign_in"/>
                                >
                            </div>

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
    </div>
</div>

</body>
</html>
