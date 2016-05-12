package io.leansoft.ac.auth.jwt.lifecycle;

import com.atlassian.jwt.core.reader.JwtIssuerSharedSecretService;
import com.atlassian.jwt.exception.JwtIssuerLacksSharedSecretException;
import com.atlassian.jwt.exception.JwtUnknownIssuerException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
class JwtIssuerSharedSecretServiceImpl implements JwtIssuerSharedSecretService {
    private final LifecycleService lifecycleService;

    @Override
    public String getSharedSecret(@Nonnull String issuer) throws JwtIssuerLacksSharedSecretException, JwtUnknownIssuerException {
        return ofNullable(findClient(issuer)
                .getSharedSecret())
                .orElseThrow(() -> new JwtIssuerLacksSharedSecretException(issuer));
    }

    private ClientInfoDtoImpl findClient(@Nonnull String issuer) throws JwtUnknownIssuerException {
        return (ClientInfoDtoImpl) lifecycleService.findClient(issuer)
                .orElseThrow(() -> new JwtUnknownIssuerException(issuer));
    }
}
