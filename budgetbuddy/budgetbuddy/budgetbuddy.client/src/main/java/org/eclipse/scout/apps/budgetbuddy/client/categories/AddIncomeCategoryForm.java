package org.eclipse.scout.apps.budgetbuddy.client.categories;

import org.eclipse.scout.apps.budgetbuddy.client.categories.AddIncomeCategoryForm.MainBox.CancelButton;
import org.eclipse.scout.apps.budgetbuddy.client.categories.AddIncomeCategoryForm.MainBox.GroupBox;
import org.eclipse.scout.apps.budgetbuddy.client.categories.AddIncomeCategoryForm.MainBox.OkButton;
import org.eclipse.scout.apps.budgetbuddy.shared.categories.AddIncomeCategoryFormData;
import org.eclipse.scout.apps.budgetbuddy.shared.categories.CreateAddIncomeCategoryPermission;
import org.eclipse.scout.apps.budgetbuddy.shared.categories.IAddIncomeCategoryService;
import org.eclipse.scout.apps.budgetbuddy.shared.categories.UpdateAddIncomeCategoryPermission;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

@FormData(value = AddIncomeCategoryFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class AddIncomeCategoryForm extends AbstractForm {
    @Override
    protected String getConfiguredTitle() {
// TODO [Dino] verify translation
        return TEXTS.get("AddIncomeCategory");
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
              return TEXTS.get("CategoryName1");
            }

            @Override
            protected int getConfiguredMaxLength() {
              return 128;
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
            AddIncomeCategoryFormData formData = new AddIncomeCategoryFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddIncomeCategoryService.class).prepareCreate(formData);
            importFormData(formData);
        }

        @Override
        protected void execStore() {
            AddIncomeCategoryFormData formData = new AddIncomeCategoryFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddIncomeCategoryService.class).create(formData);
            importFormData(formData);
        }
    }

    public class ModifyHandler extends AbstractFormHandler {
        @Override
        protected void execLoad() {
            AddIncomeCategoryFormData formData = new AddIncomeCategoryFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddIncomeCategoryService.class).load(formData);
            importFormData(formData);
        }

        @Override
        protected void execStore() {
            AddIncomeCategoryFormData formData = new AddIncomeCategoryFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddIncomeCategoryService.class).store(formData);
            importFormData(formData);
        }
    }
}
