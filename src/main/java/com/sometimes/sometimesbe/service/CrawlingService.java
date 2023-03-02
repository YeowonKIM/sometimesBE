package com.sometimes.sometimesbe.service;

import com.sometimes.sometimesbe.entity.Image;
import com.sometimes.sometimesbe.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CrawlingService {
    private static final String url = "https://www.google.com/search?q=%EC%98%9B%EB%82%A0+%EA%B0%90%EC%84%B1%EC%82%AC%EC%A7%84&sxsrf=AJOqlzWmk53ZZCWS93xfmXsSW4up-FrcpQ:1677330096012&source=lnms&tbm=isch&sa=X&ved=2ahUKEwiwpKzE3bD9AhX5m1YBHY0JAwQQ_AUoAXoECAEQAw&biw=1079&bih=883&dpr=2";
    private final ImageRepository imageRepository;

    @Transactional
    public ResponseEntity<List<String>> createImage() {
        List<String> imageList = createImageUrl();
        Image image;
        for (String url : imageList) {
            imageRepository.save(new Image(url));
        }
        return ResponseEntity.ok()
                .body(imageList);
    }

    private static List<String> createImageUrl() {
        Connection conn = Jsoup.connect(url);
        Document document = null;
        try {
            document = conn.get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements imgElements = document.select("img.rg_i");

        List<String> imageUrls = new ArrayList<>();
        for (Element imgElement : imgElements) {
            String dataSrc = imgElement.attr("data-src");
            if (!dataSrc.isEmpty()) {
                imageUrls.add(dataSrc);
            }
            if (imageUrls.size() >= 40) {
                break;
            }
        }

        return imageUrls;
    }
}
