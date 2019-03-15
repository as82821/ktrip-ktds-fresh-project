<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
</head>
<body>
	<!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
        <div class="container">
            <a class="navbar-brand" href="./index.jsp"><img src="./img/logo2.png" id="nav-logo" alt="KTrip"></a>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="true" aria-label="Toggle navigation">
                Menu
                <i class="fas fa-bars"></i>
            </button>
            <div class="navbar-collapse collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="./index.jsp">service</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./guide.jsp">guide</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./tourlist.jsp">tourlist</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./contactus.jsp">contact</a>
                    </li>
                    <li class="nav-item"><div class="popup" onclick="user_popup()">
                        <img src="./img/user2.png" id="nav-user" alt="User">
                        <%if(session.getAttribute("user_id")==null){ %>
                        <span class="popuptext" id="myPopup"><a href="login2.jsp">Login</a>
                        <br>
                        <%}else{ %>
                        <span class="popuptext" id="myPopup"><a href="userinfo2.jsp">MyPage</a>
                        <br>
                        <a href="guideRegister.jsp">GuideRegister</a>
                        <a href="logoutAction.jsp">LogOut</a></span>
                        </span>
                        <%} %>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</body>
</html>