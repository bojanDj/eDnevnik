<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <header id="header" class="header header-hide">
    <div class="container">

      <div id="logo" class="pull-left">
        <h1><a href="#body" class="scrollto"><span>e</span>Dnevnik</a></h1>
        <!-- Uncomment below if you prefer to use an image logo -->
        <!-- <a href="#body"><img src="img/logo.png" alt="" title="" /></a>-->
      </div>

      <nav id="nav-menu-container">
          
        <ul class="nav-menu">
          <li class="menu-active"><a href="<c:url value = "/admin/homeAdmin"/>" >Pocetna strana</a></li>
          <li><a href="<c:url value = "/admin/homeAdmin#features"/>" >Pocnite sa radom</a></li>
          <li class="menu-has-children"><a href="">Ocenjivanje</a>
            <ul>
              <li class="menu-has-children"><a href="">Prvi razred</a>
                <ul>
                  <li class="menu-has-children"><a href="${pageContext.request.contextPath}/admin/ucenici/11">I-1</a></li>
                  <li class="menu-has-children"><a href="${pageContext.request.contextPath}/admin/ucenici/12">I-2</a></li>
                  <li class="menu-has-children"><a href="${pageContext.request.contextPath}/admin/ucenici/13">I-3</a></li>
                  <li class="menu-has-children"><a href="${pageContext.request.contextPath}/admin/ucenici/14">I-4</a></li>
                </ul></li>
              <li class="menu-has-children"><a href="">Drugi razred</a>
                <ul>
                  <li class="menu-has-children"><a href="${pageContext.request.contextPath}/admin/ucenici/21">II-1</a></li>
                  <li class="menu-has-children"><a href="${pageContext.request.contextPath}/admin/ucenici/22">II-2</a></li>
                  <li class="menu-has-children"><a href="${pageContext.request.contextPath}/admin/ucenici/23">II-3</a></li>
                  <li class="menu-has-children"><a href="${pageContext.request.contextPath}/admin/ucenici/24">II-4</a></li>
                </ul></li>
              <li class="menu-has-children"><a href="">Treci razred</a>
                <ul>
                  <li class="menu-has-children"><a href="${pageContext.request.contextPath}/admin/ucenici/31">III-1</a></li>
                  <li class="menu-has-children"><a href="${pageContext.request.contextPath}/admin/ucenici/32">III-2</a></li>
                  <li class="menu-has-children"><a href="${pageContext.request.contextPath}/admin/ucenici/32">III-3</a></li>
                  <li class="menu-has-children"><a href="${pageContext.request.contextPath}/admin/ucenici/34">III-4</a></li>
                </ul></li>
              <li class="menu-has-children"><a href="">Cetvrti razred</a>
                <ul>
                  <li class="menu-has-children"><a href="${pageContext.request.contextPath}/admin/ucenici/41">IV-1</a></li>
                  <li class="menu-has-children"><a href="${pageContext.request.contextPath}/admin/ucenici/42">IV-2</a></li>
                  <li class="menu-has-children"><a href="${pageContext.request.contextPath}/admin/ucenici/43">IV-3</a></li>
                  <li class="menu-has-children"><a href="${pageContext.request.contextPath}/admin/ucenici/44">IV-4</a></li>
                </ul></li>
            </ul>
          </li>
          <li><a href="<c:url value = "/logout"/>" >Odjava</a></li>
        </ul>
      </nav><!-- #nav-menu-container -->
    </div>
  </header>