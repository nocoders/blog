package com.crop.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.crop.web.dao.ArticleDao;
import com.crop.web.dto.ArticleUpdateReq;
import com.crop.web.dto.ArticleDetail;
import com.crop.web.dto.ArticlePageParam;
import com.crop.web.dto.PageBean;
import com.crop.mbg.mapper.CArticleContentMapper;
import com.crop.mbg.model.CArticle;
import com.crop.mbg.model.CArticleContent;
import com.crop.mbg.model.CArticleExample;
import com.crop.mbg.model.CUser;
import com.crop.web.service.ArticleService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章 service
 *
 * @author linmeng
 * @version 1.0
 * @date 24/8/2020 下午2:52
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private CArticleContentMapper cArticleContentMapper;

    /**
     * 文章添加，添加时
     * @param param 前端传递需要添加文章
     * @author linmeng
     * @date 24/8/2020 下午3:29
     * @return com.crop.mapper.model.CArticle
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long add(ArticleUpdateReq param, CUser user) {

        CArticle article = new CArticle();
        BeanUtil.copyProperties(param,article);
        article.setUserId(user.getId());
        articleDao.insertSelective(article);
        CArticleContent articleContent = new CArticleContent();
        articleContent.setArticleId(article.getId());
        articleContent.setContent(param.getContent());

        cArticleContentMapper.insertSelective(articleContent);

        return article.getId();
    }

    /**
     * 根据文章id查询文章详情
     * @param id 文章id
     * @author linmeng
     * @date 25/8/2020 下午4:39
     * @return com.crop.mapper.dto.ArticleDetails
     */
    @Override
    public ArticleDetail getDetailById(Long id) {

        return articleDao.getArticleDetailById(id);
    }

    /**
     * 分页查询当前登录用户的 文章，查询条件中状态无草稿状态时，
     * @param pageBean 分页查询条件参数
     * @author linmeng
     * @date 27/8/2020 上午9:50
     * @return java.util.List<com.crop.mapper.dto.ArticleBean>
     */
    @Override
    public List<CArticle> pageList(PageBean<ArticlePageParam> pageBean) {

        PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());

        ArticlePageParam req = pageBean.getParam();
        CArticleExample example = new CArticleExample();
        CArticleExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(req.getUserId());
        if (StringUtils.isNotBlank(req.getTitle())){
            criteria.andTitleLike("%"+req.getTitle()+"%");
        }
        if (null != req.getIsOriginal()){
            criteria.andIsOriginalEqualTo(req.getIsOriginal());
        }
        if (null != req.getStatus()){
            criteria.andStatusEqualTo(req.getStatus());
        }
        example.setOrderByClause("update_time desc");

        return articleDao.selectByExample(example);
    }

    /**
     * 修改文章:检查文章是否存在，文章所属用户是否匹配
     * @param param
     * @author linmeng
     * @date 27/8/2020 下午1:25
     * @return int
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(ArticleUpdateReq param) {
        Long articleId = param.getId();
        ArticleDetail articleDetail = articleDao.getArticleDetailById(articleId);
        if (null != articleDetail && param.getUserId().equals(articleDetail.getUserId())
                && StringUtils.isNotBlank(articleDetail.getContent())){
            CArticle cArticle = new CArticle();
            BeanUtils.copyProperties(param,cArticle);
            int articleCount = articleDao.updateByPrimaryKey(cArticle);
            CArticleContent articleContent = new CArticleContent();
            articleContent.setArticleId(articleId);
            articleContent.setContent(param.getContent());
            int contentCount = cArticleContentMapper.updateByPrimaryKey(articleContent);

            return contentCount & articleCount;
        }

        return 0;
    }
}
