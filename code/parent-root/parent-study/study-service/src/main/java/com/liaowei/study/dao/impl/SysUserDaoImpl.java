package com.liaowei.study.dao.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.liaowei.study.dao.ISysUserDao;
import com.liaowei.study.entity.SysUser;

@Repository("sysUserDao")
public class SysUserDaoImpl implements ISysUserDao {

	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	@Override
	public void addUser(SysUser sysUser) {
		sessionFactory.getCurrentSession().save(sysUser);
	}

	@Override
	public SysUser findUser(String userId) {
		return sessionFactory.getCurrentSession().get(SysUser.class, userId);
	}

	@Override
	public List<SysUser> findList(SysUser sysUser) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<SysUser> criteria = criteriaBuilder.createQuery(SysUser.class);
		Root<SysUser> root = criteria.from(SysUser.class);
		criteria.select(root);
		List<Predicate> predicateList = Lists.newArrayList();
		String userName = sysUser.getUserName();
		if (!Strings.isNullOrEmpty(userName)) {
			predicateList.add(criteriaBuilder.equal(root.get("userName"), userName));
		}
		String password = sysUser.getPassword();
		if (!Strings.isNullOrEmpty(password)) {
			predicateList.add(criteriaBuilder.equal(root.get("password"), password));
		}
		criteria = criteria.where(criteriaBuilder.and((Predicate[]) predicateList.toArray()));
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public void delList(List<String> userIds) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaDelete<SysUser> criteria = criteriaBuilder.createCriteriaDelete(SysUser.class);
		Root<SysUser> root = criteria.from(SysUser.class);
		Expression<String> exp = root.get("id");
		Predicate predicate = exp.in(userIds);
		criteria = criteria.where(predicate);
		session.createQuery(criteria).executeUpdate();
	}
}