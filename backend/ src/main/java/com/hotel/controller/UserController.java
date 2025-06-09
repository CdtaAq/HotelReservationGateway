@PostMapping("/signup")
public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
    userService.signup(request);
    return ResponseEntity.ok("User registered");
}

@PostMapping("/refresh-token")
public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
    String newToken = userService.refreshToken(request.getRefreshToken());
    return ResponseEntity.ok(new JwtResponse(newToken));
}
