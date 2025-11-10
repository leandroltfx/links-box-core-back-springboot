package br.com.links_box_core_back_springboot.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

@Service
public class JWTProvider {

    public String generateToken(UUID id) {
        return JWT
                .create()
                .withIssuer("linkbox")
                .withExpiresAt(generateTokenExpirationTime())
                .withSubject(id.toString())
                .sign(getAlgorithm());
    }

    public Instant generateTokenExpirationTime() {
        return Instant.now().plus(Duration.ofHours(2));
    }

    public DecodedJWT validateToken(String token) {
        token = token.replace("Bearer ", "");

        try {
            return JWT
                    .require(getAlgorithm())
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private String getSecret() {
        Dotenv dotenv = Dotenv.load();
        return dotenv.get("JWT_SECRET");
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(getSecret());
    }

}
