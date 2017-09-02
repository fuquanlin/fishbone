package cn.fql.fishbone.service.base;

import cn.fql.fishbone.model.domain.Config;
import cn.fql.fishbone.web.dto.ConfigParam;

import java.util.List;

/**
 * Created by fuquanlin on 2016/10/27.
 */
public interface ConfigService {

    List<Config> queryConfig(ConfigParam configParam);
}
