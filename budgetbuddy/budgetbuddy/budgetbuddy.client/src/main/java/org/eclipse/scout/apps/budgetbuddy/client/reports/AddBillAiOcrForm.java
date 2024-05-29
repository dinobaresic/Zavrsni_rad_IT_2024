package org.eclipse.scout.apps.budgetbuddy.client.reports;

import org.eclipse.scout.apps.budgetbuddy.client.informations.MessageBoxHelper;
import org.eclipse.scout.apps.budgetbuddy.client.reports.AddBillAiOcrForm.MainBox.CancelButton;
import org.eclipse.scout.apps.budgetbuddy.client.reports.AddBillAiOcrForm.MainBox.GroupBox;
import org.eclipse.scout.apps.budgetbuddy.client.reports.AddBillAiOcrForm.MainBox.OkButton;
import org.eclipse.scout.apps.budgetbuddy.shared.lookups.BudgetLookupCall;
import org.eclipse.scout.apps.budgetbuddy.shared.lookups.WalletLookupCall;
import org.eclipse.scout.apps.budgetbuddy.shared.reports.*;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.bigdecimalfield.AbstractBigDecimalField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.filechooserfield.AbstractFileChooserField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.resource.BinaryResource;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.Map;
import java.util.List;


@FormData(value = AddBillAiOcrFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class AddBillAiOcrForm extends AbstractForm {
    @Override
    protected String getConfiguredTitle() {
// TODO [Dino] verify translation
        return TEXTS.get("AddBillAiOcr0");
    }

    public MainBox.DataFieldBox.AddressField getAddressField() {
        return getFieldByClass(MainBox.DataFieldBox.AddressField.class);
    }

    public MainBox.DataFieldBox.AmountField getAmountField() {
        return getFieldByClass(MainBox.DataFieldBox.AmountField.class);
    }

    public GroupBox.BillField getBillField() {
        return getFieldByClass(GroupBox.BillField.class);
    }

    public GroupBox.BillImageField getBillImageField() {
        return getFieldByClass(GroupBox.BillImageField.class);
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

    public MainBox.DataFieldBox getDataFieldBox() {
        return getFieldByClass(MainBox.DataFieldBox.class);
    }

    public MainBox.DataFieldBox.DateField getDateField() {
        return getFieldByClass(MainBox.DataFieldBox.DateField.class);
    }

    public MainBox.DataFieldBox.NameField getNameField() {
        return getFieldByClass(MainBox.DataFieldBox.NameField.class);
    }

    public MainBox.DataFieldBox.TaxAmountField getTaxAmountField() {
        return getFieldByClass(MainBox.DataFieldBox.TaxAmountField.class);
    }

  public MainBox.DataFieldBox.WalletField getWalletField() {
    return getFieldByClass(MainBox.DataFieldBox.WalletField.class);
  }

  @Order(1000)
    public class MainBox extends AbstractGroupBox {
        @Order(1000)
        public class GroupBox extends AbstractGroupBox {

            @Order(1000)
            public class BillField extends AbstractFileChooserField {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("Bill0");
                }

                @Override
                protected java.util.List<String> getConfiguredFileExtensions() {
                    return CollectionUtility.arrayList("pdf","jpg", "png");
                }
                protected void execChangedValue() {

                    if(getBillField().isEmpty()) {
                        return;
                    }
                    getNameField().resetValue();
                    getDateField().resetValue();
                    getAmountField().resetValue();
                    getAddressField().resetValue();
                    getTaxAmountField().resetValue();

                    BinaryResource file = getBillField().getValue();

                    try {

                        File file2 = createFileFromBinaryResource(file, "bill");
                        Map<String,String> receiptMap  = ReceiptAiOcr.processFile(file2);

                        if(receiptMap.get("merchantName") != null) {
                            String merchantName = receiptMap.get("merchantName");
                            getNameField().setValue(merchantName);
                        }

                        if(receiptMap.get("merchantAddress") != null) {
                            String merchantAddress = receiptMap.get("merchantAddress");
                            getAddressField().setValue(merchantAddress);
                        }

                        if(receiptMap.get("taxAmount") != null) {
                            Double taxAmountDo = Double.parseDouble(receiptMap.get("taxAmount"));
                            BigDecimal taxAmount = BigDecimal.valueOf(taxAmountDo);
                            getTaxAmountField().setValue(taxAmount);
                        }


                        if(receiptMap.get("date") != null) {
                            String dateString = receiptMap.get("date");
                            OffsetDateTime dateTime = OffsetDateTime.parse(dateString);
                            Date date = new Date();
                            date.setTime(dateTime.toInstant().toEpochMilli());
                            getDateField().setValue(date);
                        }

                        if(receiptMap.get("totalAmount")!= null) {
                            Double totalAmountDouble = Double.parseDouble(receiptMap.get("totalAmount"));
                            BigDecimal totalAmount = BigDecimal.valueOf(totalAmountDouble);
                            getAmountField().setValue(totalAmount);
                        }

                        setEnabled(false);

                    }catch(IOException io) {

                    }
                    if(getNameField().isEmpty() && getAddressField().isEmpty() && getTaxAmountField().isEmpty() && getDateField().isEmpty() && getAmountField().isEmpty()) {
                        setEnabled(true);
                        MessageBoxHelper.showWarningMessage("Can not find any data, try another one...");
                    }else {
                        getBillImageField().setImage(file);
                    }
                    super.execChangedValue();
                }
                private File createFileFromBinaryResource(BinaryResource binaryResource, String fileName) throws IOException {
                    byte[] data = binaryResource.getContent();
                    File file = new File(fileName);
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        fos.write(data);
                    }
                    return file;
                }
            }

            @Order(2000)
            public class BillImageField extends AbstractImageField {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("MyNlsKey");
                }

                @Override
                protected boolean getConfiguredAutoFit() {
                    return true;
                }

                @Override
                protected int getConfiguredGridH() {
                    return 7;
                }

                @Override
                protected int getConfiguredGridW() {
                    return 6;
                }

                @Override
                protected boolean getConfiguredLabelVisible() {
                    return false;
                }

                @Override
                protected boolean getConfiguredStatusVisible() {
                    // TODO Auto-generated method stub
                    return false;
                }

                @Override
                protected void execInitField() {
                    clearErrorStatus();
                    super.execInitField();
                }
            }


        }

        @Order(1500)
        public class DataFieldBox extends AbstractGroupBox {
            @Override
            protected String getConfiguredLabel() {
                return TEXTS.get("Data0");
            }

            @Order(1000)
            public class NameField extends AbstractStringField {
                @Override
                protected String getConfiguredLabel() {
                    return TEXTS.get("MerchantName0");
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
                    return TEXTS.get("MerchantAddress0");
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
                    return TEXTS.get("TaxAmount1");
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
                    return TEXTS.get("BillPrice0");
                }

                @Override
                protected BigDecimal getConfiguredMinValue() {
                    return new BigDecimal("-999999999999999999");
                }



                @Override
                protected boolean getConfiguredMandatory() {
                    return true;
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
                    return TEXTS.get("Date0");
                }


            }

          @Order(6000)
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
            AddBillAiOcrFormData formData = new AddBillAiOcrFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddBillAiOcrService.class).prepareCreate(formData);
            importFormData(formData);

        }

        @Override
        protected void execStore() {
            AddBillAiOcrFormData formData = new AddBillAiOcrFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddBillAiOcrService.class).create(formData);
            importFormData(formData);
        }
    }

    public class ModifyHandler extends AbstractFormHandler {
        @Override
        protected void execLoad() {
            AddBillAiOcrFormData formData = new AddBillAiOcrFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddBillAiOcrService.class).load(formData);
            importFormData(formData);

        }

        @Override
        protected void execStore() {
            AddBillAiOcrFormData formData = new AddBillAiOcrFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddBillAiOcrService.class).store(formData);
            importFormData(formData);
        }
    }
}
