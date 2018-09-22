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

import com.flyhaze.framework.mvc.response.ResponseData;
import com.flyhaze.platform.enums.MenuTypeEnum;
import com.flyhaze.platform.enums.SubSystemEnum;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * CategoryController
 *
 * 类型枚举数据转数组返回
 *
 * @author 廖维(EmailTo：liaowei-0627@163.com)
 * @date 2018-04-23 13:20:32
 * @since jdk1.8
 */
@Controller
@RequestMapping(path = {"/category"})
@Slf4j
public class CategoryController {

    /**
     * 请求分类信息，一般用于下拉框
     * 
     * 参数category值： 菜单分类：menu
     * 
     * 
     * @param category 指名请求的是哪种分类信息
     * @return
     */
    @RequestMapping(path = {"/map_list"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseData<List<Map<String, String>>> mapList(@RequestParam(name = "category", required = true) String category) {
        if (Strings.isNullOrEmpty(category)) {
            log.error("ERROR：参数为空");
            return new ResponseData<>(0, "参数为空");
        }
        List<Map<String, String>> list = Lists.newArrayList();
        Map<String, String> map;
        if ("menu".equals(category)) {
            MenuTypeEnum[] types = MenuTypeEnum.values();
            for (MenuTypeEnum type : types) {
                map = Maps.newHashMap();
                map.put("name", type.name());
                map.put("text", type.getText());
                list.add(map);
            }
        } else if ("system".equals(category)) {
            SubSystemEnum[] systems = SubSystemEnum.values();
            for (SubSystemEnum system : systems) {
                map = Maps.newHashMap();
                map.put("name", system.name());
                map.put("text", system.getText());
                list.add(map);
            }
        }
        return new ResponseData<List<Map<String, String>>>(1, "取得菜单类型列表成功", list);
    }
}