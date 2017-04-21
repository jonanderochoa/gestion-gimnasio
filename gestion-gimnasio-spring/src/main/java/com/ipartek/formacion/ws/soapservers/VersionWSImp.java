/**
 * 
 */
package com.ipartek.formacion.ws.soapservers;
/**
 * @author Jon Ander Ochoa Ruiz
 * 18 de abr. de 2017
 */

import javax.jws.WebService;



@WebService(endpointInterface="com.ipartek.formacion.ws.soapservers.VersionWS", serviceName = "versionService")
public class VersionWSImp implements VersionWS {

	/* (non-Javadoc)
	 * @see com.ipartek.formacion.ws.soapservers.VersionWS#getVersion()
	 */
	@Override
	public String getVersion() {
		return  "Version 1.2";
	}
	
}
