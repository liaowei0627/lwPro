/**
 * platform-web
 * CategoryController.java
 */
package com.liaowei.platform.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liaowei.framework.response.ResponseData;
import com.liaowei.platform.SessionUser;
import com.liaowei.platform.enums.MenuTypeEnum;

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

//    @RequestMapping(path = {"/menu"})
//    @ResponseBody
//    public ResponseData<List<Map<String, String>>> menu() {
//        MenuTypeEnum.
//    }

}