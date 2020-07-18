<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
      <h1>Stranica za izbor odeljenja</h1>
      <h2>Izaberite jedno od ponudjenih odeljenja</h2>
      <h3>${message}</h3>
      <img src="${pageContext.request.contextPath}/resources/img/grb.jpg" alt="Hero Imgs">
      <div style="display: inline">
         <!--Ova cetri u for petlji sa godinom i brojacem $godina-->
         <c:forEach var="odeljenje" items="${odeljenja}">
             <c:if test = "${Math.round(odeljenje.odeljenjeID / 10) == godina}">
                <a href="<c:url value = "/admin/ucenici/${odeljenje.odeljenjeID}"/>" class="btn-get-started scrollto">${odeljenje.naziv}</a>
             </c:if>
         </c:forEach>
         </div>
    </div>
  </section><!-- #hero -->

</body>
</html>