<p align="center">
<a href="https://vaadin.com/directory/component/swagger" target="__blank"><img src="https://user-images.githubusercontent.com/60381251/176435959-29c48982-bbc5-43d0-8769-084074d8c9a3.png" alt="Vaadin Swagger"></a>
<br>
<a href="https://vaadin.com/directory/component/swagger"><img alt="Vaadin Directory" src="https://img.shields.io/vaadin-directory/status/swagger?color=6D9A00"></a>
<a href="https://vaadin.com/directory/component/swagger"><img alt="Vaadin Directory version" src="https://img.shields.io/vaadin-directory/v/swagger?color=6D9A00&label=%20"></a>
<a href="https://vaadin.com/directory/component/swagger"><img alt="Vaadin Directory" src="https://img.shields.io/vaadin-directory/stars/swagger?color=6D9A00"></a>
<a href="https://preview.f0rce.de/vaadin-swagger/" target="__blank"><img src="https://img.shields.io/static/v1?label=&message=Demo&color=6D9A00" alt="Demo"></a>
<br>
<a href="https://github.com/f0rce/vaadin-swagger" target="__blank"><img alt="GitHub stars" src="https://img.shields.io/github/stars/f0rce/vaadin-swagger?style=social"></a>
</p>


<p align="center">
Easily visualize and interact with your REST API in your Vaadin project.
</p>

<p align="center">
<table>
<tbody>
<td align="center">
<img width="2000" height="0" /><br>
Status: <b>Public Beta 🎉</b><br>
<sub><a href="https://github.com/sponsors/f0rce">Sponsor Program 💖</a><br> Follow me <a href="https://twitter.com/F0rceDev">@F0rceDev</a> 🐦</sub><br>
<img width="2000" height="0" /><br>
</td>
</tbody>
</table>
</p>


## Technical structure

- [Swagger UI](https://swagger.io/tools/swagger-ui/) has been packaged as a web component using [Lit](https://lit.dev) (`<lit-swagger-ui>`)
- [Swagger Editor](https://swagger.io/tools/swagger-editor) has be replicated using my own [ace](https://github.com/F0rce/ace) and `<lit-swagger-ui>` in a Vaadin `SplitLayout` 


## Future plans

Currently there is limited to none further functionality / customization besides setting the `spec` in `<lit-swagger-ui>` *(which is sufficient in my current use case)*. If there is enough interest or some interesting use cases I am willing to maintain this Vaadin Add-on actively.</br>If you are missing some functionality or have a feature request please open a new issue.


## Install

Install the component using [Vaadin Directory](https://vaadin.com/directory/component/swagger) or by adding the `swagger-*.jar` from the [latest release](https://github.com/F0rce/vaadin-swagger/releases/latest) to your project.


## Demo

[preview.f0rce.de/vaadin-swagger](https://preview.f0rce.de/vaadin-swagger)


## Example Usage 

<details>
    <summary>Swagger Editor (SplitLayout)</summary>

```java
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
              // OpenAPI 3.0 sample
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
  }
}
```
</details>

<details>
    <summary>Swagger UI (Standalone)</summary>

```java
@Route("")
public class TestView extends Div {

  public TestView() {
    // Set the parent <div> to full size (fullscreen)
    this.setSizeFull();

    // Initialize new SwaggerUI
    SwaggerUI swaggerUI = new SwaggerUI();

    // Set the size to fullscreen to match parents height/width
    swaggerUI.setSizeFull();

    swaggerUI.addReadyListener(
        event -> {
          // OpenAPI 3.0 sample
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

    // Add SwaggerUI to the parent <div>
    this.add(swaggerUI)
  }
}
```
</details>

<details>
    <summary>Customizing Ace</summary>

If you want to change the default behaviour of [ace](https://github.com/F0rce/ace) you can access the instance using:
```java
SwaggerEditor swaggerEditor = new SwaggerEditor();
AceEditor ace = swaggerEditor.getAceEditor();

// turn of read-only mode
ace.setReadOnly(false);
```

Please refer to [ace's documentation](https://docs.f0rce.de/ace) for further information.
</details>


## Documentation

[docs.f0rce.de/vaadin-swagger](https://docs.f0rce.de/vaadin-swagger)


## Licence

[MIT License](https://github.com/F0rce/vaadin-swagger/blob/master/LICENSE) © 2022 [David Dodlek](https://github.com/F0rce)
