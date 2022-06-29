/**
@license MIT
Copyright 2022 David "F0rce" Dodlek
Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import { LitElement, html, css } from "lit";

import { SwaggerUIBundle } from "swagger-ui-dist";
import { swaggerUIStyle } from "./swagger-ui.css.js";

class LitSwaggerUI extends LitElement {
  static get properties() {
    return {
      spec: { type: String },
    };
  }

  constructor() {
    super();
    this.spec = "";
  }

  static get styles() {
    return [
      swaggerUIStyle,
      css`
        :host {
          display: block;
          width: 100%;
          height: 100%;
        }
        #swagger-ui {
          border: 1px solid var(--lumo-contrast-20pct);
          border-radius: var(--lumo-border-radius);
        }
      `,
    ];
  }

  render() {
    return html`
      <div
        id="swagger-ui-container"
        style="height: 100%; width: 100%; position: relative;"
      >
        <div
          id="swagger-ui"
          style="position: absolute; top: 0; right: 0; bottom: 0; left: 0;"
        ></div>
      </div>
    `;
  }

  firstUpdated(changedProperties) {
    this.initialInit = true;

    this.swaggerUIDiv = this.shadowRoot.getElementById("swagger-ui");
    this.swaggerContainerDiv = this.shadowRoot.getElementById(
      "swagger-ui-container"
    );

    let self = this;

    this.swaggerUI = SwaggerUIBundle({
      domNode: self.swaggerUIDiv,
      presets: [SwaggerUIBundle.presets.apis],
      layout: "BaseLayout",
      spec: self.spec,
    });
  }

  updated(changedProperties) {
    for (let i = 0; i < changedProperties.size; i++) {
      var toUpdate = Array.from(changedProperties.keys())[i];
      var funcToCall = toUpdate + "Changed";
      if (typeof this[funcToCall] == "function") {
        this[funcToCall](); // This line is freaking cool
      }
      // If last initial property update done, send ready event to ensure every next operation has an existing editor
      if (i == changedProperties.size - 1) {
        if (this.initialInit) {
          this.dispatchEvent(
            new CustomEvent("swagger-ui-ready", {
              detail: {},
            })
          );
          this.initialInit = false;
        }
      }
    }
  }

  updateSpec(spec) {
    if (this.swaggerUI == undefined) {
      this.addEventListener("swagger-ui-ready", () => this._updateSpec(spec), {
        once: true,
      });
    } else {
      this._updateSpec(spec);
    }
  }

  /** @private */
  _updateSpec(spec) {
    this.swaggerUI.specActions.updateSpec(spec);
  }
}

customElements.define("lit-swagger-ui", LitSwaggerUI);
