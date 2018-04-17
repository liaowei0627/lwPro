/**
 * platform-service
 * SiteVo.java
 */
package com.liaowei.platform.vo;

import java.time.LocalDateTime;

import com.liaowei.framework.vo.BaseVo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * SiteVo
 *
 * 站点信息
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-19 22:01:35
 * @see com.liaowei.framework.service.vo.BaseVo
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Getter
@Setter
@ToString(callSuper = true)
public class SiteVo extends BaseVo {

    /**
     * 站点名称
     */
    private String siteName;

    public SiteVo() {
        super();
    }

    public SiteVo(String id, String siteName, Boolean valid, String creator, LocalDateTime createTime, String reviser,
            LocalDateTime modifyTime) {
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
        if (!(obj instanceof SiteVo))
            return false;
        SiteVo other = (SiteVo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}