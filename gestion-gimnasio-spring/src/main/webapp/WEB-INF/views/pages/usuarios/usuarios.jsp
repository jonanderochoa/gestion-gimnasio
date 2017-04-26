<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<main>
	<div class="container-fluid">
		<header><h2>Usuarios</h2></header>
	</div>
	<div class="container-fluid">
		<div id="nuevo" class ="row col-xs-12"><a class="btn btn-primary" href="<c:url value="/usuarios/addUsuario"/>">Nuevo usuario</a></div>
		<div id="cabecera" class="row">
			<div class="col-xs-2">Nombre</div>
			<div class="col-xs-2">Apellidos</div>
			<div class="col-xs-3">Email</div>
			<div class="col-xs-1">User</div>
			<div class="col-xs-1">Password</div>
			<div class="col-xs-3"></div>
		</div>
		<div class="row">
			<c:choose>
				<c:when test="${not empty listadoUsuarios}">
					<c:forEach var="usuario" items="${listadoUsuarios}">
						<div class="col-xs-2"><a href="usuarios/${usuario.codigo}">${usuario.nombre}</a></div>
						<div class="col-xs-2">${usuario.apellidos}</div>
						<div class="col-xs-3">${usuario.email}</div>
						<div class="col-xs-1">${usuario.user}</div>
						<div class="col-xs-1">${usuario.pass}</div>
						<div class="btn-group col-xs-3">
							<a class="btn btn-success" href="<c:url value='/entrenamientos/${usuario.codigo}'/>">Ver</a>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<a class="btn btn-warning" href="<c:url value='/usuarios/${usuario.codigo}'/>">Editar</a>
								<a class="btn btn-danger" href="<c:url value='/usuarios/deleteUsuario/${usuario.codigo}'/>">Borrar</a>
							</sec:authorize>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div class="col-xs-12">No se han encontrado usuarios</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</main>