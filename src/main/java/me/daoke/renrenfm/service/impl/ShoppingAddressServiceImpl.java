package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.dao.IShoppingAddressDao;
import me.daoke.renrenfm.entity.ShoppingAddress;
import me.daoke.renrenfm.service.IShoppingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收货地址service的实现层
 * @author zhuosh
 * @date 2015/6/18
 */
@Service
public class ShoppingAddressServiceImpl implements IShoppingAddressService {


    @Autowired
    private IShoppingAddressDao shoppingAddressDao;

    @Override
    public List<ShoppingAddress> getAddressList(String listenerAccountID) {
        Map map = new HashMap<String,Object>();
        map.put("accountID",listenerAccountID);
        return shoppingAddressDao.selectList("getAllList", map);
    }


}
