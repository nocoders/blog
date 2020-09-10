package com.crop.portal.task;

import com.crop.common.service.impl.RedisServiceImpl;
import com.crop.common.util.RedisKeyUtil;
import com.crop.mapper.dao.ArticleDao;
import com.crop.mapper.model.CArticle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
    private ArticleDao articleDao;

    @Value("${executor.threshold}")
    private Integer threshold;

    @Autowired
    private RedisServiceImpl redisService;

    /**
     * 每天定时同步文章点赞相关数据 到数据库，
     * 不去查询用户相关信息，直接查询已经发布的文章，然后根据文章id查询redis中点赞的数量，同步到数据库
     * @author linmeng
     * @date 6/9/2020 下午11:33 
     * @return void
     */
    @Scheduled(cron = "0 0 4 * * ?")
    private void articleLikedPersistent(){
        // 查询已发布文章id
        List<Long> distributeArticleIds = articleDao.getDistributeArticleIds();
        if (CollectionUtils.isEmpty(distributeArticleIds)){
            log.info("没有已发布的文章");
            return;
        }
        distributeArticleIds.forEach(a ->{
            List<Object> counts = redisService.hVals(RedisKeyUtil.getArticleLikedKey(a));
            int totalCount = 0;
            for (Object count : counts) {
                totalCount = totalCount + (Integer)count;
            }
            articleDao.updateByPrimaryKeySelective(new CArticle(a, null, null, totalCount, null));
        });
    }
}
