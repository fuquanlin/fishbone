package cn.fql.fishbone.service.base.impl;

import cn.fql.fishbone.model.domain.Config;
import cn.fql.fishbone.service.base.ConfigService;
import cn.fql.fishbone.web.dto.ConfigParam;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fuquanlin on 2016/10/27.
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    @Override
    public List<Config> queryConfig(ConfigParam configParam) {
        return null;
    }
}
