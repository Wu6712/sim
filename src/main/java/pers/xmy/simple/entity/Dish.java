package pers.xmy.simple.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-06-20
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="Dish对象", description="")
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "名字")
      private String name;

      @ApiModelProperty(value = "描述")
      private String represent;

      @ApiModelProperty(value = "菜单id")
      private Integer menuId;

      @ApiModelProperty(value = "价格")
      private Integer price;

      @ApiModelProperty(value = "图片url")
      private String image;

      @ApiModelProperty(value = "数量")
      private Integer sum;

      @ApiModelProperty(value = "是否收藏，1是，0否")
      private Integer isCollect;
}
