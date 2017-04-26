<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<main>
<div class="container-fluid">
	<header>
		<h2>Entrenamientos</h2>
	</header>
</div>
<div class="container-fluid">
	<div id="nuevo" class="row col-xs-12">
		<a class="btn btn-primary text-capitalize"
			href="<c:url value='/entrenamientos/addEntrenamiento'/>">nuevo entrenamiento</a>
	</div>
	<div id="cabecera" class="row">
		<div class="col-xs-5 text-capitalize">número</div>
		<div class="col-xs-5 text-capitalize">fecha</div>
		<div class="col-xs-2"></div>
	</div>
	<c:set var="contador" value="${0}"/>
	<c:choose>
		<c:when test="${not empty listadoEntrenamientos}">
			<c:forEach var="entrenamiento" items="${listadoEntrenamientos}">
				<div class="row">
					<div class="col-xs-5">${contador=contador+1}</div>
					<div class="col-xs-5"><fmt:formatDate value="${entrenamiento.fecha}" dateStyle ="full"/> </div>
					<div class="btn-group col-xs-2">
						<a class="btn btn-success" href="<c:url value='/entrenamientos/getInforme/${entrenamiento.codigo}'/>">Ver</a>
						<a class="btn btn-warning" href="<c:url value='/entrenamientos/id/${entrenamiento.codigo}'/>">Editar</a>
						<a class="btn btn-danger" href="<c:url value='/entrenamientos/deleteEntrenamiento/${entrenamiento.codigo}'/>">Borrar</a>
						
					</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div class="row">
				<span class="text-danger text-center text-capitalize">no se
					han encontrado entrenamientos en la BBDD</span>
			</div>
		</c:otherwise>
	</c:choose>

</div>
</main>