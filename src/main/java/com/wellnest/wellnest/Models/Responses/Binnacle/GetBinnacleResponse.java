package com.wellnest.wellnest.Models.Responses.Binnacle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wellnest.wellnest.Models.Binnacle;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GetBinnacleResponse(
        long idBinnacle,
        long idUser,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss") Date date,
        String content
) {

    public GetBinnacleResponse (Binnacle binnnacle) {
        this(binnnacle.getIdBinnacle(), binnnacle.getUser().getIdUser(), binnnacle.getDate(), binnnacle.getContent());
    }
}
