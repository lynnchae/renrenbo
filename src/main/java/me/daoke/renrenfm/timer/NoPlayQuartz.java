package me.daoke.renrenfm.timer;

import me.daoke.renrenfm.entity.NoPlay;
import me.daoke.renrenfm.service.IAccountInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 扫描禁播定时器
 * Created by zhaoym on 2015/9/15.
 */
public class NoPlayQuartz {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private IAccountInfoService accountInfoService;

    /**
     * 定时扫描是否有待解禁的主播
     */
    public void relievePlay(){
        logger.debug("-------------->"+"开始扫描禁播的主播"+new Date());
        List<NoPlay> noPlayList = accountInfoService.getNoPlayList();
        for(NoPlay noPlay : noPlayList){
            String accountID = noPlay.getAccountID();//禁播人员对应的ID
            Date noPlayTime = noPlay.getNoPlayTime();//禁播时间
            String formerStatus = noPlay.getFormerStatus();//禁播前状态
            int duration = noPlay.getDuration();//禁播时长
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date();
                //获取时差
                long temp = date.getTime() - noPlayTime.getTime();
                long hours = temp / 1000 / 3600;
                long temp2 = temp % (1000 * 3600);
                long mins = temp2 / 1000 / 60;//转换成分
                //如果时差是0的话，代表该主播禁播时长已到，需解禁该主播
                if(mins == 0){
                try{
                    //更新主播状态
                    accountInfoService.updateAccountInfoOfStatus(accountID,"1",formerStatus);
                    //添加解禁数据
                    accountInfoService.addRelievePlay(accountID,null,"1");
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        logger.debug("-------------->"+"结束扫描禁播的主播"+new Date());
    }

}
