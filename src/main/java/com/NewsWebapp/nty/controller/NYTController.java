package com.NewsWebapp.nty.controller;

import com.NewsWebapp.nty.model.MostPopular;
import com.NewsWebapp.nty.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NYTController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/most-popular")
    public List<MostPopular> getMostPopular() {
        return articleService.getMostPopular();
    }
}
