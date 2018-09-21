/**
 * study-service
 * 宝石配方表.java
 */
package com.flyhaze.study.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.flyhaze.framework.hibernate.entity.BaseEntity;

/**
 * GemstoneFormula
 * 
 * 宝石配方表实体类
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-07 23:18:04
 * @see com.flyhaze.framework.entity.BaseEntity
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "GEMSTONE_FORMULAS")
public class GemstoneFormula extends BaseEntity<GemstoneFormula> {

    /**
     * 宝石信息
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "GEMSTONE_ID")
    private Gemstone gemstone;

    /**
     * 材料宝石
     */
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "MATERIAL_ID")
    private Gemstone material;

    /**
     * 材料等级
     */
    private String materialLevel;

    /**
     * 数量
     */
    private Integer num;

    public GemstoneFormula() {
        super();
    }

    public GemstoneFormula(String id, Gemstone gemstone, Gemstone material, String materialLevel, Integer num, Boolean valid, String creator, LocalDateTime createTime, String reviser, LocalDateTime modifyTime) {
        super(id, valid, creator, createTime, reviser, modifyTime);
        this.gemstone = gemstone;
        this.material = material;
        this.materialLevel = materialLevel;
        this.num = num;
    }

    /**
     * 宝石信息
     */
    public Gemstone getGemstone() {
        return gemstone;
    }

    /**
     * 宝石信息
     */
    public void setGemstone(Gemstone gemstone) {
        this.gemstone = gemstone;
    }

    /**
     * 材料宝石
     */
    public Gemstone getMaterial() {
        return material;
    }

    /**
     * 材料宝石
     */
    public void setMaterial(Gemstone material) {
        this.material = material;
    }

    /**
     * 材料等级
     */
    public String getMaterialLevel() {
        return materialLevel;
    }

    /**
     * 材料等级
     */
    public void setMaterialLevel(String materialLevel) {
        this.materialLevel = materialLevel;
    }

    /**
     * 数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public void setEntity(GemstoneFormula e) {
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
        if (!(obj instanceof GemstoneFormula))
            return false;
        GemstoneFormula other = (GemstoneFormula) obj;
        if (id == null) {
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}