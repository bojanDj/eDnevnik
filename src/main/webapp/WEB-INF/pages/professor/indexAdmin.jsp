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
	
  <!--==========================
    Hero Section
  ============================-->
  <section id="hero" class="wow fadeIn">
    <div class="hero-container">
      <h1>Korisnice ${username}, dobrodosli u eDnevnik</h1>
      <h2>Mesto na kome mozete elektronski ocenjivati ucenike</h2>
      <img src="${pageContext.request.contextPath}/resources/img/grb.jpg" alt="Hero Imgs">
      <a href="#features" class="btn-get-started scrollto">Pocnite sa radom</a>
      <div class="btns">
        <a href="#"><i class="fa fa-apple fa-3x"></i> App Store</a>
        <a href="#"><i class="fa fa-play fa-3x"></i> Google Play</a>
        <a href="#"><i class="fa fa-windows fa-3x"></i> windows</a>
      </div>
    </div>
  </section><!-- #hero -->

  <!--==========================
    Features Section
  ============================-->

  <section id="features" class="padd-section text-center wow fadeInUp">

    <div class="container">
      <div class="section-title text-center">
        <h2>Izbor odeljenja.</h2>
        <p class="separator">Izaberite odeljenje.</p>
      </div>
    </div>

    <div class="container">
      <div class="row">
        <!--staviti u foreach petlji-->
        <div class="col-md-6 col-lg-3">
          <a href="<c:url value = "/admin/odeljenja/1"/>" style="text-decoration: none;">
          <div class="feature-block">
            <img src="${pageContext.request.contextPath}/resources/img/number/one.png" alt="img" class="img-fluid">
            <h4>Prvi razred</h4>
            <p>Opis prvog razreda</p>
          </div>
        </a>
        </div>

        <div class="col-md-6 col-lg-3">
          <a href="<c:url value = "/admin/odeljenja/2"/>" style="text-decoration: none;">
          <div class="feature-block">
            <img src="${pageContext.request.contextPath}/resources/img/number/two.jpg" alt="img" class="img-fluid">
            <h4>Drugi razred</h4>
            <p>Opis drugog razreda</p>
          </div>
          </a>
        </div>

        <div class="col-md-6 col-lg-3">
          <a href="<c:url value = "/admin/odeljenja/3"/>" style="text-decoration: none;">
          <div class="feature-block">
            <img src="${pageContext.request.contextPath}/resources/img/number/three.png" alt="img" class="img-fluid">
            <h4>Treci razred</h4>
            <p>Opis treceg razreda</p>
          </div>
          </a>
        </div>

        <div class="col-md-6 col-lg-3">
          <a href="<c:url value = "/admin/odeljenja/4"/>" style="text-decoration: none;">
          <div class="feature-block">
            <img src="${pageContext.request.contextPath}/resources/img/number/four.png" alt="img" class="img-fluid">
            <h4>Cetvrti razred</h4>
            <p>Opis cetvrtog razreda</p>
          </div>
          </a>
        </div>

      </div>
    </div>
  </section>
</body>
</html>