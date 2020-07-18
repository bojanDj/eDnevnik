<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profesore dobrososli</title>
</head>
<body>

  <div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-pic js-tilt" data-tilt>
					<img src="${pageContext.request.contextPath}/resources/img/grb.jpg" alt="IMG">
				</div>

				<form th:action="/login" method="post" class="login100-form validate-form">
					<span class="login100-form-title">
						Prijavite se
					</span>

					<div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                                                            <input id="login__username" type="text" name="username" class="form__input" placeholder="Korisnicko ime" required>
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "Password is required">
                                                <input id="login__password" type="password" name="password" class="form__input" placeholder="Sifra" required>
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
					</div>
					
					<div class="container-login100-form-btn ">
						<button class=" btn-primary" id = "loginbtn">
							Prijavi se
						</button>
					</div>

				</form>
			</div>
		</div>
	</div>
    


</body>
</html>