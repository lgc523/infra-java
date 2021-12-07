package dev.spider.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import entity.HelloReq;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author lgc
 */
public class StandardHttpTest {

    @Test
    public void testStandardHttp() throws Exception {
        String uri = "https://liguangchang.cn";
        URL url = new URL(uri);
        HttpURLConnection uc = (HttpURLConnection) url.openConnection();
        uc.setRequestMethod("GET");
        uc.setConnectTimeout(500);
        uc.setReadTimeout(500);

        uc.connect();
        int responseCode = uc.getResponseCode();
        if (responseCode == 200) {
            InputStream inputStream = uc.getInputStream();
            byte[] bytes = inputStream.readAllBytes();
            System.out.println(new String(bytes, "utf-8"));
            inputStream.close();
        }
        uc.disconnect();
    }

    @Test
    public void testHttpClientIn11() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().
                connectTimeout(Duration.ofMillis(500)).
//                followRedirects(HttpClient.Redirect.NEVER).build();
        followRedirects(HttpClient.Redirect.ALWAYS).build();
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create("http://liguangchang.cn：5678/")).
                timeout(Duration.ofMillis(5000)).
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        System.out.println(response.body());
    }

    @Test
    public void testHttpClientIn11WithAuth() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder().
                connectTimeout(Duration.ofMillis(500)).
                followRedirects(HttpClient.Redirect.ALWAYS).
                authenticator(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("admin", "c3BpZGVyLDUyM19uZXh1cwo=".toCharArray());
                    }
                }).
                build();
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create("http://liguangchang.cn:5678/repository/maven-public/")).
                timeout(Duration.ofMillis(5000)).
                header("Cookie", "31358d9e-65c6-43ee-9e84-aa03292da837").
                header("Cookie", "0.7181014049884815").
                build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        System.out.println(response.body());
    }

    @Test
    public void testHttpClientAsync() throws ExecutionException, InterruptedException {
        String uri = "http://liguangchang.cn:5678/repository/maven-public/";
        HttpClient client = HttpClient.newBuilder().
                connectTimeout(Duration.ofMillis(500)).
                followRedirects(HttpClient.Redirect.ALWAYS).
                authenticator(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("admin", "c3BpZGVyLDUyM19uZXh1cwo=".toCharArray());
                    }
                }).
                build();
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(uri)).
                timeout(Duration.ofMillis(500)).
                build();

        CompletableFuture<HttpResponse<String>> future = client.sendAsync(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        CompletableFuture<String> stringCompletableFuture = future.thenApply(HttpResponse::body);

        System.out.println(stringCompletableFuture.get());
    }

    @Test
    public void testPostJson() throws ExecutionException, InterruptedException, JsonProcessingException {
        String uri = "http://localhost:8567/hello";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        HelloReq helloReq = new HelloReq();
        helloReq.setName("spider");
        helloReq.setAge(24);
        helloReq.setCity("bei\\jing");
        helloReq.setEmail("spider.nns@gmail.com");
        String body = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(helloReq);
        System.out.println(body);

        HttpClient client = HttpClient.newBuilder().
                connectTimeout(Duration.ofMillis(500)).
                build();
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(uri)).
                timeout(Duration.ofMillis(500)).
                header("Content-Type", "application/json").
                POST(HttpRequest.BodyPublishers.ofString(body)).
                build();
        CompletableFuture<String> sf = client.sendAsync(request,
                HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8)).
                thenApply(HttpResponse::body);
        System.out.println(sf.get());

    }
}
