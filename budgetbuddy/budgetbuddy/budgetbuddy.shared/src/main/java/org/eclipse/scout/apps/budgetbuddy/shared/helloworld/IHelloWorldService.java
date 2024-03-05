package org.eclipse.scout.apps.budgetbuddy.shared.helloworld;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

import org.eclipse.scout.apps.budgetbuddy.shared.helloworld.HelloWorldFormData;

/**
 * @author Dino
 */
@TunnelToServer
public interface IHelloWorldService extends IService {
      HelloWorldFormData load(HelloWorldFormData input);
}
