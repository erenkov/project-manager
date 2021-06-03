package com.feign;


import com.feign.dto.PlaylistRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "youTube-service", url = "${youtube.base.api}")
public interface ClientYouTube {

    @GetMapping(path = "/channels")
    String getChannelInformationById(@RequestParam String key, @RequestParam String id, @RequestParam String part);

    @PostMapping("/playlists")
    String createPlaylist(@RequestHeader("Authorization") String authorizationHeader,
                          @RequestBody PlaylistRequestDto playlistRequestDto, @RequestParam String part);
}
