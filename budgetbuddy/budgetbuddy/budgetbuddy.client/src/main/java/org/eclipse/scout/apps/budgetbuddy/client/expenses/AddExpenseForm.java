package org.eclipse.scout.apps.budgetbuddy.client.expenses;

import org.eclipse.scout.apps.budgetbuddy.client.expenses.AddExpenseForm.MainBox.CancelButton;
import org.eclipse.scout.apps.budgetbuddy.client.expenses.AddExpenseForm.MainBox.GroupBox;
import org.eclipse.scout.apps.budgetbuddy.client.expenses.AddExpenseForm.MainBox.OkButton;
import org.eclipse.scout.apps.budgetbuddy.client.informations.MessageBoxHelper;
import org.eclipse.scout.apps.budgetbuddy.client.reports.AddBillForm;
import org.eclipse.scout.apps.budgetbuddy.shared.expenses.AddExpenseFormData;
import org.eclipse.scout.apps.budgetbuddy.shared.expenses.CreateAddExpensePermission;
import org.eclipse.scout.apps.budgetbuddy.shared.expenses.IAddExpenseService;
import org.eclipse.scout.apps.budgetbuddy.shared.expenses.UpdateAddExpensePermission;
import org.eclipse.scout.apps.budgetbuddy.shared.lookups.BudgetLookupCall;
import org.eclipse.scout.apps.budgetbuddy.shared.lookups.CategoriesLookupCall;
import org.eclipse.scout.apps.budgetbuddy.shared.lookups.WalletLookupCall;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.IBillsService;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.bigdecimalfield.AbstractBigDecimalField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;

import java.math.BigDecimal;

@FormData(value = AddExpenseFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class AddExpenseForm extends AbstractForm {
    @Override
    protected String getConfiguredTitle() {
// TODO [Dino] verify translation
        return TEXTS.get("AddExpense");
    }

    public GroupBox.AmountField getAmountField() {
        return getFieldByClass(GroupBox.AmountField.class);
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

    public GroupBox.CategoryField getCategoryField() {
        return getFieldByClass(GroupBox.CategoryField.class);
    }

    public GroupBox.DateField getDateField() {
        return getFieldByClass(GroupBox.DateField.class);
    }

    public GroupBox.NameField getNameField() {
        return getFieldByClass(GroupBox.NameField.class);
    }

  public GroupBox.WalletField getWalletField() {
    return getFieldByClass(GroupBox.WalletField.class);
  }

  @Order(1000)
    public class MainBox extends AbstractGroupBox {
        @Order(1000)
        public class GroupBox extends AbstractGroupBox {

            @Order(1000)
            public class NameField extends AbstractStringField {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("Name0");
                }

                @Override
                protected int getConfiguredMaxLength() {
                    return 128;
                }
            }

            @Order(2000)
            public class DateField extends AbstractDateField {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("Date");
                }

                @Override
                protected boolean getConfiguredMandatory() {
                    return true;
                }

                @Override
                protected String getConfiguredFormat() {
                    return "dd.MM.yyyy";
                }
            }



            @Order(3000)
            public class CategoryField extends AbstractSmartField<Long> {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("Category");
                }

                @Override
                protected boolean getConfiguredMandatory() {
                    return true;
                }

                @Override
                protected Class<? extends ILookupCall<Long>> getConfiguredLookupCall() {
                    return CategoriesLookupCall.class;
                }
            }

            @Order(4000)
            public class AmountField extends AbstractBigDecimalField {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("Amount");
                }

                @Override
                protected boolean getConfiguredMandatory() {
                    return true;
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

          @Order(4500)
          public class WalletField extends AbstractSmartField<Long> {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("WalletTablePage");
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
            AddExpenseFormData formData = new AddExpenseFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddExpenseService.class).prepareCreate(formData);
            importFormData(formData);

        }

        @Override
        protected void execStore() {
            AddExpenseFormData formData = new AddExpenseFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddExpenseService.class).create(formData);
            importFormData(formData);
        }
    }

    public class ModifyHandler extends AbstractFormHandler {
        @Override
        protected void execLoad() {
            AddExpenseFormData formData = new AddExpenseFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddExpenseService.class).load(formData);
            importFormData(formData);

        }

        @Override
        protected void execStore() {
            AddExpenseFormData formData = new AddExpenseFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddExpenseService.class).store(formData);
            importFormData(formData);
        }
    }
}
