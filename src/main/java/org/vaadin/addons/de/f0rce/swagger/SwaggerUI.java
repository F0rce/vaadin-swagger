package org.vaadin.addons.de.f0rce.swagger;

import org.vaadin.addons.de.f0rce.swagger.event.SwaggerUIReadyEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.shared.Registration;

/** @author David "F0rce" Dodlek */
@Tag("lit-swagger-ui")
@NpmPackage(value = "swagger-ui-dist", version = "4.12.0")
@NpmPackage(value = "lit", version = "2.2.6")
@JsModule("./@f0rce/swagger-editor/lit-swagger-ui.js")
public class SwaggerUI extends Component implements HasSize, HasComponents {

  private String spec = "";

  public SwaggerUI() {}

  /**
   * Sets the spec to be rendered.
   *
   * @param spec {@link String}
   */
  public void setSpec(String spec) {
    this.getElement().callJsFunction("updateSpec", spec);
    this.spec = spec;
  }

  /**
   * Returns the current rendered spec.
   *
   * @return {@link String}
   */
  public String getSpec() {
    return this.spec;
  }

  /**
   * Adds a <code>swagger-ui-ready</code> listener.
   *
   * @param listener {@link ComponentEventListener}
   * @return {@link Registration}
   */
  public Registration addReadyListener(ComponentEventListener<SwaggerUIReadyEvent> listener) {
    return this.addListener(SwaggerUIReadyEvent.class, listener);
  }
}
