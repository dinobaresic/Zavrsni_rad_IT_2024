package org.eclipse.scout.apps.budgetbuddy.shared.reports;

import org.eclipse.scout.rt.security.AbstractPermission;

public class ReadAddBudgetPermission extends AbstractPermission {
    private static final long serialVersionUID = 1L;

    public ReadAddBudgetPermission() {
        super("ReadAddBudgetPermission");
    }
}