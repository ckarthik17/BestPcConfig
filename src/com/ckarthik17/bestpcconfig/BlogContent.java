package com.ckarthik17.bestpcconfig;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class BlogContent {
    private String responseString;

    public BlogContent(String responseString) {
        this.responseString = responseString;
    }

    public Elements parseTable(String tableId) throws JSONException {
        JSONObject json = new JSONObject(responseString);
        String htmlContent = (String) json.get("content");

        Document blogContentDocument = Jsoup.parse(htmlContent);
        Elements intelTable = blogContentDocument.select(tableId);
        return intelTable.get(0).select("tr");
    }
}
