package io.leansoft.ac.auth.jwt.lifecycle;

import io.leansoft.ac.auth.jwt.api.ClientInfoDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString
@EqualsAndHashCode(of = {"clientKey", "baseUrl", "productType", "installed", "enabled"})
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Getter
@Builder
class ClientInfoDtoImpl implements ClientInfoDto {
    Long id;
    String key;
    String clientKey;
    String publicKey;
    String sharedSecret;
    String serverVersion;
    String pluginsVersion;
    String baseUrl;
    String productType;
    String description;
    String serviceEntitlementNumber;
    boolean installed;
    boolean enabled;
}
