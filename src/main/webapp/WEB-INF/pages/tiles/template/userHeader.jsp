<%-- 
    Document   : userHeader
    Created on : Jun 22, 2020, 4:20:12 AM
    Author     : Bojan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <header id="header" class="header header-hide">
    <div class="container">

      <div id="logo" class="pull-left">
        <h1><a href="#body" class="scrollto"><span>e</span>Dnevnik</a></h1>
        <!-- Uncomment below if you prefer to use an image logo -->
        <!-- <a href="#body"><img src="img/logo.png" alt="" title="" /></a>-->
      </div>

      <nav id="nav-menu-container">
        <ul class="nav-menu">
            <li class="menu-active"><a href="<c:url value="/homeUser"/>">Pocetna strana</a></li>
          <li><a href="#probaGo">Ocene dece</a></li>
          <li><a href="<c:url value = "/logout"/>" >Odjava</a></li>
        </ul>
      </nav><!-- #nav-menu-container -->
    </div>
  </header><!-- #header -->
