<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="../includes/header.jsp" />
<main>
<div class="container">
	<header>
		<h2>Entrenamientos de la fecha: </h2>
	</header>
</div>
<div class="container">
	<div id="cabecera" class="row">
		<div class="col-xs-1 text-capitalize">Número</div>
		<div class="col-xs-1 text-capitalize">Actividad</div>
		<div class="col-xs-1 text-capitalize">Grupo Muscular</div>
		<div class="col-xs-2 text-capitalize">Ejercicio/Máquina</div>
		<div class="col-xs-1 text-capitalize">Descripcion</div>
		<div class="col-xs-1 text-capitalize">Series</div>
		<div class="col-xs-1 text-capitalize">Repeticiones</div>
		<div class="col-xs-1 text-capitalize">Peso</div>
		<div class="col-xs-1 text-capitalize">Tiempo</div>
		<div class="col-xs-2"></div>
	</div>
	
	<c:choose>
		<c:set var="contador" value="${0}"/>	
		<c:when test="${not empty listadoEntrenamientoEjercicios}">
			<c:forEach var="entrenamiento" items="${listadoEntrenamientoEjercicios}">
				<div class="row">
					<div class="col-xs-1 text-capitalize">${contador=contador+1}</div>
					<div class="col-xs-1 text-capitalize">${entrenamiento.actividad}</div>
					<div class="col-xs-1 text-capitalize">${entrenamiento.grupomuscular}</div>
					<div class="col-xs-2 text-capitalize">${entrenamiento.maquina}</div>
					<div class="col-xs-1 text-capitalize">${entrenamiento.descripcion}</div>
					<div class="col-xs-1 text-capitalize">${entrenamiento.series}</div>
					<div class="col-xs-1 text-capitalize">${entrenamiento.repeticiones}</div>
					<div class="col-xs-1 text-capitalize">${entrenamiento.peso}</div>
					<div class="col-xs-1 text-capitalize">${entrenamiento.tiempo}</div>
					<div class="btn-group col-xs-2">
						<a class="btn btn-default"
							href="<c:url value='/entrenamientoEjercicios/${entrenamiento.codigo}'/>">Editar</a>
						<a class="btn btn-danger"
							href="<c:url value='/entrenamientoEjercicios/deleteEntrenamientoEjercicio/${entrenamiento.codigo}'/>">Borrar</a>
					</div>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div class="row">
				<span class="text-danger text-center text-capitalize">no se
					han encontrado Ningun entrenamiento con ejercicios en la BBDD</span>
			</div>
		</c:otherwise>
	</c:choose>

</div>
</main>
<jsp:include page="../includes/footer.jsp" />