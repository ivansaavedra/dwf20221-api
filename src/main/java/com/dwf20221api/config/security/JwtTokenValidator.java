package com.dwf20221api.config.security;

import com.auth0.jwk.Jwk;
import com.auth0.jwk.JwkException;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.isNull;

public class JwtTokenValidator {

    private JwkProvider jwkProvider = null;
    private static final Logger log  = LoggerFactory.getLogger(JwtTokenValidator.class);

    public JwtTokenValidator(JwkProvider jwkProvider) {
			this.jwkProvider = jwkProvider;
		}

	public AccessToken validateAuthorizationHeader(String authorizationHeader) throws InvalidTokenException {
        String tokenValue = subStringBearer(authorizationHeader);
        validateToken(tokenValue);
        return new AccessToken(tokenValue);
    }

    private void validateToken(String value) {
        DecodedJWT decodedJWT = decodeToken(value);
        verifyTokenHeader(decodedJWT);
        verifySignature(decodedJWT);
        verifyPayload(decodedJWT);
    }

    private DecodedJWT decodeToken(String value) {
        if (isNull(value)){
            throw new InvalidTokenException("No se proporciono ningun token de autorizacion");
        }
        DecodedJWT decodedJWT = JWT.decode(value);
        log.debug("Token decoded successfully");
        return decodedJWT;
    }

    private void verifyTokenHeader(DecodedJWT decodedJWT) {
        try {
            Preconditions.checkArgument(decodedJWT.getType().equals("JWT"));
            log.debug("La cabecera del token es correcta");
        } catch (IllegalArgumentException ex) {
            throw new InvalidTokenException("El token no es de tipo JWT", ex);
        }
    }

    private void verifySignature(DecodedJWT decodedJWT) {
        try {
            Jwk jwk = jwkProvider.get(decodedJWT.getKeyId());
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
            algorithm.verify(decodedJWT);
            log.debug("La firma del token proporcionada es correcta ");
        } catch (JwkException | SignatureVerificationException ex) {
            throw new InvalidTokenException("Token has invalid signature", ex);
        }
    }

    private void verifyPayload(DecodedJWT decodedJWT) {
        JsonObject payloadAsJson = decodeTokenPayloadToJsonObject(decodedJWT);
        if (hasTokenExpired(payloadAsJson)) {
            throw new InvalidTokenException("El token proporcionado ha expirado");
        }
        log.debug("El token se encuentra vigente");

        if (!hasTokenRealmRolesClaim(payloadAsJson)) {
            throw new InvalidTokenException("El token proporcionado no contiene las demandas(claims) con los roles del realm");
        }
        log.debug("El token proporcionado contiene las demandas(claims) con los roles del realm");

        if (!hasTokenScopeInfo(payloadAsJson)) {
            throw new InvalidTokenException("El token no contiene informacion del alcance");
        }
        log.debug("El token no contiene informacion del alcance");
    }

    private JsonObject decodeTokenPayloadToJsonObject(DecodedJWT decodedJWT) {
        try {
            String payloadAsString = decodedJWT.getPayload();
            return new Gson().fromJson(
                    new String(Base64.getDecoder().decode(payloadAsString), StandardCharsets.UTF_8),
                    JsonObject.class);
        }   catch (RuntimeException exception){
            throw new InvalidTokenException("JWT o formato JSON invalido para cada una de las partes que conforman el JWT", exception);
        }
    }

    private boolean hasTokenExpired(JsonObject payloadAsJson) {
        Instant expirationDatetime = extractExpirationDate(payloadAsJson);
        return Instant.now().isAfter(expirationDatetime);
    }

    private Instant extractExpirationDate(JsonObject payloadAsJson) {
        try {
            return Instant.ofEpochSecond(payloadAsJson.get("exp").getAsLong());
        } catch (NullPointerException ex) {
            throw new InvalidTokenException("No existe el valor 'exp' el payload del JWT");
        }
    }

    private boolean hasTokenRealmRolesClaim(JsonObject payloadAsJson) {
        try {
            return payloadAsJson.getAsJsonObject("realm_access").getAsJsonArray("roles").size() > 0;
        } catch (NullPointerException ex) {
            return false;
        }
    }

    private boolean hasTokenScopeInfo(JsonObject payloadAsJson) {
        return payloadAsJson.has("scope");
    }

    private String subStringBearer(String authorizationHeader) {
        try {
            return authorizationHeader.substring(AccessToken.BEARER.length());
        } catch (Exception ex) {
            throw new InvalidTokenException("No hay un token de autorizacion en la cabecera(header) de la petición");
        }
    }
}
