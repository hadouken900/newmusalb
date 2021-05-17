package com.hadouken900.hmusic.service;

import com.hadouken900.hmusic.model.Album;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumService {

    @Cacheable(cacheNames = "albums")
    public Collection<Album> getAlbums() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://newalbumreleases.net/category/cat/").get();
            List<String> imgs = doc.select("p").select("img").eachAttr("src");
            List<String> title = doc.select("h2").select("a").eachText();
            List<String> title1 = title.stream().map(t -> t.substring(0, t.length() - 7)).collect(Collectors.toList());
            List<Album> albums = new ArrayList<>();
            for (int i = 0; i < imgs.size(); i++) {
                albums.add(new Album(imgs.get(i), title1.get(i)));
            }
            return albums;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
