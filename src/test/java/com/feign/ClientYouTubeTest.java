package com.feign;

import com.feign.dto.PlaylistRequestDto;
import com.feign.dto.SnippetRequestDto;
import com.feign.dto.StatusRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class ClientYouTubeTest {

    @Value("${header.authorization}")
    String authorizationHeader;
    @Value("${api.key}")
    String key;
    @Autowired
    private ClientYouTube clientYouTube;

    @Test
    void getChannelInformationById() {
        String part = "snippet%2CcontentDetails%2Cstatistics";
        assertTrue(clientYouTube.getChannelInformationById(key, "UC_x5XG1OV2P6uZZ5FSM9Ttw", part).contains("Google Developers"));
        assertTrue(clientYouTube.getChannelInformationById(key, "UCSJ4gkVC6NrvII8umztf0Ow", part).contains("Lofi"));
        assertTrue(clientYouTube.getChannelInformationById(key, "UCHnyfMqiRRG1u-2MsSQLbXA", part).contains("Veritasium"));
    }

    @Test
    void createPlaylist() {
        String part = "snippet%2Cstatus";
        StatusRequestDto status = new StatusRequestDto("private");
        ArrayList<String> tags = new ArrayList<>(List.of("Sample playlist", "API call"));
        SnippetRequestDto snippet = new SnippetRequestDto("Test title", "Test description", tags, "en");
        PlaylistRequestDto playlistRequestDto = new PlaylistRequestDto(snippet, status);
        assertTrue(clientYouTube.createPlaylist(authorizationHeader, playlistRequestDto, part).contains("Sample playlist"));
    }
}
