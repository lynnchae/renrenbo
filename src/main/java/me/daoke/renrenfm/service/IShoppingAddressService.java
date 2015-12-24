package me.daoke.renrenfm.service;

import me.daoke.renrenfm.entity.ShoppingAddress;

import java.util.List;

/**
 * 听众收货地址 service层对象
 * @author zhuosh
 * @date 2015/6/18
 */
public  interface IShoppingAddressService {
    /**
     * 查询指定的听众用户的所有的收货地址
     * @param listenerAccountID
     *         听众ID
     * @return
     */
    public List<ShoppingAddress> getAddressList(String listenerAccountID);



}
