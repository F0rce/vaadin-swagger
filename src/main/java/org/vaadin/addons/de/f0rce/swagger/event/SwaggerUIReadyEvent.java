package org.vaadin.addons.de.f0rce.swagger.event;

import org.vaadin.addons.de.f0rce.swagger.SwaggerUI;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;

/** @author David "F0rce" Dodlek */
@DomEvent("swagger-ui-ready")
public class SwaggerUIReadyEvent extends ComponentEvent<SwaggerUI> {

  public SwaggerUIReadyEvent(SwaggerUI source, boolean fromClient) {
    super(source, fromClient);
  }
}
