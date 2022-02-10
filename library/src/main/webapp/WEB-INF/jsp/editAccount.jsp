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
    <title><fmt:message key="edit_account.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addBook.css">
</head>
<body>
<div class="layout">
    <c:import url="header.jsp"/>
    <div class="layout-body"
         style="background-image: url(${pageContext.request.contextPath}/images/main.jpg);">

        <c:choose>
            <c:when test="${not empty requestScope.successEditAccount}">
                <p>${requestScope.error}</p>
                <a href="${pageContext.request.contextPath}/controller?command=SHOW_MAIN_PAGE">Try again</a>
            </c:when>
            <c:otherwise>
                <c:if test="${sessionScope.successEditAccount == true}">
                    <div class = "container p-3">
                        <div class="alert alert-success alert-dismissible">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <fmt:message key="alert.success_edit_account"/>
                        </div>
                        <%session.setAttribute("successEditAccount", false);%>
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
                                <h3 class="text-center text-light"><fmt:message key="edit_account.edit_account"/></h3>

                                <div class="form-group">
                                    <label for="firstName" class="text-light"><fmt:message key="edit_account.first_name"/></label><br>
                                    <input type="text" name="firstName" id="firstName" class="form-control"
                                           value="${sessionScope.account.firstName}" required pattern="^[a-zA-Z]{3,20}$"
                                           oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_name"/>')"
                                           onchange="this.setCustomValidity('')" value="${userData['firstName']}"/>
                                </div>

                                <div class="form-group">
                                    <label for="secondName" class="text-light"><fmt:message key="edit_account.second_name"/></label><br>
                                    <input type="text" name="secondName" id="secondName" class="form-control"
                                           value="${sessionScope.account.secondName}" required pattern="^[a-zA-Z]{3,20}$"
                                           oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_surname"/>')"
                                           onchange="this.setCustomValidity('')" value="${userData['secondName']}"/>
                                </div>

                                <div class="form-group">
                                    <label for="phone" class="text-light"><fmt:message key="edit_account.phone"/></label><br>
                                    <input type="text" name="phone" id="phone" class="form-control"
                                           value="${sessionScope.account.phone}" required
                                           pattern="^\+375(25|44|29|33)\d{7}$"
                                           oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_phone"/>')"
                                           onchange="this.setCustomValidity('')" value="${userData['phone']}"/>
                                </div>

                                <div class="form-group">
                                    <label for="subscriptionId" class="text-light"><fmt:message key="edit_account.subscription"/></label><br>
                                    <input type="text" name="subscriptionId" id="subscriptionId" class="form-control"
                                           value="${sessionScope.account.subscriptionId}" required
                                           pattern="(^[\d+]{6}$)||(^0$)"
                                           oninvalid="this.setCustomValidity('<fmt:message key="edit_account.invalid_subscription"/>')"
                                           onchange="this.setCustomValidity('')" value="${userData['subscriptionId']}"/>
                                </div>


                                <div class="form-group">
                                    <input type="hidden" name="command" value="EDIT_ACCOUNT"/>
                                    <input type="submit" class="btn btn-primary btn-md" value=
                                    <fmt:message key="edit_account.edit_button"/>
                                    >
                                    <a href="library?command=SHOW_MAIN_PAGE"
                                       class="btn btn-secondary">
                                        <fmt:message key="edit_account.cancel_button"/>
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
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</html>
