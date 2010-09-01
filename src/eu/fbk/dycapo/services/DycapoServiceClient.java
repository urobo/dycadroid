/**
 * 
 */
package eu.fbk.dycapo.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.factories.DycapoObjectsFactory;
import eu.fbk.dycapo.maputils.DirectionsResponseParser;
import eu.fbk.dycapo.persistency.DBPerson;

/**
 * @author riccardo
 *	HEAD 		Asks for the response identical to the one that would correspond to a GET request, but without the response body. This is useful for retrieving meta-information written in response headers, without having to transport the entire content.
 *	GET			Requests a representation of the specified resource. Note that GET should not be used for operations that cause side-effects, such as using it for taking actions in web applications. One reason for this is that GET may be used arbitrarily by robots or crawlers, which should not need to consider the side effects that a request should cause. See safe methods below.
 *	POST		Submits data to be processed (e.g., from an HTML form) to the identified resource. The data is included in the body of the request. This may result in the creation of a new resource or the updates of existing resources or both.
 *	PUT			Uploads a representation of the specified resource.
 *	DELETE		Deletes the specified resource.
 */
public abstract class DycapoServiceClient {
	public static final String URL_BASIS = "http://test.dycapo.org/api/";
	
	private static final String TAG = "DycapoServiceClient";
	
	public static final int HEAD = 0;
	public static final int GET = 1;
	public static final int POST = 2;
	public static final int PUT = 3;
	public static final int DELETE = 4;
	
	public static final Object callDycapo(int method,String uri,JSONObject jsonObject,String username,String password) throws DycapoException, JSONException{
		HttpResponse response = doJSONRequest(method,uri,jsonObject,username,password);
		try {
			String stringResp = DirectionsResponseParser.convertStreamToString(response.getEntity().getContent());
			Log.d(TAG, stringResp);
			
			return DycapoObjectsFactory.getDycapoObject(DycapoObjectsFactory.REST, new JSONObject(stringResp), true);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		}
		return null;
	}
	
	private static final HttpResponse doJSONRequest(int method,String uri,JSONObject jsonObject,String username,String password){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		StringBuilder sb =new StringBuilder();
		sb.append(URL_BASIS);
		sb.append(uri);
		sb.append("/");
		URI uriF;
		try {
			uriF = new URI(sb.toString());
			if (username instanceof String || password instanceof String)
				httpclient.getCredentialsProvider().setCredentials(
						new AuthScope(uriF.getHost(), uriF.getPort(),AuthScope.ANY_REALM),
						new UsernamePasswordCredentials(username, password));
			HttpResponse response = null;

			switch(method){
			case HEAD:
				HttpHead requestHead = new HttpHead();
				break;
			case GET:
				HttpGet requestGet = new HttpGet();
				break;
			case POST:
				StringEntity se;
				se = new StringEntity(jsonObject.toString());
				
				HttpPost requestPost = new HttpPost(uriF);
				requestPost.setHeader("Accept", "application/json");
				requestPost.setHeader("Content-type", "application/json");
				requestPost.setEntity(se);
				response = (HttpResponse) httpclient.execute(requestPost);
				break;
			case PUT:
				HttpPut requestPut = new HttpPut();
				break;
			case DELETE:
				HttpDelete requestDelete = new HttpDelete();
				break;
			}
			return response;
		} catch (ClientProtocolException e) {
	
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		} catch (IOException e) {
			
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Log.e(TAG, e1.getMessage());
		}

		return null;
	}
}
