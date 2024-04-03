package org.eclipse.scout.apps.budgetbuddy.client.general;

import org.eclipse.scout.apps.budgetbuddy.client.general.GeneralDataSearchForm.MainBox.CancelButton;
import org.eclipse.scout.apps.budgetbuddy.client.general.GeneralDataSearchForm.MainBox.GroupBox;
import org.eclipse.scout.apps.budgetbuddy.client.general.GeneralDataSearchForm.MainBox.OkButton;
import org.eclipse.scout.apps.budgetbuddy.shared.general.GeneralDataFormData;
import org.eclipse.scout.apps.budgetbuddy.shared.lookups.WalletLookupCall;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractSearchForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractResetButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractSearchButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.quartz.impl.calendar.MonthlyCalendar;

import java.lang.reflect.Field;

@FormData(value = GeneralDataFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class GeneralDataSearchForm extends AbstractSearchForm {
    @Override
    protected String getConfiguredTitle() {
// TODO [Dino] verify translation
        return TEXTS.get("GeneralDataSearch");
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

    public GroupBox.PeriodBox.FromField getFromField() {
        return getFieldByClass(GroupBox.PeriodBox.FromField.class);
    }

    public GroupBox.PeriodBox getPeriodBox() {
        return getFieldByClass(GroupBox.PeriodBox.class);
    }

    public GroupBox.PeriodBox.ToField getToField() {
        return getFieldByClass(GroupBox.PeriodBox.ToField.class);
    }


    public GroupBox.WalletField getWalletField() {
        return getFieldByClass(GroupBox.WalletField.class);
    }

    @Order(1000)
    public class MainBox extends AbstractGroupBox {
        @Order(1000)
        public class GroupBox extends AbstractGroupBox {


            @Order(1000)
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

            @Order(2000)
            public class PeriodBox extends AbstractSequenceBox {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("Period");
                }

                @Override
                protected boolean getConfiguredAutoCheckFromTo() {
                    return true;
                }

                @Order(1000)
                public class FromField extends AbstractDateField {
                    @Override
                    protected String getConfiguredLabel() {
                        return TEXTS.get("From0");
                    }

                    @Override
                    protected String getConfiguredFormat() {
                        return "dd.MM.yyyy";
                    }
                }

                @Order(2000)
                public class ToField extends AbstractDateField {
                    @Override
                    protected String getConfiguredLabel() {
                        return TEXTS.get("To");
                    }

                    @Override
                    protected String getConfiguredFormat() {
                        return "dd.MM.yyyy";
                    }
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
