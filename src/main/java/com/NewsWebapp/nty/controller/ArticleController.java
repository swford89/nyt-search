package com.NewsWebapp.nty.controller;

import com.NewsWebapp.nty.model.Form;
import com.NewsWebapp.nty.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("articleList", articleService.getMostPopular());
        return "index";
    }

    @GetMapping("/search")
    public String searchArticle(Model model) {
        Form form = new Form();
        model.addAttribute("form", form);
        return "search";
    }

    @PostMapping("/search")
    public String searchQuery(Model model, @ModelAttribute("form") Form queryForm) {
        Form form = new Form();
        model.addAttribute("form", form);
        model.addAttribute("searchResults", articleService.getSearchResults(queryForm.getQueryString()));
        return "search-results";
    }
}
