package com.NewsWebapp.nty.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MostPopular {
    private String url;
    private Long id;
    @JsonProperty("published_date")
    private String publishedDate;
    private String updated;
    private String byline;
    private String type;
    private String title;
    @JsonProperty("abstract")
    private String summary;
    @JsonProperty("media")
    private List<Media> media;
    private String imageUrl;
}
