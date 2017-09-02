package cn.fql.fishbone.dao.mapper;

import cn.fql.fishbone.model.domain.Config;
import cn.fql.fishbone.web.dto.ConfigParam;

import java.util.List;

/**
 * Created by fuquanlin on 2016/10/28.
 */
public interface ConfigMapper {

    List<Config> queryConfig(ConfigParam configParam);
}
