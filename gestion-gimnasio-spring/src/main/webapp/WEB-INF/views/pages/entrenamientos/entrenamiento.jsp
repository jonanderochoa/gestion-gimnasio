<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<main>
		<!-- FORMULARIO DE SPRING -->
	<form:form action="save" method="post" modelAttribute="entrenamiento" class="form-horizontal container">
		<c:if test="${!empty entrenamiento}">
			<form:hidden path="codigo"/>
		</c:if>
		<div class="form-group row">
			<form:label cssClass="control-label hidden-xs col-sm-3" path="fecha">Fecha:</form:label>
			<div class="col-sm-6">
				<form:input path="fecha" cssErrorClass="text-danger" cssClass="form-control" placeholder="dd/MM/yyyy" pattern="[0-9]{2}/[0-9]{2}/[0-9]{4}"/>
			</div>
			<div class="col-sm-3">
				<form:errors path="fecha" cssClass="form-control" cssErrorClass="text-danger"/>
			</div>
		</div>
		<c:set var="men" value="Crear" />
		<c:if test="${entrenamiento.codigo > 0}">
			<c:set var="men" value="Editar" />
		</c:if>
		<input class="btn btn-success" type="submit" value="${men}">
	</form:form>
</main>