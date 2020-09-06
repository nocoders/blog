package com.crop.portal.task;

import com.crop.mapper.dao.ArticleDao;
import com.crop.mapper.dao.UserDao;
import com.crop.mapper.mapper.CUserMapper;
import com.crop.mapper.model.CUserExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 文章点赞持久化定时任务
 * @author linmeng
 * @version 1.0
 * @date 6/9/2020 下午9:13
 */
@Component
@Slf4j
public class ArticleLikedPersistentTask {

    @Autowired
    private UserDao userDao;

    private ArticleDao articleDao;

    @Value("${executor.threshold}")
    private Integer threshold;

    /**
     * 每天定时同步文章点赞相关数据 到数据库
     * @author linmeng
     * @date 6/9/2020 下午11:33 
     * @return void
     */
    @Scheduled(cron = "0 0 4 * ? * ?")
    private void articleLikedPersistent(){
        // 查询正在使用的用户数量
        CUserExample userExample = new CUserExample();
        userExample.createCriteria().andStatusEqualTo((byte) 1);
        userExample.setOrderByClause(" order by create_time desc");
        long userCount = userDao.countByExample(userExample);
        if (userCount<threshold){

        }
    }
}
