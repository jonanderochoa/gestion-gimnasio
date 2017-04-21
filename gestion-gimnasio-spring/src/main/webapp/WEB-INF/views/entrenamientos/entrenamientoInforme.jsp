<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../includes/header.jsp" />
<main>
<div class="container">
	<header>
		<h2>Entrenamientos</h2>
	</header>
</div>
<div class="container">
	
	<div id="cabecera" class="row">
		<div class="col-xs-1 text-capitalize">fecha</div>
		<div class="col-xs-1 text-capitalize">actividad</div>
		<div class="col-xs-1 text-capitalize">grupo muscular</div>
		<div class="col-xs-1 text-capitalize">máquina</div>
		<div class="col-xs-2 text-capitalize">descripción</div>
		<div class="col-xs-1 text-capitalize">series</div>
		<div class="col-xs-1 text-capitalize">repeticiones</div>
		<div class="col-xs-1 text-capitalize">peso</div>
		<div class="col-xs-1 text-capitalize">tiempo</div>
		<div class="col-xs-2"></div>
	</div>

	<c:choose>
		<c:when test="${not empty entrenamiento}">
			<c:forEach var="ent" items="${entrenamiento.listaEntrenamientoEjercicio}">
				<div class="row">
					<div class="col-xs-1"><fmt:formatDate value="${entrenamiento.fecha}" dateStyle ="full"/></div>
					<div class="col-xs-1 text-capitalize">${ent.actividad}</div>
					<div class="col-xs-1 text-capitalize">${ent.grupomuscular}</div>
					<div class="col-xs-1 text-capitalize">${ent.maquina}</div>
					<div class="col-xs-2 text-capitalize">${ent.descripcion}</div> 
					<div class="col-xs-1 text-capitalize">${ent.series}</div>
					<div class="col-xs-1 text-capitalize">${ent.repeticiones}</div>
					<div class="col-xs-1 text-capitalize">${ent.peso}</div>
					<div class="col-xs-1 text-capitalize">${ent.tiempo}</div>
					<div class="btn-group col-xs-2">
						<a class="btn btn-warning" href="<c:url value='/entrenamientos/${entrenamiento.codigo}'/>">Editar</a>
						<a class="btn btn-danger" href="<c:url value='/entrenamientos/deleteEntrenamiento/${entrenamiento.codigo}'/>">Borrar</a>
					</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div class="row">
				<span class="text-danger text-center text-capitalize">no se
					ha encontrado entrenamiento en la BBDD</span>
			</div>
		</c:otherwise>
	</c:choose>

</div>
</main>
<jsp:include page="../includes/footer.jsp" />