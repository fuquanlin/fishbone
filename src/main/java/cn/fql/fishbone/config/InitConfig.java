package cn.fql.fishbone.config;

import cn.fql.fishbone.FishboneStore;
import cn.fql.fishbone.service.base.PermissionService;
import cn.fql.fishbone.util.FishBoneSecurityUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Created by fuquanlin on 2016/10/10.
 */
@Configuration
public class InitConfig {


    @Resource
    private PermissionService permissionService;

    @Bean(initMethod = "init")
    FishboneStore fishboneStore(){
        FishboneStore fishboneStore = new FishboneStore(permissionService);
        return fishboneStore;
    }
}
