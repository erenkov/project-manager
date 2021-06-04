package com.developing.simbir_product.controller.Dto;

public class WikipediaTitleResponseDto {

    WikipediaItemDto[] items;
    boolean redirect;

    public WikipediaItemDto[] getItems() {
        return items;
    }

    public void setItems(WikipediaItemDto[] items) {
        this.items = items;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }

}
