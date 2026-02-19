# Ooodle-Game

## 1. Introducción

El presente proyecto consiste en el desarrollo de una aplicación de escritorio utilizando JavaFX y Maven, cuyo objetivo es implementar un juego interactivo basado en la resolución de ecuaciones matemáticas.

El sistema permite al usuario ingresar ecuaciones como intentos de solución para alcanzar un resultado objetivo previamente definido, validando automáticamente el cumplimiento de las reglas del juego.

---

## 2. Objetivo del Proyecto

Desarrollar una aplicación que:

- Permita el ingreso de ecuaciones matemáticas.
- Valide el cumplimiento de reglas establecidas.
- Controle el número de intentos.
- Determine automáticamente condiciones de victoria o derrota.
- Almacene información relevante en una base de datos MySQL.

---

## 3. Tecnologías Utilizadas

- **Lenguaje de programación:** Java 17+
- **Framework gráfico:** JavaFX
- **Gestor de dependencias:** Maven
- **Base de datos:** MySQL
- **Entorno de desarrollo:** Visual Studio Code

---

## 4. Requisitos del Sistema

Para ejecutar correctamente la aplicación se requiere:

- JDK 17 o superior.
- Maven instalado y configurado.
- MySQL Server en ejecución.
- JavaFX SDK correctamente enlazado.
- IDE compatible (Visual Studio Code recomendado).

---

## 5. Funcionalidades Implementadas

- Ingreso de ecuaciones por parte del usuario.
- Registro de cada ecuación como un intento.
- Validación automática al presionar el botón correspondiente.
- Verificación de:
  - Orden correcto de operaciones.
  - Uso de números dentro del rango permitido.
  - Uso único de cada número.
- Detección y visualización de errores específicos.
- Contador de intentos realizados.
- Detección de condición de victoria.
- Detección de condición de derrota.
- Finalización automática de la partida.
- Persistencia de datos en base de datos MySQL.

---
