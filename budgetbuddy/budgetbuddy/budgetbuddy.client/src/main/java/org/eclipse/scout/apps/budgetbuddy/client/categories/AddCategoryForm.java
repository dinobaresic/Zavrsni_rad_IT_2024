package org.eclipse.scout.apps.budgetbuddy.client.categories;

import org.eclipse.scout.apps.budgetbuddy.client.categories.AddCategoryForm.MainBox.CancelButton;
import org.eclipse.scout.apps.budgetbuddy.client.categories.AddCategoryForm.MainBox.GroupBox;
import org.eclipse.scout.apps.budgetbuddy.client.categories.AddCategoryForm.MainBox.OkButton;
import org.eclipse.scout.apps.budgetbuddy.shared.categories.AddCategoryFormData;
import org.eclipse.scout.apps.budgetbuddy.shared.categories.CreateAddCategoryPermission;
import org.eclipse.scout.apps.budgetbuddy.shared.categories.IAddCategoryService;
import org.eclipse.scout.apps.budgetbuddy.shared.categories.UpdateAddCategoryPermission;
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

@FormData(value = AddCategoryFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class AddCategoryForm extends AbstractForm {
    @Override
    protected String getConfiguredTitle() {
// TODO [Dino] verify translation
        return TEXTS.get("AddCategory");
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
                    return TEXTS.get("CategoryName0");
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
            AddCategoryFormData formData = new AddCategoryFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddCategoryService.class).prepareCreate(formData);
            importFormData(formData);
        }

        @Override
        protected void execStore() {
            AddCategoryFormData formData = new AddCategoryFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddCategoryService.class).create(formData);
            importFormData(formData);
        }
    }

    public class ModifyHandler extends AbstractFormHandler {
        @Override
        protected void execLoad() {
            AddCategoryFormData formData = new AddCategoryFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddCategoryService.class).load(formData);
            importFormData(formData);

        }

        @Override
        protected void execStore() {
            AddCategoryFormData formData = new AddCategoryFormData();
            exportFormData(formData);
            formData = BEANS.get(IAddCategoryService.class).store(formData);
            importFormData(formData);
        }
    }
}
