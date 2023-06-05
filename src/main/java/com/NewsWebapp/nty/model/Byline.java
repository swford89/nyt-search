package com.NewsWebapp.nty.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Byline {
    private String original;
    private List<Person> person;
    private String organization;
}
