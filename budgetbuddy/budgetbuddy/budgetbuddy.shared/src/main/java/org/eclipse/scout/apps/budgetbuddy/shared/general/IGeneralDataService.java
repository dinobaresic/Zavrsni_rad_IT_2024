package org.eclipse.scout.apps.budgetbuddy.shared.general;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IGeneralDataService extends IService {
    GeneralDataTablePageData getGeneralDataTableData(SearchFilter filter);

    GeneralDataFormData prepareCreate(GeneralDataFormData formData);

    GeneralDataFormData create(GeneralDataFormData formData);

    GeneralDataFormData load(GeneralDataFormData formData);

    GeneralDataFormData store(GeneralDataFormData formData);
}
