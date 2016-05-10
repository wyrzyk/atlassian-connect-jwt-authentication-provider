package wyrzyk.ac.api;

import org.joor.Reflect;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.method.HandlerMethod;
import wyrzyk.ac.auth.JwtService;
import wyrzyk.ac.lifecycle.LifecycleService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


public class JwtAuthenticationInterceptorTest {
    @Mock
    private HandlerMethod handlerMethod;
    @Mock
    private JwtService jwtService;
    @Mock
    private LifecycleService lifecycleService;

    private JwtAuthenticationInterceptorProxy jwtAuthenticationInterceptorProxy;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
        final JwtAuthenticationInterceptor jwtAuthenticationInterceptor = new JwtAuthenticationInterceptor();
        Reflect.on(jwtAuthenticationInterceptor).set("jwtService", jwtService);
        Reflect.on(jwtAuthenticationInterceptor).set("lifecycleService", lifecycleService);
        jwtAuthenticationInterceptorProxy = Reflect.on(jwtAuthenticationInterceptor)
                .as(JwtAuthenticationInterceptorProxy.class);

    }

    @Test
    public void testIsClientAlreadyInstalledEmptyToken() {
        final boolean isclientAlreadyInstalled = jwtAuthenticationInterceptorProxy
                .isClientAlreadyInstalled(Optional.empty());
        assertThat(isclientAlreadyInstalled).isFalse();
    }

    interface JwtAuthenticationInterceptorProxy {
        boolean isClientAlreadyInstalled(Optional<String> jwtTokenOptional);

        boolean isInstalledRequest(HandlerMethod handlerMethod);
    }

}