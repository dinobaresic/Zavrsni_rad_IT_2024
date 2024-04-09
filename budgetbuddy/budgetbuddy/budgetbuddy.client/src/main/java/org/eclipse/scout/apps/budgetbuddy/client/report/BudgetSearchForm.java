package org.eclipse.scout.apps.budgetbuddy.client.report;

import org.eclipse.scout.apps.budgetbuddy.client.report.BudgetSearchForm.MainBox.CancelButton;
import org.eclipse.scout.apps.budgetbuddy.client.report.BudgetSearchForm.MainBox.GroupBox;
import org.eclipse.scout.apps.budgetbuddy.client.report.BudgetSearchForm.MainBox.OkButton;
import org.eclipse.scout.apps.budgetbuddy.shared.lookups.BudgetLookupCall;
import org.eclipse.scout.apps.budgetbuddy.shared.lookups.WalletLookupCall;
import org.eclipse.scout.apps.budgetbuddy.shared.report.BudgetSearchFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractSearchForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractResetButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractSearchButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

@FormData(value = BudgetSearchFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class BudgetSearchForm extends AbstractSearchForm {
    @Override
    protected String getConfiguredTitle() {
// TODO [Dino] verify translation
        return TEXTS.get("BudgetSearch");
    }

    public GroupBox.WalletField getBudgetField() {
        return getFieldByClass(GroupBox.WalletField.class);
    }

    public MainBox getMainBox() {
        return getFieldByClass(MainBox.class);
    }

    public GroupBox getGroupBox() {
        return getFieldByClass(GroupBox.class);
    }

    public OkButton getOkButton() {
        return getFieldByClass(OkButton.class);
    }

    public CancelButton getCancelButton() {
        return getFieldByClass(CancelButton.class);
    }

    @Order(1000)
    public class MainBox extends AbstractGroupBox {
        @Order(1000)
        public class GroupBox extends AbstractGroupBox {

            @Order(1000)
            public class WalletField extends AbstractSmartField<Long> {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("WalletName");
                }

                @Override
                protected boolean getConfiguredMandatory() {
                    return true;
                }

                @Override
                protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
                    return WalletLookupCall.class;
                }

            }


        }

        @Order(2000)
        public class OkButton extends AbstractSearchButton {

        }

        @Order(3000)
        public class CancelButton extends AbstractResetButton {

        }
    }

    public void startModify() {
        startInternalExclusive(new ModifyHandler());
    }

    public void startNew() {
        startInternal(new NewHandler());
    }

    public class NewHandler extends AbstractFormHandler {
        @Override
        protected void execLoad() {


        }

        @Override
        protected void execStore() {

        }
    }

    public class ModifyHandler extends AbstractFormHandler {
        @Override
        protected void execLoad() {

        }

        @Override
        protected void execStore() {

        }
    }
}
