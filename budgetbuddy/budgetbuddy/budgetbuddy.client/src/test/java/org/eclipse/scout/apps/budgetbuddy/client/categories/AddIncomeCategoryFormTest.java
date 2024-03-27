package org.eclipse.scout.apps.budgetbuddy.client.categories;

import org.eclipse.scout.apps.budgetbuddy.shared.categories.AddIncomeCategoryFormData;
import org.eclipse.scout.apps.budgetbuddy.shared.categories.IAddIncomeCategoryService;
import org.eclipse.scout.rt.client.testenvironment.TestEnvironmentClientSession;
import org.eclipse.scout.rt.testing.client.runner.ClientTestRunner;
import org.eclipse.scout.rt.testing.client.runner.RunWithClientSession;
import org.eclipse.scout.rt.testing.platform.mock.BeanMock;
import org.eclipse.scout.rt.testing.platform.runner.RunWithSubject;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

@RunWithSubject("anonymous")
@RunWith(ClientTestRunner.class)
@RunWithClientSession(TestEnvironmentClientSession.class)
public class AddIncomeCategoryFormTest {
    @BeanMock
    private IAddIncomeCategoryService m_mockSvc;
// TODO [Dino] add test cases

    @Before
    public void setup() {
        AddIncomeCategoryFormData answer = new AddIncomeCategoryFormData();
        Mockito.when(m_mockSvc.prepareCreate(ArgumentMatchers.any())).thenReturn(answer);
        Mockito.when(m_mockSvc.create(ArgumentMatchers.any())).thenReturn(answer);
        Mockito.when(m_mockSvc.load(ArgumentMatchers.any())).thenReturn(answer);
        Mockito.when(m_mockSvc.store(ArgumentMatchers.any())).thenReturn(answer);
    }
}
