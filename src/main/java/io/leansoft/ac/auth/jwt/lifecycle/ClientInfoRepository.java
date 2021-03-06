package io.leansoft.ac.auth.jwt.lifecycle;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

interface ClientInfoRepository extends CrudRepository<ClientInfoEntity, Long> {
    ClientInfoEntity findByClientKey(String clientKey);

    @Modifying
    @Transactional
    @Query("UPDATE ClientInfoEntity client SET client.installed = ?2 WHERE client.clientKey = ?1")
    int setInstalled(String clientKey, boolean installed);

    @Modifying
    @Transactional
    @Query("UPDATE ClientInfoEntity client SET client.enabled = ?2 WHERE client.clientKey = ?1")
    int setEnabled(String clientKey, boolean enabled);

    @Query("SELECT client.enabled FROM  ClientInfoEntity client WHERE client.clientKey = ?1")
    Boolean isEnabled(String clientKey);

    @Query("SELECT client.installed FROM  ClientInfoEntity client WHERE client.clientKey = ?1")
    Boolean isInstalled(String clientKey);
}
