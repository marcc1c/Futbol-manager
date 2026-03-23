# Futbol Manager ⚽

¡Bienvenido a **Futbol Manager**, una mini‑aplicación de gestión de equipos de fútbol hecha en **Java** con complementos web y base de datos para mantener tus datos guardados entre ejecuciones.

---

## 🔎 ¿Qué encontrarás en este proyecto?

### 📂 Estructura de carpetas

- **Aplicacio/** - La aplicación principal en **Java**:
  - `src/` - Contiene el código fuente Java.
  - `archivosGuardado/` - Archivos de texto usados para almacenar equipos, jugadores y entrenadores.
  - `JavaDoc/` - Documentación generada automáticamente del código.

- **BBDD/** - Scripts SQL para crear/llenar la base de datos, útil si quieres persistir datos en un motor SQL:
  - `footbal_manager_new.sql` - Esquema y datos iniciales.

- **Web/** - Versión web / frontend para visualizar datos y páginas estáticas:
  - `index.html` - Página de entrada.
  - `css.css` - Estilos globales.
  - `Pagines/` - Páginas HTML de la aplicación (clasificación, resultados, plantillas, etc.).
  - `src/` - Recursos (imágenes, JSON, scripts) usados por la web.

---

## 🚀 Cómo ejecutar la versión Java (Aplicación de consola)

1. Abre un terminal en `Aplicacio/`.
2. Compila:

```bash
javac -d out src/*.java src/personas/*.java
```

3. Ejecuta:

```bash
java -cp out Main
```

> 💡 Consejo: Ejecuta el proyecto desde un IDE (IntelliJ IDEA, Eclipse, NetBeans) para navegar mejor por el código y usar la generación de JavaDoc.

---

## 🧠 Qué hace la aplicación Java

La aplicación es un **gestor de equipos** con estas funcionalidades principales:

- Cargar **jugadores** y **entrenadores** desde `archivosGuardado/mercat_fitxatges.txt`.
- Cargar **equipos** y sus componentes desde `archivosGuardado/guardarEquipos.txt` y los archivos individuales de cada equipo en `archivosGuardado/equipos/`.
- Menú interactivo (terminal) con opciones como:
  - Ver clasificación de la liga
  - Dar de alta equipos, jugadores y entrenadores
  - Consultar datos de equipos/jugadores
  - Simular ligas y sesiones de entrenamiento
  - Guardar los datos en disco

---

## 🌐 Qué ofrece la parte Web

La carpeta `Web/` es una versión estática con páginas que muestran:

- Clasificación de la liga
- Plantillas de equipos
- Resultados de partidos
- Formularios para crear jugadores / equipos (principalmente con JS + JSON)

Puedes abrir [Este Link](https://rbe-politecnics.github.io/Web-Football-Manager/) directamente en tu navegador para verla.

---

## 🛠️ Cómo contribuir

- Si quieres agregar nuevas funcionalidades, empieza por `Aplicacio/src/` (Java) o `Web/src/` (JS).
- Añade documentación de tus cambios en el JavaDoc (`Aplicacio/JavaDoc/`).
- Si amplías el esquema SQL, actualiza `BBDD/footbal_manager_new.sql`.

---

## 📌 Notas rápidas

- Los datos se guardan en archivos de texto plano bajo `Aplicacio/src/archivosGuardado/`.
- Para limpiar datos, borra esos archivos y reinicia la aplicación.

¡Disfruta programando tu propio **Futbol Manager**! ⚽
