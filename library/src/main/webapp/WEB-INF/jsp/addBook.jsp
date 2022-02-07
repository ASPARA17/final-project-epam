<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title><fmt:message key="add_book.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addBook.css">
</head>
<body>
<div class="layout">
    <c:import url="header.jsp"/>
    <div class="layout-body"
         style="background-image: url(${pageContext.request.contextPath}/images/main.jpg);">

        <c:choose>
            <c:when test="${not empty requestScope.successAddBook}">
                <p>${requestScope.error}</p>
                <a href="${pageContext.request.contextPath}/controller?command=SHOW_ADD_BOOK_PAGE">Try again</a>
            </c:when>
            <c:otherwise>
                <c:if test="${sessionScope.successAddBook == true}">
                    <div class = "container p-3">
                        <div class="alert alert-success alert-dismissible">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <fmt:message key="alert.success_add_book"/>
                        </div>
                        <%session.setAttribute("successAddBook", false);%>
                    </div>
                </c:if>
            </c:otherwise>
        </c:choose>

        <div id="login">
            <div class="container">
                <div id="login-row" class="row justify-content-center align-items-center">
                    <div id="login-column" >
                        <div id="login-box">
                            <form id="login-form" class="form" action="library" method="post">
                                <h3 class="text-center text-light"><fmt:message key="add_book.add_book"/></h3>

                                <div class="form-group">
                                    <label for="author" class="text-light">Author</label><br>
                                    <input type="text" name="author" id="author" class="form-control"
                                        placeholder="Author" required
                                        pattern="^[a-zA-Z\s]{3,40}$"
                                        oninvalid="this.setCustomValidity('Invalid author')"
                                        onchange="this.setCustomValidity('')" value="${bookData['author']}"/>
                                </div>

                                <div class="form-group">
                                    <label for="name" class="text-light">Name</label><br>
                                    <input type="text" name="name" id="name" class="form-control"
                                           placeholder="Name" required
                                           pattern="^.{1,100}$"
                                           oninvalid="this.setCustomValidity('Invalid name')"
                                           onchange="this.setCustomValidity('')" value="${bookData['name']}"/>
                                </div>

                                <div class="form-group">
                                    <label for="publisher" class="text-light">Publisher</label><br>
                                    <input type="text" name="publisher" id="publisher" class="form-control"
                                           placeholder="Publisher" required
                                           pattern="^[a-zA-Z\s]{3,40}$"
                                           oninvalid="this.setCustomValidity('Invalid publisher')"
                                           onchange="this.setCustomValidity('')" value="${bookData['publisher']}"/>
                                </div>

                                <div class="row">

                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <span class="form-label">Genre</span>
                                            <select class="form-control" required name="genre">
                                                <option value="" selected hidden>Select genre</option>
                                                <option value="1">Detective</option>
                                                <option value="2">Fantastic</option>
                                                <option value="3">Adventure</option>
                                                <option value="4">Novel</option>
                                                <option value="5">Scientific</option>
                                                <option value="6">Children</option>
                                                <option value="7">Educational</option>
                                            </select>
                                            <span class="select-arrow"></span>
                                        </div>
                                    </div>

                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <label for="yearPublishing" class="text-light">Year publishing</label><br>
                                            <input type="text" name="yearPublishing" id="yearPublishing" class="form-control"
                                                   required pattern="^\d+$"
                                                   oninvalid="this.setCustomValidity('Invalid year')"
                                                   onchange="this.setCustomValidity('')" value="${bookData['yearPublishing']}"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <label for="pages" class="text-light">Pages</label><br>
                                            <input type="text" name="pages" id="pages" class="form-control"
                                                   required pattern="^\d+{1,2000}$"
                                                   oninvalid="this.setCustomValidity('Invalid pages')"
                                                   onchange="this.setCustomValidity('')" value="${bookData['pages']}"/>
                                        </div>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6">
                                        <div class="form-group">
                                            <label for="quantity" class="text-light">Quantity</label><br>
                                            <input type="text" name="quantity" id="quantity" class="form-control"
                                                   required pattern="^\d+{0,50}$"
                                                   oninvalid="this.setCustomValidity('Invalid quantity')"
                                                   onchange="this.setCustomValidity('')" value="${bookData['quantity']}"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <input type="hidden" name="command" value="ADD_BOOK"/>
                                    <input type="submit" class="btn btn-primary btn-md" value=
                                    "Add"
                                    >
                                </div>
                                <span class="error" style="+color:#ff340a">${errorMessage}</span>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</html>
