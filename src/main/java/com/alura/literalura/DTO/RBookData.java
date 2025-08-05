package com.alura.literalura.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RBookData(
        String title,
        List<RAuthorData> authors,
        List<String> languages,
        @JsonAlias("download_count") Double downloadCount
) {}