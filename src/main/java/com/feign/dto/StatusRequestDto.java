package com.feign.dto;

public class StatusRequestDto {

    private String privacyStatus;

    public StatusRequestDto(String privacyStatus) {
        this.privacyStatus = privacyStatus;
    }

    public String getPrivacyStatus() {
        return privacyStatus;
    }

    public void setPrivacyStatus(String privacyStatus) {
        this.privacyStatus = privacyStatus;
    }
}
