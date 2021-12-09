<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${not empty language}">
        <fmt:setLocale value="${sessionScope.language}"/>
    </c:when>
    <c:when test="${empty language}">
        <fmt:setLocale value="en"/>
    </c:when>
</c:choose>

<fmt:setBundle basename="language"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/regnew.css">--%>
<%--    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">--%>
<%--    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>--%>
<%--    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>--%>

    <title><fmt:message key="registration_page.title"/></title>
</head>
<%--<body>--%>
<%--<div class="container" id="wrap">--%>
<%--    <div class="row">--%>
<%--        <div class="col-md-6 col-md-offset-3">--%>
<%--            <form action="library?command=REGISTRATION" method="post" accept-charset="utf-8" class="form" role="form">   <legend>Sign Up</legend>--%>
<%--                <h4>It's free and always will be.</h4>--%>
<%--                <div class="row">--%>
<%--                    <div class="col-xs-6 col-md-6">--%>
<%--                        <input type="text" name="firstname" value="" class="form-control input-lg" placeholder="First Name"  />                        </div>--%>
<%--                    <div class="col-xs-6 col-md-6">--%>
<%--                        <input type="text" name="lastname" value="" class="form-control input-lg" placeholder="Last Name"  />                        </div>--%>
<%--                </div>--%>
<%--                <input type="text" name="email" value="" class="form-control input-lg" placeholder="Your Email"  /><input type="password" name="password" value="" class="form-control input-lg" placeholder="Password"  /><input type="password" name="confirm_password" value="" class="form-control input-lg" placeholder="Confirm Password"  />                    <label>Birth Date</label>                    <div class="row">--%>
<%--                    <div class="col-xs-4 col-md-4">--%>
<%--                        <select name="month" class = "form-control input-lg">--%>
<%--                            <option value="01">Jan</option>--%>
<%--                            <option value="02">Feb</option>--%>
<%--                            <option value="03">Mar</option>--%>
<%--                            <option value="04">Apr</option>--%>
<%--                            <option value="05">May</option>--%>
<%--                            <option value="06">Jun</option>--%>
<%--                            <option value="07">Jul</option>--%>
<%--                            <option value="08">Aug</option>--%>
<%--                            <option value="09">Sep</option>--%>
<%--                            <option value="10">Oct</option>--%>
<%--                            <option value="11">Nov</option>--%>
<%--                            <option value="12">Dec</option>--%>
<%--                        </select>                        </div>--%>
<%--                    <div class="col-xs-4 col-md-4">--%>
<%--                        <select name="day" class = "form-control input-lg">--%>
<%--                            <option value="1">1</option>--%>
<%--                            <option value="2">2</option>--%>
<%--                            <option value="3">3</option>--%>
<%--                            <option value="4">4</option>--%>
<%--                            <option value="5">5</option>--%>
<%--                            <option value="6">6</option>--%>
<%--                            <option value="7">7</option>--%>
<%--                            <option value="8">8</option>--%>
<%--                            <option value="9">9</option>--%>
<%--                            <option value="10">10</option>--%>
<%--                            <option value="11">11</option>--%>
<%--                            <option value="12">12</option>--%>
<%--                            <option value="13">13</option>--%>
<%--                            <option value="14">14</option>--%>
<%--                            <option value="15">15</option>--%>
<%--                            <option value="16">16</option>--%>
<%--                            <option value="17">17</option>--%>
<%--                            <option value="18">18</option>--%>
<%--                            <option value="19">19</option>--%>
<%--                            <option value="20">20</option>--%>
<%--                            <option value="21">21</option>--%>
<%--                            <option value="22">22</option>--%>
<%--                            <option value="23">23</option>--%>
<%--                            <option value="24">24</option>--%>
<%--                            <option value="25">25</option>--%>
<%--                            <option value="26">26</option>--%>
<%--                            <option value="27">27</option>--%>
<%--                            <option value="28">28</option>--%>
<%--                            <option value="29">29</option>--%>
<%--                            <option value="30">30</option>--%>
<%--                            <option value="31">31</option>--%>
<%--                        </select>                        </div>--%>
<%--                    <div class="col-xs-4 col-md-4">--%>
<%--                        <select name="year" class = "form-control input-lg">--%>
<%--                            <option value="1935">1935</option>--%>
<%--                            <option value="1936">1936</option>--%>
<%--                            <option value="1937">1937</option>--%>
<%--                            <option value="1938">1938</option>--%>
<%--                            <option value="1939">1939</option>--%>
<%--                            <option value="1940">1940</option>--%>
<%--                            <option value="1941">1941</option>--%>
<%--                            <option value="1942">1942</option>--%>
<%--                            <option value="1943">1943</option>--%>
<%--                            <option value="1944">1944</option>--%>
<%--                            <option value="1945">1945</option>--%>
<%--                            <option value="1946">1946</option>--%>
<%--                            <option value="1947">1947</option>--%>
<%--                            <option value="1948">1948</option>--%>
<%--                            <option value="1949">1949</option>--%>
<%--                            <option value="1950">1950</option>--%>
<%--                            <option value="1951">1951</option>--%>
<%--                            <option value="1952">1952</option>--%>
<%--                            <option value="1953">1953</option>--%>
<%--                            <option value="1954">1954</option>--%>
<%--                            <option value="1955">1955</option>--%>
<%--                            <option value="1956">1956</option>--%>
<%--                            <option value="1957">1957</option>--%>
<%--                            <option value="1958">1958</option>--%>
<%--                            <option value="1959">1959</option>--%>
<%--                            <option value="1960">1960</option>--%>
<%--                            <option value="1961">1961</option>--%>
<%--                            <option value="1962">1962</option>--%>
<%--                            <option value="1963">1963</option>--%>
<%--                            <option value="1964">1964</option>--%>
<%--                            <option value="1965">1965</option>--%>
<%--                            <option value="1966">1966</option>--%>
<%--                            <option value="1967">1967</option>--%>
<%--                            <option value="1968">1968</option>--%>
<%--                            <option value="1969">1969</option>--%>
<%--                            <option value="1970">1970</option>--%>
<%--                            <option value="1971">1971</option>--%>
<%--                            <option value="1972">1972</option>--%>
<%--                            <option value="1973">1973</option>--%>
<%--                            <option value="1974">1974</option>--%>
<%--                            <option value="1975">1975</option>--%>
<%--                            <option value="1976">1976</option>--%>
<%--                            <option value="1977">1977</option>--%>
<%--                            <option value="1978">1978</option>--%>
<%--                            <option value="1979">1979</option>--%>
<%--                            <option value="1980">1980</option>--%>
<%--                            <option value="1981">1981</option>--%>
<%--                            <option value="1982">1982</option>--%>
<%--                            <option value="1983">1983</option>--%>
<%--                            <option value="1984">1984</option>--%>
<%--                            <option value="1985">1985</option>--%>
<%--                            <option value="1986">1986</option>--%>
<%--                            <option value="1987">1987</option>--%>
<%--                            <option value="1988">1988</option>--%>
<%--                            <option value="1989">1989</option>--%>
<%--                            <option value="1990">1990</option>--%>
<%--                            <option value="1991">1991</option>--%>
<%--                            <option value="1992">1992</option>--%>
<%--                            <option value="1993">1993</option>--%>
<%--                            <option value="1994">1994</option>--%>
<%--                            <option value="1995">1995</option>--%>
<%--                            <option value="1996">1996</option>--%>
<%--                            <option value="1997">1997</option>--%>
<%--                            <option value="1998">1998</option>--%>
<%--                            <option value="1999">1999</option>--%>
<%--                            <option value="2000">2000</option>--%>
<%--                            <option value="2001">2001</option>--%>
<%--                            <option value="2002">2002</option>--%>
<%--                            <option value="2003">2003</option>--%>
<%--                            <option value="2004">2004</option>--%>
<%--                            <option value="2005">2005</option>--%>
<%--                            <option value="2006">2006</option>--%>
<%--                            <option value="2007">2007</option>--%>
<%--                            <option value="2008">2008</option>--%>
<%--                            <option value="2009">2009</option>--%>
<%--                            <option value="2010">2010</option>--%>
<%--                            <option value="2011">2011</option>--%>
<%--                            <option value="2012">2012</option>--%>
<%--                            <option value="2013">2013</option>--%>
<%--                        </select>                        </div>--%>
<%--                </div>--%>
<%--                <label>Gender : </label>                    <label class="radio-inline">--%>
<%--                    <input type="radio" name="gender" value="M" id=male />                        Male--%>
<%--                </label>--%>
<%--                <label class="radio-inline">--%>
<%--                    <input type="radio" name="gender" value="F" id=female />                        Female--%>
<%--                </label>--%>
<%--                <br />--%>
<%--                <span class="help-block">By clicking Create my account, you agree to our Terms and that you have read our Data Use Policy, including our Cookie Use.</span>--%>
<%--                <button class="btn btn-lg btn-primary btn-block signup-btn" type="submit">--%>
<%--                    Create my account</button>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--</body>--%>




