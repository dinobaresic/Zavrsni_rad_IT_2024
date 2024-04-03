package org.eclipse.scout.apps.budgetbuddy.client.wallet;

import org.eclipse.scout.apps.budgetbuddy.client.wallet.AddWalletForm.MainBox.CancelButton;
import org.eclipse.scout.apps.budgetbuddy.client.wallet.AddWalletForm.MainBox.GroupBox;
import org.eclipse.scout.apps.budgetbuddy.client.wallet.AddWalletForm.MainBox.OkButton;
import org.eclipse.scout.apps.budgetbuddy.shared.wallet.AddWalletFormData;
import org.eclipse.scout.apps.budgetbuddy.shared.wallet.CreateAddWalletPermission;
import org.eclipse.scout.apps.budgetbuddy.shared.wallet.IAddWalletService;
import org.eclipse.scout.apps.budgetbuddy.shared.wallet.UpdateAddWalletPermission;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.bigdecimalfield.AbstractBigDecimalField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

import java.math.BigDecimal;

@FormData(value = AddWalletFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class AddWalletForm extends AbstractForm {
    @Override
    protected String getConfiguredTitle() {
// TODO [Dino] verify translation
        return TEXTS.get("AddWallet");
    }

    public GroupBox.BalanceField getBalanceField() {
        return getFieldByClass(GroupBox.BalanceField.class);
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

    public GroupBox.NameField getNameField() {
        return getFieldByClass(GroupBox.NameField.class);
    }
    

    @Order(1000)
    public class MainBox extends AbstractGroupBox {
        @Order(1000)
        public class GroupBox extends AbstractGroupBox {

            @Order(1000)
            public class NameField extends AbstractStringField {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("WalletName");
                }

                @Override
                protected int getConfiguredMaxLength() {
                    return 128;
                }
            }

            @Order(2000)
            public class BalanceField extends AbstractBigDecimalField {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("StartingBalance");
                }

                @Override
                protected BigDecimal getConfiguredMinValue() {
                    return new BigDecimal("-999999999999999999");
                }

                @Override
                protected BigDecimal getConfiguredMaxValue() {
                    return new BigDecimal("999999999999999999");
                }
            }



        }

        @Order(2000)
        public class OkButton extends AbstractOkButton {

        }

        @Order(3000)
        public class CancelButton extends AbstractCancelButton {

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
            AddWalletFormData formData = new AddWalletFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddWalletService.class).prepareCreate(formData);
            importFormData(formData);
        }

        @Override
        protected void execStore() {
            AddWalletFormData formData = new AddWalletFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddWalletService.class).create(formData);
            importFormData(formData);
        }
    }

    public class ModifyHandler extends AbstractFormHandler {
        @Override
        protected void execLoad() {
            AddWalletFormData formData = new AddWalletFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddWalletService.class).load(formData);
            importFormData(formData);
        }

        @Override
        protected void execStore() {
            AddWalletFormData formData = new AddWalletFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddWalletService.class).store(formData);
            importFormData(formData);
        }
    }
}
