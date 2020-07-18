<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profesore dobrososli</title>
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
                <h3>${message}</h3>
                <div class="form-group">
                  <label path = "broj" for="ocena">Roditelj</label>
                  <input type="text" disabled="disabled" class="form-control" id ="broj" placeholder="Ocena" value="${student.roditelj.username}" path = "broj"/>
                </div>
                <form:form action="${pageContext.request.contextPath}/admin/odeljenja/${razred}/${student.ucenikID}/profilUcenika/update" method="POST" modelAttribute="roditeljAtr">
                <h3>---------Izaberite roditelja iz liste postojecih---------</h3>
                <div class="form-group">
                   <form:label path = "roditeljID" for="sel1">Izaberite roditelja</form:label>
                    <form:select class="form-control" id="sel1" path = "roditeljID">
                      <c:forEach var="roditelj" items="${roditelji}">
                        <form:option value = "${roditelj.roditeljID}">${roditelj.username}</form:option>
                      </c:forEach>
                    </form:select>
                  </div>
                <button type="submit" class="btn btn-primary">Potvrdite</button>
              </form:form>
              <h3>----------Ili kreirajte nov nalog za roditelja----------</h3>
              <form:form action="${pageContext.request.contextPath}/admin/odeljenja/${razred}/${student.ucenikID}/profilUcenika/add" method="POST" modelAttribute="roditeljAtr">
                  <form:label path = "email" for="email">Email</form:label>
                  <form:input type="email" class="form-control" id ="email" placeholder="Email" path = "email"/>
                  
                  <form:label path = "username" for="username">Username</form:label>
                  <form:input type="text" class="form-control" id ="username" placeholder="Username" path = "username"/>
                  <button type="submit" class="btn btn-primary">Potvrdite</button>
              </form:form>
</div>
</div>

</body>
</html>

