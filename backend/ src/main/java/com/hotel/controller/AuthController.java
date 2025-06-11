@PostMapping("/signup")
public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
    if (userRepository.existsByUsername(signupRequest.getUsername())) {
        return ResponseEntity.badRequest().body("Username is already taken!");
    }

    Employee user = new Employee();
    user.setUsername(signupRequest.getUsername());
    user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
    user.setRole(signupRequest.getRole() != null ? signupRequest.getRole() : "USER");

    userRepository.save(user);
    return ResponseEntity.ok("User registered successfully!");
}
