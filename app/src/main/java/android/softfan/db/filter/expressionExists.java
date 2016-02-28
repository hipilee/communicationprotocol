
package android.softfan.db.filter;

import java.util.Iterator;
import java.util.List;

import android.softfan.db.wf_Db_Exception;

public class expressionExists extends expressionArray {

	public expressionExists() {
	}

	public expressionExists(String sql) {
		this.value = sql;
	}

	public void build(StringBuffer where_sql, List<IConditionParam> conditionParamList) throws wf_Db_Exception {
		where_sql.append("(exists (");
		where_sql.append(value.toString());
		{
			if (getExpressions().size() > 0) {
				boolean first = true;
				where_sql.append(" where ");
				for (Iterator<expressionBase> i = getExpressions().iterator(); i.hasNext();) {
					if (first) {
						first = false;
					} else {
						where_sql.append(" and ");
					}
					expressionBase expression = i.next();
					expression.build(where_sql, conditionParamList);
				}
			}
		}
		where_sql.append("))");
	}

	public expressionBase getCopy() {
		expressionExists expression = new expressionExists();
		expression.setValue(this.getValue());
		if (getExpressions() != null) {
			for (Iterator<expressionBase> i = getExpressions().iterator(); i.hasNext();) {
				expressionBase base =  i.next();
				expression.add(base.getCopy());
			}
		}
		return expression;
	}
}
