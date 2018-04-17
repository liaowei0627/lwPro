/**
 * platform-web
 * KaptchaController.java
 */
package com.liaowei.platform.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.common.base.Strings;

import lombok.extern.slf4j.Slf4j;

/**
 * KaptchaController
 *
 * 图片验证码
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 上午11:21:32 
 * @since jdk1.8
 */
@Controller
@RequestMapping(path="/kaptcha")
@Slf4j
public class KaptchaController {

    @Resource
    private Producer kaptchaProducer;

    /**
     * 取得验证码图片
     * 输出图片输出流
     * 
     * @param request
     * @param response
     */
    @RequestMapping(path = {"/getKaptchaCode"})
    public void getKaptchaCode(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        ServletOutputStream out = null;
        try {
            // 生成验证码文本
            String kapText = kaptchaProducer.createText();
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, kapText);
            log.debug("生成验证码文本====" + kapText);
            // 利用生成的字符串构建图片
            BufferedImage bi = kaptchaProducer.createImage(kapText);
            out = response.getOutputStream();
            ImageIO.write(bi, "jpg", out);
            out.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (null != out) {
                    out.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Easyui validation组建远程验证方法
     * 检查验证码是否正确
     * 
     * @param kaptchaCode
     * @param request
     * @return
     */
    @RequestMapping(path = {"/checkKaptchaCode"})
    @ResponseBody
    public boolean checkKaptchaCode(
            @RequestParam(name = "kaptchaCode", required = true) String kaptchaCode,
            HttpServletRequest request){
        if (Strings.isNullOrEmpty(kaptchaCode)) {
            return false;
        }
        String generateCode =(String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(generateCode.equals(kaptchaCode)){
            log.info("验证成功");
            return true;
        }else{
            return false;
        }
    }
}