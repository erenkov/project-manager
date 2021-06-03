package com.feign.dto;

public class PlaylistRequestDto {

    private SnippetRequestDto snippet;

    private StatusRequestDto status;

    public PlaylistRequestDto(SnippetRequestDto snippet, StatusRequestDto status) {
        this.snippet = snippet;
        this.status = status;
    }

    public SnippetRequestDto getSnippet() {
        return snippet;
    }

    public void setSnippet(SnippetRequestDto snippet) {
        this.snippet = snippet;
    }

    public StatusRequestDto getStatus() {
        return status;
    }

    public void setStatus(StatusRequestDto status) {
        this.status = status;
    }
}
