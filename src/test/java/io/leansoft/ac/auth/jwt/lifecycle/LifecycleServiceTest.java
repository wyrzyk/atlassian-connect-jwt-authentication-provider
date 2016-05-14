package io.leansoft.ac.auth.jwt.lifecycle;

import org.joor.Reflect;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


public class LifecycleServiceTest {
    private static final String CLIENT_KEY = "clientKey";
    private LifecycleService lifecycleService;
    private LifecycleServiceProxy lifecycleServiceProxy;
    @Mock
    private ClientInfoRepository clientInfoRepository;
    @Mock
    private ClientInfoEntity entity;

    @Before
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);
        lifecycleService = new LifecycleService(clientInfoRepository);
        lifecycleServiceProxy = Reflect.on(lifecycleService).as(LifecycleServiceProxy.class);
        when(entity.getClientKey()).thenReturn(CLIENT_KEY);
    }

    @Test
    public void testSaveSuccess() throws Exception {
        when(clientInfoRepository.save(entity)).thenReturn(entity);
        when(clientInfoRepository.findByClientKey(CLIENT_KEY)).thenReturn(entity);
        final ClientInfoEntity clientInfoDto = lifecycleServiceProxy.saveOrUpdate(entity);
        assertThat(clientInfoDto).isNotNull();
    }

    @Test
    public void testSaveFail() throws Exception {
        Mockito.doThrow(new DuplicateKeyException("")).when(clientInfoRepository).save(entity);
        when(clientInfoRepository.findByClientKey(CLIENT_KEY)).thenReturn(entity);
        final ClientInfoEntity clientInfoDto = lifecycleServiceProxy.saveOrUpdate(entity);
        assertThat(clientInfoDto).isNull();
    }

    interface LifecycleServiceProxy {
        ClientInfoEntity saveOrUpdate(ClientInfoEntity newClientInfoEntity);
    }
}