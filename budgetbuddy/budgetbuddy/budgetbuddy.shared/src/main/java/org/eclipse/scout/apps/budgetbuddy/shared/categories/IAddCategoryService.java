package org.eclipse.scout.apps.budgetbuddy.shared.categories;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IAddCategoryService extends IService {
    AddCategoryFormData prepareCreate(AddCategoryFormData formData);

    AddCategoryFormData create(AddCategoryFormData formData);

    AddCategoryFormData load(AddCategoryFormData formData);

    AddCategoryFormData store(AddCategoryFormData formData);
}
