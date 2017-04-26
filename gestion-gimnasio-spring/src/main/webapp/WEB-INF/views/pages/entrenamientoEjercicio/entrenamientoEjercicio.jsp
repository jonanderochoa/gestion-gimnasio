<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<main>
		<!-- FORMULARIO DE SPRING -->
	<form:form action="save" method="post" modelAttribute="entrenamientoEjercicio" class="container">
		<c:if test="${!empty entrenamientoEjercicio}">
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
		<div class="form-group row">
			<form:label cssClass="control-label hidden-xs col-sm-3" path="actividad">Actividad:</form:label>
			<div class="col-sm-6">
				<form:input path="actividad" cssErrorClass="text-danger" cssClass="form-control"/>
			</div>
			<div class="col-sm-3">
				<form:errors path="actividad" cssClass="form-control" cssErrorClass="text-danger"/>
			</div>
		</div>
		<div class="form-group row">
			<form:label cssClass="control-label hidden-xs col-sm-3" path="grupomuscular">Grupo Muscular:</form:label>
			<div class="col-sm-6">
				<form:input path="grupomuscular" cssErrorClass="text-danger" cssClass="form-control"/>
			</div>
			<div class="col-sm-3">
				<form:errors path="grupomuscular" cssClass="form-control" cssErrorClass="text-danger"/>
			</div>
		</div>
		<div class="form-group row">
			<form:label cssClass="control-label hidden-xs col-sm-3" path="maquina">Máquina:</form:label>
			<div class="col-sm-6">
				<form:input path="maquina" cssErrorClass="text-danger" cssClass="form-control"/>
			</div>
			<div class="col-sm-3">
				<form:errors path="maquina" cssClass="form-control" cssErrorClass="text-danger"/>
			</div>
		</div>
		<div class="form-group row">
			<form:label cssClass="control-label hidden-xs col-sm-3" path="fecha">Fecha:</form:label>
			<div class="col-sm-6">
				<form:input path="fecha" cssErrorClass="text-danger" cssClass="form-control"/>
			</div>
			<div class="col-sm-3">
				<form:errors path="fecha" cssClass="form-control" cssErrorClass="text-danger"/>
			</div>
		</div>
		<div class="form-group row">
			<form:label cssClass="control-label hidden-xs col-sm-3" path="descripcion">Descripción:</form:label>
			<div class="col-sm-6">
				<form:input path="descripcion" cssErrorClass="text-danger" cssClass="form-control"/>
			</div>
			<div class="col-sm-3">
				<form:errors path="descripcion" cssClass="form-control" cssErrorClass="text-danger"/>
			</div>
		</div>
		<div class="form-group row">
			<form:label cssClass="control-label hidden-xs col-sm-3" path="series">Series:</form:label>
			<div class="col-sm-6">
				<form:input path="series" cssErrorClass="text-danger" cssClass="form-control"/>
			</div>
			<div class="col-sm-3">
				<form:errors path="series" cssClass="form-control" cssErrorClass="text-danger"/>
			</div>
		</div>
		<div class="form-group row">
			<form:label cssClass="control-label hidden-xs col-sm-3" path="repeticiones">Repeticiones:</form:label>
			<div class="col-sm-6">
				<form:input path="repeticiones" cssErrorClass="text-danger" cssClass="form-control"/>
			</div>
			<div class="col-sm-3">
				<form:errors path="repeticiones" cssClass="form-control" cssErrorClass="text-danger"/>
			</div>
		</div>
		<div class="form-group row">
			<form:label cssClass="control-label hidden-xs col-sm-3" path="peso">Peso:</form:label>
			<div class="col-sm-6">
				<form:input path="peso" cssErrorClass="text-danger" cssClass="form-control"/>
			</div>
			<div class="col-sm-3">
				<form:errors path="peso" cssClass="form-control" cssErrorClass="text-danger"/>
			</div>
		</div>
		<div class="form-group row">
			<form:label cssClass="control-label hidden-xs col-sm-3" path="tiempo">Tiempo:</form:label>
			<div class="col-sm-6">
				<form:input path="tiempo" cssErrorClass="text-danger" cssClass="form-control"/>
			</div>
			<div class="col-sm-3">
				<form:errors path="tiempo" cssClass="form-control" cssErrorClass="text-danger"/>
			</div>
		</div>
		<c:set var="men" value="Crear" />
		<c:if test="${entrenamientoEjercicio.codigo > 0}">
			<c:set var="men" value="Editar" />
		</c:if>
		<input type="submit" value="${men}">
	</form:form>
</main>
