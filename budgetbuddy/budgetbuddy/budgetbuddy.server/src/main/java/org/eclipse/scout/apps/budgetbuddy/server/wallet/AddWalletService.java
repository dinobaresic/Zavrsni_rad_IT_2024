package org.eclipse.scout.apps.budgetbuddy.server.wallet;

import org.eclipse.scout.apps.budgetbuddy.shared.wallet.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class AddWalletService implements IAddWalletService {
    @Override
    public AddWalletFormData prepareCreate(AddWalletFormData formData) {

        return formData;
    }

    @Override
    public AddWalletFormData create(AddWalletFormData formData) {

        String stmt = "INSERT INTO wallet (name, balance) VALUES (:Name, :Balance)";
        SQL.insert(stmt, formData);
        return formData;
    }

    @Override
    public AddWalletFormData load(AddWalletFormData formData) {


        return formData;
    }

    @Override
    public AddWalletFormData store(AddWalletFormData formData) {

        return formData;
    }
}
