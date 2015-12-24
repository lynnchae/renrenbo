package me.daoke.renrenfm.service;

import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.entity.WithdrawCash;
import me.daoke.renrenfm.vo.PresentUserInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 财务service的接口层
 * @author zhaoym
 * @date 2015/9/9
 */
public interface IFinanceService {

    /**
     * 获取提现列表信息
     * @param pageList
     * @param userName 提现用户名
     * @param receiptNumber 回执单号
     * @param alipayAccount 支付宝账号
     * @return
     */
    public List<WithdrawCash> getWithdrawCashList(JqgridPageList pageList,String userName,String receiptNumber,String alipayAccount);

    /**
     * 查询提现用户信息
     * @param pageList
     * @param nickName 用户昵称
     * @param realName 真实姓名
     * @param mobile 用户手机号
     * @return
     */
    public  List<PresentUserInfo>  getPresentUserList(JqgridPageList pageList,String nickName,String realName,String mobile);

    /**
     * 更新提现表状态
     * @param params
     * @return
     */
    public boolean updatWithdrawCashOfStatus(Map params,String diamondsRMBRate);


    /**
     * 修改用户钻数
     * @param diamond
     *          要修改的钻数
     * @param accountID
     *          用户ID
     * @return
     */
    public boolean updateDiamondOfWallet(BigDecimal diamond,String accountID);

    /**
     * 根据用户表的主键获取用户的AccountID
     * @param id
     * @return
     */
    public String getAccountInfoOfAccountID(String id);

    /**
     * 根据用户ID查询提现列表信息
     * @param accountID 用户的ID
     * @return
     */
    public List<WithdrawCash> getPresentDetails(String accountID);


}
