package org.eclipse.scout.apps.budgetbuddy.server.categories;

import org.eclipse.scout.apps.budgetbuddy.shared.categories.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class AddCategoryService implements IAddCategoryService {
    @Override
    public AddCategoryFormData prepareCreate(AddCategoryFormData formData) {

        return formData;
    }

    @Override
    public AddCategoryFormData create(AddCategoryFormData formData) {
        String stmt = "INSERT INTO categories (name) VALUES (:Name)";
        SQL.insert(stmt, formData);
        return formData;
    }

    @Override
    public AddCategoryFormData load(AddCategoryFormData formData) {

        return formData;
    }

    @Override
    public AddCategoryFormData store(AddCategoryFormData formData) {

        return formData;
    }
}
