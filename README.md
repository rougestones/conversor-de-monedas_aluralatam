# 💰 Conversor de Monedas - Aplicación de Consola en Java

Este proyecto surge como parte de un desafío del programa **ONE**.  
Consiste en una aplicación de consola desarrollada en **Java** que permite realizar conversiones de divisas en tiempo real gracias a la integración con la **ExchangeRate-API**.  
El sistema ofrece un menú interactivo con opciones predefinidas y guarda un historial de todas las operaciones realizadas.

---

## ✨ Funcionalidades
- **Tasas de Cambio Actualizadas**: Consulta valores en tiempo real mediante la API.  
- **Menú Interactivo**: Presenta 6 conversiones preconfiguradas (USD, ARS, MXN, BRL).  
- **Protección de la Clave API**: La clave se gestiona a través de un archivo `.properties` y se excluye del repositorio con `.gitignore`.  
- **Registro de Operaciones**: Cada conversión exitosa se almacena en una lista y se exporta en formato JSON (`Historial.json`) al cerrar la aplicación.  
- **Robustez ante Errores**: Implementa manejo de excepciones (`try-catch`) para problemas de red (`IOException`) o interrupciones, asegurando una salida controlada.  

---

## 🛠️ Tecnologías Utilizadas
- **Java 17 o superior**  
- **Cliente HTTP nativo**: `java.net.http.HttpClient`  
- **Serialización JSON**: [Google Gson](https://github.com/google/gson)  
- **Gestor de dependencias**: Maven o Gradle  

> Recuerda incluir la librería Gson en tu archivo de configuración (`pom.xml` o `build.gradle`).

---

## ⚙️ Instalación y Configuración
1. **Obtén tu clave de API**  
   Regístrate en [ExchangeRate-API](https://www.exchangerate-api.com/) para conseguir tu clave personal.  

2. **Crea el archivo `config.properties`**  
   Ubícalo en la raíz del proyecto (junto a la carpeta `src`).  

3. **Agrega tu clave**  
   Dentro del archivo escribe:  
   ```properties
   api.key=TU-CLAVE-DE-API
