@Autowired
private PasswordEncoder passwordEncoder;

public User registerUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    if (!user.getRole().startsWith("ROLE_")) {
        user.setRole("ROLE_" + user.getRole().toUpperCase());
    }
    return userRepository.save(user);
}

public User login(String email, String rawPassword) {
    User user = userRepository.findByEmail(email);
    if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
        return user;
    }
    return null;
}
