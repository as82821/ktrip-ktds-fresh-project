<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>KTrip: 나만을 위한 여행 가이드</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- jQuery and moment.js and rome.js-->
    <script src="./bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="./bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<link href="./bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom fonts for this template -->
	<link href="./vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	<link href="./index_files/css" rel="stylesheet" type="text/css">
	<link href="./index_files/css(1)" rel="stylesheet" type="text/css">
	<link href="./index_files/icon" rel="stylesheet">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href="./css/clean-blog.min.css" rel="stylesheet">
	<!-- faq.css -->
	<link href="./css/notice.css" rel="stylesheet" type="text/css">
	<!-- common.css, common.js clean-blog.min.js -->
	<link href="./css/common.css" rel="stylesheet" type="text/css">
	<script src="./js/common.js"></script>
	<script src="js/clean-blog.min.js"></script>
	<link rel="icon" type="image/png" href="img/icons/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="login_fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
	<link rel="stylesheet" type="text/css" href="vendor/login_vendor/animate/animate.css">
	<link rel="stylesheet" type="text/css" href="vendor/login_vendor/css-hamburgers/hamburgers.min.css">
	<link rel="stylesheet" type="text/css" href="vendor/login_vendor/animsition/css/animsition.min.css">
	<link rel="stylesheet" type="text/css" href="vendor/login_vendor/select2/select2.min.css">
	<link rel="stylesheet" type="text/css" href="vendor/login_vendor/daterangepicker/daterangepicker.css">
	<link rel="stylesheet" type="text/css" href="css/login_css/util.css">
	<link rel="stylesheet" type="text/css" href="css/login_css/main.css">
	<script src="vendor/login_vendor/animsition/js/animsition.min.js"></script>
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.login.min.js"></script>
	<script src="vendor/login_vendor/select2/select2.min.js"></script>
	<script src="vendor/login_vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/login_vendor/daterangepicker/daterangepicker.js"></script>
	<script src="vendor/login_vendor/countdowntime/countdowntime.js"></script>
	<script src="js/login_main.js"></script>
