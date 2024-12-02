package com.wellnest.wellnest.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wellnest.wellnest.Models.Request.Binnacle.InsertBinnacleRequest;
import org.springframework.stereotype.Service;
import com.wellnest.wellnest.Models.Responses.Binnacle.GetBinnacleResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

@Service
public class BinnacleService {

    private final String apiBinnacleUrl = "http://140.84.187.91:3001/api-rest/";


    public GetBinnacleResponse getBinnacleById(long id){
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(apiBinnacleUrl + "getBinnacle.php?idBinnacle=" + id))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                List<GetBinnacleResponse> list =  objectMapper.readValue(response.body(), new TypeReference<List<GetBinnacleResponse>>() {});
                return list.get(0);
            }
        } catch (InterruptedException | URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<GetBinnacleResponse> getAllBinnacles() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(apiBinnacleUrl + "getBinnacle.php"))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                List<GetBinnacleResponse> list =  objectMapper.readValue(response.body(), new TypeReference<List<GetBinnacleResponse>>() {});
                return list;
            }
        } catch (InterruptedException | URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        return Collections.emptyList();
    }

    public boolean insertBinnacle(InsertBinnacleRequest insertBinnacleRequest) {
        try {
           String urlWithParams = apiBinnacleUrl + "createBinnacle.php?idUser=" +
                   insertBinnacleRequest.idUser() + "&content=" + insertBinnacleRequest.content();

           urlWithParams = urlWithParams.replace(" ", "%20");

           HttpClient client = HttpClient.newHttpClient();
           HttpRequest request = HttpRequest.newBuilder()
                   .uri(new URI(urlWithParams))
                   .POST(HttpRequest.BodyPublishers.noBody())
                   .header("Content-Type", "application/x-www-form-urlencoded")
                   .build();

           HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

           return true;
        } catch (InterruptedException | URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }


}
