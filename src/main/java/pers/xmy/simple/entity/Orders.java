package pers.xmy.simple.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-08-05
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="Order对象", description="")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "订单号")
      private String number;

      @ApiModelProperty(value = "订单状态 1待付款，2待派送，3已派送，4已完成，5已取消")
      private Integer status;

      @ApiModelProperty(value = "下单用户")
      private Long userId;

      @ApiModelProperty(value = "地址")
      private String address;

      @ApiModelProperty(value = "下单时间")
      private LocalDateTime orderTime;

      @ApiModelProperty(value = "结账时间")
      private LocalDateTime checkoutTime;

      @ApiModelProperty(value = "支付方式 1微信，2支付宝")
      private Integer payMethod;

      @ApiModelProperty(value = "实收金额")
      private BigDecimal amount;


}
