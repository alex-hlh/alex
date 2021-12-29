package com.bus.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 操作日志记录
 * </p>
 *
 * @author stx
 * @since 2021-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_operate_log")
@ApiModel(value="SysOperateLog对象", description="操作日志记录")
public class SysOperateLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日志主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "链路追踪编号")
    @TableField("trace_id")
    private String traceId;

    @ApiModelProperty(value = "用户编号")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "模块标题")
    private String module;

    @ApiModelProperty(value = "操作名")
    private String name;

    @ApiModelProperty(value = "操作分类")
    @TableField("operate_type")
    private Long operateType;

    @ApiModelProperty(value = "操作内容")
    private String content;

    @ApiModelProperty(value = "拓展字段")
    private String exts;

    @ApiModelProperty(value = "请求方法名")
    @TableField("request_method")
    private String requestMethod;

    @ApiModelProperty(value = "请求地址")
    @TableField("request_url")
    private String requestUrl;

    @ApiModelProperty(value = "用户 IP")
    @TableField("user_ip")
    private String userIp;

    @ApiModelProperty(value = "浏览器 UA")
    @TableField("user_agent")
    private String userAgent;

    @ApiModelProperty(value = "Java 方法名")
    @TableField("java_method")
    private String javaMethod;

    @ApiModelProperty(value = "Java 方法的参数")
    @TableField("java_method_args")
    private String javaMethodArgs;

    @ApiModelProperty(value = "操作时间")
    @TableField("start_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime startTime;

    @ApiModelProperty(value = "执行时长")
    private Integer duration;

    @ApiModelProperty(value = "结果码")
    @TableField("result_code")
    private Integer resultCode;

    @ApiModelProperty(value = "结果提示")
    @TableField("result_msg")
    private String resultMsg;

    @ApiModelProperty(value = "结果数据")
    @TableField("result_data")
    private String resultData;

    @ApiModelProperty(value = "创建者")
    private String creator;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新者")
    private String updater;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除")
    private Boolean deleted;


}
