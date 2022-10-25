package com.song.dailytime.dailytime.Entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class ArticleVO {
    private String id;
    private String articleAuthor;
    private String editTime;
    private String articleContent;
    private String readCount;
}
