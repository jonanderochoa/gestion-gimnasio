<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<main class="container-fluid">
	<section>
		<c:url var="loginURL" value="/login"/>
		<div class="container">
			<form:form action="${loginURL}" method="post">
			<div class= "form-group input-group input-sm">
				<div class="row">
					<label>Usuario:</label>
					<input type="text" id="userId" name="userId" value="${SPRING_SECURITY_LAST_USERNAME}" required/>
				</div>
			</div>
			<div class= "form-group input-group input-sm">
				<div class="row">
					<label>Contraseña:</label>
					<input type="password" id="password" name="password" required/>
				</div>
			</div>
			<input type="submit" value="Log In" class="btn btn-block btn-success"/>
		</form:form>
		</div>
	</section>	
</main>