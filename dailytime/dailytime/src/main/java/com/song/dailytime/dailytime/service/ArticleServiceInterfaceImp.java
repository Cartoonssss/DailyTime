package com.song.dailytime.dailytime.service;

import com.song.dailytime.dailytime.Entity.ArticleVO;
import com.song.dailytime.dailytime.dao.ArticleDaoMapper;
import com.song.dailytime.dailytime.service.serviceInterface.ArticleServiceInterFace;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceInterfaceImp implements ArticleServiceInterFace {

    @Resource
    private ArticleDaoMapper articleDaoMapper;
    @Override
    public int saveArticle(ArticleVO articleVO) {
        return articleDaoMapper.saveArticle(articleVO);
    }

    @Override
    public int deleteArticle(String id) {
        return articleDaoMapper.deleteArticle(id);
    }

    @Override
    public int updateArticle(ArticleVO articleVO) {
        return articleDaoMapper.updateArticle(articleVO);
    }

    @Override
    public ArticleVO selectArticleById(String id) {
        return articleDaoMapper.selectArticleById(id);
    }

    @Override
    public List<ArticleVO> queryArticleList() {
        return articleDaoMapper.queryArticleList();
    }
}
