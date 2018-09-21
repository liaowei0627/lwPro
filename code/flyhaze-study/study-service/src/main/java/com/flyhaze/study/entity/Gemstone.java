/**
 * study-service
 * Gemstone.java
 */
package com.flyhaze.study.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.flyhaze.framework.hibernate.entity.BaseEntity;

/**
 * Gemstone
 *
 * 宝石表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-07 23:11:57
 * @see com.flyhaze.framework.entity.BaseEntity
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "GEMSTONES")
public class Gemstone extends BaseEntity<Gemstone> {

    /**
     * 宝石名称
     */
    private String name;

    /**
     * 是否稀有
     */
    private Boolean rare;

    /**
     * 配方
     */
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Column(name = "gemstone")
    private Set<GemstoneFormula> materials;

    public Gemstone() {
        super();
    }

    public Gemstone(String id, String name, Boolean rare, Set<GemstoneFormula> materials, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.name = name;
        this.rare = rare;
        this.materials = materials;
    }

    /**
     * 宝石名称
     */
    public String getName() {
        return name;
    }

    /**
     * 宝石名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 是否稀有
     */
    public Boolean getRare() {
        return rare;
    }

    /**
     * 是否稀有
     */
    public void setRare(Boolean rare) {
        this.rare = rare;
    }

    /**
     * 配方
     */
    public Set<GemstoneFormula> getMaterials() {
        return materials;
    }

    /**
     * 配方
     */
    public void setMaterials(Set<GemstoneFormula> materials) {
        this.materials = materials;
    }

    @Override
    public void setEntity(Gemstone e) {
        // TODO Auto-generated method stub
        
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
        if (!(obj instanceof Gemstone))
            return false;
        Gemstone other = (Gemstone) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}