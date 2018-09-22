/**
 * platform-web
 * MenuTest.java
 */
package com.flyhaze.platform.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.flyhaze.framework.core.page.Pagination;
import com.flyhaze.framework.core.query.Where;
import com.flyhaze.framework.core.query.operator.NoValueComparisonOperator;
import com.flyhaze.framework.core.query.operator.OneValueComparisonOperator;
import com.flyhaze.framework.core.query.order.OrderBy;
import com.flyhaze.framework.core.query.order.OrderEnum;
import com.flyhaze.framework.test.TestService;
import com.flyhaze.platform.enums.MenuTypeEnum;
import com.flyhaze.platform.vo.MenuVo;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

/**
 * MenuTest
 *
 * 菜单管理服务测试用例
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-30 19:10:14
 * @see com.flyhaze.framework.test.TestService
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
            vo.setMenuType(MenuTypeEnum.NAVIGATION);
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
            List<OrderBy> orderBy = Lists.<OrderBy>newArrayList();
            orderBy.add(new OrderBy("orderNum", OrderEnum.ASC));
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