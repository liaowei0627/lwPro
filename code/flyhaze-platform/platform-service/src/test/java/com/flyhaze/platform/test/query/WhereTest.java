package com.flyhaze.platform.test.query;

import org.junit.Test;

import com.flyhaze.core.query.Where;
import com.flyhaze.core.query.operator.OneValueComparisonOperator;
import com.flyhaze.core.query.operator.TwoValueComparisonOperator;
import com.flyhaze.framework.hibernate.query.QueryUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WhereTest {

    @Test
    public void test() {
        try {
            Where where = Where.rootWhere("a", OneValueComparisonOperator.EQ, "b");
            where.andWhere("c", OneValueComparisonOperator.EQ, "d");
            Where child = where.childAndWhere("e", OneValueComparisonOperator.EQ, "f");
            child.andWhere("g", OneValueComparisonOperator.EQ, "h");
            where.childOrWhere("i", TwoValueComparisonOperator.BETWEEN, "j", "k");
            log.info(QueryUtils.toWhereClause(where).toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}