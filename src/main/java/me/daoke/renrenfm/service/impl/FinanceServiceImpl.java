package me.daoke.renrenfm.service.impl;

import me.daoke.renrenfm.common.exception.ServiceException;
import me.daoke.renrenfm.common.page.JqgridPageList;
import me.daoke.renrenfm.common.util.AbDateUtil;
import me.daoke.renrenfm.common.util.AbStrUtil;
import me.daoke.renrenfm.dao.IFinanceDao;
import me.daoke.renrenfm.entity.BaseEntity;
import me.daoke.renrenfm.entity.WithdrawCash;
import me.daoke.renrenfm.service.IFinanceService;
import me.daoke.renrenfm.vo.PresentUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 财务service层
 * Created by zhaoym on 2015/9/9.
 */
@Service
public class FinanceServiceImpl implements IFinanceService {

    @Autowired
    private IFinanceDao financeDao;

    @Override
    public List<WithdrawCash> getWithdrawCashList(JqgridPageList pageList, String userName, String receiptNumber, String alipayAccount) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace", JqgridPageList.getStartOfPage(pageList.getPage(), pageList.getRecords()));
        params.put("size",pageList.getRecords());
        params.put("isValid",WithdrawCash.ISVALID.VALID);
        if(userName != null && !("").equals(userName)){
            params.put("userName",userName);
        }
        if(receiptNumber != null && !("").equals(receiptNumber)){
            params.put("receiptNumber",receiptNumber);
        }
        if(alipayAccount != null && !("").equals(alipayAccount)){
            params.put("alipayAccount",alipayAccount);
        }
        List<WithdrawCash> resultList = financeDao.selectList("getWithdrawCashList", params);
        int num = (Integer)financeDao.selectOne("getWithdrawCashNum", params);
        pageList.setRecords(num);
        return resultList;
    }

    @Override
    public List<PresentUserInfo> getPresentUserList(JqgridPageList pageList, String nickName, String realName, String mobile) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("startPlace", JqgridPageList.getStartOfPage(pageList.getPage(), pageList.getRecords()));
        params.put("size",pageList.getRecords());
        params.put("isValid",WithdrawCash.ISVALID.VALID);
        if(nickName != null && !("").equals(nickName)){
            params.put("nickName",nickName);
        }
        if(realName != null && !("").equals(realName)){
            params.put("realName",realName);
        }
        if(mobile != null && !("").equals(mobile)){
            params.put("mobile",mobile);
        }
        List<PresentUserInfo> resultList = financeDao.selectList("getPresentUserList", params);
        int num = (Integer)financeDao.selectOne("getPresentUserNum", params);
        pageList.setRecords(num);
        return resultList;
    }

    @Override
    @Transactional
    public boolean updatWithdrawCashOfStatus(Map params,String diamondsRMBRate) {
        boolean flag = false;//默认提交失败
        Date currentDate = new Date();
        params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        int status = Integer.valueOf(params.get("status").toString());
        String accountID = null;
        //入账失败要将钻返还给用户
        if(status == WithdrawCash.STATUS.FAILURE){
            params.put("isValid",WithdrawCash.ISVALID.VALID);
            Map<String,Object> cash = financeDao.selectOne("getWithdrawCashById",params);
            if(cash != null){
                accountID = cash.get("accountID").toString();
                String compareDate = "2015-09-10";
                String createTime = cash.get("createTime").toString();
                float money = Float.valueOf(cash.get("cashNum").toString());
                BigDecimal diamond = null;
                if(createTime.compareTo(compareDate) >= 0){
                    //2015-09-10（包括）之后的提现记录都是坏账，按照钻：RMB = 10000：7的比例，返还
                    diamond = new BigDecimal(money/(Double.valueOf(diamondsRMBRate)/100));
                    diamond = diamond.setScale(0,BigDecimal.ROUND_UP);
                }else{
                    //2015-09-10（不包括）之前的提现记录都是坏账，按照钻：RMB = 100：1的比例，返还
                    diamond = new BigDecimal(money/(Double.valueOf(1)/100));
                }
                //修改用户钱包
                flag = this.updateDiamondOfWallet(diamond,accountID);
                //记录修改钱包的日志

            }
        }else {
            flag = true;
        }
        if(flag){
            //修改提现记录的状态
            flag = (Integer)financeDao.update("updatWithdrawCashOfStatus",params) > 0;
        }else{
            //修改钱包信息失败，抛出异常信息，使实物回滚
            throw new ServiceException();
        }
        return flag;
    }

    /**
     * 修改用户钻数
     * @param diamond
     *          要修改的钻数
     * @param accountID
     *          用户ID
     * @return
     */
    public boolean updateDiamondOfWallet(BigDecimal diamond,String accountID){
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("diamond",diamond);
        params.put("accountID",accountID);
        params.put("isValid",WithdrawCash.ISVALID.VALID);
        Date currentDate = new Date();
        params.put("updateTime", AbDateUtil.getConfirmDateTimeOfDay(currentDate));
        int num = (Integer)financeDao.update("updateDiamond",params);
        return num > 0;
    }

    @Override
    public String getAccountInfoOfAccountID(String id) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("id",id);
        params.put("isValid",WithdrawCash.ISVALID.VALID);
        return financeDao.selectOne("getAccountInfoOfAccountID",params);
    }

    @Override
    public List<WithdrawCash> getPresentDetails(String accountID) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("isValid",WithdrawCash.ISVALID.VALID);
        if(accountID != null && !("").equals(accountID)){
            params.put("accountID",accountID);
        }
        List<WithdrawCash> resultList = financeDao.selectList("getPresentDetailsList", params);
        return resultList;
    }

}
