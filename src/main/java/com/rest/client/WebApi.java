package com.rest.client;

import com.rest.model.User;
import com.rest.utils.ResponseUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.stream.Collectors;

public class WebApi {
    private static Logger LOG = Logger.getLogger(WebApi.class);
    private String host;
    private String protocol;

    public WebApi(String protocol, String host) {
        this.host = host;
        this.protocol = protocol;
    }

    private String getBaseUrl(String path) {
        return String.format("%s://%s/%s", protocol, host, path);
    }

    private String buildArgs(Map<String, String> args) {
        return args.entrySet().stream().map(el -> String.format("%s=%s", el.getKey(), el.getValue()))
                .collect(Collectors.joining("&"));
    }

    private String getUrl(String path, Map<String, String> args) {
        return String.format("%s?%s", getBaseUrl(path), buildArgs(args));
    }

    public String sendGetRequest(String path, Map<String, String> args) {
        String url = getUrl(path, args);
        LOG.info(String.format("Creating client by url [%s]...", url));
        WebClient client = WebClient.create(url);
        LOG.info("Setting accept 'application/json'");
        client.accept(MediaType.APPLICATION_JSON);
        LOG.info("Getting results...");
        Response response = client.get();
        return response.readEntity(String.class);
    }

    public String sendPostRequest(String path, Map<String, String> headerArg, User user) {
        String url = getUrl(path, headerArg);
        LOG.info(String.format("Update method by url '%s' started", url));
        LOG.info("Creating client...");
        WebClient client = WebClient.create(url);
        LOG.info("Setting content-type 'application/json'");
        client.type(MediaType.APPLICATION_JSON);
        LOG.info("Setting accept 'application/json'");
        client.accept(MediaType.APPLICATION_JSON);
        LOG.info("Getting results...");
        Response response = client.post(ResponseUtils.getJsonFromUserInfo(user));
        LOG.info(String.format("Results has been received. Response: %s", response.readEntity(String.class)));
        return response.readEntity(String.class);
    }

    public String sendPutRequest(String path, Map<String, String> headerArg, User user) {
        String url = getUrl(path, headerArg);
        LOG.info(String.format("Update method by url '%s' started", url));
        LOG.info("Creating client...");
        WebClient client = WebClient.create(url);
        LOG.info("Setting content-type 'application/json'");
        client.type(MediaType.APPLICATION_JSON);
        LOG.info("Setting accept 'application/json'");
        client.accept(MediaType.APPLICATION_JSON);
        LOG.info("Getting results...");
        Response response = client.put(ResponseUtils.getJsonFromUserInfo(user));
        LOG.info(String.format("Results has been received. Response: %s", response.readEntity(String.class)));
        return response.readEntity(String.class);
    }

    public String sendDeleteRequest(String path, Map<String, String> headerArg) {
        String url = getUrl(path, headerArg);
        LOG.info(String.format("Update method by url '%s' started", url));
        LOG.info("Creating client...");
        WebClient client = WebClient.create(url);
        LOG.info("Setting content-type 'application/json'");
        client.type(MediaType.APPLICATION_JSON);
        LOG.info("Setting accept 'application/json'");
        client.accept(MediaType.APPLICATION_JSON);
        LOG.info("Getting results...");
        Response response = client.delete();
        LOG.info(String.format("Results has been received. Response: %s", response.readEntity(String.class)));
        return response.readEntity(String.class);
    }
}
