/**
 * 
 */
package eu.fbk.dycapo.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import eu.fbk.dycapo.exceptions.DycapoException;
import eu.fbk.dycapo.util.StreamConverter;

/**
 * @author riccardo HEAD Asks for the response identical to the one that would
 *         correspond to a GET request, but without the response body. This is
 *         useful for retrieving meta-information written in response headers,
 *         without having to transport the entire content. GET Requests a
 *         representation of the specified resource. Note that GET should not be
 *         used for operations that cause side-effects, such as using it for
 *         taking actions in web applications. One reason for this is that GET
 *         may be used arbitrarily by robots or crawlers, which should not need
 *         to consider the side effects that a request should cause. See safe
 *         methods below. POST Submits data to be processed (e.g., from an HTML
 *         form) to the identified resource. The data is included in the body of
 *         the request. This may result in the creation of a new resource or the
 *         updates of existing resources or both. PUT Uploads a representation
 *         of the specified resource. DELETE Deletes the specified resource.
 */
public abstract class DycapoServiceClient {
	public static final String URL_BASIS = "http://test.dycapo.org/api/";
	// public static final String URL_BASIS = "http://10.7.199.101/api/";
	private static final String TAG = "DycapoServiceClient";
	private static UsernamePasswordCredentials USRN_PWD_CRD = null;

	/**
	 * @param code
	 * @return
	 * @throws DycapoException
	 */
	public static final void translateStatusCode(int code, boolean log)
			throws DycapoException {
		boolean eThrow = false;
		String msg = null;
		switch (code) {
		case 200:
			msg = "Ok!";
			break;
		case 201:
			msg = "Resource Created";
			break;
		case 204:
			msg = "Resource Deleted";
			break;
		case 401:
			msg = "Unauthorized! Invalid Credentials";
			Log.d(TAG + " username in use:", USRN_PWD_CRD.getUserName());
			Log.d(TAG + " password in use:", USRN_PWD_CRD.getPassword());
			eThrow = true;
			break;
		case 403:
			msg = "Forbidden";
			eThrow = true;
			break;
		case 404:
			msg = "Resource Not Found";
			eThrow = true;
			break;
		case 415:
			msg = "Unsupported Media Type";
			eThrow = true;
			break;
		}

		if (log == true)
			Log.d(TAG + ".translateStatusCode", "status code: " + code
					+ "message: " + msg);
		if (eThrow == true) {
			Log.e(TAG, msg);
			throw new DycapoException("status code : " + String.valueOf(code));
		}
	}

	public static final String MESSAGE = "message";
	public static final int HEAD = 0;
	public static final int GET = 1;
	public static final int POST = 2;
	public static final int PUT = 3;
	public static final int DELETE = 4;

	public static final JSONArray callDycapoForDataCollections(int method,
			String uri, JSONObject jsonObject, String username, String password)
			throws DycapoException, JSONException {
		Log.d(TAG, (jsonObject instanceof JSONObject) ? jsonObject.toString()
				: "no attachment");
		HttpResponse response = doJSONRequest(method, uri, jsonObject,
				username, password);

		try {

			String stringResp = StreamConverter.convertStreamToString(response
					.getEntity().getContent());
			try {
				translateStatusCode(response.getStatusLine().getStatusCode(),
						true);
			} catch (DycapoException e) {
				throw new DycapoException(e.getMessage() + " cause : "
						+ stringResp);
			}

			return new JSONArray(stringResp);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		} catch (NullPointerException e) {
			e.printStackTrace();

		} finally {
			USRN_PWD_CRD = null;
		}
		return null;
	}

	public static final JSONObject callDycapo(int method, String uri,
			JSONObject jsonObject, String username, String password)
			throws DycapoException, JSONException {
		Log.d(TAG, (jsonObject instanceof JSONObject) ? jsonObject.toString()
				: "no attachment");
		HttpResponse response = doJSONRequest(method, uri, jsonObject,
				username, password);

		try {

			String stringResp = StreamConverter.convertStreamToString(response
					.getEntity().getContent());
			try {
				translateStatusCode(response.getStatusLine().getStatusCode(),
						true);
			} catch (DycapoException e) {
				throw new DycapoException(e.getMessage() + " cause : "
						+ stringResp);
			}

			return new JSONObject(stringResp);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		} catch (NullPointerException e) {
			e.printStackTrace();

		} finally {
			USRN_PWD_CRD = null;
		}
		return null;
	}

	public static final String uriBuilder(String puri) {
		StringBuilder sb = new StringBuilder();
		sb.append(URL_BASIS);
		sb.append(puri);
		sb.append("/");
		return sb.toString();
	}

	private static final HttpResponse doJSONRequest(int method, String uri,
			JSONObject jsonObject, String username, String password) {
		DefaultHttpClient httpclient = new DefaultHttpClient();

		URI uriF;
		try {
			uriF = new URI(uri);
			Log.d(TAG + " resource URI:", uriF.toString());
			if (username instanceof String || password instanceof String) {
				USRN_PWD_CRD = new UsernamePasswordCredentials(username,
						password);
				httpclient.getCredentialsProvider().setCredentials(
						new AuthScope(uriF.getHost(), uriF.getPort(),
								AuthScope.ANY_REALM), USRN_PWD_CRD);
				Log.d(TAG + " username :", username);
				Log.d(TAG + " password :", password);
			}
			HttpResponse response = null;
			StringEntity se;
			String reqType = null;
			switch (method) {
			case HEAD:
				reqType = "HEAD";
				HttpHead headRequest = new HttpHead(uriF);
				response = (HttpResponse) httpclient.execute(headRequest);
				break;
			case GET:
				reqType = "GET";
				HttpGet getRequest = new HttpGet(uriF);
				response = (HttpResponse) httpclient.execute(getRequest);
				break;

			case POST:
				reqType = "POST";
				se = new StringEntity(jsonObject.toString());

				HttpPost requestPost = new HttpPost(uriF);
				requestPost.setHeader("Accept", "application/json");
				requestPost.setHeader("Content-type", "application/json");
				requestPost.setEntity(se);
				response = (HttpResponse) httpclient.execute(requestPost);
				break;

			case PUT:
				reqType = "PUT";
				se = new StringEntity(jsonObject.toString());

				HttpPut requestPut = new HttpPut(uriF);
				requestPut.setHeader("Accept", "application/json");
				requestPut.setHeader("Content-type", "application/json");
				requestPut.setEntity(se);
				response = (HttpResponse) httpclient.execute(requestPut);
				break;
			case DELETE:
				reqType = "DELETE";
				HttpDelete requestDelete = new HttpDelete(uriF);
				response = (HttpResponse) httpclient.execute(requestDelete);
				break;
			default:

			}
			Log.d(TAG, "Request Type : " + reqType);
			return response;
		} catch (ClientProtocolException e) {

			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		} catch (IOException e) {

			e.printStackTrace();
			Log.e(TAG, e.getMessage());
		} catch (URISyntaxException e1) {

			e1.printStackTrace();
			Log.e(TAG, e1.getMessage());
		}

		return null;
	}
}
