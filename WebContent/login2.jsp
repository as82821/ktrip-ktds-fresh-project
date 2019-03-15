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
<link href="./bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom fonts for this template -->
<link href="./vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link src="./index_files/css" rel="stylesheet" type="text/css">
<link src="./index_files/css(1)" rel="stylesheet" type="text/css">
<link src="./index_files/icon" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="./css/clean-blog.min.css" rel="stylesheet">
<!-- faq.css -->
<link href="./css/notice.css" rel="stylesheet" type="text/css">
<!-- common.css, common.js clean-blog.min.js -->
<link href="./css/common.css" rel="stylesheet" type="text/css">
<script src="./js/common.js"></script>
<script src="js/clean-blog.min.js"></script>
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="img/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="login_fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/login_vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/login_vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/login_vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/login_vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="vendor/login_vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/login_css/util.css">
<link rel="stylesheet" type="text/css" href="css/login_css/main.css">
<!--===============================================================================================-->
<script src="vendor/login_vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.login.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/login_vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/login_vendor/daterangepicker/moment.min.js"></script>
<script src="vendor/login_vendor/daterangepicker/daterangepicker.js"></script>
<script src="vendor/login_vendor/countdowntime/countdowntime.js"></script>
<script src="js/login_main.js"></script>
<script type="text/javascript">
	// 회원가입 화면의 입력값들을 검사한다.
	function checkValue() {
		var form = document.loginForm;

		if (!form.id.value) {
			alert("아이디를 입력하세요.");
			return false;
		}

		if (!form.pwd.value) {
			alert("비밀번호를 입력하세요.");
			return false;
		}
	}

	function checkValue2() {
		var form = document.signup;

		if (!form.id.value) {
			alert("아이디를 입력하세요.");
			return false;
		}
		/*
		if(form.idDuplication.value != "idCheck"){
			alert("아이디 중복체크를 해주세요.");
			return false;
		}
		 */
		if (!form.pwd.value) {
			alert("비밀번호를 입력하세요.");
			return false;
		}

		if (!form.name.value) {
			alert("이름을 입력하세요.");
			return false;
		}

		if (!form.email.value) {
			alert("메일 주소를 입력하세요.");
			return false;
		}

/*		if (!form.sex.value) {
			alert("성별을 입력하세요.");
			return false;
		}*/

		if (!form.phone_num.value) {
			alert("전화번호를 입력하세요.");
			return false;
		}

		if (!form.country.value) {
			alert("국적을 입력하세요.");
			return false;
		}

		if (!form.residential_country.value) {
			alert("현재 거주중인 국가를 입력하세요.");
			return false;
		}
	}

	function inputPhoneNumber(obj) {
		var number = obj.value.replace(/[^0-9]/g, "");
		var phone = "";

		if (number.length < 4) {
			return number;
		} else if (number.length < 7) {
			phone += number.substr(0, 3);
			phone += "-";
			phone += number.substr(3);
		} else if (number.length < 11) {
			phone += number.substr(0, 3);
			phone += "-";
			phone += number.substr(3, 3);
			phone += "-";
			phone += number.substr(6);
		} else {
			phone += number.substr(0, 3);
			phone += "-";
			phone += number.substr(3, 4);
			phone += "-";
			phone += number.substr(7);
		}
		obj.value = phone;
	}
