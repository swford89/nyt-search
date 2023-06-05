package com.NewsWebapp.nty.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NytSearchResponse {
    private String status;
    private String copyright;
    private Response response;
}