</head>
<body>
	<div class="limiter">
		<div class="container-login100" style="background-image: url('img/login4-bg.jpg');">
			<div class="wrap-login100 p-t-30 p-b-50">
				<span class="login100-form-title p-b-41">
					<img src="./img/logo2.png" width="220" height="80" alt="User">
				</span>
				<form class="login100-form validate-form p-b-25 p-t-5">
					<div class="wrap-input100 validate-input" data-validate = "Enter ID">
						<input class="input100" type="text" name="username" placeholder="ID">
						<span class="focus-input100" data-placeholder="&#xe82a;"></span>
					</div>
					<div class="wrap-input100 validate-input" data-validate="Enter password">
						<input class="input100" type="password" name="pass" placeholder="PASSWORD">
						<span class="focus-input100" data-placeholder="&#xe80f;"></span>
					</div>
					<div class="container-login100-form-btn m-t-28">
						<button type="button" class="login100-form-btn" data-toggle="modal" data-target="#signup-modal">
							SIGN UP
						</button>
						<button type="button" class="login100-form-btn" onclick="window.location.href='./index.jsp'">
							SHORT CUT
						</button>
					</div>
					<div class="container-login100-form-btn m-t-16">
						<button type="submit" class="login100-form-btn2" id="signin">
							LOGIN
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="signup-modal" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<!-- Page Header -->
				<header class="signup-modal-header modal-header masthead header-block">
					<div class="overlay"></div>
					<div class="container">
						<div class="row">
							<div class="col-lg-8 col-md-10 mx-auto">
								<div class="post-heading" id="signup-heading">
									<h2>SIGN UP</h2>
								</div>
							</div>
						</div>
					</div>
				</header>
				<!-- Main Content -->
				<article class="modal-body">
					<div class="container">
						<div class="row">
							<div class="col-lg-8 col-md-10 mx-auto">
							<form name="signup" id="signup" method="post">
								<div class="control-group">
									<h5 class=text-info>ID</h5>
									<input type="text" class="form-control" placeholder="ID를 입력해 주세요" id="id" data-validation-required-message="필수 항목입니다.">
									<p class="help-block text-danger"></p>
								</div>
								<div class="control-group">
								<h5 class=text-info>PASSWORD</h5>
									<input type="Password" class="form-control" placeholder="비밀번호를 입력해 주세요" id="pwd" data-validation-required-message="필수 항목입니다.">
									<p class="help-block text-danger"></p>
								</div>
								<div class="control-group">
								<h5 class=text-info>EMAIL</h5>
									<input type="Email" class="form-control" placeholder="이메일을 입력해 주세요" id="email" data-validation-required-message="필수 항목입니다.">
									<p class="help-block text-danger"></p>
								</div>
								<div class="control-group">
									<h5 class=text-info>NAME</h5>
									<input type="text" class="form-control" placeholder="이름을 입력해 주세요" id="name" data-validation-required-message="필수 항목입니다.">
									<p class="help-block text-danger"></p>
								</div>
								<div class="control-group">
										<h5 class=text-info>GENDER</h5>
										<table class="gender">
											<tr>
												<td>
													<input type="radio" id="sex" name="sex" value="man">
													<label for="MAN">MAN</label>
												</td>
												<td>
													<input type="radio" id="sex" name="sex" value="woman">
													<label for="WOMAN">WOMAN</label>
												</td>
											</tr>
										</table>
										<p></p>
									</div>
								<div class="control-group">
									<h5 class=text-info>PHONE</h5>
									<input type="text" class="form-control" placeholder="휴대폰 번호(-제외)를 입력해 주세요" id="phone_num" data-validation-required-message="필수 항목입니다.">
									<p class="help-block text-danger"></p>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group form-horizontal">
											<h5 class=text-info>COUNTRY</h5>
											<select class="form-control gds-cr gds-countryflag" id="nationaility" data-language="ko"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group form-horizontal">
											<h5 class=text-info>RESIDENTIAL COUNTRY</h5>
											<select class="form-control gds-cr gds-countryflag" id="residential_country" data-language="ko"></select>
										</div>
									</div>
								</div>
								<p>&nbsp;</p>
								<div class="form-group">
								<button type="submit" class="btn btn-primary" id="register">REGISTER</button>
								<button type="button" class="btn btn-primary" id="cancel" onclick="location.href='./index.jsp' "> CANCEL </button>
								</div>
							</form>
							</div>
						</div>
					</div>
				</article>
				<p></p>
				<!-- Footer -->
				<footer class="modal-footer">
					<div class="container">
						<div class="row">
						<div class="col-lg-8 col-md-10 mx-auto">
							<ul class="list-inline text-center">
							<li class="list-inline-item">
								<a href="./index.jsp#">
								<span class="fa-stack fa-lg">
									<i class="fas fa-circle fa-stack-2x"></i>
									<i class="fab fa-chrome fa-stack-1x fa-inverse"></i>
								</span>
								</a>
							</li>
							<li class="list-inline-item">
								<a href="https://github.com/WonHyeongCho/ktcp-fresh-ktds#">
								<span class="fa-stack fa-lg">
									<i class="fas fa-circle fa-stack-2x"></i>
									<i class="fab fa-github fa-stack-1x fa-inverse"></i>
								</span>
								</a>
							</li>
							<li class="list-inline-item">
								<a href="./contactus.jsp">
								<span class="fa-stack fa-lg">
									<i class="fas fa-circle fa-stack-2x"></i>
									<i class="fas fa-envelope fa-stack-1x fa-inverse"></i>
								</span>
								</a>
							</li>
							</ul>
							<p class="copyright text-muted">주소: 서울특별시 서초구 방배동 1001-1 대표 전화 : 02-523-7029 <br>
							<a href="./index.jsp#">KTrip</a>
							Copyright © <a href="https://www.ktds.com/index.jsp">KTds</a> All Rights Reserved.</p>
						</div>
						</div>
					</div>
				</footer>
			</div>
		</div>
	</div>				
</body>
</html>