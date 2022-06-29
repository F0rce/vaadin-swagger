package org.vaadin.addons.de.f0rce.swagger;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

/**
 * Test View for our {@link SwaggerUI} add-on class. This class and others in the test folder will
 * not be included in the final JAR.
 */
@Route("")
public class TestView extends Div {

  public TestView() {
    // Set the parent <div> to full size (fullscreen)
    this.setSizeFull();

    // Initialize new SwaggerEditor aka SplitView with AceEditor as primary and SwaggerUI as
    // secondary
    SwaggerEditor swaggerEditor = new SwaggerEditor();

    // Add the spec as soon as SwaggerUI is ready --> this is not neccessary, as the frontend
    // handles it automatically
    swaggerEditor
        .getSwaggerUI()
        .addReadyListener(
            event -> {
              swaggerEditor.setSpec(
                  "openapi: 3.0.0\n"
                      + "info:\n"
                      + "  version: 1.0.0\n"
                      + "  title: Sample API\n"
                      + "  description: A sample API to illustrate OpenAPI concepts\n"
                      + "paths:\n"
                      + "  /list:\n"
                      + "    get:\n"
                      + "      description: Returns a list of stuff              \n"
                      + "      responses:\n"
                      + "        '200':\n"
                      + "          description: Successful response");
            });

    // Add SwaggerEditor to the parent <div>
    this.add(swaggerEditor);

    // Initialize new SwaggerUI
    SwaggerUI swaggerUI = new SwaggerUI();

    // Set the size to fullscreen to match parents height/width
    swaggerUI.setSizeFull();

    swaggerUI.addReadyListener(
        event -> {
          swaggerUI.setSpec(
              "openapi: 3.0.0\n"
                  + "info:\n"
                  + "  version: 1.0.0\n"
                  + "  title: Sample API\n"
                  + "  description: A sample API to illustrate OpenAPI concepts\n"
                  + "paths:\n"
                  + "  /list:\n"
                  + "    get:\n"
                  + "      description: Returns a list of stuff              \n"
                  + "      responses:\n"
                  + "        '200':\n"
                  + "          description: Successful response");
        });

    this.add(swaggerUI);
  }
}
