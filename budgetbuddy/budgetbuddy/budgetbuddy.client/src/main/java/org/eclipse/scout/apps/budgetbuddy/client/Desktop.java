package org.eclipse.scout.apps.budgetbuddy.client;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.client.ui.action.keystroke.IKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktop;
import org.eclipse.scout.rt.client.ui.desktop.notification.NativeNotificationDefaults;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.client.ui.form.ScoutInfoForm;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.security.IAccessControlService;

import org.eclipse.scout.apps.budgetbuddy.client.Desktop.UserProfileMenu.ThemeMenu.DarkThemeMenu;
import org.eclipse.scout.apps.budgetbuddy.client.Desktop.UserProfileMenu.ThemeMenu.DefaultThemeMenu;
import org.eclipse.scout.apps.budgetbuddy.client.search.SearchOutline;
import org.eclipse.scout.apps.budgetbuddy.client.settings.SettingsOutline;
import org.eclipse.scout.apps.budgetbuddy.client.work.WorkOutline;
import org.eclipse.scout.apps.budgetbuddy.shared.Icons;

/**
 * @author Dino
 */
public class Desktop extends AbstractDesktop {

  public Desktop() {
    addPropertyChangeListener(PROP_THEME, this::onThemeChanged);
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ApplicationTitle");
  }

  @Override
  protected String getConfiguredLogoId() {
    return Icons.AppLogo;
  }

  @Override
  protected NativeNotificationDefaults getConfiguredNativeNotificationDefaults() {
    return super.getConfiguredNativeNotificationDefaults().withIconId("application_logo.png");
  }

  @Override
  protected List<Class<? extends IOutline>> getConfiguredOutlines() {
    return CollectionUtility.<Class<? extends IOutline>>arrayList(
      WorkOutline.class, SearchOutline.class, SettingsOutline.class);
  }

  @Override
  protected void execDefaultView() {
    selectFirstVisibleOutline();
  }

  protected void selectFirstVisibleOutline() {
    setOutline(WorkOutline.class);
    /*
    for (IOutline outline : getAvailableOutlines()) {
      if (outline.isEnabled() && outline.isVisible()) {
        setOutline(outline.getClass());
        return;
      }
    }

     */
  }

  protected void onThemeChanged(PropertyChangeEvent evt) {
    IMenu darkMenu = getMenuByClass(DarkThemeMenu.class);
    IMenu defaultMenu = getMenuByClass(DefaultThemeMenu.class);
    String newThemeName = (String) evt.getNewValue();
    if (DarkThemeMenu.DARK_THEME.equalsIgnoreCase(newThemeName)) {
      darkMenu.setIconId(Icons.CheckedBold);
      defaultMenu.setIconId(null);
    } else {
      darkMenu.setIconId(null);
      defaultMenu.setIconId(Icons.CheckedBold);
    }
  }

  @Order(1000)
  public class UserProfileMenu extends AbstractMenu {

    @Override
    protected String getConfiguredKeyStroke() {
      return IKeyStroke.F10;
    }

    @Override
    protected String getConfiguredIconId() {
      return Icons.PersonSolid;
    }

    @Override
    protected String getConfiguredText() {
      String userId = BEANS.get(IAccessControlService.class).getUserIdOfCurrentSubject();
      return StringUtility.uppercaseFirst(userId);
    }

    @Order(1000)
    public class AboutMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("About");
      }

      @Override
      protected void execAction() {
        ScoutInfoForm form = new ScoutInfoForm();
        form.startModify();
      }
    }

    @Order(2000)
    public class ThemeMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("Theme");
      }

      @Order(1000)
      public class DefaultThemeMenu extends AbstractMenu {

        private static final String DEFAULT_THEME = "Default";

        @Override
        protected String getConfiguredText() {
          return DEFAULT_THEME;
        }

        @Override
        protected void execAction() {
          setTheme(DEFAULT_THEME.toLowerCase());
        }
      }

      @Order(2000)
      public class DarkThemeMenu extends AbstractMenu {

        private static final String DARK_THEME = "Dark";

        @Override
        protected String getConfiguredText() {
          return DARK_THEME;
        }

        @Override
        protected void execAction() {
          setTheme(DARK_THEME.toLowerCase());
        }
      }
    }

    @Order(3000)
    public class LogoutMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("Logout");
      }

      @Override
      protected void execAction() {
        ClientSessionProvider.currentSession().stop();
      }
    }
  }

  @Order(1000)
  public class WorkOutlineViewButton extends AbstractOutlineViewButton {

    public WorkOutlineViewButton() {
      this(WorkOutline.class);
    }

    protected WorkOutlineViewButton(Class<? extends WorkOutline> outlineClass) {
      super(Desktop.this, outlineClass);
    }

    @Override
    protected String getConfiguredKeyStroke() {
      return IKeyStroke.F2;
    }
  }

  @Order(2000)
  public class SearchOutlineViewButton extends AbstractOutlineViewButton {

    public SearchOutlineViewButton() {
      this(SearchOutline.class);
    }

    protected SearchOutlineViewButton(Class<? extends SearchOutline> outlineClass) {
      super(Desktop.this, outlineClass);
    }

    @Override
    protected DisplayStyle getConfiguredDisplayStyle() {
      return DisplayStyle.TAB;
    }

    @Override
    protected String getConfiguredKeyStroke() {
      return IKeyStroke.F3;
    }
  }

  @Order(3000)
  public class SettingsOutlineViewButton extends AbstractOutlineViewButton {

    public SettingsOutlineViewButton() {
      this(SettingsOutline.class);
    }

    protected SettingsOutlineViewButton(Class<? extends SettingsOutline> outlineClass) {
      super(Desktop.this, outlineClass);
    }

    @Override
    protected DisplayStyle getConfiguredDisplayStyle() {
      return DisplayStyle.TAB;
    }

    @Override
    protected String getConfiguredKeyStroke() {
      return IKeyStroke.F10;
    }
  }
}
