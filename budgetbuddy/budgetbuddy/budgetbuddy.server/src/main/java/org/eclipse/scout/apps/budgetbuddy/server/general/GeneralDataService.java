package org.eclipse.scout.apps.budgetbuddy.server.general;

import org.eclipse.scout.apps.budgetbuddy.shared.general.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class GeneralDataService implements IGeneralDataService {
    @Override
    public GeneralDataTablePageData getGeneralDataTableData(SearchFilter filter) {

        GeneralDataTablePageData pageData = new GeneralDataTablePageData();
// TODO [Dino] fill pageData.
        return pageData;
    }

    @Override
    public GeneralDataFormData prepareCreate(GeneralDataFormData formData) {
        if (!ACCESS.check(new CreateGeneralDataPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [Dino] add business logic here.
        return formData;
    }

    @Override
    public GeneralDataFormData create(GeneralDataFormData formData) {
        if (!ACCESS.check(new CreateGeneralDataPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [Dino] add business logic here.
        return formData;
    }

    @Override
    public GeneralDataFormData load(GeneralDataFormData formData) {
        if (!ACCESS.check(new ReadGeneralDataPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [Dino] add business logic here.
        return formData;
    }

    @Override
    public GeneralDataFormData store(GeneralDataFormData formData) {
        if (!ACCESS.check(new UpdateGeneralDataPermission())) {
            throw new VetoException(TEXTS.get("AuthorizationFailed"));
        }
// TODO [Dino] add business logic here.
        return formData;
    }
}
