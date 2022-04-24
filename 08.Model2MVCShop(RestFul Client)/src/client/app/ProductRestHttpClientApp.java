package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.Product;



public class ProductRestHttpClientApp {
   
   // main Method
   public static void main(String[] args) throws Exception{
      
      ////////////////////////////////////////////////////////////////////////////////////////////
      // �ּ��� �ϳ��� ó���ذ��� �ǽ�
      ////////////////////////////////////////////////////////////////////////////////////////////
      

//	   ProductRestHttpClientApp.addProductTest_JsonSimple();
      
//	   ProductRestHttpClientApp.addProductTest_Codehaus();   
      
//      System.out.println("\n====================================\n");
//      // 1.1 Http Get ��� Request : JsonSimple lib ���
//     ProductRestHttpClientApp.getProductTest_JsonSimple();
      
//      System.out.println("\n====================================\n");
//      // 1.2 Http Get ��� Request : CodeHaus lib ���
//      ProductRestHttpClientApp.getProductTest_Codehaus();
	   
//	   System.out.println("\n====================================\n");
//	    ProductRestHttpClientApp.updateProductTest_JsonSimple();
	      
//      ProductRestHttpClientApp.updateProductTest_Codehaus();   
     
//	   System.out.println("\n====================================\n");
	    ProductRestHttpClientApp.listProductTest_JsonSimple();
	      
    //ProductRestHttpClientApp.listProductTest_Codehaus();  
   
   }

   public static void addProductTest_JsonSimple() throws Exception{
	      // HttpClient : Http Protocol �� client �߻�ȭ 
	      HttpClient httpClient = new DefaultHttpClient();
	      
	      String url = "http://127.0.0.1:8080/product/json/addProduct";
	      HttpPost httpPost = new HttpPost(url);
	      httpPost.setHeader("Accept", "application/json");
	      httpPost.setHeader("Content-Type", "application/json");
	      
	      JSONObject json = new JSONObject();
	       json.put("prodName", "testName");
	       json.put("prodDetail", "testDetail");
	       json.put("manuDate", "22/04/24");
	       json.put("price", 2000);
	       json.put("fileName", "testImage");
	      HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

	      httpPost.setEntity(httpEntity01);
	      
	      HttpResponse httpResponse = httpClient.execute(httpPost);
	      
	      //==> Response Ȯ��
	      System.out.println(httpResponse);
	      System.out.println();

	      //==> Response �� entity(DATA) Ȯ��
	      HttpEntity httpEntity = httpResponse.getEntity();
	      
	      //==> InputStream ����
	      InputStream is = httpEntity.getContent();
	      BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	      
	      System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
	      String serverData = br.readLine();
	      System.out.println(serverData);
	      
	      //==> �����б�(JSON Value Ȯ��)
	      JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
	      System.out.println(jsonobj);
	      }
   

