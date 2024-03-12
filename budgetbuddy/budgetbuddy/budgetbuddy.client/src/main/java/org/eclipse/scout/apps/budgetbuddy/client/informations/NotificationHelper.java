package org.eclipse.scout.apps.budgetbuddy.client.informations;

import org.eclipse.scout.apps.budgetbuddy.client.ClientSession;
import org.eclipse.scout.rt.client.ui.desktop.notification.DesktopNotification;
import org.eclipse.scout.rt.platform.text.TEXTS;
public class NotificationHelper {
  DesktopNotification notification;

  public static void showNotification(String text) {
    DesktopNotification notification = new DesktopNotification(text);
    ClientSession.get().getDesktop().addNotification(notification);
  }

  public static void showSaveSuccessNotification() {
    DesktopNotification notification = new DesktopNotification(TEXTS.get("SaveSuccess"));
    ClientSession.get().getDesktop().addNotification(notification);
  }

  public static void showDeleteSuccessNotification() {
    DesktopNotification notification = new DesktopNotification(TEXTS.get("DeleteSuccess"));
    ClientSession.get().getDesktop().addNotification(notification);
  }
}
