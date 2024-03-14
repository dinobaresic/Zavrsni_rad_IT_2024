package org.eclipse.scout.apps.budgetbuddy.shared.expenses;

import org.eclipse.scout.rt.security.AbstractPermission;

public class ReadAddExpensePermission extends AbstractPermission {
    private static final long serialVersionUID = 1L;

    public ReadAddExpensePermission() {
        super("ReadAddExpensePermission");
    }
}
