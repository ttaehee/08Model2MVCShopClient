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

import com.model2.mvc.service.domain.User;



public class RestHttpClientApp {
   
   // main Method
   public static void main(String[] args) throws Exception{
      
      ////////////////////////////////////////////////////////////////////////////////////////////
      // �ּ��� �ϳ��� ó���ذ��� �ǽ�
      ////////////////////////////////////////////////////////////////////////////////////////////
      

//	   RestHttpClientApp.addUserTest_JsonSimple();
      
//       RestHttpClientApp.addUserTest_Codehaus();   
      
//      System.out.println("\n====================================\n");
//      // 1.1 Http Get ��� Request : JsonSimple lib ���
//     RestHttpClientApp.getUserTest_JsonSimple();
      
//      System.out.println("\n====================================\n");
//      // 1.2 Http Get ��� Request : CodeHaus lib ���
//      RestHttpClientApp.getUserTest_Codehaus();
	   
//	   System.out.println("\n====================================\n");
//	    RestHttpClientApp.updateUserTest_JsonSimple();
	      
     //RestHttpClientApp.updateUserTest_Codehaus();   
      
//      System.out.println("\n====================================\n");
//      // 2.1 Http Post ��� Request : JsonSimple lib ���
//	   RestHttpClientApp.LoginTest_JsonSimple();
      
//      System.out.println("\n====================================\n");
//      // 1.2 Http Post ��� Request : CodeHaus lib ���
//      RestHttpClientApp.LoginTest_Codehaus();      
	   
//	   System.out.println("\n====================================\n");
	    RestHttpClientApp.listUserTest_JsonSimple();
	      
    //RestHttpClientApp.listUserTest_Codehaus();  
   
   }

