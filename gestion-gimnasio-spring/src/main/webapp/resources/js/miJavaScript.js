/**
 * Fichero que contiene codigo javaScript
 */

/**
 * Javascript que se encarga de la consumir el servicio web del tiempo
 */
const url ="http://api.openweathermap.org/data/2.5/weather?q=Bilbao,sp&appid=842bcf5b443efc38fb8873534b253434";
jQuery(document).ready(function($) {
  $.ajax({
  url : url,
  dataType : "jsonp",
  success : function(parsed_json) {
	  console.log(parsed_json);
	  
	  //Guardamos el valor title del valor 0 del array en el h2
	  $("#today h2").text(parsed_json.name);
	  $("#icon").attr("src", parsed_json.weather.icon);
	  $("#temp").text(parsed_json.main.temp);
	  $("#pressure").text(parsed_json.main.pressure);
	  $("#humedity").text(parsed_json.main.humidity);
	  $("#temp_min").text(parsed_json.main.temp_min);
	  $("#temp_max").text(parsed_json.main.temp_max);
	  $("#visibility").text(parsed_json.visibility);
	  $("#speed").text(parsed_json.wind.deg);
	  $("#deg").text(parsed_json.wind.speed);
	  
  }
  });
});
