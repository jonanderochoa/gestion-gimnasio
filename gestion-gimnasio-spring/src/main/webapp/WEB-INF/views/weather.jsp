<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="includes/header.jsp"/>

</head>
<body>
	<header><h1>Ejemplo de Cliente con Servicio Meteorologico</h1></header>
	<main>
		<div clss="container">
			<section id="today">
				<header><h2></h2></header>
				<div>
					<img id="icon" src="" alt="" title="">
					<label>Temperatura: </label><p id="temp"></p>
					<label>Presión: </label><p id="pressure"></p>
					<label>Humedad: </label><p id="humedity"></p>
					<label>Temperatura mínima:: </label><p id="temp_min"></p>
					<label>Temperatura máxima: </label><p id="temp_max"></p>
					<label>Visibilidad: </label><p id="visibility"></p>
					<label>Velocidad del viento: </label><p id="speed"></p>
					<label>Temperatura: </label><p id="deg"></p>
				</div>
				<div>
					
					<p>nombre: "${nombre}"</p>
					<p>IP interna: "${direccionInterna}"</p>
					<p>IP externa: "${direccionExterna}"</p>
					
				</div>
			</section>
		</div>
	</main>
<jsp:include page="includes/footer.jsp"/>