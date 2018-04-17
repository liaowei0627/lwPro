/**
 * platform-service
 * SysSite.java
 */
package com.liaowei.platform.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.liaowei.framework.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SysSite
 *
 * 系统站点表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-17 03:33:15
 * @see com.liaowei.framework.entity.BaseEntity
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "SYS_SITES")
public class SysSite extends BaseEntity {

    /**
     * 站点名称
     */
    private String siteName;

    public SysSite() {
        super();
    }

    public SysSite(String id, String siteName, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.siteName = siteName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof SysSite))
            return false;
        SysSite other = (SysSite) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}