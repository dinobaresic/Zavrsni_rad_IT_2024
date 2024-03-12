package org.eclipse.scout.apps.budgetbuddy.client.reports;

import org.eclipse.scout.apps.budgetbuddy.client.reports.AddBillForm.MainBox.CancelButton;
import org.eclipse.scout.apps.budgetbuddy.client.reports.AddBillForm.MainBox.GroupBox;
import org.eclipse.scout.apps.budgetbuddy.client.reports.AddBillForm.MainBox.OkButton;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.AddBillFormData;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.CreateAddBillPermission;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.IAddBillService;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.UpdateAddBillPermission;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.bigdecimalfield.AbstractBigDecimalField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

import java.lang.reflect.Field;
import java.math.BigDecimal;

@FormData(value = AddBillFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class AddBillForm extends AbstractForm {
    @Override
    protected String getConfiguredTitle() {
        return TEXTS.get("AddBill");
    }

  public GroupBox.DataFieldsBox.AddressField getAddressField() {
    return getFieldByClass(GroupBox.DataFieldsBox.AddressField.class);
  }

  public GroupBox.DataFieldsBox.AmountField getAmountField() {
    return getFieldByClass(GroupBox.DataFieldsBox.AmountField.class);
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

  public GroupBox.DataFieldsBox getDataFieldsBox() {
    return getFieldByClass(GroupBox.DataFieldsBox.class);
  }

  public GroupBox.DataFieldsBox.DateField getDateField() {
    return getFieldByClass(GroupBox.DataFieldsBox.DateField.class);
  }

  public GroupBox.DataFieldsBox.NameField getNameField() {
    return getFieldByClass(GroupBox.DataFieldsBox.NameField.class);
  }

  public GroupBox.DataFieldsBox.TaxAmountField getTaxAmountField() {
    return getFieldByClass(GroupBox.DataFieldsBox.TaxAmountField.class);
  }

  @Order(1000)
    public class MainBox extends AbstractGroupBox {
        @Order(1000)
        public class GroupBox extends AbstractGroupBox {

          @Order(1000)
          public class DataFieldsBox extends AbstractGroupBox {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Data");
            }

            @Order(1000)
            public class NameField extends AbstractStringField {
              @Override
              protected String getConfiguredLabel() {
                return TEXTS.get("MerchantName");
              }

              @Override
              protected int getConfiguredMaxLength() {
                return 128;
              }
            }

            @Order(2000)
            public class AddressField extends AbstractStringField {
              @Override
              protected String getConfiguredLabel() {
                return TEXTS.get("MerchantAddress");
              }

              @Override
              protected int getConfiguredMaxLength() {
                return 128;
              }
            }

            @Order(3000)
            public class TaxAmountField extends AbstractBigDecimalField {
              @Override
              protected String getConfiguredLabel() {
                return TEXTS.get("TaxAmount0");
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


            @Order(4000)
            public class AmountField extends AbstractBigDecimalField {
              @Override
              protected String getConfiguredLabel() {
                return TEXTS.get("TotalPrice");
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

            @Order(5000)
            public class DateField extends AbstractDateField {
              @Override
              protected String getConfiguredLabel() {
                return TEXTS.get("Date");
              }

              @Override
              protected String getConfiguredFormat() {
                return "dd.MM.yyyy";
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
            AddBillFormData formData = new AddBillFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddBillService.class).prepareCreate(formData);
            importFormData(formData);
        }

        @Override
        protected void execStore() {
            AddBillFormData formData = new AddBillFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddBillService.class).create(formData);
            importFormData(formData);
        }
    }

    public class ModifyHandler extends AbstractFormHandler {
        @Override
        protected void execLoad() {
            AddBillFormData formData = new AddBillFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddBillService.class).load(formData);
            importFormData(formData);
        }

        @Override
        protected void execStore() {
            AddBillFormData formData = new AddBillFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddBillService.class).store(formData);
            importFormData(formData);
        }
    }
}