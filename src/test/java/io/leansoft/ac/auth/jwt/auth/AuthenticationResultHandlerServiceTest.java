package io.leansoft.ac.auth.jwt.auth;

import com.atlassian.jwt.Jwt;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

import static org.assertj.core.api.Assertions.assertThat;


public class AuthenticationResultHandlerServiceTest {
    @Mock
    private Exception exception;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private Principal principal;
    @Mock
    private Jwt jwt;
    private final String MESSAGE = "message";

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testAuthenticationShouldReturnTrueOnlyForSuccessCall() throws Exception {
        final AuthenticationResultHandlerService authenticationResultHandlerService = new AuthenticationResultHandlerService();
        assertThat(authenticationResultHandlerService.createAndSendBadRequestError(exception, httpServletResponse, MESSAGE)).isFalse();
        assertThat(authenticationResultHandlerService.createAndSendForbiddenError(exception, httpServletResponse)).isFalse();
        assertThat(authenticationResultHandlerService.createAndSendInternalError(exception, httpServletResponse, MESSAGE)).isFalse();
        assertThat(authenticationResultHandlerService.createAndSendUnauthorisedFailure(exception, httpServletResponse, MESSAGE)).isFalse();
        assertThat(authenticationResultHandlerService.success(MESSAGE, principal, jwt)).isTrue();
    }

}