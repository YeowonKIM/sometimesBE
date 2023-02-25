package com.sometimes.sometimesbe.service;

import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CrawlingService {
    private static final String url = "https://www.google.co.kr/search?q=%EC%9E%90%EC%97%B0%EC%9D%B4%EB%AF%B8%EC%A7%80&tbm=isch&chips=q:%EC%9E%90%EC%97%B0+%EC%9D%B4%EB%AF%B8%EC%A7%80,online_chips:%EC%9E%90%EC%97%B0%EA%B2%BD%EA%B4%80:zo_35zrTL54%3D&hl=en&sa=X&ved=2ahUKEwiTmfnYp7D9AhX_TPUHHWFIB_MQ4lYoA3oECAEQKw&biw=1905&bih=895";

    public List<String> process() {
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
            if (imageUrls.size() >= 20) {
                break;
            }
        }

        return imageUrls;
    }
}
