# 📘 Registro Académico

Aplicación de escritorio en **Java Swing** con **Maven**, que permite gestionar estudiantes, calcular promedios de notas, guardar/cargar datos en CSV y manejar un login seguro.  

![Java](https://img.shields.io/badge/Java-17-blue)  
![Maven](https://img.shields.io/badge/Maven-Build-orange)  
![Swing](https://img.shields.io/badge/Swing-UI-green)  

---

## 🚀 Características

- **Login con validación** de usuario y contraseña (máx. 3 intentos).  
- **Registro de estudiantes** con:
  - Nombre  
  - Edad  
  - Email generado automáticamente  
  - Tres notas  
- **Cálculo automático**:
  - Promedio (redondeado a 2 decimales)  
  - Nota máxima y mínima  
  - Estado de aprobación (✅ Aprobado / ❌ Reprobado)  
- **Gestión de datos en CSV**:
  - Guardar lista de estudiantes  
  - Cargar estudiantes previamente registrados  
- **Interfaz amigable** con Swing y tabla dinámica.  

---

## 📂 Estructura del Proyecto

```bash
RegistroAcademico/
 ├── src/main/java
 │   ├── com/mycompany/academico/domain   # Clases de dominio (Estudiante, Nota)
 │   ├── com/mycompany/academico/service  # Servicios (PromedioCalculo, CSVarchivo)
 │   ├── sd/registroacademico/ui          # UI (LoginFrame, RegistroEstudianteFrame)
 │   └── sd/registroacademico             # Clase principal RegistroAcademico
 ├── pom.xml
 └── README.md
```

---

## ⚙️ Requisitos

- **Java 17** o superior  
- **Maven 3.8+**  

Verifica versiones:  
```bash
java -version
mvn -v
```

---

## 🛠️ Instalación y Ejecución

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/TU_USUARIO/RegistroAcademico.git
   cd RegistroAcademico
   ```

2. Compilar el proyecto:
   ```bash
   mvn clean compile
   ```

3. Generar el JAR ejecutable:
   ```bash
   mvn package
   ```

   Esto crea:
   ```
   target/RegistroAcademico-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

4. Ejecutar la aplicación:
   ```bash
   java -jar target/RegistroAcademico-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

---

## 🎮 Uso

1. **Login**  
   - Usuario: `User`  
   - Contraseña: `riwi941`  

2. **Registro de Estudiantes**  
   - Ingresar nombre, edad y notas.  
   - Calcular → Ver promedio, máxima, mínima y estado.  
   - Guardar → Añadir estudiante a la tabla.  
   - Guardar CSV → Exportar lista a archivo.  
   - Cargar CSV → Importar estudiantes previos.  

---

## 👨‍💻 Autor

- **AlexWason** (Proyecto académico con Java & Swing)  
- 🚀 Inspirado para aprender **POO, Swing y manejo de archivos CSV**  
