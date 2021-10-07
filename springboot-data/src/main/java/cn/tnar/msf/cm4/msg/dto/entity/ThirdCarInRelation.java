package cn.tnar.msf.cm4.msg.dto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 云云车场上传入场流水表
 * @TableName t_third_car_in_relation
 */
@TableName(value ="t_third_car_in_relation")
@Data
public class ThirdCarInRelation implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 停车场编号
     */
    @TableField(value = "park_code")
    private String parkCode;

    /**
     * 平台车辆流水号
     */
    @TableField(value = "serialno")
    private String serialno;

    /**
     * 云云场端入场记录Id
     */
    @TableField(value = "remote_id")
    private String remoteId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Long createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Long updateTime;

}