   public static void addUserTest_JsonSimple() throws Exception{
	      // HttpClient : Http Protocol �� client �߻�ȭ 
	      HttpClient httpClient = new DefaultHttpClient();
	      
	      String url = "http://127.0.0.1:8080/user/json/addUser";
	      HttpPost httpPost = new HttpPost(url);
	      httpPost.setHeader("Accept", "application/json");
	      httpPost.setHeader("Content-Type", "application/json");
	      
	      JSONObject json = new JSONObject();
	      json.put("userId", "testUserId");
	       json.put("password", "testPass");
	       json.put("userName", "testName");
	       json.put("ssn", "1234567891234");
	       json.put("phone1", "010");
	       json.put("phone2", "010");
	       json.put("phone3", "010");
	       json.put("addr", "���ѹα�");
	       json.put("email", "test@test.com");
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
   public static void addUserTest_Codehaus() throws Exception{
      
      // HttpClient : Http Protocol �� client �߻�ȭ 
      HttpClient httpClient = new DefaultHttpClient();
      
      String url = "http://127.0.0.1:8080/user/json/addUser";
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
      User user01 =  new User();
      user01.setUserId("codeUserId");
      user01.setPassword("codePass");
      user01.setUserName("codeName");
      user01.setSsn("1234567891234");
      user01.setPhone("010-1111-1111");
      user01.setAddr("���ѹα�");
      user01.setEmail("code@test.com");
      ObjectMapper objectMapper01 = new ObjectMapper();
      //Object ==> JSON Value �� ��ȯ
      String jsonValue = objectMapper01.writeValueAsString(user01);
      
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
       User user = objectMapper.readValue(jsonobj.toString(), User.class);
       System.out.println(user);
   }   
   
//================================================================//
   //1.1 Http Protocol GET Request : JsonSimple 3rd party lib ���
   public static void getUserTest_JsonSimple() throws Exception{
      
      // HttpClient : Http Protocol �� client �߻�ȭ 
      HttpClient httpClient = new DefaultHttpClient();
      
      String url=    "http://127.0.0.1:8080/user/json/getUser/admin";
            
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
   public static void getUserTest_Codehaus() throws Exception{
      
      // HttpClient : Http Protocol �� client �߻�ȭ 
      HttpClient httpClient = new DefaultHttpClient();
      
      String url=    "http://127.0.0.1:8080/user/json/getUser/admin";

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
       User user = objectMapper.readValue(jsonobj.toString(), User.class);
       System.out.println(user);
   }
//================================================================//  
   
   public static void updateUserTest_JsonSimple() throws Exception{
	      // HttpClient : Http Protocol �� client �߻�ȭ 
	      HttpClient httpClient = new DefaultHttpClient();
	      
	      String url = "http://127.0.0.1:8080/user/json/updateUser";
	      HttpPost httpPost = new HttpPost(url);
	      httpPost.setHeader("Accept", "application/json");
	      httpPost.setHeader("Content-Type", "application/json");
	      
	      JSONObject json = new JSONObject();
	      json.put("userId", "testUserId");
	      json.put("userName", "testUpName");
	      json.put("phone", "000-000-000");
	      //json.put("phone2", "000");
	     // json.put("phone3", "000");
	      json.put("addr", "�̱�");
	      json.put("email", "testUp@test.com");
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

	public static void updateUserTest_Codehaus() throws Exception{
	   
	   // HttpClient : Http Protocol �� client �߻�ȭ 
	   HttpClient httpClient = new DefaultHttpClient();
	   
	   String url = "http://127.0.0.1:8080/user/json/updateUser";
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
	   User user01 =  new User();
	   user01.setUserId("codeUserId");
	   user01.setUserName("codeUpName");
	   user01.setPhone("010-2222-2222");
	   user01.setAddr("�̱�");
	   user01.setEmail("codeUp@test.com");
	   ObjectMapper objectMapper01 = new ObjectMapper();
	   //Object ==> JSON Value �� ��ȯ
	   String jsonValue = objectMapper01.writeValueAsString(user01);
	   
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
	    User user = objectMapper.readValue(jsonobj.toString(), User.class);
	    System.out.println(user);
	}   

   
//================================================================//
   //2.1 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
   public static void LoginTest_JsonSimple() throws Exception{
      
      // HttpClient : Http Protocol �� client �߻�ȭ 
      HttpClient httpClient = new DefaultHttpClient();
      
      String url = "http://127.0.0.1:8080/user/json/login";
      HttpPost httpPost = new HttpPost(url);
      httpPost.setHeader("Accept", "application/json");
      httpPost.setHeader("Content-Type", "application/json");
      
      //[ ��� 1 : String ���]
//         String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//         HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
      
      //[ ��� 2 : JSONObject ���]
      JSONObject json = new JSONObject();
      json.put("userId", "admin");
      json.put("password", "1234");
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
   public static void LoginTest_Codehaus() throws Exception{
      
      // HttpClient : Http Protocol �� client �߻�ȭ 
      HttpClient httpClient = new DefaultHttpClient();
      
      String url = "http://127.0.0.1:8080/user/json/login";
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
      User user01 =  new User();
      user01.setUserId("admin");
      user01.setPassword("1234");
      ObjectMapper objectMapper01 = new ObjectMapper();
      //Object ==> JSON Value �� ��ȯ
      String jsonValue = objectMapper01.writeValueAsString(user01);
      
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
       User user = objectMapper.readValue(jsonobj.toString(), User.class);
       System.out.println(user);
   }   	   
	   public static void listUserTest_JsonSimple() throws Exception{
		      
		      // HttpClient : Http Protocol �� client �߻�ȭ 
		      HttpClient httpClient = new DefaultHttpClient();
		      
		      String url = "http://127.0.0.1:8080/user/json/listUser";
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
		   public static void listUserTest_Codehaus() throws Exception{
		      
		      // HttpClient : Http Protocol �� client �߻�ȭ 
		      HttpClient httpClient = new DefaultHttpClient();
		      
		      String url = "http://127.0.0.1:8080/user/json/listUser";
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
		      User user01 =  new User();
		      
		      ObjectMapper objectMapper01 = new ObjectMapper();
		      //Object ==> JSON Value �� ��ȯ
		      String jsonValue = objectMapper01.writeValueAsString(user01);
		      
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
		       User user = objectMapper.readValue(jsonobj.toString(), User.class);
		       System.out.println(user);
		   }   

}