package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.dao.IDataCountDao;
import me.daoke.renrenfm.service.IDataCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据统计
 * Created by zhaoym on 2015/10/20.
 */
@Service
public class DataCountServiceImpl implements IDataCountService {

    @Autowired
    private IDataCountDao dataCountDao;


}
