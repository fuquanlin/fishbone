package cn.fql.fishbone.config;

import cn.fql.fishbone.FishBoneStore2;
import cn.fql.fishbone.service.base.PermissionService;
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
    FishBoneStore2 fishboneStore(){
        FishBoneStore2 fishBoneStore = new FishBoneStore2(permissionService);
        return fishBoneStore;
    }
}
