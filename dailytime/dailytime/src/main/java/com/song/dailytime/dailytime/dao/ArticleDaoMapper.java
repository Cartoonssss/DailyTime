package com.song.dailytime.dailytime.dao;

import com.song.dailytime.dailytime.Entity.ArticleVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleDaoMapper {
    //保存文章
    int saveArticle(ArticleVO articleVO);

    //删除文章
    int deleteArticle(String id);

    //更新文章
    int updateArticle(ArticleVO articleVO);

    //根据ID查找文章
    ArticleVO selectArticleById(String id);
}
