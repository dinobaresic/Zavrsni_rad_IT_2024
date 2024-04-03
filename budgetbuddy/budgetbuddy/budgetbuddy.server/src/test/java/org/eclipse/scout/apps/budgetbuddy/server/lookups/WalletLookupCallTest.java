package org.eclipse.scout.apps.budgetbuddy.server.lookups;

import org.eclipse.scout.apps.budgetbuddy.shared.lookups.WalletLookupCall;
import org.eclipse.scout.rt.server.IServerSession;
import org.eclipse.scout.rt.shared.services.lookup.ILookupRow;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.eclipse.scout.rt.testing.server.runner.RunWithServerSession;
import org.eclipse.scout.rt.testing.server.runner.ServerTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWithSubject("anonymous")
@RunWith(ServerTestRunner.class)
@RunWithServerSession(IServerSession.class)
public class WalletLookupCallTest {
// TODO [Dino] add test cases

    protected WalletLookupCall createLookupCall() {
        return new WalletLookupCall();
    }

    @Test
    public void testGetDataByAll() {
        WalletLookupCall call = createLookupCall();
// TODO [Dino] fill call
        List<? extends ILookupRow<Long>> data = call.getDataByAll();
// TODO [Dino] verify data
    }

    @Test
    public void testGetDataByKey() {
        WalletLookupCall call = createLookupCall();
// TODO [Dino] fill call
        List<? extends ILookupRow<Long>> data = call.getDataByKey();
// TODO [Dino] verify data
    }

    @Test
    public void testGetDataByText() {
        WalletLookupCall call = createLookupCall();
// TODO [Dino] fill call
        List<? extends ILookupRow<Long>> data = call.getDataByText();
// TODO [Dino] verify data
    }
}
