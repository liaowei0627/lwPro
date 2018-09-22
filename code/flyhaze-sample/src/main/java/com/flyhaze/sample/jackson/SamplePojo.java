/**
 * Sample
 * SamplePojo.java
 */
package com.flyhaze.sample.jackson;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * SamplePojo
 *
 * @author liaowei
 * @date 创建时间：2018年3月31日 下午12:27:21
 * @since jdk1.8
 */
public class SamplePojo {

    private String str;
    private Integer intObj;
    private int intNum;
    private Long longObj;
    private long longNum;
    private Date date;
    private LocalDateTime localDateTime;
    private List<SamplePojo> list;
    private Map<String, SamplePojo> map;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Integer getIntObj() {
        return intObj;
    }

    public void setIntObj(Integer intObj) {
        this.intObj = intObj;
    }

    public int getIntNum() {
        return intNum;
    }

    public void setIntNum(int intNum) {
        this.intNum = intNum;
    }

    public Long getLongObj() {
        return longObj;
    }

    public void setLongObj(Long longObj) {
        this.longObj = longObj;
    }

    public long getLongNum() {
        return longNum;
    }

    public void setLongNum(long longNum) {
        this.longNum = longNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public List<SamplePojo> getList() {
        return list;
    }

    public void setList(List<SamplePojo> list) {
        this.list = list;
    }

    public Map<String, SamplePojo> getMap() {
        return map;
    }

    public void setMap(Map<String, SamplePojo> map) {
        this.map = map;
    }
}
