package org.eclipse.scout.apps.budgetbuddy.client;

import org.eclipse.scout.rt.client.AbstractClientSession;
import org.eclipse.scout.rt.client.IClientSession;
import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.shared.services.common.code.CODES;

import org.eclipse.scout.apps.budgetbuddy.client.Desktop;

import java.util.Locale;

/**
 * @author Dino
 */
public class ClientSession extends AbstractClientSession {

  public ClientSession() {
    super(true);
  }

  /**
   * @return The {@link IClientSession} which is associated with the current thread, or {@code null} if not found.
   */
  public static ClientSession get() {
    return ClientSessionProvider.currentSession(ClientSession.class);
  }

  @Override
  protected void execLoadSession() {
    //pre-load all known code types
    CODES.getAllCodeTypes("org.eclipse.scout.apps.budgetbuddy.shared");

    Locale locale = new Locale("hr", "HR");
    setLocale(locale);
    setDesktop(new Desktop());
  }
}
