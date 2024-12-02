package com.wellnest.wellnest.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wellnest.wellnest.Models.Binnacle;
import com.wellnest.wellnest.Models.Note;
import com.wellnest.wellnest.Models.Request.Binnacle.EditBinnacleRequest;
import com.wellnest.wellnest.Models.Request.Binnacle.InsertBinnacleRequest;
import com.wellnest.wellnest.Models.User;
import com.wellnest.wellnest.Repository.BinnacleRepository;
import com.wellnest.wellnest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
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
import java.util.stream.Collectors;

@Service
public class BinnacleService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private BinnacleRepository binnacleRepository;


    private final String apiBinnacleUrl = "http://140.84.187.91:3001/api-rest/";
    @Autowired
    private UserRepository userRepository;


    private boolean findBinnacleAndCheckOwnership(String token, Long idBinnacle){
        Long userId = jwtService.getUserIdFromToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("User Not Found"));
        Binnacle binnacle = binnacleRepository.findById(idBinnacle)
                .orElseThrow(() -> new IllegalArgumentException("Binnacle not found"));

        if (!(binnacle.getUser().getIdUser() == userId || user.getRole().toString().equals("ADMIN"))) {
            throw new SecurityException("You are not authorized to modify this note");
        }

        return true;
    }




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

    public boolean insertBinnacle(String token, InsertBinnacleRequest insertBinnacleRequest) {
        try {
            long userId = jwtService.getUserIdFromToken(token);
            String urlWithParams = apiBinnacleUrl + "createBinnacle.php?idUser=" +
                   userId + "&content=" + insertBinnacleRequest.content();

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

    public boolean deleteBinnacle(String token, long idBinnacle){
        try {
            String urlWithParams = apiBinnacleUrl + "deleteBinnacle.php?idBinnacle=" + idBinnacle;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(urlWithParams))
                    .DELETE()
                    .header("Content-Type", "application/x-www-form-urlencoded") // Cabecera estándar
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return true;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean modifyBinnacle(String token, long idBinnacle, EditBinnacleRequest editBinnacleRequest) {
        try {
            String urlWithParams = apiBinnacleUrl + "modifyBinnacle.php?idBinnacle=" + idBinnacle + "&content="
                    + editBinnacleRequest.content();
            urlWithParams = urlWithParams.replace(" ", "%20");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(urlWithParams))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .header("Content-Type", "application/x-www-form-urlencoded") // Cabecera estándar
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return true;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public List<GetBinnacleResponse> getUserBinnacles(String token){
        long userId = jwtService.getUserIdFromToken(token);
        User user = userRepository.findById(userId)
                .orElseThrow(()->new IllegalArgumentException("User Not Found"));

        return binnacleRepository.findAllByUserQuery(userId).stream()
                .map(binnacle -> new GetBinnacleResponse(binnacle))
                .collect(Collectors.toList());
    }


}
