package kz.edu.kbtu.endterm.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public String getSubject(String token) {
        return jwtDecoder.decode(token).getSubject();
    }

    public String generateAccessToken(UserDetails user) {

        var now = Instant.now();

        var claims = JwtClaimsSet.builder()
                                 .subject(user.getUsername())
                                 .issuer("EndTerm")
                                 .issuedAt(now)
                                 .expiresAt(now.plus(5, ChronoUnit.MINUTES))
                                 .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateRefreshToken(UserDetails user) {
        var now = Instant.now();

        var claims = JwtClaimsSet.builder()
                                 .subject(user.getUsername())
                                 .issuer("EndTerm")
                                 .issuedAt(now)
                                 .claim("Type", "Refresh")
                                 .expiresAt(now.plus(30, ChronoUnit.MINUTES))
                                 .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
