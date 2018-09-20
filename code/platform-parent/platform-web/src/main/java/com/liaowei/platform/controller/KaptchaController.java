/**
 * platform-web
 * KaptchaController.java
 */
package com.liaowei.platform.controller;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.common.base.Strings;
import com.liaowei.framework.controller.BaseController;
import com.liaowei.framework.core.model.IBasisModel;
import com.liaowei.framework.core.vo.IBasisIdVo;

import lombok.extern.slf4j.Slf4j;

/**
 * KaptchaController
 *
 * 图片验证码
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 上午11:21:32
 * @see com.liaowei.framework.controller.BaseController
 * @since jdk1.8
 */
@SuppressWarnings("rawtypes")
@Controller
@RequestMapping(path = "/kaptcha")
@Slf4j
public class KaptchaController extends BaseController {

    @Resource
    private Producer kaptchaProducer;

    /**
     * 取得验证码图片 输出图片输出流
     * 
     * @param response
     */
    @RequestMapping(path = {"/getKaptchaCode"}, method = RequestMethod.GET)
    public void getKaptchaCode(HttpServletResponse response) {
        // 生成验证码文本
        String kapText = kaptchaProducer.createText();
        setSessionAttr(Constants.KAPTCHA_SESSION_KEY, kapText);
        log.debug("DEBUG：生成验证码文本====" + kapText);
        // 利用生成的字符串构建图片
        BufferedImage bi = kaptchaProducer.createImage(kapText);
        responseJPEG(bi, response);
    }

    /**
     * Easyui validation组建远程验证方法 检查验证码是否正确
     * 
     * @param kaptchaCode
     * @param request
     * @return
     */
    @RequestMapping(path = {"/checkKaptchaCode"}, method = RequestMethod.POST)
    @ResponseBody
    public boolean checkKaptchaCode(@RequestParam(name = "kaptchaCode", required = true) String kaptchaCode) {
        if (Strings.isNullOrEmpty(kaptchaCode)) {
            return false;
        }
        String generateCode = (String) getSessionAttr(Constants.KAPTCHA_SESSION_KEY);
        if (generateCode.equals(kaptchaCode)) {
            log.debug("DEBUG：验证成功");
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected IBasisModel voToModel(IBasisIdVo v) {
        return null;
    }
}