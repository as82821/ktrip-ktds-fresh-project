<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Notice</title>
	
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
</head>
<body>
	<%@ include file="./header.jsp"%>
	<!-- Page Header -->
    <header class="masthead header-block" id="notice-header-img">
        <div class="overlay"></div>
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-md-10 mx-auto">
	                <div class="page-heading">
	                    <h1>NOTICE</h1>
	                </div>
                </div>
            </div>
        </div>
    </header>
    <!-- Main Content -->
    <div class="container">
        <div class="row">
            <div class="col-lg-9 col-md-10 mx-auto">
                <!--Sub Header-->
                <nav class="navbar navbar-expand-lg">
                    <div class="container-fluid">
                        <div class="navbar-collapse collapse show" id="navbarResponsive">
                            <ul class="navbar-nav mr-auto">
                                <li class="nav-item">
                                    <a class="nav-link" href="./notice.jsp">공지사항</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="./faq.jsp">자주 묻는 질문</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="./contactus.jsp">문의하기</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
                <hr>
            </div>
            <div class="col-lg-8 col-md-10 mx-auto">
                <table>
                    <tr>
                        <td>전화번호 변경 안내입니다.</td>
                        <td class="more" rowspan="2"><a data-toggle="modal" href="#notice-modal-1" id="notice-modal" ><img class="next-img" src="./img/next.png"></a>
                        </td>
                    </tr>
                    <tr class="even">
                        <td>관리자 | 2019.02.28</td>
                    </tr>
                    <tr>
                        <td>가이드에게 문의를 했는데 답변이 없어요.</td>
                        <td class="more" rowspan="2"><a href="./notice_detail.jsp"> <img class="next-img" src="./img/next.png"></a>
                    </tr>
                    <tr class="even">
                        <td>관리자 | 2019.02.28</td>
                    </tr>
                    <tr>
                        <td>환불은 언제 되나요?</td>
                        <td class="more" rowspan="2"><a href="./notice_detail.jsp"> <img class="next-img" src="./img/next.png"></a></td>
                    </tr>
                    <tr class="even">
                        <td>관리자 | 2019.02.28</td>
                    </tr>
                    <tr>
                        <td>계정 탈퇴는 어떻게 하나요?</td>
                        <td class="more" rowspan="2"><a href="./notice_detail.jsp"> <img class="next-img" src="./img/next.png"></a></td>
                    </tr>
                    <tr class="even">
                        <td>관리자 | 2019.02.28</td>
                    </tr>
                    <tr>
                        <td>비밀번호/로그인 계정을 잊어버렸는데 초기화할 수 있나요?</td>
                        <td class="more" rowspan="2"><a href="./notice_detail.jsp"> <img class="next-img" src="./img/next.png"></a></td>
                    </tr>
                    <tr class="even">
                        <td>관리자 | 2019.02.28</td>
                    </tr>
                </table>
                <!-- modal content -->
                <div class="modal fade" id="notice-modal-1" role="dialog">
	                <div class="modal-dialog modal-lg">
	                    <div class="modal-content">
	                        <!-- Page Header -->
	                        <header class="notice-modal modal-header masthead header-block">
	                            <div class="overlay"></div>
	                            <div class="container">
	                                <div class="row">
	                                    <div class="col-lg-8 col-md-10 mx-auto">
	                                        <div class="post-heading" id="notice-heading">
	                                            <h2>전화번호 변경 안내입니다.</h2>
	                                            <span class="meta">관리자 | 2019.02.28</span>
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </header>
	                        <!-- Main Content -->
	                        <article class="modal-body">
	                            <div class="container">
	                                <div class="row">
	                                    <div class="col-lg-10 col-md-10 mx-auto">
	                                        <h5 class="text-info">제목</h5>
	                                        <div class="title" id="notice_title">전화번호 변경 안내입니다</div>
	                                        <hr>
	                                        <h5 class="text-info">내용</h5>
	                                        <div class="content" id="notice_content">
	                                            ktrip 대표 전화번호가 02-1111-2222로 변경되었습니다.<br>
	                                            * 연결 가능 시간 9:00 ~ 18:00
	                                        </div>
	                                    </div>
	                                </div>
	                            </div>
	                        </article>
	                        <hr>
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
	                                        <a href="./contactus.jsp#">
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
	            <!-- modal content -->
                <div class="row justify-content-center">
                    <div class="pagination">
                        <a></a>
                        <a class="active">1</a>
                        <a>></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
	<hr>
	<%@ include file="./footer.jsp"%>
</body>
</html>