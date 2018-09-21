/**
 * framework-mvc
 * SessionUser.java
 */
package com.flyhaze.framework;

import java.io.Serializable;
import java.util.List;

import com.flyhaze.framework.entity.BaseTreeEntity;
import com.flyhaze.framework.model.BaseTreeModel;
import com.flyhaze.framework.view.TreeView;
import com.flyhaze.framework.vo.BaseTreeVo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * SessionUser
 *
 * 当前登录用户对象
 *
 * @author liaowei
 * @date 创建时间：2018年4月6日 下午6:14:17
 * @see java.io.Serializable
 * @since jdk1.8
 */
@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SessionUser<E extends BaseTreeEntity<E>, V extends BaseTreeVo<E, V>, M extends BaseTreeModel<E, V, M>>
        implements Serializable {

    private String id;
    private String userName;
    private String siteCode;
    private List<TreeView<E, V, M>> menuList;
}