<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
	integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
	crossorigin="anonymous">

<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<div class="container">
		<div class="row">
			<div class="col">
				<c:if test="${not empty message}">
					<div class="alert alert-success">${message}</div>
				</c:if>
				<c:if test="${not empty error}">
					<div class="alert alert-danger">${error}</div>

				</c:if>
			</div>
		</div>
		<br />

		<div class="row">
			<div class="col-sm-12">
				<form action="login" method="post">
					<div class="form-group">
						<label for="username">User Name: </label> <input type="text"
							class="form-control" id="username" name="username">
					</div>
					<div class="form-group">
						<label for="password">Password: </label> <input type="password"
							class="form-control" id="password" name="password">
					</div>

					<div class="col-xs-6">
						<label class="checkbox"><input type="checkbox"
							name="remember" /> <i></i> Nhớ tôi</label>
					</div>
					<div class="col-xs-6 text-right">
						<a href="${pageContext.request.contextPath }/forgotpass">Quên
							mật khẩu?</a>
					</div>
					<button type="submit" class="btn btn-primary">Login</button>
				</form>
			</div>
		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous" type="text/javascript"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
		crossorigin="anonymous" type="text/javascript"></script>
</body>
</html>