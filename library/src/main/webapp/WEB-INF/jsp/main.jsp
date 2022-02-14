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
    <title><fmt:message key="main_page.title"/></title>
</head>
<div class="layout">
    <c:import url="header.jsp"/>
    <div class="layout-body" style="background-image: url(${pageContext.request.contextPath}/images/main.jpg);">

        <c:choose>
            <c:when test="${not empty sessionScope.error}">
                <div class = "container p-3">
                    <div class="alert alert-danger alert-dismissible" style="width: 500px; margin:auto">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            ${error}
                    </div>
                    <%session.setAttribute("error", "");%>
                </div>
            </c:when>
        </c:choose>

        <div class="notification">
            <h2 class="notification__title"><fmt:message key="main_page.welcome"/></h2>
            <p class="notification__text">
                <fmt:message key="main_page.description"/><br/>
                <fmt:message key="main_page.address"/><br/>
            </p>
        </div>
    </div>
    <c:import url="footer.jsp"/>
</div>
</body>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>