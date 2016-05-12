package io.leansoft.ac.auth.jwt.api;

import lombok.experimental.UtilityClass;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;

@UtilityClass
/**
 * Utility class which provides static factory methods for the Atlassian Connect authentication.
 */
public class AtlassianConnectAuthUtils {
    /**
     * Creates a new instance of {@link HandlerMethodArgumentResolver}. The resolver helps to bind {@link ClientInfoDto} from
     * a request. An example of usage can be found in the implementation of
     * {@link io.leansoft.ac.auth.jwt.lifecycle.LifecycleResource#installed(ClientInfoRequest, ClientInfoDto)}.
     * Configuration classes of the integration tests could help you to understand how to register this handler.
     *
     * @return resolver for {@link ClientInfoDto}
     */
    public static HandlerMethodArgumentResolver createClientDtoResolver() {
        return new ClientInfoDtoResolver();
    }

    /**
     * Creates a new instance of {@link JwtAuthenticationInterceptor}.
     *
     * @return interceptor responsible for jwtAuthentication.
     */
    public static HandlerInterceptor createJwtAuthenticationInterceptor() {
        return new JwtAuthenticationInterceptor();
    }
}
