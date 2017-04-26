<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<main>
	<form:form action="save" method="post" modelAttribute="ejercicio" class="container">
	<c:if test="${!empty ejercicio}">
		<form:hidden path="ejercicioCodigo" />
	</c:if>

	<div class="form-group row">
		<form:label cssClass="control-label hidden-xs col-sm-2" path="actividad">Actividad:</form:label>
		<div class="col-sm-7"><form:input placeholder="Introduzca la actividad" path="actividad"	cssErrorClass="text-danger" cssClass="form-control" /></div>
		<div class="col-sm-3"><form:errors path="actividad" cssClass="text-danger" /></div>
	</div>

	<div class="form-group row">
		<form:label cssClass="control-label hidden-xs col-sm-2" path="grupomuscular">Grupo Muscular:</form:label>
		<div class="col-sm-7"><form:input placeholder="Elija el grupo muscular" path="grupomuscular" cssErrorClass="text-danger" cssClass="form-control" /></div>
		<div class="col-sm-3"><form:errors path="grupomuscular" cssClass="text-danger" /></div>
	</div>
	<div class="form-group row">
		<form:label cssClass="control-label hidden-xs col-sm-2" path="maquina">Ejercicio:</form:label>
		<div class="col-sm-7"><form:input placeholder="Introduzca el ejercicio" path="maquina" cssErrorClass="text-danger" cssClass="form-control" /></div>
		<div class="col-sm-3"><form:errors path="maquina" cssClass="text-danger" /></div>
	</div>
	<div class="form-group row">
		<form:label cssClass="control-label hidden-xs col-sm-2" path="descripcion">Descripción:</form:label>
		<div class="col-sm-7"><form:input placeholder="Introduzca la descripción" path="descripcion" cssErrorClass="text-danger" cssClass="form-control" /></div>
		<div class="col-sm-3"><form:errors path="descripcion" cssClass="text-danger" /></div>
	</div>

	<c:set var="men" value="Crear" />
	<c:if test="${ejercicio.ejercicioCodigo > 0}">
		<c:set var="men" value="Editar" />
	</c:if>
	<input class="btn btn-success" type="submit" value="${men}">
</form:form> 
</main>
