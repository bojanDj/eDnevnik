<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profesore dobrososli</title>
</head>
<body>
	


  <div style="display: flex; justify-content: space-evenly; margin-left: 10%;">
<div style="margin-left: 10px; margin-top: 5%;  " >
<c:forEach var="student" items="${students}">
    <div class="card" style="width: 18rem; margin-top: 10px;">
    <img class="card-img-top" src="${student.url}" alt="Card image cap">
    <div class="card-body">
        <a href="<c:url value = "/admin/odeljenja/${razred}/${student.ucenikID}/profilUcenika"/>" style="text-decoration: none;">
        <h5 class="card-title">${student.ime} ${student.prezime}</h5>
      </a>
      <p class="card-text">Adresa i telefon</p>
      <a href="<c:url value = "/admin/odeljenja/${razred}/${student.ucenikID}"/>" class="btn btn-primary">Vidi ocene</a>
    </div>
  </div>
    </c:forEach>
    
</div>

<div id = "probaGo" style=" width: 49%; margin-top: 5%; margin-left: 10%;">
    <h4>${message}</h4>
    <c:if test="${not empty studentID}">
    <h2>${student.ime} ${student.prezime}</h2>
    <h3>${predmetNaziv}</h3>
    <c:if test="${not empty zakljucena}"><h3>Zakljucena ocena: ${zakljucena.broj}</h3></c:if>
    <table class="table">
        <thead class="thead-light">
          <tr>
            <th scope="col">Ocena</th>
            <th scope="col">Tip ocene</th>
            <th scope="col">Napomena</th>
            <c:if test="${empty zakljucena}">
                <th scope="col">Izmeni</th>
                <th scope="col">Obrisi</th>
            </c:if>
          </tr>
        </thead>
        <tbody>
            <c:set var="count" value="0" scope="page" />
            <c:forEach var="ocena" items="${ocene}">
                <c:set var="count" value="${count + ocena.broj}" scope="page"/>
            <tr>
            <td>${ocena.broj}</td>
            <td>${ocena.tipOcene.naziv}</td>
            <td>${ocena.napomena}</td>
            <c:if test="${empty zakljucena}">
                <td><a href="${pageContext.request.contextPath}/admin/odeljenja/${razred}/${studentID}/${ocena.ocenaID}/update" class="btn btn-primary use-address" style="display: table-cell; vertical-align: middle">Izmeni</a></td>
                <td><a href="${pageContext.request.contextPath}/admin/odeljenja/${razred}/${studentID}/${ocena.ocenaID}/delete" class="btn btn-primary use-address" style="display: table-cell; vertical-align: middle">Obrisi</a></td>
            </c:if>
            </tr>
            </c:forEach>
            <c:set var="prosek" value="${count / fn:length(ocene)}" scope="page" />
        </tbody>
      </table>
      <c:if test="${empty zakljucena}">
        <a href="${pageContext.request.contextPath}/admin/odeljenja/${razred}/${studentID}/${predmetID}/add" class="btn btn-primary use-address" style="display: table-cell; vertical-align: middle">Dodaj ocenu</a>
      </c:if>
      <c:if test="${not empty ocene}">

      <c:if test="${empty zakljucena}">
        <a href="" class="btn btn-primary use-address" style="display: table-cell; vertical-align: middle" data-toggle="modal" data-target="#myModal">Zakljuci ocenu</a>
      </c:if>
      <c:if test="${not empty zakljucena}">
        <a href="" class="btn btn-primary use-address" style="display: table-cell; vertical-align: middle" data-toggle="modal" data-target="#myModal">Izmeni zakljucenu ocenu</a>
      </c:if>
  <div id="myModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg" >
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">Zakljucivanje ocene</h4>
        </div>
        <div class="modal-body">
            <h4>Prosek ocena: ${prosek}</h4>
            <h4>Zakljuci ocenu: </h4> 
            
            <form:form action="${pageContext.request.contextPath}/admin/odeljenja/${razred}/${studentID}/${ocene[0].predmet.predmetID}/finalGrade"  method="POST" modelAttribute="konacna">
                <div class="form-group">
                  <form:label path = "broj" for="ocena">Ocena</form:label>
                  <form:input type="text" class="form-control" name = "broj" id ="broj" placeholder="Ocena"  path = "broj"/>
                </div>
                <button type="submit" class="btn btn-primary">Zakljuci</button>
              </form:form>
                
        </div>
        <div style="margin-right: 10px;">
    </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Zatvorite</button>
        </div>
      </div>
    </div>
  </div>
    </c:if>
        </c:if>
       
</div>
</div>

</body>
</html>