<body style="background-image: url(${pageContext.request.contextPath}/images/registration.jpg);background-size: cover;">
<div class="container">
    <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title" align="center">
                        <fmt:message key="registration_page.please_registration"/></h3>
                </div>
                <div class="panel-body">
                    <form action="library?command=REGISTRATION" method="post">
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="firstName" id="firstName" class="form-control input-sm"
                                           placeholder=<fmt:message key="registration_page.name"/> required pattern="^[a-zA-Z]{3,20}$"
                                           oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_name"/>')"
                                           onchange="this.setCustomValidity('')" value="${registrationData['firstName']}"/>
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="text" name="secondName" id="secondName" class="form-control input-sm"
                                           placeholder=
                                           <fmt:message key="registration_page.surname"/> required pattern="^[a-zA-Z]{3,20}$"
                                           oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_surname"/>')"
                                           onchange="this.setCustomValidity('')" value="${registrationData['secondName']}"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="text" name="phone" id="phone" class="form-control input-sm"
                                   placeholder="+375(xx)xxx-xx-xx" required
<%--                                   pattern="^(\+375\([\d]{2}\)[\d]{3}\-[\d]{2}\-[\d]{2})$"--%>
                                   oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_phone"/>')"
                                   onchange="this.setCustomValidity('')" value="${registrationData['phone']}"/>
                        </div>
                        <div class="form-group">
                            <input type="text" name="login" id="login" class="form-control input-sm"
                                   placeholder=
                                   <fmt:message key="registration_page.login"/> required pattern="^[a-zA-Z0-9_]{3,20}$"
                                   oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_login"/>')"
                                   onchange="this.setCustomValidity('')" value="${registrationData['login']}"/>
                        </div>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password" id="password" class="form-control input-sm"
                                           placeholder=
                                           <fmt:message key="registration_page.password"/> required pattern="^.{3,20}$"
                                           oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_password"/>')"
                                           onchange="this.setCustomValidity('')" value="${registrationData['password']}"/>
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password_confirmation" id="password_confirmation"
                                           class="form-control input-sm" placeholder=
                                           <fmt:message key="registration_page.password_confirmation"/> required pattern="^.{3,20}$"
                                           oninvalid="this.setCustomValidity('<fmt:message key="registration_page.invalid_password"/>')"
                                           onchange="this.setCustomValidity('')" value="${registrationData['password_confirmation']}"/>
                                </div>
                            </div>
                        </div>
<%--                        <div class="someDiv">--%>
<%--                            <input type="hidden" name="command" value="REGISTRATION"/>--%>
<%--                            <input type="submit" class="btn btn-info btn-block" value=--%>
<%--                            <fmt:message key="registration_page.registration"/>>--%>
<%--                            <p class="message">--%>
<%--                                <fmt:message key="registration_page.return_to_sign_in"/>--%>
<%--                                <a href="library?command=SHOW_LOGIN_PAGE">--%>
<%--                                    <fmt:message key="registration_page.sign_in"/>--%>
<%--                                </a>--%>
<%--                            </p>--%>
<%--                        </div>--%>

<%--                        <input type="submit" class="btn btn-info btn-block"--%>
<%--                               value=<fmt:message key="registration_page.registration"/>>--%>

                        <div class="controls">
                            <button class="btn btn-success"><fmt:message key="registration_page.registration"/></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
