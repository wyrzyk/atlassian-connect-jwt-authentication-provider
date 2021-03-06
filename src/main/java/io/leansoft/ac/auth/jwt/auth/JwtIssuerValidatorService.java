package io.leansoft.ac.auth.jwt.auth;

import com.atlassian.jwt.core.reader.JwtIssuerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.leansoft.ac.auth.jwt.lifecycle.LifecycleService;

@Component
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
class JwtIssuerValidatorService implements JwtIssuerValidator {
    private final LifecycleService lifecycleService;

    @Override
    public boolean isValid(String issuer) {
        return lifecycleService.findClient(issuer).isPresent();
    }
}
