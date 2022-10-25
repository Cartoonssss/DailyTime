package com.song.dailytime.dailytime.controller;

import com.song.dailytime.dailytime.Entity.ArticleVO;
import com.song.dailytime.dailytime.common.ResponseStatus;
import com.song.dailytime.dailytime.common.RestResponse;
import com.song.dailytime.dailytime.service.serviceInterface.ArticleServiceInterFace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

@Controller
public class ArticleController {
    private static Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Resource
    private ArticleServiceInterFace articleServiceInterFace;

    /**
     * 保存或更新文章
     *
     * @param articleVO
     * @return
     */
    @PostMapping(value = "saveArticle")
    @ResponseBody
    public Object saveArticle(@RequestBody ArticleVO articleVO) {
        RestResponse<ArticleVO> restResponse = new RestResponse<>();
        String id = articleVO.getId();
        if (StringUtils.isEmpty(id)) {
            id = UUID.randomUUID().toString().replace("-", "");
            articleVO.setId(id);
            int i = articleServiceInterFace.saveArticle(articleVO);
            if (i > 0) {
                restResponse.setData(articleVO).setStatus(ResponseStatus.Ok);
            } else {
                restResponse.setMsg("保存失败!").setStatus(ResponseStatus.Error);
            }
        } else {
            int i = articleServiceInterFace.updateArticle(articleVO);
            ArticleVO article = articleServiceInterFace.selectArticleById(id);
            if (i > 0) {
                restResponse.setData(article).setMsg("更新成功").setStatus(ResponseStatus.Ok);
            } else {
                restResponse.setData(null).setMsg("更新失败！").setStatus(ResponseStatus.Error);
            }
        }
        return restResponse;
    }

    /**
     * 根据id删除文章
     *
     * @param id
     * @return
     */
    @GetMapping(value = "deleteArticle")
    @ResponseBody
    public Object deleteArticle(@RequestBody String id) {
        logger.info("========" + id + "=======");
        RestResponse<String> restResponse = new RestResponse<>();
        int i = articleServiceInterFace.deleteArticle(id);
        if (i > 0) {
            return restResponse.setMsg("删除成功").setStatus(ResponseStatus.Ok);
        } else {
            return restResponse.setMsg("删除失败").setStatus(ResponseStatus.Error);
        }
    }

    /**
     * 根据ID查找文章
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "selectArticleById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object selectArticleById(@PathVariable("id") String id) {
        RestResponse<ArticleVO> restResponse = new RestResponse<>();
        ArticleVO articleVO = articleServiceInterFace.selectArticleById(id);
        if (null != articleVO) {
            restResponse.setData(articleVO).setMsg("查询成功").setStatus(ResponseStatus.Ok);
        } else {
            restResponse.setData(null).setMsg("查询失败").setStatus(ResponseStatus.Error);
        }
        return restResponse;
    }
}
