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
    <title><fmt:message key="admin_users.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="layout">
    <c:import url="header.jsp"/>
    <div class="layout-body"
         style="background-image: url(${pageContext.request.contextPath}/images/allUsers.jpg);">

        <div class="booking-section">
            <div class="booking-section__inner">
                <div class="orders">
                    <div class="orders__header">
                        <h2>Users</h2>
                    </div>
                    <table class="rooms-table">
                        <c:choose>
                            <c:when test="${empty allUsers && allUsers.size() == 0}">
                                <tr>
                                    <td><fmt:message key="catalog.not_found_message"/></td>
                                </tr>
                            </c:when>
                            <c:when test="${not empty allUsers}">
                                <tr>
                                    <th>Login</th>
                                    <th>First name</th>
                                    <th>Second name</th>
                                    <th>Phone</th>
                                    <th>Subscription</th>
                                    <th></th>
                                </tr>
                                <c:forEach items="${sessionScope.allUsers}" var="user">
                                    <tr>
                                        <td>${user.user.login}</td>
                                        <td>${user.firstName}</td>
                                        <td>${user.secondName}</td>
                                        <td>${user.phone}</td>
                                        <td>${user.subscriptionId}</td>
                                        <td class="rooms-table__action">
                                            <a href="#"
                                               class="rooms-table__button">
                                                Delete|Block
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </table>
                </div>
            </div>
            <div class="pages-orders">
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href="library?command=SHOW_ALL_USERS&page=1" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                    <li class="page-item"><a class="page-link" href="library?command=SHOW_ALL_USERS&page=1">1</a></li>
                    <li class="page-item"><a class="page-link" href="library?command=SHOW_ALL_USERS&page=2">2</a></li>
                    <li class="page-item"><a class="page-link" href="library?command=SHOW_ALL_USERS&page=3">3</a></li>
                    <li class="page-item"><a class="page-link" href="library?command=SHOW_ALL_USERS&page=4">4</a></li>
                    <li class="page-item"><a class="page-link" href="library?command=SHOW_ALL_USERS&page=5">5</a></li>
                    <li class="page-item">
                        <a class="page-link" href="library?command=SHOW_ALL_USERS&page=5" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>
                </ul>
            </div>
        </div>

    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>