package com.pnoker.camera.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pnoker.camera.bean.Position;
import com.pnoker.camera.utils.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pnoker
 */
@Service
public class PositionServiceImpl {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<Position> getPositions() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.getClass().getResource("/").getFile() + "/data.json"))) {
            reader.lines().forEach(s -> sb.append(s));
            return JSON.parseArray(sb.toString(), Position.class);
        } catch (Exception e) {
            logger.error("", e);
        }
        return new ArrayList<>();
    }


    public List<Position> getPositionFromWeb() {
        String url = "https://www.jinjing365.com/index.asp";

        String content = HttpUtil.get(url);

        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByTag("script");
            elements.stream().forEach(element -> {
                System.out.println(element.data());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        int start = content.indexOf("var LabelsData = [");
        int end = content.indexOf("var title = '详细';");

        return JSON.parseArray(content.substring(start, end).trim(), Position.class);
    }
}
