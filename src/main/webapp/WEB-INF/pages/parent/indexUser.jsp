<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
      <h1>Dobrodosli u eDnevnik</h1>
      <h2>Mesto na kome mozete proveriti ocene svoje dece</h2>
      <img src="${pageContext.request.contextPath}/resources/img/grb.jpg" alt="Hero Imgs">
      <a href="#glades" class="btn-get-started scrollto">Ocene dece</a>
      <div class="btns">
        <a href="#"><i class="fa fa-apple fa-3x"></i> App Store</a>
        <a href="#"><i class="fa fa-play fa-3x"></i> Google Play</a>
        <a href="#"><i class="fa fa-windows fa-3x"></i> windows</a>
      </div>
    </div>
  </section><!-- #hero -->

    <div style="display: flex; justify-content: space-evenly; margin-left: 10%;">
<div style="margin-left: 10px; margin-top: 5%;  " >
<c:forEach var="kid" items="${kids}">
    <div class="card" style="width: 18rem; margin-top: 10px;">
    <img class="card-img-top" src="${kid.url}" alt="Card image cap">
    <div class="card-body">
      <h5 class="card-title">${kid.ime} ${kid.prezime}</h5>
      <p class="card-text">Adresa i telefon</p>
          <c:if test="${empty ocene}">
              
             <a href="<c:url value = "${id}/${kid.ucenikID}#glades"/>" class="btn btn-primary">Vidi ocene</a>
          </c:if>
             <c:if test="${not empty ocene}">
             <a href="<c:url value = "../${id}/${kid.ucenikID}#glades"/>" class="btn btn-primary">Vidi ocene</a>
          </c:if>
    </div>
  </div>
    </c:forEach>
    
</div>

<div id = "glades" style=" width: 49%; margin-top: 5%; margin-left: 10%;">
    <c:if test="${not empty ocene}">
     <h2>${ocene[0].ucenik.ime} ${ocene[0].ucenik.prezime}</h2>
     <c:forEach var = "predmet" items="${predmetiBezPonavljanja}">
    <h3>${predmet}</h3>
    <c:if test="${not empty konacne}">    
        <c:forEach var = "konacnaOcena" items="${konacne}">
            <c:if test="${konacnaOcena.predmet.naziv == predmet}">    
                <h3>Zakljucena ocena: ${konacnaOcena.broj}</h3>
            </c:if>
        </c:forEach>
    </c:if>
    <table class="table">
        <thead class="thead-light">
          <tr>
            <th scope="col">Ocena</th>
            <th scope="col">Tip ocene</th>
            <th scope="col">Datum</th>
            <th scope="col">Napomena</th>
          </tr>
        </thead>
        <tbody>
            <c:forEach var="ocena" items="${ocene}">
                <c:if test="${ocena.predmet.naziv == predmet}">
                    <tr>
                    <td>${ocena.broj}</td>
                    <td>${ocena.tipOcene.naziv}</td>
                    <td><fmt:formatDate pattern="dd-MM-yyyy" value="${ocena.datum}" /></td>
                    <td>${ocena.napomena}</td>
                     </tr>
                </c:if>
            </c:forEach>
        </tbody>
      </table>      
      </c:forEach>
    </c:if>
</div>
</div>
 
</body>
</html>