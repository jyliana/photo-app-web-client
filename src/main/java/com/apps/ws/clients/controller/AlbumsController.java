package com.apps.ws.clients.controller;

import com.apps.ws.clients.response.AlbumRest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AlbumsController {

  @GetMapping("/albums")
  public String getAlbums(Model model) {
    var album1 = AlbumRest.builder()
            .albumId("AlbumOneId")
            .albumTitle("Album one")
            .albumUrl("http://localhost:8082/albums/1")
            .build();

    var album2 = AlbumRest.builder()
            .albumId("AlbumTwoId")
            .albumTitle("Album two")
            .albumUrl("http://localhost:8082/albums/2")
            .build();

    model.addAttribute("albums", List.of(album1, album2));

    return "albums";
  }
}