</script>
</head>
<body>

	<div class="limiter">
		<div class="container-login100"
			style="background-image: url('img/login4-bg.jpg');">
			<div class="wrap-login100 p-t-30 p-b-50">
				<span class="login100-form-title p-b-41"> <img
					src="./img/logo2.png" width="220" height="80" alt="User">
				</span>
				<form class="login100-form validate-form p-b-25 p-t-5"
					id="loginForm" name="loginForm" action="loginCheck" method="post"
					onsubmit="return checkValue()">
					<div class="wrap-input100 validate-input" data-validate="Enter ID">
						<input class="input100" type="text" name="id" placeholder="ID">
						<span class="focus-input100" data-placeholder="&#xe82a;"></span>
					</div>
					<div class="wrap-input100 validate-input"
						data-validate="Enter password">
						<input class="input100" type="password" name="password"
							placeholder="PASSWORD"> <span class="focus-input100"
							data-placeholder="&#xe80f;"></span>
					</div>
					<div class="container-login100-form-btn m-t-28">
						<button type="button" class="login100-form-btn"
							data-toggle="modal" data-target="#signup-modal">SIGN UP
						</button>
						<button type="button" class="login100-form-btn"
							onclick="window.location.href='./index.jsp'">SHORT CUT</button>
					</div>
					<div class="container-login100-form-btn m-t-16">
						<button type="submit" class="login100-form-btn2" id="signin">
							LOGIN</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="signup-modal" role="dialog">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<!-- Page Header -->
				<header
					class="signup-modal-header modal-header masthead header-block">
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
								<form name="signup" id="signup" action="signupCheck"
									method="post" onsubmit="return checkValue2()"
									enctype="multipart/form-data">
									<div class="control-group">
										<h5 class=text-info>ID</h5>
										<input type="text" class="form-control"
											placeholder="ID를 입력해 주세요" id="id" name="id" required=""
											data-validation-required-message="필수 항목입니다.">
										<p class="help-block text-danger"></p>
									</div>

									<div class="control-group">
										<h5 class=text-info>PASSWORD</h5>
										<input type="Password" class="form-control"
											placeholder="비밀번호를 입력해 주세요" id="pwd" name="pwd" required=""
											data-validation-required-message="필수 항목입니다.">
										<p class="help-block text-danger"></p>
									</div>

									<div class="control-group">
										<h5 class=text-info>EMAIL</h5>
										<input type="Email" class="form-control"
											placeholder="이메일을 입력해 주세요" id="email" name="email"
											required="" data-validation-required-message="필수 항목입니다.">
										<p class="help-block text-danger"></p>
									</div>

									<div class="control-group">
										<h5 class=text-info>NAME</h5>
										<input type="text" class="form-control"
											placeholder="이름을 입력해 주세요" id="name" name="name" required=""
											data-validation-required-message="필수 항목입니다.">
										<p class="help-block text-danger"></p>
									</div>

									<div class="control-group">
										<h5 class=text-info>GENDER</h5>
										<table class="gender">
											<tr>
												<td><input type="radio" id="sex" name="sex" value="man">
													<label for="MAN">MAN</label></td>
												<td><input type="radio" id="sex" name="sex"
													value="woman"> <label for="WOMAN">WOMAN</label></td>
											</tr>
										</table>
										<p class></p>
									</div>
									<div class="control-group">
										<h5 class=text-info>PHONE</h5>
										<input type="text" class="form-control"
											onKeyup="inputPhoneNumber(this);" maxlength="13"
											placeholder="휴대폰 번호(-제외)를 입력해 주세요" id="phone_num"
											name="phone_num" data-validation-required-message="필수 항목입니다.">
										<p class="help-block text-danger"></p>
									</div>

									<div class="control-group">
										<h5 class=text-info>COUNTRY</h5>
										<input type="text" class="form-control"
											placeholder="국적을 입력해 주세요" id="country" name="country"
											required="" data-validation-required-message="필수 항목입니다.">
										<p class="help-block text-danger"></p>
									</div>

									<div class="control-group">
										<h5 class=text-info>RESIDENTIAL COUNTRY</h5>
										<input type="text" class="form-control"
											placeholder="거주국가를 입력해 주세요" id="residential_country"
											name="residential_country" required=""
											data-validation-required-message="필수 항목입니다.">
										<p class="help-block text-danger"></p>
									</div>

									<div class="control-group">
										<h5 class=text-info>USER PHOTO</h5>
										<input type="file" id="photo" name="photo">
									</div>
									<p>&nbsp;</p>
									<div class="form-group">
										<button type="submit" class="btn btn-primary" id="register">REGISTER</button>
										<button type="button" class="btn btn-primary" id="cancel"
											onclick="location.href='./login2.jsp' ">CANCEL</button>
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
									<li class="list-inline-item"><a href="./index.html#">
											<span class="fa-stack fa-lg"> <i
												class="fas fa-circle fa-stack-2x"></i> <i
												class="fab fa-chrome fa-stack-1x fa-inverse"></i>
										</span>
									</a></li>
									<li class="list-inline-item"><a
										href="https://github.com/WonHyeongCho/ktcp-fresh-ktds#"> <span
											class="fa-stack fa-lg"> <i
												class="fas fa-circle fa-stack-2x"></i> <i
												class="fab fa-github fa-stack-1x fa-inverse"></i>
										</span>
									</a></li>
									<li class="list-inline-item"><a href="./contactus.html">
											<span class="fa-stack fa-lg"> <i
												class="fas fa-circle fa-stack-2x"></i> <i
												class="fas fa-envelope fa-stack-1x fa-inverse"></i>
										</span>
									</a></li>
								</ul>
								<p class="copyright text-muted">
									주소: 서울특별시 서초구 방배동 1001-1 대표 전화 : 02-523-7029 <br> <a
										href="./index.html#">KTrip</a> Copyright © <a
										href="https://www.ktds.com/index.jsp">KTds</a> All Rights
									Reserved.
								</p>
							</div>
						</div>
					</div>
				</footer>
			</div>
		</div>
	</div>
</body>
</html>
