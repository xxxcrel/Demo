package beer.cheese.okhttp;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {

    private static final Log logger = LogFactory.getLog(HttpUtils.class);

    private static final String HTTP_SCHEMA = "http://";

    private static final String HTTPS_SCHEMA = "https://";

    private static OkHttpClient client;

    private static ObjectMapper mapper;

    static {
        try {
            client = new OkHttpClient.Builder().build();
            if (logger.isTraceEnabled()) {
                logger.trace("OkHttpClient init successful!");
            }
            mapper = new ObjectMapper();
        } catch (Exception e) {
            logger.error("OkHttpClient init failed!!!");
        }
    }


    public static String get(String host, String path) {
        String url = HTTP_SCHEMA + host + path;
        Request request = new Request.Builder().url(url).build();
        return internalCall(request);
    }

    public static String postForm(String url, Map<String, String> form) {
        FormBody.Builder builder = new FormBody.Builder();
        form.forEach(builder::add);
        Request formRequest = new Request.Builder().url(url).post(builder.build()).build();
        return internalCall(formRequest);
    }

    public static String postJson(String url, Object obj) {
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        return postJson(url, jsonString);
    }

    public static String postJson(String url, String jsonString) {
        Assert.isTrue(url.contains(HTTP_SCHEMA) || url.contains(HTTPS_SCHEMA), "Http schema not specified");
        String result = null;
        try {
            mapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
        RequestBody jsonBody = RequestBody.create(jsonString, MediaType.parse("application/json"));
        Request jsonRequest = new Request.Builder().url(url).post(jsonBody).build();
        return internalCall(jsonRequest);
    }

    public static String postJson(String url, Map<String, String> valueMap) {
        Assert.isTrue(url.contains(HTTP_SCHEMA) || url.contains(HTTPS_SCHEMA), "Http schema not specified");
        String result = null;
        try {
            result = postJson(url, mapper.writeValueAsString(valueMap));
        } catch (JsonProcessingException e) {
            logger.error("Jackson write Map failed!");
        }
        return result;
    }

    private static String internalCall(Request request) {
        Response response;
        String result = null;
        try {
            response = client.newCall(request).execute();
            result = Optional.ofNullable(response.body().string()).get();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return result;
    }
}
