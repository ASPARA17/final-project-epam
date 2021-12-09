<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Library</title>
    <link href="${pageContext.request.contextPath}/css/main_page.css" rel="stylesheet">
</head>
<body>
<c:import url="header.jsp"/>
ABOUT LIBRARY
    <a class="nav-link active" href="${pageContext.request.contextPath}/library?command=SHOW_ERROR_PAGE">
    <fmt:message key="header.home"/></a>
</body>
</html>
