package org.eclipse.scout.apps.budgetbuddy.shared.reports;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IAddBillService extends IService {
    AddBillFormData prepareCreate(AddBillFormData formData);

    AddBillFormData create(AddBillFormData formData);

    AddBillFormData load(AddBillFormData formData);

    AddBillFormData store(AddBillFormData formData);
}
