package cn.fql.fishbone.web.controller;

import cn.fql.fishbone.model.domain.Config;
import cn.fql.fishbone.model.domain.common.Result;
import cn.fql.fishbone.service.base.ConfigService;
import cn.fql.fishbone.util.ResultBuilder;
import cn.fql.fishbone.web.dto.ConfigParam;
import cn.fql.fishbone.web.dto.pagination.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by fuquanlin on 2016/10/27.
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public Result<PageResult<Config>> queryRole(ConfigParam configParam) {
        List<Config> configs = configService.queryConfig(configParam);
        return ResultBuilder.build(configParam, configs);
    }
}
