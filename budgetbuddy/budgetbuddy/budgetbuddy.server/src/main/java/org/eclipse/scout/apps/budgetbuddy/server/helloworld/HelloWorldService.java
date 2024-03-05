package org.eclipse.scout.apps.budgetbuddy.server.helloworld;

import org.eclipse.scout.apps.budgetbuddy.server.ServerSession;
import org.eclipse.scout.apps.budgetbuddy.shared.helloworld.HelloWorldFormData;
import org.eclipse.scout.apps.budgetbuddy.shared.helloworld.IHelloWorldService;

/**
 * @author Dino
 */
public class HelloWorldService implements IHelloWorldService {

  @Override
  public HelloWorldFormData load(HelloWorldFormData input) {
    StringBuilder msg = new StringBuilder();
    msg.append("Hello ").append(ServerSession.get().getUserId()).append('!');
    input.getMessage().setValue(msg.toString());
    return input;
  }
}
