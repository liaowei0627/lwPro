/**
 * Sample
 * TestCommont.java
 * 创建于2018年3月31日 上午11:33:02
 */
package com.flyhaze.sample;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * TestCommont
 *
 * @author liaowei
 * @date 创建时间：2018年3月31日 上午11:48:55 
 * @since jdk1.8
 */
@Slf4j
public class TestCommont {

    @Test
    public void test() {
        log.info(String.valueOf(null == null));
        log.info(String.valueOf("".equals(null)));
    }
}