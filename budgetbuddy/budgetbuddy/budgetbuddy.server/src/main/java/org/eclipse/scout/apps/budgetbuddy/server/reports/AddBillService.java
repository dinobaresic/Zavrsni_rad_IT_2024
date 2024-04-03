package org.eclipse.scout.apps.budgetbuddy.server.reports;

import org.eclipse.scout.apps.budgetbuddy.shared.reports.*;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.IntegerHolder;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.holders.StringHolder;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.security.ACCESS;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class AddBillService implements IAddBillService {
    @Override
    public AddBillFormData prepareCreate(AddBillFormData formData) {

        return formData;
    }

    @Override
    public AddBillFormData create(AddBillFormData formData) {

      String stmt = "INSERT INTO bills (name, address, taxamount, price, date, wallet_id) VALUES (:Name, :Address, :TaxAmount, :Amount, :Date, :Wallet)";
      SQL.insert(stmt, formData);

      String stmt1 = "Update wallet set balance = balance - :Amount, last_used = now() where id = :Wallet";
      SQL.update(stmt1, formData);

        return formData;
    }



    @Override
    public AddBillFormData load(AddBillFormData formData) {

        return formData;
    }

    @Override
    public AddBillFormData store(AddBillFormData formData) {

        return formData;
    }
}
