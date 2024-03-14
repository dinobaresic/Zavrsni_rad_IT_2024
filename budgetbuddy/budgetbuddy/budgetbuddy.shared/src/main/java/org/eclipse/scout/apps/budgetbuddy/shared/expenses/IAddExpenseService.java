package org.eclipse.scout.apps.budgetbuddy.shared.expenses;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IAddExpenseService extends IService {
    AddExpenseFormData prepareCreate(AddExpenseFormData formData);

    AddExpenseFormData create(AddExpenseFormData formData);

    AddExpenseFormData load(AddExpenseFormData formData);

    AddExpenseFormData store(AddExpenseFormData formData);
}
