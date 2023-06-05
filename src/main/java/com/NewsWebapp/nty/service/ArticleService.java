package com.NewsWebapp.nty.service;

import com.NewsWebapp.nty.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Value("${api_key}")
    private String apiKey;
    @Value("${mostPopularUrl}")
    private String mostPopularUrl;

    @Value("${articleSearchUrl}")
    private String articleSearchUrl;

    @Autowired
    RestTemplate restTemplate;

    public List<MostPopular> getMostPopular() {
        String fullUrl = mostPopularUrl + "api-key=" + apiKey;
        NytResponse response = restTemplate.getForObject(fullUrl, NytResponse.class);
        List<MostPopular> results = new ArrayList<>();

        if (response != null && response.getStatus().equals("OK")) {

            // remove articles without media
            System.out.println("Size before filter: " + response.getResults().size());
            response.getResults().removeIf(article -> article.getMedia().isEmpty());
            System.out.println("Size after filter: " + response.getResults().size());

            // set MostPopular imageUrl
            for (MostPopular popularArticle: response.getResults()) {
                List<Media> articleMediaList = popularArticle.getMedia();
                for (Media mediaData: articleMediaList) {
                    List<Thumbnail> thumbnailList = mediaData.getMediaMetadata();
                    if (thumbnailList.size() > 2) {
                        String mediumQualityImage = thumbnailList.get(1).getUrl();
                        popularArticle.setImageUrl(mediumQualityImage);
                    } else {
                        String lowQualityImage = thumbnailList.get(0).getUrl();
                        popularArticle.setImageUrl(lowQualityImage);
                    }
                    break;
                }
            }
            return response.getResults();
        } else {
            return results;
        }
    };

    public List<Doc> getSearchResults(String keyword) {
        System.out.println("Article Search Keyword: " + keyword);
        String fullSearchUrl = articleSearchUrl + "q=" + keyword + "&api-key=" + apiKey;
        ResponseEntity<NytSearchResponse> responseEntity = restTemplate.getForEntity(fullSearchUrl, NytSearchResponse.class);
        List<Doc> results = new ArrayList<>();

        System.out.println(fullSearchUrl);

        if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) {
            responseEntity.getBody().getResponse().getDocs().removeIf(someDoc -> someDoc.getMultimedia().isEmpty());
            for (Doc article : responseEntity.getBody().getResponse().getDocs()) {
                article.setImageUrl("http://static01.nyt.com/"+ article.getMultimedia().get(0).getUrl());
                String[] pubDateList = article.getPubDate().split("T");
                String yearMonthDay = pubDateList[0];
                article.setPubDate(yearMonthDay);
            }
            return Objects.requireNonNull(responseEntity.getBody()).getResponse().getDocs();
        } else {
            return results;
        }
    }
}
