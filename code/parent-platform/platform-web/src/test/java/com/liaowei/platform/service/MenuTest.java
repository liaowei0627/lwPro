/**
 * platform-web
 * MenuTest.java
 */
package com.liaowei.platform.service;

import java.time.LocalDateTime;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.liaowei.framework.page.Pagination;
import com.liaowei.framework.page.Pagination.OrderEnum;
import com.liaowei.framework.query.Where;
import com.liaowei.framework.query.operator.NoValueComparisonOperator;
import com.liaowei.framework.query.operator.OneValueComparisonOperator;
import com.liaowei.framework.test.TestService;
import com.liaowei.platform.enums.MenuTypeEnum;
import com.liaowei.platform.vo.MenuVo;

import lombok.extern.slf4j.Slf4j;

/**
 * MenuTest
 *
 * 菜单管理服务测试用例
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-30 19:10:14
 * @see com.liaowei.framework.test.TestService
 * @since jdk1.8
 */
@Slf4j
public class MenuTest extends TestService {

    @Resource(name = "menuService")
    private IMenuService menuService;

    @Test
    public void testAdd() {
        try {
            MenuVo vo = new MenuVo();
            vo.setCode("SUB_SYS_SYSTEM");
            vo.setText("菜单管理");
            vo.setMenuType(MenuTypeEnum.SYSTEM);
            vo.setValid(Boolean.TRUE);
            vo.setOrderNum(Integer.valueOf(999));
            vo.setCreator("admin");
            vo.setCreateTime(LocalDateTime.now());
            vo.setReviser("admin");
            vo.setModifyTime(LocalDateTime.now());
            vo = menuService.addVo(vo);
            log.info(vo.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Test
    public void testFindList() {
        try {
            Map<String, OrderEnum> orderBy = Maps.newHashMap();
            orderBy.put("orderNum", OrderEnum.ASC);
            Pagination<MenuVo> page = new Pagination<>(orderBy);
            page.setPageable(false);
            Where where = Where.rootWhere("valid", OneValueComparisonOperator.EQ, Boolean.TRUE);
            where.andWhere("parent", NoValueComparisonOperator.IS_NULL);
            Pagination<MenuVo> pagination = menuService.findList(page, where);
            log.info(pagination.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}