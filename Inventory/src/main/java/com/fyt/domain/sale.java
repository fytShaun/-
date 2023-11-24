package com.fyt.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sale")
public class sale {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("goodsName")
    private String goodsName;
    private Integer number;
    @TableField("userId")
    private String userId;
    private String provider;
    private Integer price;
    private String time;
    private String unit;
}