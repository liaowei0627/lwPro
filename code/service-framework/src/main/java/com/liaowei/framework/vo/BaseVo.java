/**
 * service-framework
 * BaseVo.java
 */
package com.liaowei.framework.vo;

import java.time.LocalDateTime;

import com.liaowei.framework.core.vo.IBasisVo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * BaseVo
 *
 * Spring服务层向控制层传递数据的封装类基类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-08 21:40:39
 * @see com.liaowei.framework.core.vo.IBaseVo<T>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString(callSuper = true)
public abstract class BaseVo extends BaseIdVo implements IBasisVo {

    /**
     * 是否有效
     */
    protected Boolean valid;

    /**
     * 创建者
     */
    protected String creator;

    /**
     * 创建时间
     */
    protected LocalDateTime createTime;

    /**
     * 修改者
     */
    protected String reviser;

    /**
     * 修改时间
     */
    protected LocalDateTime modifyTime;

    public BaseVo() {
        super();
    }

    public BaseVo(String id, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id);
        this.valid = valid;
        this.creator = creator;
        this.createTime = createTime;
        this.reviser = reviser;
        this.modifyTime = modifyTime;
    }
}