<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
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
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main_page.css" >--%>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">--%>
    <title><fmt:message key="main_page.title"/></title>
</head>

<%--<body class="text-left">--%>

<%--    <div class="container d-flex h-100 p-3 mx-auto flex-column">--%>
<%--        <c:import url="header.jsp"/>--%>
<%--            <div class="section-welcome"--%>
<%--                 style="background-image: url(/images/main.jpg);">--%>
<%--                <div class="section-welcome__content">--%>
<%--                    <main role="main" class="inner cover">--%>
<%--                        <h1 class="cover-heading">Cover your page.</h1>--%>
<%--                        <p class="lead">Cover is a one-page template for building simple and beautiful home pages. Download, edit the text, and add your own fullscreen background photo to make it your own.</p>--%>
<%--                        <p class="lead">--%>
<%--                            <a href="#" class="btn btn-lg btn-secondary">Learn more</a>--%>
<%--                        </p>--%>
<%--                    </main>--%>
<%--                </div>--%>
<%--            </div>--%>


<%--        <c:import url="footer.jsp"/>--%>
<%--    </div>--%>


<div class="layout">
    <c:import url="header.jsp"/>
    <div class="layout-body" style="background-image: url(${pageContext.request.contextPath}/images/main.jpg);">

    </div>
</div>
</body>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>