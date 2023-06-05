package com.NewsWebapp.nty.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Headline {
    private String main;
    @JsonProperty("print_headline")
    private String printHeadline;
    private String name;
    private String seo;

}
