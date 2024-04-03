package org.eclipse.scout.apps.budgetbuddy.shared.wallet;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IAddWalletService extends IService {
    AddWalletFormData prepareCreate(AddWalletFormData formData);

    AddWalletFormData create(AddWalletFormData formData);

    AddWalletFormData load(AddWalletFormData formData);

    AddWalletFormData store(AddWalletFormData formData);
}
