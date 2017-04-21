/**
 * 
 */
package com.ipartek.formacion.ws.soapclients;

import java.io.IOException;

import com.ipartek.formacion.service.Util;
import com.ipartek.formacion.ws.soapclients.net.webservicex.geoip.GeoIPService;
import com.ipartek.formacion.ws.soapclients.net.webservicex.geoip.GeoIPServiceSoap;

/**
 * @author Jon Ander Ochoa Ruiz
 * 18 de abr. de 2017
 */
public class WebClientSoapIp {

	private String countryName;
	private String countryCode;
	private String codeDetails;
	private String ip;
	/**
	 * Entregandole una ip nos dice el pais de destino y el codigo
	 * @return
	 */
	public WebClientSoapIp(){
		GeoIPService geoIP = new GeoIPService();
		GeoIPServiceSoap soapcliente = geoIP.getGeoIPServiceSoap();
		try {
			ip = Util.getIP();
			countryName = soapcliente.getGeoIP(Util.getIP()).getCountryName();
			countryCode = soapcliente.getGeoIP(Util.getIP()).getCountryCode();
			codeDetails = soapcliente.getGeoIP(Util.getIP()).getReturnCodeDetails();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCodeDetails() {
		return codeDetails;
	}
	public void setCodeDetails(String codeDetails) {
		this.codeDetails = codeDetails;
	}
	
	
}