   //2.2 Http Protocol POST ��� Request : FromData���� 
   //==> JsonSimple + codehaus 3rd party lib ���
   public static void addProductTest_Codehaus() throws Exception{
      
      // HttpClient : Http Protocol �� client �߻�ȭ 
      HttpClient httpClient = new DefaultHttpClient();
      
      String url = "http://127.0.0.1:8080/product/json/addProduct";
      HttpPost httpPost = new HttpPost(url);
      httpPost.setHeader("Accept", "application/json");
      httpPost.setHeader("Content-Type", "application/json");
      
//      //[ ��� 1 : String ���]
//      String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//      HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
   
//      //[ ��� 2 : JSONObject ���]
//      JSONObject json = new JSONObject();
//      json.put("userId", "admin");
//      json.put("password", "1234");
//      HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
      
      //[ ��� 3 : codehaus ���]
      Product product01 =  new Product();
      product01.setProdName("codeName");
      product01.setProdDetail("codeDetail");
      product01.setManuDate("2022/04/24");
      product01.setPrice(2000);
      product01.setFileName("codeImage");

      ObjectMapper objectMapper01 = new ObjectMapper();
      //Object ==> JSON Value �� ��ȯ
      String jsonValue = objectMapper01.writeValueAsString(product01);
      
      System.out.println(jsonValue);
      
      HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
      
      httpPost.setEntity(httpEntity01);
      HttpResponse httpResponse = httpClient.execute(httpPost);
      
      //==> Response Ȯ��
      System.out.println(httpResponse);
      System.out.println();

      //==> Response �� entity(DATA) Ȯ��
      HttpEntity httpEntity = httpResponse.getEntity();
      
      //==> InputStream ����
      InputStream is = httpEntity.getContent();
      BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
      
      //==> �ٸ� ������� serverData ó�� 
      //System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
      //String serverData = br.readLine();
      //System.out.println(serverData);
      
      //==> API Ȯ�� : Stream ��ü�� ���� ���� 
      JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
      System.out.println(jsonobj);
   
      ObjectMapper objectMapper = new ObjectMapper();
       Product product= objectMapper.readValue(jsonobj.toString(), Product.class);
       System.out.println(product);
   }   
   
//================================================================//
   //1.1 Http Protocol GET Request : JsonSimple 3rd party lib ���
   public static void getProductTest_JsonSimple() throws Exception{
      
      // HttpClient : Http Protocol �� client �߻�ȭ 
      HttpClient httpClient = new DefaultHttpClient();
      
      String url=    "http://127.0.0.1:8080/product/json/getProduct/10008";
            
      // HttpGet : Http Protocol �� GET ��� Request
      HttpGet httpGet = new HttpGet(url);
      httpGet.setHeader("Accept", "application/json");
      httpGet.setHeader("Content-Type", "application/json");
      
      // HttpResponse : Http Protocol ���� Message �߻�ȭ
      HttpResponse httpResponse = httpClient.execute(httpGet);
      
      //==> Response Ȯ��
      System.out.println(httpResponse);
      System.out.println();

      //==> Response �� entity(DATA) Ȯ��
      HttpEntity httpEntity = httpResponse.getEntity();
      
      //==> InputStream ����
      InputStream is = httpEntity.getContent();
      BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
      
      System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
      String serverData = br.readLine();
      System.out.println(serverData);
      
      //==> �����б�(JSON Value Ȯ��)
      JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
      System.out.println(jsonobj);
   }
   
   
   //1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
   public static void getProductTest_Codehaus() throws Exception{
      
      // HttpClient : Http Protocol �� client �߻�ȭ 
      HttpClient httpClient = new DefaultHttpClient();
      
      String url=    "http://127.0.0.1:8080/product/json/getProduct/10009";

      // HttpGet : Http Protocol �� GET ��� Request
      HttpGet httpGet = new HttpGet(url);
      httpGet.setHeader("Accept", "application/json");
      httpGet.setHeader("Content-Type", "application/json");
      
      // HttpResponse : Http Protocol ���� Message �߻�ȭ
      HttpResponse httpResponse = httpClient.execute(httpGet);
      
      //==> Response Ȯ��
      System.out.println(httpResponse);
      System.out.println();

      //==> Response �� entity(DATA) Ȯ��
      HttpEntity httpEntity = httpResponse.getEntity();
      
      //==> InputStream ����
      InputStream is = httpEntity.getContent();
      BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
      
      //==> �ٸ� ������� serverData ó�� 
      //System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
      //String serverData = br.readLine();
      //System.out.println(serverData);
      
      //==> API Ȯ�� : Stream ��ü�� ���� ���� 
      JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
      System.out.println(jsonobj);
   
      ObjectMapper objectMapper = new ObjectMapper();
      Product product= objectMapper.readValue(jsonobj.toString(), Product.class);
      System.out.println(product);
   }
//================================================================//  
   
   public static void updateProductTest_JsonSimple() throws Exception{
	      // HttpClient : Http Protocol �� client �߻�ȭ 
	      HttpClient httpClient = new DefaultHttpClient();
	      
	      String url = "http://127.0.0.1:8080/product/json/updateProduct";
	      HttpPost httpPost = new HttpPost(url);
	      httpPost.setHeader("Accept", "application/json");
	      httpPost.setHeader("Content-Type", "application/json");
	      
	      JSONObject json = new JSONObject();
	      json.put("prodName", "updateName");
	      json.put("prodDetail", "updateDetail");
	      json.put("manuDate", "22/04/24");
	      json.put("price", 2000);
	      json.put("fileName", "updateImage");
	      json.put("prodNo", "10008");
	      HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

	      httpPost.setEntity(httpEntity01);
	      
	      HttpResponse httpResponse = httpClient.execute(httpPost);
	      
	      //==> Response Ȯ��
	      System.out.println(httpResponse);
	      System.out.println();

	      //==> Response �� entity(DATA) Ȯ��
	      HttpEntity httpEntity = httpResponse.getEntity();
	      
	      //==> InputStream ����
	      InputStream is = httpEntity.getContent();
	      BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	      
	      System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
	      String serverData = br.readLine();
	      System.out.println(serverData);
	      
	      //==> �����б�(JSON Value Ȯ��)
	      JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
	      System.out.println(jsonobj);
	      }

