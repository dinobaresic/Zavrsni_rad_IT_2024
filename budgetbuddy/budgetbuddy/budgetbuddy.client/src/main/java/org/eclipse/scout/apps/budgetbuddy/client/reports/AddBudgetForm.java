package org.eclipse.scout.apps.budgetbuddy.client.reports;

import org.eclipse.scout.apps.budgetbuddy.client.reports.AddBudgetForm.MainBox.CancelButton;
import org.eclipse.scout.apps.budgetbuddy.client.reports.AddBudgetForm.MainBox.GroupBox;
import org.eclipse.scout.apps.budgetbuddy.client.reports.AddBudgetForm.MainBox.OkButton;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.AddBudgetFormData;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.CreateAddBudgetPermission;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.IAddBudgetService;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.UpdateAddBudgetPermission;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.WidgetEvent;
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

@FormData(value = AddBudgetFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class AddBudgetForm extends AbstractForm {
    @Override
    protected String getConfiguredTitle() {
// TODO [Dino] verify translation
        return TEXTS.get("AddBudget");
    }

  public GroupBox.DataBox.AmountField getAmountField() {
    return getFieldByClass(GroupBox.DataBox.AmountField.class);
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

  public GroupBox.DataBox getDataBox() {
    return getFieldByClass(GroupBox.DataBox.class);
  }

  public GroupBox.DataBox.NameField getNameField() {
    return getFieldByClass(GroupBox.DataBox.NameField.class);
  }

  @Order(1000)
    public class MainBox extends AbstractGroupBox {
        @Order(1000)
        public class GroupBox extends AbstractGroupBox {

          @Order(1000)
          public class DataBox extends AbstractGroupBox {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Data");
            }

            @Order(1000)
            public class NameField extends AbstractStringField {
              @Override
              protected String getConfiguredLabel() {
                return TEXTS.get("BudgetName");
              }

              @Override
              protected int getConfiguredMaxLength() {
                return 128;
              }
            }

            @Order(2000)
            public class AmountField extends AbstractBigDecimalField {
              @Override
              protected String getConfiguredLabel() {
                return TEXTS.get("Amount");
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
            AddBudgetFormData formData = new AddBudgetFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddBudgetService.class).prepareCreate(formData);
            importFormData(formData);


        }

        @Override
        protected void execStore() {
            AddBudgetFormData formData = new AddBudgetFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddBudgetService.class).create(formData);
            importFormData(formData);
        }
    }

    public class ModifyHandler extends AbstractFormHandler {
        @Override
        protected void execLoad() {
            AddBudgetFormData formData = new AddBudgetFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddBudgetService.class).load(formData);
            importFormData(formData);

        }

        @Override
        protected void execStore() {
            AddBudgetFormData formData = new AddBudgetFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddBudgetService.class).store(formData);
            importFormData(formData);
        }
    }
}