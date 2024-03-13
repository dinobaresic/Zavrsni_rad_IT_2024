package org.eclipse.scout.apps.budgetbuddy.shared.reports;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IAddBillAiOcrService extends IService {
    AddBillAiOcrFormData prepareCreate(AddBillAiOcrFormData formData);

    AddBillAiOcrFormData create(AddBillAiOcrFormData formData);

    AddBillAiOcrFormData load(AddBillAiOcrFormData formData);

    AddBillAiOcrFormData store(AddBillAiOcrFormData formData);
}
