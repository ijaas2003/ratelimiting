package com.ratelimit.ratelimit.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GlobalController {

  @GetMapping("/users")
  public ResponseEntity<Map<String, Object>> getUsers() {
    Map<String, Object> response = new HashMap<>();
    response.put("status", "success");
    response.put("message", "User data fetched successfully");
    response.put("count", 5);

    return ResponseEntity
        .status(HttpStatus.OK) // sets status 202
        .body(response); // sets body
  }

  @GetMapping("/{userId}/getUsersDetails")
  public ResponseEntity<Map<String, Object>> getUsersDetails(@PathVariable("userId") Long userId) {
    Map<String, Object> response = new HashMap<>();
    response.put("status", "success");
    response.put("message", "User data fetched successfully");
    response.put("count", 5);

    return ResponseEntity
        .status(HttpStatus.OK) // sets status 202
        .body(response); // sets body

  }

}
