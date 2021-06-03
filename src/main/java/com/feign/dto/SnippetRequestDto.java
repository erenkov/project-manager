package com.feign.dto;

import java.util.List;


public class SnippetRequestDto {

    private String title;

    private String description;

    private List<String> tags;

    private String defaultLanguage;

    public SnippetRequestDto(String title, String description, List<String> tags, String defaultLanguage) {
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.defaultLanguage = defaultLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }
}
