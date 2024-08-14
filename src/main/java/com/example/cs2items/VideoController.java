package com.example.cs2items;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/yaku1t")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/")
    public String home() {
        return "index"; // 根据您的实际路径调整
    }

    @GetMapping("/videos")
    public ResponseEntity<List<Video>> search(@RequestParam String query) {
        List<Video> results = videoService.searchVideos(query);
        return ResponseEntity.ok(results); // 返回 JSON 响应
    }
}
