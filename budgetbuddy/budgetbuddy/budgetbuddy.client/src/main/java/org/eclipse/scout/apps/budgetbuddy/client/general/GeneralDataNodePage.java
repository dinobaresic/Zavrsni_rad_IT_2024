package org.eclipse.scout.apps.budgetbuddy.client.general;


import org.eclipse.scout.apps.budgetbuddy.shared.Icons;
import org.eclipse.scout.apps.budgetbuddy.shared.general.GeneralDataTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.platform.text.TEXTS;


@Data(GeneralDataTablePageData.class)
public class GeneralDataNodePage extends AbstractPageWithNodes {
    @Override
    protected boolean getConfiguredLeaf() {
        return true;
    }

    @Override
    protected String getConfiguredTitle() {
        return TEXTS.get("GeneralData");
    }

    @Override
    protected String getConfiguredIconId() {
        return Icons.PersonSolid;
    }

    @Override
    protected String getConfiguredOverviewIconId() {
        return Icons.PersonSolid;
    }

    @Override
    protected void execPageActivated() {
        if(getDetailForm() == null) {
            GeneralDataForm form = new GeneralDataForm();
            setDetailForm(form);
            form.start();
        }
    }

    @Override
    protected void execPageDeactivated() {
        if(getDetailForm() != null) {
            getDetailForm().doClose();
            setDetailForm(null);
        }
    }
}
