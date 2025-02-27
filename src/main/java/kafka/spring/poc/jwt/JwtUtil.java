package kafka.spring.poc.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // Expiration is set to 1 hour
    private static final SecretKey SECRET_KEY = generateSecretKey(); // Generate secret key and store it as a class variable

    /**
     * Generates a secure secret key using the HMAC-SHA256 algorithm.
     * <p>
     * The method uses a {@link KeyGenerator} to generate a secret key used for JWT signing.
     * </p>
     *
     * @return a newly generated {@link SecretKey} for secure JWT signing
     * @throws RuntimeException if an error occurs while generating the key
     */
    private static SecretKey generateSecretKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            keyGen.init(256);
            return keyGen.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("Error generating secret key", e);
        }
    }

    /**
     * Generates a JWT token for the specified username.
     * <p>
     * The generated token includes:
     * <ul>
     *   <li>The provided username as the subject.</li>
     *   <li>The current date.</li>
     *   <li>An expiration time defined by {@code EXPIRATION_TIME} class variable.</li>
     *   <li>A signature using the configured secret key.</li>
     * </ul>
     * </p>
     *
     * @param username the username for which the token is generated
     * @return a JWT token as a {@code String}
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    /**
     * Extracts the username for the provided JWT token.
     * <p>
     * Retrieves the subject claim from the token.
     * </p>
     *
     * @param token the JWT token
     * @return the username contained in the JWT token
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts a specific claim from the provided JWT token.
     * <p>
     * This method allows retrieving any claim by applying a claim resolver function.
     * </p>
     *
     * @param <T>            the type of the claim to extract
     * @param token          the JWT token from which to extract the claim
     * @param claimsResolver a function to resolve the desired claim from the parsed claims
     * @return the extracted claim of type {@code T}
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Parses and extracts all claims from the provided JWT token.
     * <p>
     * This method validates the token's signature using the configured secret key
     * and retrieves all claims contained within the token.
     * </p>
     *
     * @param token the JWT token to parse
     * @return the {@link Claims} object containing all claims in the token
     * @throws JwtException if the token is invalid or its signature cannot be verified
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Validates the provided JWT token.
     * <p>
     * This method checks whether the token is well-formed, signed correctly, and not expired.
     * If any validation check fails, it returns {@code false}.
     * </p>
     *
     * @param token the JWT token to validate
     * @return {@code true} if the token is valid, otherwise {@code false}
     */
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token); // This will throw an exception if the token is invalid
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}