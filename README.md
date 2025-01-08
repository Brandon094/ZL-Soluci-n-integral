# ZL Solución Integral

ZL Solución Integral es un software de gestión empresarial que facilita la administración de usuarios, productos, ventas e informes. Desarrollado en **Java 17** con interfaz gráfica **Swing** y una base de datos **SQLite**, ofrece un sistema de roles y permite un manejo eficiente de datos a través de módulos especializados.

## Requisitos para ejecutar la aplicación

Para poder ejecutar esta aplicación, es necesario tener instalado en el equipo el **Java Development Kit (JDK) 17** o una versión compatible con Java 17, ya que la aplicación utiliza características específicas de esta versión.

### Pasos para instalar Java Development Kit (JDK 17)

1. **Descargar JDK:**
   - Visita la página oficial de Oracle u OpenJDK para descargar el JDK: [Descargar JDK](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
   - Selecciona la versión de JDK compatible con tu sistema operativo (Windows, macOS o Linux).
   - Asegúrate de elegir la versión de 32 bits si tu sistema es de 32 bits, o de 64 bits si tu sistema es de 64 bits.

2. **Instalar JDK:**
   - Ejecuta el instalador descargado.
   - Sigue las instrucciones proporcionadas por el instalador.
   - Acepta los términos y condiciones de la licencia cuando se te solicite.
   - El instalador configurará automáticamente las variables de entorno necesarias.

3. **Verificar la instalación del JDK:**
   - Abre una terminal o símbolo del sistema (cmd en Windows).
   - Escribe el siguiente comando para verificar que el JDK está instalado correctamente:
     ```bash
     java -version
     ```
   - Deberías ver una salida que muestre la versión de Java instalada, algo como:
     ```bash
     java version "17.0.x"
     ```

### Base de datos

La base de datos de **ZL Solución Integral** está almacenada en el archivo `base_de_datos.sqlite`, ubicado en la raíz del proyecto. Este archivo contiene los datos iniciales necesarios para el funcionamiento de la aplicación.

### Ejecutar la aplicación

1. **Ejecutar la aplicación desde el archivo `.jar`**
   - Asegúrate de que el JDK 17 esté instalado correctamente.
   - Haz doble clic en el archivo `.jar` de la aplicación, `ZL_Solución_Integral-17.0.jar`.

2. **Ejecutar la aplicación desde la terminal:**
   - Si tu sistema no abre el archivo `.jar` directamente, puedes ejecutarlo desde la terminal o símbolo del sistema con el siguiente comando:
     ```bash
     java -jar ZL_Solución_Integral-17.0.jar
     ```

3. **Problemas comunes al ejecutar:**
   - Si la aplicación no se ejecuta, verifica que:
     - El JDK 17 está instalado y configurado correctamente.
     - El archivo `.jar` no está dañado.
     - No hay bloqueos del sistema operativo o antivirus.

Una vez seguidos estos pasos, la aplicación debería ejecutarse correctamente en tu equipo.
