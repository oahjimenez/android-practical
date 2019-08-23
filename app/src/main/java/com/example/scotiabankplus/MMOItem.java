package com.example.scotiabankplus;

import lombok.Getter;
import lombok.Setter;

public class MMOItem {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String pubDate;

    @Getter
    @Setter
    private String link;

    @Getter
    @Setter
    private String guid;

    @Getter
    @Setter
    private String author;

    @Getter
    @Setter
    private String thumbnail;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String content;

    @Override
    public String toString() {
        return title;
    }
}
