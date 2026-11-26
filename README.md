# Sistema de Gestión de Inventarios

## Información académica

- Institución: Tecnológica del Oriente
- Programa: Ingeniería de Software
- Asignatura: Programación Orientada a Objetos
- Unidad: III
- Actividad: AA1
- Año: 2026

## 1. Descripción del proyecto

Este proyecto consiste en el desarrollo de una aplicación de escritorio
para la gestión de inventarios, utilizando Java y los principios de
la Programación Orientada a Objetos.

La aplicación permite registrar, buscar, consultar y eliminar productos
mediante una interfaz gráfica desarrollada con Java Swing, incorporando
el manejo de excepciones, la validación de datos y la gestión de eventos.

El diseño visual utiliza los colores verde y naranja, tomando como
referencia la identidad institucional de la Tecnológica del Oriente.

## 2. Objetivo

Desarrollar una aplicación funcional que permita aplicar los conocimientos
adquiridos sobre manejo de excepciones, programación orientada a eventos,
colecciones de datos y pruebas unitarias, garantizando un adecuado
control de la información ingresada por el usuario.

## 3. Tecnologías utilizadas

- Java 21.
- Java Swing.
- Apache Maven.
- JUnit 5.
- Git y GitHub.
- GitHub Codespaces.
- Visual Studio Code.
- noVNC para la visualización de la interfaz gráfica en Codespaces.

## 4. Funcionalidades

- Registro de productos.
- Búsqueda de productos mediante su ID.
- Eliminación de productos con confirmación previa.
- Consulta de productos registrados mediante una tabla.
- Limpieza de los campos del formulario.
- Validación de campos obligatorios.
- Validación de cantidades negativas.
- Validación de precios inválidos.
- Control de identificadores duplicados.
- Manejo de productos inexistentes.
- Registro de eventos de ejecución.

## 5. Estructura del proyecto

```text
POO-AA1-Gestion-Inventarios/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── inventario/
│   │           ├── excepciones/
│   │           │   ├── CantidadInvalidaException.java
│   │           │   └── ProductoNoEncontradoException.java
│   │           ├── modelo/
│   │           │   └── Producto.java
│   │           ├── servicio/
│   │           │   └── InventarioService.java
│   │           └── vista/
│   │               └── InventarioVista.java
│   └── test/
│       └── java/
│           └── inventario/
│               └── InventarioServiceTest.java
│
├── .gitignore
├── pom.xml
└── README.md
```

## 6. Arquitectura de la aplicación

El proyecto se encuentra organizado en paquetes que permiten separar
las responsabilidades de cada componente.

| Componente | Responsabilidad |
|---|---|
| Modelo | Representa los atributos de cada producto. |
| Servicio | Gestiona las operaciones y reglas del inventario. |
| Excepciones | Controla situaciones de error específicas. |
| Vista | Gestiona la interfaz gráfica y los eventos del usuario. |
| Pruebas | Verifica el comportamiento de la lógica del negocio. |

Esta separación facilita la comprensión, el mantenimiento y la
realización de pruebas sobre el código.

## 7. Manejo de excepciones

La aplicación incorpora excepciones personalizadas y propias de Java
para controlar situaciones que pueden presentarse durante su ejecución.

| Excepción | Propósito |
|---|---|
| CantidadInvalidaException | Controlar cantidades negativas. |
| ProductoNoEncontradoException | Controlar búsquedas o eliminaciones de productos inexistentes. |
| IllegalArgumentException | Controlar datos inválidos, campos obligatorios e identificadores duplicados. |
| NumberFormatException | Controlar el ingreso de valores no numéricos. |

Los errores de validación se presentan mediante mensajes en la interfaz
gráfica, permitiendo que el usuario identifique y corrija la información
sin necesidad de cerrar la aplicación.

## 8. Programación orientada a eventos

La interfaz gráfica responde a las acciones realizadas por el usuario
mediante los siguientes botones:

- Agregar: registra un producto después de validar sus datos.
- Buscar: consulta un producto mediante su identificador.
- Eliminar: solicita confirmación antes de eliminar un producto.
- Limpiar: restablece los campos del formulario.

