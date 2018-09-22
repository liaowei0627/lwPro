/**
 * platform-web
 * EventController.java
 */
package com.flyhaze.platform.controller;

/**
 * EventController
 *
 * 前端服务器事件响应
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 下午6:09:19 
 * @since jdk1.8
 */
//@Controller
//@RequestMapping()
//@Slf4j
public class EventController {

    /**
     * 检查当前是否登录
     * 
     * @param request
     * @param response
     */
//    @RequestMapping(path = {"/checkLogged"})
//    public void checkLogged(HttpServletRequest request, HttpServletResponse response) {
//
//        response.setDateHeader("Expires", -1);
//        response.setHeader("Cache-Control", "no-cache");
//        response.setContentType("text/event-stream");
//
//        ServletOutputStream out = null;
//        try {
//            out = response.getOutputStream();
//            SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("sessionUser");
//            if (null == sessionUser) {
//                out.println("data: " + false);
//            } else {
//                out.println("data: " + true);
//            }
//            out.println();
//            out.flush();
//        } catch (IOException e) {
//            log.error(e.getMessage(), e);
//        } finally {
//            try {
//                if (null != out) {
//                    out.close();
//                }
//            } catch (IOException e) {
//                log.error(e.getMessage(), e);
//            }
//        }
//    }
}