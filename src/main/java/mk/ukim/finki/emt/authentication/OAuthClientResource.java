package mk.ukim.finki.emt.authentication;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

/**
 * Created by ristes on 3/17/16.
 */
public class OAuthClientResource {

  private OAuth2ProtectedResourceDetails client = new AuthorizationCodeResourceDetails();
  private ResourceServerProperties resource = new ResourceServerProperties();

  public OAuth2ProtectedResourceDetails getClient() {
    return client;
  }

  public ResourceServerProperties getResource() {
    return resource;
  }
}
