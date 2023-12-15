package kz.edu.kbtu.endterm.controller;

import kz.edu.kbtu.endterm.dto.request.UserDtoRequest;
import kz.edu.kbtu.endterm.model.User;
import kz.edu.kbtu.endterm.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final String REFRESH = "Refresh";

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDtoRequest request) {
        return getTokenWrapperResponseEntity(authService.login(request));
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader(REFRESH) String refresh) {
        return getTokenWrapperResponseEntity(authService.refresh(refresh));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDtoRequest request) {
        return getTokenWrapperResponseEntity(authService.register(request));
    }

    private static ResponseEntity<AuthService.TokenWrapper> getTokenWrapperResponseEntity(AuthService.TokenWrapper wrapper) {
        final var headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, wrapper.access());
        headers.add(REFRESH, wrapper.refresh());

        return new ResponseEntity<>(wrapper, headers, HttpStatus.OK);
    }

}
