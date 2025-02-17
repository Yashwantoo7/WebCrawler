package com.yashwant.webcrawler.controllers;

import com.yashwant.webcrawler.services.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlerController {

    @Autowired
    private CrawlerService crawlerService;

    @GetMapping("/crawl")
    public String startCrawl(@RequestParam String url, @RequestParam(defaultValue = "2") int depth) {
        crawlerService.crawl(url, depth);
        return "Crawling started";
    }
}
