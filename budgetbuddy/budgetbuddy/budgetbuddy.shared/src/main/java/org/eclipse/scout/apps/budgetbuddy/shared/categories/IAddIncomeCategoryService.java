package org.eclipse.scout.apps.budgetbuddy.shared.categories;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IAddIncomeCategoryService extends IService {
    AddIncomeCategoryFormData prepareCreate(AddIncomeCategoryFormData formData);

    AddIncomeCategoryFormData create(AddIncomeCategoryFormData formData);

    AddIncomeCategoryFormData load(AddIncomeCategoryFormData formData);

    AddIncomeCategoryFormData store(AddIncomeCategoryFormData formData);
}
