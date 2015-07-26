package meru.messaging.http;

import java.io.Serializable;

import meru.messaging.MessageSender;

public class HttpMessageSender extends HttpHandler implements MessageSender {

  public HttpMessageSender() {

  }

  public Object send(String subject,
                     Serializable object) {

    return null;
  }

  public Object send(String subject,
                     String data,
                     String type) {

    return post(subject,
                data,
                type);

    /*
     * CloseableHttpClient httpClient = HttpClients.createDefault(); HttpPost
     * httpPost = new HttpPost(subject.getName());
     * 
     * CloseableHttpResponse response = null; try { httpPost.setEntity(new
     * StringEntity(data, ContentType.create(type))); response =
     * httpClient.execute(httpPost);
     * 
     * int status = response.getStatusLine() .getStatusCode(); if (status !=
     * 200) { throw new RuntimeException(); } } catch (Exception e) { throw new
     * RuntimeException(e); } finally { try { response.close(); } catch
     * (IOException e) { e.printStackTrace(); } }
     */}

}
