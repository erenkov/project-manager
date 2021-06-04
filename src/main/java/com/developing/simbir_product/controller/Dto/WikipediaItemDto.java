package com.developing.simbir_product.controller.Dto;


import java.util.UUID;

public class WikipediaItemDto {
    String title;
    int pageId;
    int rev;
    UUID tid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getRev() {
        return rev;
    }

    public void setRev(int rev) {
        this.rev = rev;
    }

    public UUID getTid() {
        return tid;
    }

    public void setTid(UUID tid) {
        this.tid = tid;
    }
}