La aplicación utiliza componentes de Java Swing para gestionar
la interacción entre el usuario y la lógica del inventario.

## 9. Almacenamiento de información

Los productos se almacenan mediante una colección ArrayList de Java.

Cada producto contiene los siguientes atributos:

- ID.
- Nombre.
- Cantidad.
- Precio.

El almacenamiento se realiza en memoria durante la ejecución de
la aplicación. Por lo tanto, al cerrar el programa, los productos
registrados no se conservan.

El proyecto no requiere una base de datos externa.

## 10. Instalación y ejecución

### Requisitos previos

- JDK 21.
- Apache Maven.
- Git.
- Entorno gráfico compatible con Java Swing.

### Clonar el repositorio

```bash
git clone https://github.com/npacavita-stack/POO-AA1-Gestion-Inventarios.git
```

### Ingresar al proyecto

```bash
cd POO-AA1-Gestion-Inventarios
```

### Compilar la aplicación

```bash
mvn clean compile
```

### Ejecutar la aplicación en un entorno gráfico local

```bash
java -cp target/classes inventario.vista.InventarioVista
```

### Ejecutar en GitHub Codespaces

Cuando el entorno gráfico virtual y noVNC se encuentren configurados
con la pantalla virtual :99, ejecutar:

```bash
DISPLAY=:99 java -cp target/classes inventario.vista.InventarioVista
```

Posteriormente, acceder al puerto 6080 mediante noVNC.

## 11. Pruebas funcionales

Durante el desarrollo se realizaron pruebas manuales sobre la
interfaz gráfica para verificar las operaciones principales
y el manejo de datos inválidos.

| Escenario | Resultado |
|---|---|
| Registro de producto válido | Aprobado |
| Registro de ID duplicado | Aprobado |
| Registro de cantidad negativa | Aprobado |
| Registro de precio inválido | Aprobado |
| Ingreso de valores no numéricos | Aprobado |
| Registro sin nombre | Aprobado |
| Registro sin ID | Aprobado |
| Búsqueda de producto existente | Aprobado |
| Búsqueda de producto inexistente | Aprobado |
| Eliminación de producto | Aprobado |
| Búsqueda de producto eliminado | Aprobado |
| Cancelación de eliminación | Aprobado |
| Limpieza del formulario | Aprobado |

## 12. Pruebas unitarias

Se implementaron pruebas unitarias utilizando JUnit 5 para
validar la lógica del servicio de inventarios.

Para ejecutar las pruebas:

```bash
mvn clean test
```

### Resultados obtenidos

| Indicador | Resultado |
|---|---:|
| Pruebas ejecutadas | 12 |
| Pruebas aprobadas | 12 |
| Fallos | 0 |
| Errores | 0 |
| Pruebas omitidas | 0 |
| Porcentaje de aprobación | 100% |

Resultado de Maven:

```text
Tests run: 12, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

El reporte generado por Maven Surefire puede consultarse en:

```text
target/surefire-reports/inventario.InventarioServiceTest.txt
```

## 13. Registro de eventos

La aplicación incorpora registros de ejecución que permiten
realizar seguimiento a las operaciones efectuadas.

Ejemplo de registro exitoso:

```text
INFO: Producto registrado: p001
INFO: Proceso de registro finalizado.
```

Estos registros facilitan la identificación de errores y
el seguimiento del comportamiento de la aplicación.

## 14. Conclusiones

El desarrollo de este proyecto permitió aplicar de manera práctica
los conceptos de Programación Orientada a Objetos, manejo de
excepciones, programación orientada a eventos y pruebas unitarias.

La implementación de una interfaz gráfica facilita la interacción
con el usuario, mientras que la separación de responsabilidades
permite mantener un código organizado y comprensible.

Las pruebas realizadas permitieron verificar las funcionalidades
principales y comprobar que la aplicación responde adecuadamente
ante diferentes escenarios de validación.

## 15. Finalidad académica

Proyecto desarrollado con fines académicos para la asignatura
de Programación Orientada a Objetos de la Tecnológica del Oriente,
como parte de la actividad AA1 de la Unidad III.