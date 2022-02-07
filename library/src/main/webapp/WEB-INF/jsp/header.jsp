<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>

<div class="container d-flex h-100 p-3 mx-auto flex-column">
        <div class="masthead mb-auto ">
            <div class="inner">
                <h3 class="masthead-brand"><fmt:message key="header.library_name"/></h3>
                <nav class="nav nav-masthead justify-content-center">
                    <a class="nav-link active" href="library?command=SHOW_MAIN_PAGE">
                        <fmt:message key="header.home"/></a>

                    <c:if test="${not empty user}">
                        <c:if test="${userRole == 'USER'}">
                            <a class="nav-link" href="library?command=SHOW_ABOUT_LIBRARY">
                                <fmt:message key="header.about_library"/></a>
                            <a class="nav-link" href="library?command=SHOW_ALL_BOOKS">
                                <fmt:message key="header.catalog"/></a>
                            <a class="nav-link" href="library?command=SHOW_USER_ORDERS">
                                <fmt:message key="header.orders"/></a>
                        </c:if>
                        <c:if test="${userRole == 'ADMIN'}">
                            <a class="nav-link" href="library?command=SHOW_ALL_BOOKS">
                                <fmt:message key="header.books"/></a>
                            <a class="nav-link" href="library?command=SHOW_ALL_USERS">
                                <fmt:message key="header.users"/></a>
                            <a class="nav-link" href="library?command=SHOW_ALL_ORDERS">
                                <fmt:message key="header.orders"/></a>
                        </c:if>
                        <a class="nav-link" href="library?command=LOGOUT">
                            <fmt:message key="header.log_out"/></a>
                    </c:if>

                    <c:if test="${empty user}">
                        <a class="nav-link" href="library?command=SHOW_ABOUT_LIBRARY">
                            <fmt:message key="header.about_library"/></a>
                        <a class="nav-link" href="library?command=SHOW_ALL_BOOKS">
                            <fmt:message key="header.catalog"/></a>
                        <a class="nav-link" href="library?command=SHOW_LOGIN_PAGE">
                            <fmt:message key="header.sign_in"/></a>
                        <a class="nav-link" href="library?command=SHOW_REGISTRATION_PAGE">
                            <fmt:message key="header.register"/></a>
                    </c:if>

                    <div class="language-select">
                        <div class="language-select__current">
                            <span class="language-select__label">
                                 <c:choose>
                                     <c:when test="${sessionScope.language == 'ru'}">
                                         <fmt:message key="header.language_ru"/>
                                     </c:when>
                                     <c:when test="${sessionScope.language == 'en'}">
                                         <fmt:message key="header.language_en"/>
                                     </c:when>
                                     <c:otherwise>
                                         <fmt:message key="header.language_en"/>
                                     </c:otherwise>
                                 </c:choose>
                            </span>
                            <span class="language-select__arrow"></span>
                        </div>
                        <div class="language-select__dropdown">
                            <ul class="menu">
                                <li>
                                    <a class="nav-link" href="library?command=CHANGE_LANGUAGE&language=ru"><span>
                                    <fmt:message key="header.language_ru"/></span></a>
                                </li>
                                <li>
                                    <a class="nav-link" href="library?command=CHANGE_LANGUAGE&language=en"><span>
                                    <fmt:message key="header.language_en"/></span></a>
                                </li>
                            </ul>
                        </div>
                    </div>




                </nav>
            </div>
        </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>