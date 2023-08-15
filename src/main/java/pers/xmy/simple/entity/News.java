package pers.xmy.simple.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author yao
 * @since 2023-04-13
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="News对象", description="")
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "id")
        private Integer id;

      @ApiModelProperty(value = "新闻标题")
      private String title;

      @ApiModelProperty(value = "新闻内容")
      private String content;

      @ApiModelProperty(value = "发布日期")
      private LocalDateTime publishDate;

      @ApiModelProperty(value = "评论数")
      private Integer commentNum;

      @ApiModelProperty(value = "阅读数")
      private Integer readNum;

      @ApiModelProperty(value = "点赞数")
      private Integer likeNum;

      @ApiModelProperty(value = "收藏数")
      private Integer collectNum;


}
