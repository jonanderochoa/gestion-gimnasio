<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>

<jsp:include page="../includes/header.jsp"/>
	<main class="center">
		<div class="container">
			<header><h2>Ejercicios</h2></header>
		</div>
		<div class="container">
			<sec:authorize access="isAuthenticated()">
				<div id="nuevo" class ="row col-xs-12"><a class="btn btn-primary" href="<c:url value="/ejercicios/addEjercicio"/>">Nuevo ejercicio</a></div>
			</sec:authorize>
			<div id="cabecera" class="row">
				<div class="col-xs-2">Actividad</div>
				<div class="col-xs-2">Grupo muscular</div>
				<div class="col-xs-3">Ejercicio</div>
				<div class="col-xs-3">Descripción</div>
				<div class="col-xs-2"></div>
			</div>
			
				<c:choose>
					<c:when test="${not empty listadoEjercicios}">
						<c:forEach var="ejercicio" items="${listadoEjercicios}">
						<div class="row">
							<div class="col-xs-2">${ejercicio.actividad}</div>
							<div class="col-xs-2">${ejercicio.grupomuscular}</div>
							<div class="col-xs-3">${ejercicio.maquina}</div>
							<div class="col-xs-3">${ejercicio.descripcion}</div>
							<div class="btn-group col-xs-2">
							<sec:authorize access="isAuthenticated()">
								
									<a class="btn btn-warning" href="<c:url value='/ejercicios/${ejercicio.ejercicioCodigo}'/>">Editar</a>
									<a class="btn btn-danger" href="<c:url value='/ejercicios/deleteEjercicio/${ejercicio.ejercicioCodigo}'/>">Borrar</a>
								
							</sec:authorize>
							</div>
						</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="row"><p class="col-xs-12">No se han encontrado ejercicios</p></div>
					</c:otherwise>
				</c:choose>
		</div>
	</main>
<jsp:include page="../includes/footer.jsp"/>