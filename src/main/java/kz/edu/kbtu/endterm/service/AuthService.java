package kz.edu.kbtu.endterm.service;

import kz.edu.kbtu.endterm.dto.request.UserDtoRequest;
import kz.edu.kbtu.endterm.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public TokenWrapper login(UserDtoRequest request) {
        final var user = userService.loadUserByUsername(request.getUsername());
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Wrong password!");
        }

        return new TokenWrapper(jwtUtil.generateAccessToken(user), jwtUtil.generateRefreshToken(user));
    }

    public TokenWrapper register(UserDtoRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        final var user = userService.create(request);
        return new TokenWrapper(jwtUtil.generateAccessToken(user), jwtUtil.generateRefreshToken(user));
    }

    public TokenWrapper refresh(String refresh) {
        final var user = userService.loadUserByUsername(jwtUtil.getSubject(refresh));

        return new TokenWrapper(jwtUtil.generateAccessToken(user), jwtUtil.generateRefreshToken(user));
    }

    public record TokenWrapper(String access, String refresh) {
    }
}
