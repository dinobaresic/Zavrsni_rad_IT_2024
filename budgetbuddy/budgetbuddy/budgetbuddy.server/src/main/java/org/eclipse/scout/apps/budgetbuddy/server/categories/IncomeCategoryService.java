package org.eclipse.scout.apps.budgetbuddy.server.categories;

import org.eclipse.scout.apps.budgetbuddy.shared.categories.IIncomeCategoryService;
import org.eclipse.scout.apps.budgetbuddy.shared.categories.IncomeCategoryTablePageData;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class IncomeCategoryService implements IIncomeCategoryService {
    @Override
    public IncomeCategoryTablePageData getIncomeCategoryTableData(SearchFilter filter) {
        IncomeCategoryTablePageData pageData = new IncomeCategoryTablePageData();
// TODO [Dino] fill pageData.
        return pageData;
    }
}
