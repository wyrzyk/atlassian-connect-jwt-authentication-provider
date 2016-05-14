package io.leansoft.ac.auth.jwt.lifecycle;

import io.leansoft.ac.auth.jwt.api.ClientInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@Slf4j
public class LifecycleService {
    private final ClientInfoRepository clientInfoRepository;

    public Optional<ClientInfoDto> findClient(String clientKey) {
        final ClientInfoEntity clientEntity = clientInfoRepository.findByClientKey(clientKey);
        return ofNullable(clientEntity).map(ClientInfoEntity::toDto);
    }

    @Transactional
    Optional<ClientInfoDtoImpl> save(ClientInfoEntity newClientInfoEntity) {
        final ClientInfoEntity entity = saveOrUpdate(newClientInfoEntity);
        return ofNullable(entity)
                .map(ClientInfoEntity::toDto);
    }

    boolean setInstalled(String clientKey, boolean installed) {
        final boolean isUpdated = clientInfoRepository.setInstalled(clientKey, installed) > 0;
        if (isUpdated) {
            log.debug("Installed flag has been changed to {} for {}", installed, clientKey);
        } else {
            log.warn("Faild to change installed flag to {} for {}", installed, clientKey);
        }
        return isUpdated;
    }

    boolean setEnabled(String clientKey, boolean enabled) {
        final boolean isUpdated = clientInfoRepository.setEnabled(clientKey, enabled) > 0;
        if (isUpdated) {
            log.debug("Enabled flag has been changed to {} for {}", enabled, clientKey);
        } else {
            log.warn("Faild to change enabled flag to {} for {}", enabled, clientKey);
        }
        return isUpdated;
    }

    private boolean isEnabled(String clientKey) {
        final Boolean enabled = clientInfoRepository.isEnabled(clientKey);
        return enabled != null && enabled;
    }

    private boolean isInstalled(String clientKey) {
        final Boolean installed = clientInfoRepository.isInstalled(clientKey);
        return installed != null && installed;
    }

    private long countClients() {
        return clientInfoRepository.count();
    }

    private ClientInfoEntity saveOrUpdate(ClientInfoEntity newClientInfoEntity) {
        final String clientKey = newClientInfoEntity.getClientKey();
        final ClientInfoEntity lifecycleEntity = clientInfoRepository.findByClientKey(clientKey);
        if (lifecycleEntity != null) {
            log.info("Plugin has been installed again for user {}", clientKey);
            newClientInfoEntity.setId(lifecycleEntity.getId());
        } else {
            log.info("Plugin has been installed for user {}", clientKey);
        }
        newClientInfoEntity.setInstalled(true);
        try {
            return clientInfoRepository.save(newClientInfoEntity);
        } catch (Exception e) {
            log.error(format("error while saving clientInfo entity %s", newClientInfoEntity), e);
            return null;
        }
    }
}