	public static void updateProductTest_Codehaus() throws Exception{
	   
	   // HttpClient : Http Protocol �� client �߻�ȭ 
	   HttpClient httpClient = new DefaultHttpClient();
	   
	   String url = "http://127.0.0.1:8080/product/json/updateProduct";
	   HttpPost httpPost = new HttpPost(url);
	   httpPost.setHeader("Accept", "application/json");
	   httpPost.setHeader("Content-Type", "application/json");
	   
	//   //[ ��� 1 : String ���]
	//   String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
	//   HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
	//   //[ ��� 2 : JSONObject ���]
	//   JSONObject json = new JSONObject();
	//   json.put("userId", "admin");
	//   json.put("password", "1234");
	//   HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
	   
	   //[ ��� 3 : codehaus ���]
	   Product product01 =  new Product();
	   product01.setProdName("codeUpName");
	   product01.setProdDetail("codeUpDetail");
	   product01.setManuDate("2022/04/24");
	   product01.setPrice(2000);
	   product01.setFileName("codeUpImage");
	   ObjectMapper objectMapper01 = new ObjectMapper();
	   //Object ==> JSON Value �� ��ȯ
	   String jsonValue = objectMapper01.writeValueAsString(product01);

	   System.out.println(jsonValue);
	   
	   HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
	   
	   httpPost.setEntity(httpEntity01);
	   HttpResponse httpResponse = httpClient.execute(httpPost);
	   
	   //==> Response Ȯ��
	   System.out.println(httpResponse);
	   System.out.println();
	
	   //==> Response �� entity(DATA) Ȯ��
	   HttpEntity httpEntity = httpResponse.getEntity();
	   
	   //==> InputStream ����
	   InputStream is = httpEntity.getContent();
	   BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	   
	   //==> �ٸ� ������� serverData ó�� 
	   //System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
	   //String serverData = br.readLine();
	   //System.out.println(serverData);
	   
	   //==> API Ȯ�� : Stream ��ü�� ���� ���� 
	   JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
	   System.out.println(jsonobj);
	
	   ObjectMapper objectMapper = new ObjectMapper();
	   Product product= objectMapper.readValue(jsonobj.toString(), Product.class);
	   System.out.println(product);
	}   

	   public static void listProductTest_JsonSimple() throws Exception{
		      
		      // HttpClient : Http Protocol �� client �߻�ȭ 
		      HttpClient httpClient = new DefaultHttpClient();
		      
		      String url = "http://127.0.0.1:8080/product/json/listProduct";
		      HttpPost httpPost = new HttpPost(url);
		      httpPost.setHeader("Accept", "application/json");
		      httpPost.setHeader("Content-Type", "application/json");
		      
		      //[ ��� 1 : String ���]
//		         String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		         HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		      
		      //[ ��� 2 : JSONObject ���]
		      JSONObject json = new JSONObject();
		      HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		      httpPost.setEntity(httpEntity01);
		      HttpResponse httpResponse = httpClient.execute(httpPost);
		      
		      //==> Response Ȯ��
		      System.out.println(httpResponse);
		      System.out.println();

		      //==> Response �� entity(DATA) Ȯ��
		      HttpEntity httpEntity = httpResponse.getEntity();
		      
		      //==> InputStream ����
		      InputStream is = httpEntity.getContent();
		      BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		      
		      System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		      String serverData = br.readLine();
		      System.out.println(serverData);
		      
		      //==> �����б�(JSON Value Ȯ��)
		      JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		      System.out.println(jsonobj);
		   
		   }
		   
		   //2.2 Http Protocol POST ��� Request : FromData���� 
		   //==> JsonSimple + codehaus 3rd party lib ���
		   public static void listProductTest_Codehaus() throws Exception{
		      
		      // HttpClient : Http Protocol �� client �߻�ȭ 
		      HttpClient httpClient = new DefaultHttpClient();
		      
		      String url = "http://127.0.0.1:8080/product/json/listProduct";
		      HttpPost httpPost = new HttpPost(url);
		      httpPost.setHeader("Accept", "application/json");
		      httpPost.setHeader("Content-Type", "application/json");
		      
//		      //[ ��� 1 : String ���]
//		      String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		      HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		   
//		      //[ ��� 2 : JSONObject ���]
//		      JSONObject json = new JSONObject();
//		      json.put("userId", "admin");
//		      json.put("password", "1234");
//		      HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		      
		      //[ ��� 3 : codehaus ���]
		      Product product01 =  new Product();
		      ObjectMapper objectMapper01 = new ObjectMapper();
		      //Object ==> JSON Value �� ��ȯ
		      String jsonValue = objectMapper01.writeValueAsString(product01);
		      
		      System.out.println(jsonValue);
		      
		      HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		    
		      httpPost.setEntity(httpEntity01);
		      HttpResponse httpResponse = httpClient.execute(httpPost);
		      
		      //==> Response Ȯ��
		      System.out.println(httpResponse);
		      System.out.println();

		      //==> Response �� entity(DATA) Ȯ��
		      HttpEntity httpEntity = httpResponse.getEntity();
		      
		      //==> InputStream ����
		      InputStream is = httpEntity.getContent();
		      BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		      
		      //==> �ٸ� ������� serverData ó�� 
		      //System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		      //String serverData = br.readLine();
		      //System.out.println(serverData);
		      
		      //==> API Ȯ�� : Stream ��ü�� ���� ���� 
		      JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		      System.out.println(jsonobj);
		   
		      ObjectMapper objectMapper = new ObjectMapper();
			   Product product= objectMapper.readValue(jsonobj.toString(), Product.class);
			   System.out.println(product);
		   }   

}