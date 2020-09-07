<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profesore dobrososli</title>
<link href="${pageContext.request.contextPath}/resources/lib/date-selector-master/src/gm-date-selector.css" rel="stylesheet" />
</head>
<body>
	


<div style="display: flex; justify-content: space-evenly; margin-left: 10%;">
<div style="margin-left: 10px; margin-top: 5%;">
    <div class="card" style="width: 18rem; margin-top: 10px;">
    <img class="card-img-top" src="${student.url}" alt="Card image cap">
    <div class="card-body">
      <h5 class="card-title">${student.ime} ${student.prezime}</h5>
      <p class="card-text">Adresa i telefon</p>
    </div>
  </div>
    
</div>
<div id = "probaGo" style=" width: 49%; margin-top: 5%; margin-left: 10%;">
                <h3 style="color: red;">${message}</h3>
                <h2>Unesite ocenu</h2>
                <form:form action="${pageContext.request.contextPath}/admin/odeljenja/${razred}/${student.ucenikID}/addGrade" method="POST" modelAttribute="ocenaAtr">
                <div class="form-group">
                  <form:label path = "broj" for="ocena">Ocena</form:label>
                  <form:input type="text" class="form-control" id ="broj" placeholder="Ocena"  path = "broj"/>
                </div>
                <div class="form-group">
                   <form:label path = "tipOcene.tipOceneID" for="sel1">Izaberite tip ocene</form:label>
                    <form:select class="form-control" id="sel1" path = "tipOcene.tipOceneID">
                      <form:option value = "1">Usmeni</form:option>
                      <form:option value = "2">Pismeni</form:option>
                      <form:option value = "3">Kontrolni</form:option>
                    </form:select>
                  </div><!--
<!---->               
                    <div class="form-group">
                    <form:label path = "datum" for="datum">Datum</form:label>
                    <form:input type="text"  id = "dateInput" placeholder="Kliknite za unos" class="dateInput" path = "datum"/>
                  </div>
                    <div class="form-group">
                    <form:label path = "napomena" for="napomena">Napomena</form:label>
                    <form:input type="text" class="form-control" id = "napomena" placeholder="Napomena"  path = "napomena"/>
                  </div>
                <button type="submit" class="btn btn-primary">Potvrdite</button>
              </form:form>
</div>
</div>

<script src="${pageContext.request.contextPath}/resources/lib/date-selector-master/src/gm-date-selector.js"></script>

<script type="text/javascript">
    dateSelector( '.dateInput');
</script>  

</body>
</html>

