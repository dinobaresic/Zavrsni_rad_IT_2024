package org.eclipse.scout.apps.budgetbuddy.server.categories;

import org.eclipse.scout.apps.budgetbuddy.shared.categories.IIncomeCategoryService;
import org.eclipse.scout.apps.budgetbuddy.shared.categories.IncomeCategoryTablePageData;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class IncomeCategoryService implements IIncomeCategoryService {
    @Override
    public IncomeCategoryTablePageData getIncomeCategoryTableData(SearchFilter filter) {
        IncomeCategoryTablePageData pageData = new IncomeCategoryTablePageData();
      StringBuffer  varname1 = new StringBuffer();
      varname1.append("SELECT id, ");
      varname1.append("       name ");
      varname1.append("FROM   income_categories ");
      varname1.append("WHERE  is_deleted IS false ");
      varname1.append(" INTO   :Id, :Name");
      SQL.selectInto(varname1.toString(), pageData);
        return pageData;
    }
}
