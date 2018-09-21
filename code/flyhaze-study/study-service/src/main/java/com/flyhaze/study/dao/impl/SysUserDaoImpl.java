package com.flyhaze.study.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.flyhaze.framework.dao.impl.DaoImpl;
import com.flyhaze.study.dao.ISysUserDao;
import com.flyhaze.study.entity.SysUser;
import com.google.common.collect.Lists;

@Repository("sysUserDao")
public class SysUserDaoImpl extends DaoImpl<SysUser> implements ISysUserDao {

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

    protected Class<SysUser> getEntityClass() {
        return SysUser.class;
    }

    @Override
    public SysUser findByUserName(String userName) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<SysUser> criteria = criteriaBuilder.createQuery(SysUser.class);
        Root<SysUser> root = criteria.from(SysUser.class);
        criteria.select(root);
        List<Predicate> predicateList = Lists.newArrayList();
        predicateList.add(criteriaBuilder.equal(root.get("userName"), userName));
        predicateList.add(criteriaBuilder.equal(root.get("valid"), Boolean.TRUE));
        criteria.where(criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()])));
        SysUser sysUser = session.createQuery(criteria).uniqueResult();
        return sysUser;
    }
}