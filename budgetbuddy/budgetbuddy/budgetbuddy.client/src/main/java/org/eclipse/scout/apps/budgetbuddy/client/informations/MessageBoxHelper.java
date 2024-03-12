package org.eclipse.scout.apps.budgetbuddy.client.informations;

import org.eclipse.scout.rt.client.ui.messagebox.MessageBoxes;
import org.eclipse.scout.rt.platform.status.IStatus;
import org.eclipse.scout.rt.platform.text.TEXTS;
public class MessageBoxHelper {
  private static int showMessage(String header, String body, int priority) {
    return MessageBoxes.createOk().withHeader(header).withBody(body).withYesButtonText(TEXTS.get("Ok")).withSeverity(priority).show();
  }

  public static int showOkMessage(String header, String body) {
    return showMessage(TEXTS.get("Info"), body, IStatus.OK);
  }

  public static int showWarningMessage(String body) {
    return showMessage(TEXTS.get("Warning"), body, IStatus.WARNING);
  }


  public static int showDeleteConfirmationMessage() {
    return MessageBoxes.createYesNoCancel().withHeader(TEXTS.get("DeleteConfirmationText")).withBody(TEXTS.get("AreYourSureDeleteText")).withSeverity(IStatus.ERROR)
      .show();
  }

  public static int showDeleteConfirmationMessage(String content) {
    return MessageBoxes.createYesNoCancel().withHeader(TEXTS.get("DeleteConfirmationText")).withBody(content).withSeverity(IStatus.ERROR).show();
  }


  public static int showYesNoConfirmationMessage(String message) {
    return MessageBoxes.createYesNoCancel().withHeader(TEXTS.get("AreYourSureDeleteText")).withBody(message).show();

  }

  //Bez cancel
  public static int showYesNoConfMessage(String message) {
    return MessageBoxes.createYesNo().withHeader(TEXTS.get("AreYourSureDeleteText")).withBody(message).show();
  }
}
