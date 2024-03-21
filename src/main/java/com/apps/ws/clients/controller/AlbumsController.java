package com.apps.ws.clients.controller;

import com.apps.ws.clients.response.AlbumRest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
public class AlbumsController {

  private OAuth2AuthorizedClientService authorizedClientService;
  private WebClient webClient;

  @GetMapping("/albums")
  public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal) {
    var url = "http://localhost:8082/albums";
    var albums = webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<AlbumRest>>() {
            }).block();

    model.addAttribute("albums", albums);

    return "albums";
  }

//  RestTemplate
//  @GetMapping("/albums")
//  public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal) {
//    var authentication = SecurityContextHolder.getContext().getAuthentication();
//    var oauthToken = (OAuth2AuthenticationToken) authentication;
//    var oauthClient = authorizedClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
//    var jwtAccessToken = oauthClient.getAccessToken().getTokenValue();
//    log.info("jwt access token = " + jwtAccessToken);
//
//    log.info("Principal = " + principal);
//    var idToken = principal.getIdToken();
//    var token = idToken.getTokenValue();
//    log.info("id token value = " + token);
//
//    var url = "http://localhost:8082/albums";
//    var headers = new HttpHeaders();
//    headers.add("Authorization", "Bearer " + jwtAccessToken);
//    var entity = new HttpEntity<>(headers);
//    var responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<AlbumRest>>() {
//    });
//    var albums = responseEntity.getBody();
//
//    model.addAttribute("albums", albums);
//
//    return "albums";
//  }
}
