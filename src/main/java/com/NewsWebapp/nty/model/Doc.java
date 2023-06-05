package com.NewsWebapp.nty.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Doc {
    @JsonProperty("abstract")
    private String summary;
    @JsonProperty("web_url")
    private String webUrl;
    private String source;
    private List<Multimedia> multimedia;
    private Headline headline;
    private Byline byline;
    @JsonProperty("pub_date")
    private String pubDate;
    private String imageUrl;
}
