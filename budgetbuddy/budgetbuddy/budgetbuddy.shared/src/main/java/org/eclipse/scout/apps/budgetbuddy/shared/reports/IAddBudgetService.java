package org.eclipse.scout.apps.budgetbuddy.shared.reports;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IAddBudgetService extends IService {
    AddBudgetFormData prepareCreate(AddBudgetFormData formData);

    AddBudgetFormData create(AddBudgetFormData formData);

    AddBudgetFormData load(AddBudgetFormData formData);

    AddBudgetFormData store(AddBudgetFormData formData);
}
