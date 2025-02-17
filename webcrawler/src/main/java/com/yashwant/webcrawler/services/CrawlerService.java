package com.yashwant.webcrawler.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class CrawlerService {
    private final Set<String> visitedUrls = new HashSet<>();

    public void crawl(String url, int depth) {
        if (depth<=0 || visitedUrls.contains(url)) {
            return;
        }

        try {
            visitedUrls.add(url);
            Document document = Jsoup.connect(url).get();
            System.out.println("Crawling: " + url);

            //Process the page content (e.g., extract data)
            String title = document.title();
            System.out.println("Title: " + title);

            //Extract links and crawl them recursively
            Elements links = document.select("a[href]");
            for (Element link : links) {
                String nextUrl = link.absUrl("href");
                crawl(nextUrl, depth-1);
            }
        } catch (IOException e) {
            System.err.println("Error crawling " + url + ": " + e.getMessage());
        }
    }
}
