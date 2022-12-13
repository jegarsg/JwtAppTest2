package com.jegar.springjwt.controllers;

import com.jegar.springjwt.payload.response.JobResponse;
import com.jegar.springjwt.payload.response.JobResponseById;
import com.jegar.springjwt.payload.response.JwtResponse;
import com.jegar.springjwt.payload.response.Position;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Content.";
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }



  @GetMapping("/getPositions")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  private ResponseEntity<JobResponse> getJob()
  {
    int statusCode=0;
    String msg= "";
    final String uri = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";
    List<Position> positions=null;
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<List<Position>> responseEntity = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Position>>() {
            });
    statusCode=responseEntity.getStatusCodeValue();
    if (responseEntity.getStatusCodeValue()==200)
    {
      msg= "Success";
      positions = responseEntity.getBody();
    }
    else
    {
      msg= "response failed";
    }
    JobResponse a = new JobResponse(statusCode,msg,positions);
    return ResponseEntity.ok(a);

  }


  @GetMapping("/getPositionById/{id}")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  private ResponseEntity<JobResponseById> getPositionById(@PathVariable String id)
  {
    int statusCode=0;
    String msg= "";
    final String uri = "http://dev3.dansmultipro.co.id/api/recruitment/positions/"+ id;
    Position positions=null;

    RestTemplate restTemplate = new RestTemplate();
    String result = restTemplate.getForObject(uri, String.class);
    ResponseEntity<Position> responseEntity = restTemplate.exchange(
            uri,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Position>() {
            });
    Position detail = responseEntity.getBody();
    statusCode=responseEntity.getStatusCodeValue();
    if (responseEntity.getStatusCodeValue()==200)
    {
      msg= "Success";
      positions = responseEntity.getBody();
      if(positions.getId()==null)
        msg= "id tidak ditemukan";
    }
    else
    {
      msg= "response failed";
    }
    JobResponseById a = new JobResponseById(statusCode,msg,positions);
    return ResponseEntity.ok(a);
  }

}
