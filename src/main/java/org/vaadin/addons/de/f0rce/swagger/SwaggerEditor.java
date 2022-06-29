package org.vaadin.addons.de.f0rce.swagger;

import com.vaadin.flow.component.splitlayout.SplitLayout;
import de.f0rce.ace.AceEditor;
import de.f0rce.ace.enums.AceMode;
import de.f0rce.ace.enums.AceTheme;

/** @author David "F0rce" Dodlek */
public class SwaggerEditor extends SplitLayout {

  private AceEditor aceEditor;
  private SwaggerUI swaggerUI;

  /**
   * {@link SplitLayout} to match https://editor.swagger.io/ while using Vaadin Components.
   *
   * <p>{@link AceEditor} used on the primary side and {@link SwaggerUI} used on the secondary side.
   */
  public SwaggerEditor() {
    super();

    aceEditor = new AceEditor();
    aceEditor.setMode(AceMode.yaml);
    aceEditor.setTheme(AceTheme.tomorrow_night_eighties);
    aceEditor.setAutoComplete(false);
    aceEditor.setLiveAutocompletion(false);
    aceEditor.setWrap(true);
    aceEditor.setDisplayIndentGuides(true);
    aceEditor.setTabSize(2);
    aceEditor.setFontSize(14);
    aceEditor.setSofttabs(true);
    aceEditor.setStatusbarEnabled(false);
    aceEditor.setUseWorker(true);
    aceEditor.setReadOnly(true);
    aceEditor.setSizeFull();

    swaggerUI = new SwaggerUI();
    swaggerUI.setSizeFull();

    super.addToPrimary(aceEditor);
    super.addToSecondary(swaggerUI);
    super.setSizeFull();

    aceEditor.addBlurListener(
        event -> {
          swaggerUI.setSpec(aceEditor.getValue());
        });
  }

  /**
   * Sets the spec to be rendered with {@link SwaggerUI} and the value of {@link AceEditor}.
   *
   * @param spec {@link String}
   */
  public void setSpec(String spec) {
    this.aceEditor.setValue(spec);
    this.swaggerUI.setSpec(spec);
  }

  /**
   * Returns the current set spec which is rendered in {@link SwaggerUI}.
   *
   * @return {@link String}
   */
  public String getSpec() {
    return this.swaggerUI.getSpec();
  }

  /**
   * Returns the current instance of the {@link AceEditor} for further configuration.
   *
   * @return {@link AceEditor}
   */
  public AceEditor getAceEditor() {
    return this.aceEditor;
  }

  /**
   * Returns the current instance of the {@link SwaggerUI} for further configuration.
   *
   * @return {@link SwaggerUI}
   */
  public SwaggerUI getSwaggerUI() {
    return this.swaggerUI;
  }
}
