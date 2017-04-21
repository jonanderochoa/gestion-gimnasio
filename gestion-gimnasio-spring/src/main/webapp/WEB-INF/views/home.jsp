<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<jsp:include page="includes/header.jsp"/>
	<section class= container-fluid>	
		<div class="row">
			<article class="col-xs-12 col-sm-6 text-justify">
				<h2><spring:message code="texto.bienvenido" text="Bienvenido"/></h2>
				<p>
					Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque imperdiet, tellus sit amet euismod 
					aliquam, turpis massa ullamcorper libero, sed suscipit augue elit sit amet lacus. Curabitur mattis quam 
					eu mattis imperdiet. Nulla sed fermentum neque, eu suscipit felis. Pellentesque eget justo lectus. Cras 
					in blandit lorem. Quisque pellentesque tempor mauris, at bibendum purus malesuada in. Duis fermentum blandit 
					egestas. Morbi non pretium neque. Nam blandit, ipsum aliquet porttitor commodo, sapien est dignissim nisi, 
					nec varius dolor tortor nec libero. Curabitur ut nisi ut magna vulputate maximus a faucibus ligula. Aenean id 
					nisl in purus hendrerit euismod non at mauris. Nullam consequat rutrum augue, iaculis scelerisque nibh rutrum eu. 
					Nunc sit amet ante ligula. Suspendisse sed magna est. Quisque porttitor, tellus eget molestie facilisis, mi felis
					 dapibus tortor, vulputate porta nisl odio vel tellus.
				</p>
				<p>	 
					Curabitur id magna sit amet ex lobortis porttitor. Etiam scelerisque sollicitudin ante et suscipit. Nullam aliquet
					 mollis odio, at finibus nisi fringilla sit amet. Integer accumsan condimentum justo, ut auctor eros vulputate at.
					  Pellentesque pretium blandit nulla. Vivamus non mauris quis augue tincidunt interdum accumsan et dui. Nunc eget sem
					   et nisl euismod ornare non quis metus. Nullam vel lorem eu neque cursus tincidunt. Aliquam ex dui, efficitur non 
					   magna et, laoreet sagittis lorem. Nulla mollis ligula nibh. Mauris ut tincidunt velit.
				</p>
			</article>
			<aside class="col-xs-12 col-sm-6">
				<div id="myCarousel" class="carousel slide" data-ride="carousel">
					  <!-- Indicators -->
					  <ol class="carousel-indicators">
					    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					    <li data-target="#myCarousel" data-slide-to="1"></li>
					    <li data-target="#myCarousel" data-slide-to="2"></li>
					    <li data-target="#myCarousel" data-slide-to="3"></li>
					  </ol>
					
					  <!-- Wrapper for slides -->
					  <div class="carousel-inner" role="listbox">
					    <div class="item active">
					      <img src="resources/img/gim.jpg" alt="Imagen de la sala 1">
					    </div>
					
					    <div class="item">
					      <img src="resources/img/Gimnasio.jpg" alt="Imagen de la sala 2">
					    </div>
					
					    <div class="item">
					      <img src="resources/img/gimnasio-bsh.jpg" alt="Imagen de la sala 3">
					    </div>
					
					    <div class="item">
					      <img src="resources/img/gim4.jpg" alt="Imagen de la sala 4">
					    </div>
					  </div>
					
					  <!-- Left and right controls -->
					  <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
					    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					    <span class="sr-only">Previous</span>
					  </a>
					  <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
					    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					    <span class="sr-only">Next</span>
					  </a>
					</div>
			</aside>	
		</div>
		<div class="row">
		
		</div>	   
	</section>
<jsp:include page="includes/footer.jsp"/>
