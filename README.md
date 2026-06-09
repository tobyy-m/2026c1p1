# 1er Parcial de Objetos II - Com. 1 y 2

---

> [!IMPORTANT]
> 👀 Leer todo antes de comenzar a resolver


## ⚙️ Datos necesarios y obligatorios a completar

* **APELLIDO, NOMBRE**: Mercuri Tobías Nahuel
* **COMISIÓN**: 1
* **DNI**: 46695812

---

## 📝Consideraciones Iniciales y Criterio de evaluación

>Se evaluará cada solución prestando especial atención a:   

- **Pautas obligatorias** (descritas abajo) correctamente cumplidas.
- **Entendimiento y correcta aplicación de los conceptos vistos en la cursada**: Solo los patrones de diseño vistos (Strategy|emplate Method|Singleto|Decorator), reificación, manejo de excepciones, test unitarios: **GWT** y **AAA**.
- **Prolijidad y legibilidad** del código presentado.
- Se realizará un control exhaustivo, incluyendo distintas herramientas de análisis estático de código para identificar posibles copias entre las soluciones entregadas, incluyendo el uso de IA.
- La solución debe aplicar patrones de diseño apropiados para la problemática planteada. **El uso inadecuado de patrones descalifica el examen automáticamente**.
- El código entregado debe tener los tests suficientes que garanticen el correcto funcionamiento de la solución propuesta por el alumno (*esperado 75%+*).
- No se aceptan entregas fuera de plazo ni que no estén correctamente subidas al repositorio de GitHub de la materia.
- Las entregas que tengan un solo commit o no reflejen el progreso del proceso de solución serán desaprobadas. **Se recomienda fuertemente realizar commits/push periódicamente y asegurarse de que impactaron correctamente en el repositorio remoto**.

## 📌 Pautas obligatorias para la entrega

> Utilizaremos un sistema de 3 'checkpoints', a saber:

- :warning: El código entregado debe compilar obligatoriamente. **Un parcial entregado cuyo código no compila queda desaprobado automáticamente**.

- **Checkpoint 1**: Push inicial. Clonar el repositorio remoto, modificar este archivo en la parte superior registrando **APELLIDO, NOMBRE**, **COMISIÓN** y **DNI** con sus datos, y hacer un primer push.
- **Checkpoint 2**: Push antes de realizar el primer test.
- **Checkpoint 3**: Push al final de la entrega, al terminar sus test.

> [!NOTE]
> *Este es el mínimo requerido, pero puede (y es recomendable) hacer más pushes para estar cubiert@ ante cualquier imprevisto. El último push es el código que se corrige, pero se revisa todo el flujo de trabajo.*

> [!Warning]
>**ESTAS PAUTAS SON OBLIGATORIAS, DE NO CUMPLIRLAS, AUNQUE LA SOLUCIÓN ESTÉ PERFECTA, NO APROBARÁ EL EXAMEN** ‼️

---

# ✈️ Agencia de Viajes y Paquetes Turísticos 🌍

Una agencia de viajes nos pidió que modelemos un sistema que le permita recomendar paquetes turísticos a sus clientes.
La agencia quiere asegurarse de que cada cliente reciba una experiencia acorde a sus intereses y necesidades 😎.

---

# 🎯 ¿Te interesa el viaje?

Cada cliente conoce el **criterio de paquetes** que le interesa. Existen los siguientes criterios:

* 🏖️ **Viajero Flexible**: cualquier paquete le interesa. Es el criterio por defecto de todos los clientes.

* 💸 **Viajes Económicos**: le interesan los paquetes cuyo precio total sea menor a un cierto valor máximo configurable.

* 👷 **Viajero Sindical**: solo le interesan los paquetes que tienen descuento por afiliación a algún sindicato y también los económicos.

* 💫 **Premium**: le interesan únicamente los paquetes ofrecidos por agencias asociadas preferidas.
  El criterio debe permitir configurar varias agencias preferidas (ej: “Travel World”, “FlyDreams”, “Ruta Sur”).

* 🏛️ **Destinos Históricos**: le interesan los paquetes que incluyan destinos con más de 300 años de relevancia histórica.

---

# 🧳 Paquetes Turísticos

Los paquetes turísticos tienen definido:

* 📛 nombre
* 🏢 agencia organizadora
* 💰 precio base
* 🕰️ años de relevancia histórica del destino

Por ahora solo existen paquetes turísticos estándar (podemos llamarle simplemente *Paquete*), aunque más adelante podrían aparecer nuevos tipos.

Un paquete se considera **histórico** si el destino posee al menos 300 años de relevancia histórica.

---

# ➕ Personalización del Viaje

La agencia puede mejorar dinámicamente un paquete agregándole distintos extras según el cliente.

Actualmente se conocen los siguientes extras:

* 🚐 traslado VIP → +250
* 🍽️ excursiones gastronómicas → +180
* 🏨 upgrade de hotel → +320
* 🧭 guía acompañante → +200

Más adelante podrían agregarse nuevos extras y el sistema debe soportarlo sin romper código existente.

---

# 👥 Clientes

De los clientes solo nos interesa conocer:

* 🪪 DNI
* 🎯 criterio de paquetes que le interesan

Cada cliente puede cambiar su criterio cuando lo desee.

Su DNI, en cambio, nunca puede modificarse.

Además, cada cliente lleva registro de los paquetes turísticos recibidos 📜.

Cada cliente debe poder informar la lista de sus paquetes recibidos, incluyendo el precio total de cada uno.

---

# 🧑‍💼 La Agencia de Viajes

La agencia de viajes es quien administra el sistema y posee la lista de paquetes turísticos disponibles 📒.

Se permite agregar nuevos paquetes.

---

# 💡 Proceso de Recomendación

Cuando la agencia ejecuta la acción de **atender a un cliente** ocurre lo siguiente:

1. Busca el **primer paquete adecuado** para el cliente.
2. Según la estrategia definida por la agencia, puede agregar uno o varios extras al paquete antes de entregarlo.
3. Finalmente, el paquete personalizado queda registrado en el historial del cliente.

---

# ⚠️ Situaciones Excepcionales

## ❌ Cliente sin criterio configurado

Si un cliente intenta ser atendido sin criterio configurado, se debe lanzar:

`ClienteSinCriterioException`


## ❌ Paquete inválido

No se puede crear un paquete:

* con precio negativo,
* con nombre vacío,
* o con años históricos negativos.

Debe lanzarse:

`PaqueteInvalidoException`


## ❌ Extra incompatible

Algunos extras podrían ser incompatibles con ciertos paquetes.
Por ejemplo:

* traslado VIP solo disponible para paquetes internacionales,
* upgrade hotel no disponible en paquetes sin alojamiento.

Debe lanzarse:

`ExtraNoDisponibleException`

---

# 🏗️ Restricciones de Diseño

* No se permite modificar los paquetes originales cada vez que se agregan extras.

* El sistema debe quedar preparado para incorporar nuevos extras turísticos en el futuro sin modificar código existente.

* Las responsabilidades deben estar correctamente distribuidas entre objetos.

---
<!--
# ✅ Lo que se evaluará

* Correcta aplicación de programación orientada a objetos.
* Uso apropiado de patrones de diseño.
* Buena distribución de responsabilidades.
* Manejo adecuado de excepciones.
* Tests claros y representativos.
-->
