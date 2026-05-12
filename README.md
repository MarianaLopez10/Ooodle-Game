# Ooodle-Game

## 1. Introducción

El presente proyecto consiste en el desarrollo de una aplicación de escritorio utilizando JavaFX y Maven, cuyo objetivo es implementar un juego interactivo basado en la resolución de ecuaciones matemáticas.

El sistema permite al usuario ingresar ecuaciones como intentos de solución para alcanzar un resultado objetivo previamente definido, validando automáticamente el cumplimiento de las reglas del juego.

---

## 2. Descripción del Proyecto

Ooodle es un juego de lógica y matemáticas en el que el jugador debe completar ecuaciones utilizando números dentro de un rango determinado (por ejemplo, del 1 al 9 o del 1 al 12), con el objetivo de obtener un resultado correcto.

El juego presenta una cuadrícula donde el usuario ubica números en posiciones específicas para resolver una ecuación. A través de cada intento, el sistema proporciona retroalimentación visual mediante colores:

- Verde: El número está en la posición correcta.
- Amarillo: El número está en la posición incorrecta.
- Gris: El número no pertenece a la ecuación.

---

## 3. Reglas Del Juego

El jugador deberá seguir las siguientes reglas para completar correctamente la ecuación:

- Utilizar los números del 1 al 9 o del 1 al 12, dependiendo del modo de juego seleccionado.
- Cada número puede utilizarse solo una vez dentro de la ecuación.
- El jugador debe ingresar una ecuación válida que cumpla las reglas matemáticas establecidas.
- Para comprobar la solución, el usuario debe presionar el botón “Validar Ecuación”.
- El sistema mostrará retroalimentación visual mediante colores:
  - Verde: El número está en la posición correcta.
  - Amarillo: El número pertenece a la ecuación, pero está en una posición incorrecta.
  - Gris: El número no pertenece a la ecuación.
- El jugador dispone de un máximo de seis intentos para resolver la ecuación.
- La partida finaliza automáticamente cuando:
  - El jugador resuelve correctamente la ecuación.
  - Se agotan los intentos disponibles.

---

## 4. Objetivo del Proyecto

Desarrollar una aplicación que:

- Permita el ingreso de ecuaciones matemáticas.
- Valide el cumplimiento de reglas establecidas.
- Controle el número de intentos.
- Determine automáticamente condiciones de victoria o derrota.
- Almacene información relevante en una base de datos MySQL.

---

## 5. Tecnologías Utilizadas

- **Lenguaje de programación:** Java 17+
- **Framework gráfico:** JavaFX
- **Gestor de dependencias:** Maven
- **Base de datos:** MySQL
- **Control de versiones:** GitHub
- **Arquitectura:** MVC (Modelo - Vista - Controlador)
- **Entorno de desarrollo:** Visual Studio Code

---

## 6. Requisitos del Sistema

Para ejecutar correctamente la aplicación se requiere:

- JDK 17 o superior.
- Maven instalado y configurado.
- MySQL Server en ejecución.
- JavaFX SDK correctamente enlazado.
- IDE compatible (Visual Studio Code).

---

## 7. Funcionalidades Implementadas

- Ingreso de ecuaciones por parte del usuario.
- Registro de cada ecuación como un intento.
- Validación automática al presionar el botón “Validar Ecuación”.
- Verificación de:
  - Orden correcto de operaciones.
  - Uso de números dentro del rango permitido.
  - Uso único de cada número.
- Retroalimentación visual mediante colores.
- Detección y visualización de errores específicos.
- Contador de intentos.
- Visualización de temporizador.
- Detección de condición de victoria.
- Detección de condición de derrota.
- Finalización automática de la partida.

---

## 8. Arquitectura del Sistema (MVC)

El proyecto está desarrollado bajo el patrón de arquitectura MVC (Modelo - Vista - Controlador), lo que permite una mejor organización del código y facilita su mantenimiento.

- Modelo (Model): Maneja la lógica del juego, validaciones, reglas matemáticas y acceso a la base de datos.
- Vista (View): Representa la interfaz gráfica del usuario desarrollada en JavaFX, incluyendo la cuadrícula, botones, colores y elementos visuales.
- Controlador (Controller): Gestiona la interacción del usuario, procesa los intentos y comunica la vista con el modelo.

---

## 9. Desarrolladores

- Mariana López Tovar
- Alexis Santiago Puentes Bohorquez

---
