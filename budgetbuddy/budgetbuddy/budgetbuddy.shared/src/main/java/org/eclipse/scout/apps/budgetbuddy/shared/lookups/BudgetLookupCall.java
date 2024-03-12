package org.eclipse.scout.apps.budgetbuddy.shared.lookups;

import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

public class BudgetLookupCall extends LookupCall<Long> {
    private static final long serialVersionUID = 1L;

    @Override
    protected Class<? extends ILookupService<Long>> getConfiguredService() {
        return IBudgetLookupService.class;
    }
}
