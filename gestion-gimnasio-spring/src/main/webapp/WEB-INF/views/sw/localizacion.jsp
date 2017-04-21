<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="../includes/header.jsp"/>

</head>
<body>
	<header><h1>LOCALIZACIÓN POR IP</h1></header>
	<main>
		<div clss="container">
			<section id="today">
				<header><h2>IP: "${ip}"</h2></header>
				
				<div>
					<p>Nombre: "${nombre}"</p>
					<p>Pais: "${pais}"</p>
					<p>Detalles: "${detalles}"</p>
				</div>
			</section>
		</div>
	</main>
<jsp:include page="../includes/footer.jsp"/>