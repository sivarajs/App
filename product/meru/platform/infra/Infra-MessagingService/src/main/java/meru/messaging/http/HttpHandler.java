package meru.messaging.http;

import java.io.ObjectInputStream;

import meru.sys.IOSystem;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpHandler {

    protected Object get(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        ObjectInputStream objectInputStream = null;
        try {
            response = httpClient.execute(httpGet);

            int status = response.getStatusLine()
                                 .getStatusCode();
            if (status != 200) {
                throw new RuntimeException();
            }

            objectInputStream = new ObjectInputStream(response.getEntity()
                                                              .getContent());
            return objectInputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOSystem.close(objectInputStream);
            IOSystem.close(response);
        }
    }

    protected Object post(String url, String data, String contentType) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        CloseableHttpResponse response = null;
        ObjectInputStream objectInputStream = null;
        try {
            httpPost.setEntity(new StringEntity(data,
                                                ContentType.create(contentType)));
            response = httpClient.execute(httpPost);

            int status = response.getStatusLine()
                                 .getStatusCode();
            if (status != 200) {
                throw new RuntimeException();
            }

            if (response.getEntity() != null) {

                HttpEntity httpEntity = response.getEntity();
                if (httpEntity.getContentType() != null) {
                    String cType = httpEntity.getContentType()
                                             .getValue();

                    if (cType != null && cType.contains("application/json")) {
                        return IOSystem.read(httpEntity.getContent());
                    }

                    objectInputStream = new ObjectInputStream(httpEntity.getContent());
                    return objectInputStream.readObject();
                }
                
            }

        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            IOSystem.close(objectInputStream);
            IOSystem.close(response);
        }

        return null;

    }

}
