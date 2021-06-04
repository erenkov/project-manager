package com.developing.simbir_product.controller.Dto;

public class WikipediaMathCheckResponseDto {
    boolean success;
    String checked;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
