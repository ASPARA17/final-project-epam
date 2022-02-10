<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
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
    <title><fmt:message key="orders.title_orders"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="layout">
    <c:import url="header.jsp"/>

        <div class="layout-body"
             style="background-image: url(${pageContext.request.contextPath}/images/userOrders.jpg);">

            <c:choose>
                <c:when test="${not empty requestScope.successReturnBook}">
                    <p>${requestScope.error}</p>
                    <a href="${pageContext.request.contextPath}/controller?command=SHOW_ALL_ORDERS">Try again</a>
                </c:when>
                <c:otherwise>
                    <c:if test="${sessionScope.successReturnBook == true}">
                        <div class = "container p-3">
                            <div class="alert alert-success alert-dismissible">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                <fmt:message key="alert.success_return_book_start"/> №${sessionScope.orderId}
                                <fmt:message key="alert.success_return_book_end"/>
                            </div>
                            <%session.setAttribute("successReturnBook", false);%>
                        </div>
                    </c:if>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${not empty requestScope.successConfirmOrder}">
                    <p>${requestScope.error}</p>
                    <a href="${pageContext.request.contextPath}/controller?command=SHOW_ALL_ORDERS">Try again</a>
                </c:when>
                <c:otherwise>
                    <c:if test="${sessionScope.successConfirmOrder == true}">
                        <div class = "container p-3">
                            <div class="alert alert-success alert-dismissible">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                <fmt:message key="alert.success_confirm_order_start"/> №${sessionScope.orderId}
                                <fmt:message key="alert.success_confirm_order_end"/>
                            </div>
                            <%session.setAttribute("successConfirmOrder", false);%>
                        </div>
                    </c:if>
                </c:otherwise>
            </c:choose>

            <div class="booking-section">
                <div class="booking-section__inner">
                    <div class="orders">
                        <div class="orders__header">
                            <h2><fmt:message key="orders.orders"/></h2>
                        </div>
                        <table class="rooms-table">
                            <c:choose>
                                <c:when test="${empty allOrders && allOrders.size() == 0}">
                                    <tr>
                                        <td><fmt:message key="catalog.not_found_message"/></td>
                                    </tr>
                                </c:when>
                                <c:when test="${not empty allOrders}">
                                    <tr>
                                        <th>№</th>
                                        <th><fmt:message key="orders.login"/></th>
                                        <th><fmt:message key="orders.name_book"/></th>
                                        <th><fmt:message key="orders.author"/></th>
                                        <th><fmt:message key="orders.order_date"/></th>
                                        <th><fmt:message key="orders.return_date"/></th>
                                        <th><fmt:message key="orders.subscription"/></th>
                                        <th><fmt:message key="orders.status"/></th>
                                        <th></th>
                                    </tr>
                                    <c:forEach items="${sessionScope.allOrders}" var="order">
                                        <tr>
                                            <td>${order.id}</td>
                                            <td>${order.account.user.login}</td>
                                            <td>${order.book.name}</td>
                                            <td>${order.book.author}</td>
                                            <td>${order.dateOfIssue}</td>
                                            <td>${order.returnDate}</td>
                                            <c:if test="${order.subscription == true}">
                                                <td>${order.account.subscriptionId}</td>
                                            </c:if>
                                            <c:if test="${order.subscription == false}">
                                                <td>-</td>
                                            </c:if>
                                            <td>${order.orderStatus}</td>
                                            <td class="rooms-table__action">
                                                <c:if test="${order.orderStatus eq 'WAITING'}">
                                                    <a href="library?command=CANCEL_ORDER&orderId=${order.id}"
                                                       class="rooms-table__button">
                                                        <fmt:message key="orders.cancel_button"/>
                                                    </a>
                                                    <a href="library?command=CONFIRM_ORDER&orderId=${order.id}"
                                                       class="rooms-table__button">
                                                        <fmt:message key="orders.confirm_button"/>
                                                    </a>
                                                </c:if>
                                                <c:if test="${order.orderStatus eq 'ACTIVE'}">
                                                    <a href="library?command=RETURN_BOOK&orderId=${order.id}"
                                                       class="rooms-table__button">
                                                        <fmt:message key="orders.return_button"/>
                                                    </a>
                                                </c:if>
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
                            <a class="page-link" href="library?command=SHOW_ALL_ORDERS&page=1" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                        <li class="page-item"><a class="page-link" href="library?command=SHOW_ALL_ORDERS&page=1">1</a></li>
                        <li class="page-item"><a class="page-link" href="library?command=SHOW_ALL_ORDERS&page=2">2</a></li>
                        <li class="page-item"><a class="page-link" href="library?command=SHOW_ALL_ORDERS&page=3">3</a></li>
                        <li class="page-item"><a class="page-link" href="library?command=SHOW_ALL_ORDERS&page=4">4</a></li>
                        <li class="page-item"><a class="page-link" href="library?command=SHOW_ALL_ORDERS&page=5">5</a></li>
                        <li class="page-item">
                            <a class="page-link" href="library?command=SHOW_ALL_ORDERS&page=5" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </ul>
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
