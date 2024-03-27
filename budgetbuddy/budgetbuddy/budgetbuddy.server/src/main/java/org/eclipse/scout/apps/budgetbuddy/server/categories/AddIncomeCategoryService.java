package org.eclipse.scout.apps.budgetbuddy.server.categories;

import org.eclipse.scout.apps.budgetbuddy.shared.categories.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class AddIncomeCategoryService implements IAddIncomeCategoryService {
    @Override
    public AddIncomeCategoryFormData prepareCreate(AddIncomeCategoryFormData formData) {

        return formData;
    }

    @Override
    public AddIncomeCategoryFormData create(AddIncomeCategoryFormData formData) {
      String stmt = "INSERT INTO income_categories (name) VALUES (:Name)";
      SQL.insert(stmt, formData);
        return formData;
    }

    @Override
    public AddIncomeCategoryFormData load(AddIncomeCategoryFormData formData) {

        return formData;
    }

    @Override
    public AddIncomeCategoryFormData store(AddIncomeCategoryFormData formData) {

        return formData;
    }
}
