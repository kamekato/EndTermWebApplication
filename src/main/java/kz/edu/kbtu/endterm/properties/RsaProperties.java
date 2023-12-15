package kz.edu.kbtu.endterm.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties("rsa")
public record RsaProperties(
        RSAPublicKey publicKey,
        RSAPrivateKey privateKey
) {
}
