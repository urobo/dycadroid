/**
 * 
 */
package eu.fbk.dycapo.services;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.factories.DycapoObjectsFactory;
import eu.fbk.dycapo.maputils.DirectionsResponseParser;

/**
 * @author riccardo
 *	HEAD 		Asks for the response identical to the one that would correspond to a GET request, but without the response body. This is useful for retrieving meta-information written in response headers, without having to transport the entire content.
 *	GET			Requests a representation of the specified resource. Note that GET should not be used for operations that cause side-effects, such as using it for taking actions in web applications. One reason for this is that GET may be used arbitrarily by robots or crawlers, which should not need to consider the side effects that a request should cause. See safe methods below.
 *	POST		Submits data to be processed (e.g., from an HTML form) to the identified resource. The data is included in the body of the request. This may result in the creation of a new resource or the updates of existing resources or both.
 *	PUT			Uploads a representation of the specified resource.
 *	DELETE		Deletes the specified resource.
 */
public abstract class DycapoServiceClient {
	public static final String URL_BASIS = "https://test.dycapo.org";
	
	private static final int HEAD = 0;
	private static final int GET = 1;
	private static final int POST = 2;
	private static final int PUT = 3;
	private static final int DELETE = 4;
	
	public static final Object callDycapo(int method,String uri,JSONObject jsonObject) throws DycapoException, JSONException{
		HttpResponse response = doJSONRequest(method,uri,jsonObject);
		try {
			String stringResp = DirectionsResponseParser.convertStreamToString(response.getEntity().getContent());
			return DycapoObjectsFactory.getDycapoObject(DycapoObjectsFactory.REST, new JSONObject(stringResp), true);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static final HttpResponse doJSONRequest(int method,String uri,JSONObject jsonObject){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpRequestBase request = null;
		switch(method){
		case HEAD:
			request = new HttpHead();
			break;
		case GET:
			request = new HttpGet();
			break;
		case POST:
			request = new HttpPost();
			break;
		case PUT:
			request = new HttpPut();
			break;
		case DELETE:
			request = new HttpDelete();
			break;
		}
		StringEntity se;
		//se = new StringEntity(jsonObjSend.toString());
		try {
			HttpResponse response = (HttpResponse) httpclient.execute(request);
			return response;
		} catch (ClientProtocolException e) {
	
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		return null;
	}
}
