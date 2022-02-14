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
    <title><fmt:message key="catalog.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/media.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/sideList.css">
</head>
<body>
    <div class="layout">
        <c:import url="header.jsp"/>
        <div class="layout-body"
             style="background-image: url(${pageContext.request.contextPath}/images/main.jpg);">

            <c:choose>
                <c:when test="${not empty requestScope.successMakeOrder}">
                    <p>${requestScope.error}</p>
                    <a href="${pageContext.request.contextPath}/controller?command=SHOW_ALL_BOOKS">Try again</a>
                </c:when>
                <c:otherwise>
                    <c:if test="${sessionScope.successMakeOrder == true}">
                        <div class = "container p-3">
                            <div class="alert alert-success alert-dismissible" style="width: 500px; margin:auto">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                <fmt:message key="alert.success_make_order"/> "${sessionScope.orderNameBook}"!
                            </div>
                            <%session.setAttribute("successMakeOrder", false);%>
                        </div>
                    </c:if>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${not empty requestScope.successEditBook}">
                    <p>${requestScope.error}</p>
                    <a href="${pageContext.request.contextPath}/controller?command=SHOW_ALL_BOOKS">Try again</a>
                </c:when>
                <c:otherwise>
                    <c:if test="${sessionScope.successEditBook == true}">
                        <div class = "container p-3">
                            <div class="alert alert-success alert-dismissible" style="width: 500px; margin:auto">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                <fmt:message key="alert.success_edit_book"/>
                            </div>
                            <%session.setAttribute("successEditBook", false);%>
                        </div>
                    </c:if>
                </c:otherwise>
            </c:choose>
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

            <div class="booking-section">
                <div class="booking-section__inner">
                    <div class="sidenav">
                        <a href="library?command=FILTER_BOOKS&genreParam=detective"><fmt:message key="catalog.detective"/></a>
                        <a href="library?command=FILTER_BOOKS&genreParam=fantastic"><fmt:message key="catalog.fantastic"/></a>
                        <a href="library?command=FILTER_BOOKS&genreParam=adventure"><fmt:message key="catalog.adventure"/></a>
                        <a href="library?command=FILTER_BOOKS&genreParam=novel"><fmt:message key="catalog.novel"/></a>
                        <a href="library?command=FILTER_BOOKS&genreParam=scientific"><fmt:message key="catalog.scientific"/></a>
                        <a href="library?command=FILTER_BOOKS&genreParam=children"><fmt:message key="catalog.children"/></a>
                        <a href="library?command=FILTER_BOOKS&genreParam=educational"><fmt:message key="catalog.educational"/></a>
                    </div>
                    <div class="rooms">
                            <div class="rooms__header">
                                <h2><fmt:message key="catalog.books"/></h2>
                                <c:if test="${not empty allBooks}">
                                    <div class="rooms__sort">
                                        <span class="rooms__label"><fmt:message key="catalog.sort_by"/></span>
                                        <a href="library?command=SORT_BOOKS&bookSortParam=name"
                                           class="rooms__sort-link"><fmt:message key="catalog.name_of_book"/></a>
                                        <a href="library?command=SORT_BOOKS&bookSortParam=quantity"
                                           class="rooms__sort-link"><fmt:message key="catalog.quantity"/></a>
                                    </div>
                                </c:if>
                                <c:if test="${userRole == 'ADMIN'}">
                                    <div class="rooms-table__action">
                                        <a href="library?command=SHOW_ADD_BOOK_PAGE"
                                           class="rooms-table__button">
                                            <fmt:message key="catalog.add_button"/>
                                        </a>
                                    </div>
                                </c:if>
                            </div>
                            <table class="rooms-table">
                                <c:choose>
                                    <c:when test="${empty allBooks && allBooks.size() == 0}">
                                        <tr>
                                            <td><fmt:message key="catalog.not_found_message"/></td>
                                        </tr>
                                    </c:when>
                                    <c:when test="${not empty allBooks}">
                                        <tr>
                                            <c:if test="${userRole == 'ADMIN'}">
                                                <th>Id book</th>
                                            </c:if>
                                            <th><fmt:message key="catalog.name_of_book"/></th>
                                            <th><fmt:message key="catalog.author"/></th>
                                            <th><fmt:message key="catalog.genre"/></th>
                                            <th><fmt:message key="catalog.publishing_house"/></th>
                                            <th><fmt:message key="catalog.year_publishing"/></th>
                                            <th><fmt:message key="catalog.number_of_page"/></th>
                                            <th><fmt:message key="catalog.quantity"/></th>
                                            <th></th>
                                        </tr>
                                        <c:forEach var="book" items="${sessionScope.allBooks}">
                                            <tr>
                                                <c:if test="${userRole == 'ADMIN'}">
                                                    <td>${book.id}</td>
                                                </c:if>
                                                <td>${book.name}</td>
                                                <td>${book.author}</td>
                                                <td>${book.genre}</td>
                                                <td>${book.publishingHouse}</td>
                                                <td>${book.yearPublishing}</td>
                                                <td>${book.numberOfPage}</td>
                                                <td>${book.quantity}</td>
                                                <td class="rooms-table__action">
                                                    <c:if test="${userRole == 'USER'}">
                                                        <c:if test="${book.quantity > 0}">
                                                            <a href="library?command=MAKE_ORDER&bookId=${book.id}"
                                                               class="rooms-table__button">
                                                                <fmt:message key="catalog.order_button"/>
                                                            </a>
                                                        </c:if>
                                                    </c:if>
                                                    <c:if test="${userRole == 'ADMIN'}">
                                                        <a href="library?command=SHOW_EDIT_BOOK_PAGE&bookId=${book.id}"
                                                           class="rooms-table__button">
                                                            <fmt:message key="catalog.edit_button"/>
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
                <div class="pages">
                    <ul class="pagination">
                        <li class="page-item">
                            <a class="page-link" href="library?command=SHOW_ALL_BOOKS&page=1" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                        <li class="page-item"><a class="page-link" href="library?command=SHOW_ALL_BOOKS&page=1">1</a></li>
                        <li class="page-item"><a class="page-link" href="library?command=SHOW_ALL_BOOKS&page=2">2</a></li>
                        <li class="page-item"><a class="page-link" href="library?command=SHOW_ALL_BOOKS&page=3">3</a></li>
                        <li class="page-item"><a class="page-link" href="library?command=SHOW_ALL_BOOKS&page=4">4</a></li>
                        <li class="page-item"><a class="page-link" href="library?command=SHOW_ALL_BOOKS&page=5">5</a></li>
                        <li class="page-item">
                            <a class="page-link" href="library?command=SHOW_ALL_BOOKS&page=5" aria-label="Next">
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
