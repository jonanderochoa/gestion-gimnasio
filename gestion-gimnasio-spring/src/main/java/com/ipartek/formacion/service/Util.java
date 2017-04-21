package com.ipartek.formacion.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que contiene utilidades para toda la aplicacion
 * @author Jon Ander Ochoa Ruiz
 * 15 de abr. de 2017
 */
public class Util {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

	/**
	 * Valida que el email sea correcto
	 * @param telefono Se le entrega un email de tipo String
	 * @return Devuelve true si es correcto
	 */
	public static boolean validarEmail(final String email) {
			boolean valido = false;
			if (email.contains("@") && email.length() < 150) {
				valido = true;
			}
			return valido;
	}
	
	/**
	 * Cambia la fecha de String a Date
	 * @param fecha Recoge una fecha formateada dd/MM/yyyy 
	 * @return Devuelve una Date
	 * @throws ParseException
	 */
	public static Date parseLatinDate(String fecha) throws ParseException{
		LOGGER.info("parseLatinDate");
		String pattern = "dd/MM/yyyy HH:mm";
		SimpleDateFormat fechaFormato = new SimpleDateFormat(pattern);
		return fechaFormato.parse(fecha);
	}
	
	/**
	 * Cambia la fecha de Date a String
	 * @param fechaSinFormato Introduce una fecha Date
	 * @return Devuelve una fecha formateada dd/MM/yyyy
	 */
	public static String formatoFecha(Date fechaSinFormato){
		LOGGER.info("parseformatoFecha");
		String fecha ="";
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(fechaSinFormato);
		fecha = gc.get(GregorianCalendar.DAY_OF_MONTH)+"/"+(gc.get(GregorianCalendar.MONTH)+1)+"/"+gc.get(GregorianCalendar.YEAR);
		return fecha;
	}
	
	/**
	 * Metodo que se encarga de mostrar la ipExterna
	 * @return Devuelve un String con la ip externa
	 * @throws IOException
	 */
	public static String getIP() throws IOException {
		LOGGER.info("Util.getIp");
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));        
        String ip = "";
        try {
			ip = in.readLine();
			LOGGER.info(ip);
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
        return ip;
    }
}
