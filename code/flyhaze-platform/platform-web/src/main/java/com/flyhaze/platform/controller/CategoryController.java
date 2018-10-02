/**
 * platform-web
 * CategoryController.java
 */
package com.flyhaze.platform.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.flyhaze.framework.mvc.controller.BaseController;
import com.flyhaze.framework.mvc.response.ResponseData;
import com.flyhaze.platform.constants.SysI18nKeyConstants;
import com.flyhaze.platform.enums.AuthTypeEnum;
import com.flyhaze.platform.enums.MenuTypeEnum;
import com.flyhaze.platform.enums.RoleTypeEnum;
import com.flyhaze.platform.enums.SubSystemEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * CategoryController
 *
 * 类型枚举数据转数组返回
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-23 13:20:32
 * @since jdk1.8
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping(path = {"/category"})
public class CategoryController extends BaseController {

    /**
     * 请求分类信息，一般用于下拉框
     * 
     * 参数category值：<br>
     * 分系统枚举：subSystem<br>
     * 菜单分类枚举：menuType<br>
     * 权限分类枚举：authType<br>
     * 
     * @param category 指名请求的是哪种分类信息
     * @return
     */
    @RequestMapping(path = {"/map_list"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<Map<String, String>>> mapList(@RequestParam(name = "category", required = true) String category) {
        List<Map<String, String>> list = Lists.newArrayList();
        Map<String, String> map;
        if ("subSystem".equals(category)) {
            SubSystemEnum[] systems = SubSystemEnum.values();
            for (SubSystemEnum system : systems) {
                map = Maps.newHashMap();
                map.put("name", system.name());
                map.put("text", system.getText());
                list.add(map);
            }
        } else if ("menuType".equals(category)) {
            MenuTypeEnum[] types = MenuTypeEnum.values();
            for (MenuTypeEnum type : types) {
                map = Maps.newHashMap();
                map.put("name", type.name());
                map.put("text", type.getText());
                list.add(map);
            }
        } else if ("authType".equals(category)) {
            AuthTypeEnum[] types = AuthTypeEnum.values();
            for (AuthTypeEnum type : types) {
                map = Maps.newHashMap();
                map.put("name", type.name());
                map.put("text", type.getText());
                list.add(map);
            }
        } else if ("roleType".equals(category)) {
            RoleTypeEnum[] types = RoleTypeEnum.values();
            for (RoleTypeEnum type : types) {
                map = Maps.newHashMap();
                map.put("name", type.name());
                map.put("text", type.getText());
                list.add(map);
            }
        }
        return ResponseData.<List<Map<String, String>>>success(
                getMessage(SysI18nKeyConstants.BASENAME, SysI18nKeyConstants.KEY_CATEGORY), list);
    }
}