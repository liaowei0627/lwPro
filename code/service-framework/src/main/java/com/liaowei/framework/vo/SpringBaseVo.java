/**
 * service-framework
 * SpringBaseVo.java
 */
package com.liaowei.framework.vo;

import java.time.LocalDateTime;

import com.liaowei.framework.core.vo.IBaseVo;
import com.liaowei.framework.entity.SpringBaseEntity;

/**
 * SpringBaseVo
 *
 * TODO
 *
 * @author liaowei
 * @date 创建时间：2018年4月5日 下午12:04:47 
 * @see com.liaowei.framework.core.vo.IBaseVo<T>
 * @since jdk1.8
 */
@SuppressWarnings("serial")
public abstract class SpringBaseVo<E extends SpringBaseEntity> implements IBaseVo<E> {

    // 主键
    protected String id;

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

    protected SpringBaseVo() {
    }

    protected SpringBaseVo(String id, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        this.id = id;
        this.valid = valid;
        this.creator = creator;
        this.createTime = createTime;
        this.reviser = reviser;
        this.modifyTime = modifyTime;
    }

    public abstract void copyEntity(E entity);

    public abstract E toEntity();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getReviser() {
        return reviser;
    }

    public void setReviser(String reviser) {
        this.reviser = reviser;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
}