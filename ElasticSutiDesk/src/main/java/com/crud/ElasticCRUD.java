package com.crud;

import java.io.IOException;

import java.util.Collections;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 * 
 * @author dhananjayp
 *
 */
public class ElasticCRUD {
	HttpHost httpHost = new HttpHost("192.168.0.250", 9200, "http");
	RestClient restClient=RestClient .builder(httpHost).build();
	/**
	 * This method has responsible to search record in elsaticsearch
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public String search(String str) throws IOException {
		//Response response = restClient.performRequest("GET", "employee/doc/_search?q="+str, Collections.singletonMap("pretty", "true"));
		//Response response = restClient.performRequest("GET", "/bank/_search?q=*&sort=account_number:asc&pretty", Collections.singletonMap("pretty", "true"));
		//return EntityUtils.toString(response.getEntity());
		
		//select all document

	/*	HttpEntity entity = new NStringEntity(
			      "{ \"query\": { \"match_all\": {}}}",
			      ContentType.APPLICATION_JSON);
			  // alternative: performRequestAsync
			  Response response = restClient.performRequest("POST","/_search", Collections.emptyMap(), entity);
			  return  EntityUtils.toString(response.getEntity());*/
			  
		//show only those document where name is match
		
		/*HttpEntity entity = new NStringEntity(
			      "{ \"query\": { \"match\": {\"name\":\""+str+"\"}}}",
			      ContentType.APPLICATION_JSON);
			  // alternative: performRequestAsync
			  Response response = restClient.performRequest("POST","/_search", Collections.emptyMap(), entity);
			  return  EntityUtils.toString(response.getEntity());
*/
		//search match word from all record
		
	/*	HttpEntity entity = new NStringEntity(
				"{  \"query\": {   \"query_string\": { \"query\": \""+str+"\"  }  }}",
			      ContentType.APPLICATION_JSON);
			  // alternative: performRequestAsync
			  Response response = restClient.performRequest("GET","/_search", Collections.emptyMap(), entity);
			  return  EntityUtils.toString(response.getEntity());*/
		
		// use patrial search on single field ************************************@@@@@@@@@@@@@@@@@@@########
		
		/*HttpEntity entity = new NStringEntity(

				  "{\r\n" + 
							"    \"query\": {\r\n" + 
							"        \"bool\": {\r\n" + 
							"            \"must\": {\r\n" + 
							"				        \"query_string\": {\r\n" + 
							"				            \"query\": \""+str+"\"\r\n" + 
							"				        }\r\n"  + 
							"				    }\r\n" + 
							"        }\r\n" + 
							"    }\r\n" + 
							"}",
			      ContentType.APPLICATION_JSON);
			  // alternative: performRequestAsync
			  Response response = restClient.performRequest("GET","employee/doc/_search", Collections.emptyMap(), entity);
			  String jsonString=  EntityUtils.toString(response.getEntity());
			  parseJSONToJava(jsonString);
			  return jsonString;
		*/
		
	/*	
		HttpEntity entity = new NStringEntity(

				  "{\r\n" + 
							"    \"query\": {\r\n" + 
							"        \"bool\": {\r\n" + 
							"            \"must\": {\r\n" + 
							"				        \"query_string\": {\r\n" + 
							"				            \"query\": \""+str+"\"\r\n" + 
							"				        }\r\n"  + 
							"				    }\r\n" + 
							"				\"filter\": {"+
						    "   					\"term\" : { \"discription\" : \"on\" }"+
						    " 					}"+
							
							"        }\r\n" + 
							"    }\r\n" + 
							"}",
			      ContentType.APPLICATION_JSON);
			  // alternative: performRequestAsync
			  Response response = restClient.performRequest("GET","employee/doc/_search", Collections.emptyMap(), entity);
			  String jsonString=  EntityUtils.toString(response.getEntity());
			  parseJSONToJava(jsonString);
			  return jsonString;*/
		
			  //Using aggregatons  in elasticsearch.
			  
			  HttpEntity entity = new NStringEntity(

					  "{\r\n" + 
					  "    \"query\" : {\r\n" + 
					  "        \"term\" : { \"description\" : \"on\" }\r\n" + 
					  "    }\r\n" + 
					  "}\r\n" + 
					  "",
				      ContentType.APPLICATION_JSON);
				  // alternative: performRequestAsync
				  Response response = restClient.performRequest("GET","employee/doc/_search", Collections.emptyMap(), entity);
				  String jsonString=  EntityUtils.toString(response.getEntity());
				 // parseJSONToJava(jsonString);
				  System.out.println();
				  return jsonString;
			  
			  
			  
			  
			  
		
	}
				
				

	
	/**
	 * This method is responsible to index(inset) record in elasticsearch
	 * @param id
	 * @param name
	 * @param email
	 * @param phone
	 * @param salary
	 * @param description
	 * @throws IOException
	 */
	public void insertIndex(String id, String name, String email, String phone, String salary, String description) throws IOException {
		HttpEntity entity = new NStringEntity(
				"{\n" + 
				" \"id\" : \""+id+"\",\n" +
				" \"name\" : \""+name+"\",\n" +
				" \"email\" : \""+email+"\",\n" +
				" \"phone\" : \""+phone+"\",\n" +
				" \"salary\" : \""+salary+"\",\n" +
				" \"description\" : \""+description+"\"\n" +
				"}",ContentType.APPLICATION_JSON);
		Response indexResponse = restClient.performRequest("PUT", "/employee/doc/"+id, Collections.<String, String>emptyMap(),entity);
		System.out.println(EntityUtils.toString(indexResponse.getEntity()));
	}
	/**
	 * This methos is responsible for deleting the index
	 * @param id
	 * @throws IOException
	 */
	public void deleteDocument(String id) throws IOException {
		restClient.performRequest("DELETE", "/employee/doc/"+id);
	}
	
	public JSONArray parseJSONToJava(String JSONString) {
		/*JSONObject json = new JSONObject(JSONString);
		JSONObject hitsObj = json.getJSONObject("hits");
		JSONArray hitsArr = hitsObj.getJSONArray("hits");
		JSONObject first = hitsArr.getJSONObject(0); // assumes 1 entry in hits array
		JSONObject source = first.getJSONObject("_source");
		
		String id = source.getString("id");
		String name = source.getString("name");
		String email = source.getString("email");
		String salary = source.getString("salary");
		String description = source.getString("description");
		System.out.println(id + "\n" + name + "\n" + email + "\n" +salary+"\n"+description);*/
		
		JSONObject json = new JSONObject(JSONString);
		JSONObject hitsObj = json.getJSONObject("hits");
		JSONArray hitsArr = hitsObj.getJSONArray("hits");
		return  hitsArr;
	}
	
}

