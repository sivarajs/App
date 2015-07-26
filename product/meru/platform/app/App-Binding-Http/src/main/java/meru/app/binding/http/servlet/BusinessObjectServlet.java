package meru.app.binding.http.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meru.app.binding.http.HttpAppRequest;
import meru.app.binding.http.HttpEntityRequest;

@MultipartConfig
public class BusinessObjectServlet extends AppServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected HttpAppRequest getAppRequestInstance(HttpServletRequest request,
                                                   HttpServletResponse response) throws UnsupportedEncodingException {

        HttpEntityRequest appRequest = new HttpEntityRequest(request,
                                                             response);
        return appRequest;
    }

    @Override
    protected void delete(HttpAppRequest appRequest) throws IOException {
        mBindingComponent.delete((HttpEntityRequest) appRequest);
    }

    @Override
    protected void get(HttpAppRequest appRequest) throws ServletException, IOException {
        mBindingComponent.get((HttpEntityRequest) appRequest);
    }

    @Override
    protected void post(HttpAppRequest appRequest) throws ServletException, IOException {
        mBindingComponent.post((HttpEntityRequest) appRequest);
    }

   
}
