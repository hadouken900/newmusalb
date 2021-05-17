package com.hadouken900.hmusic.controller;

import com.hadouken900.hmusic.model.Album;
import com.hadouken900.hmusic.service.AlbumService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@AllArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping
    public Collection<Album> getAlbums() {
        return albumService.getAlbums();
    }

}
