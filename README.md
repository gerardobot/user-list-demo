# User List Demo

El proyecto está estructurado siguiendo los principios de Clean Architecture, con cada capa implementada como un módulo independiente, comunicados entre sí gracias a la inyección de dependencias proporcionada por Koin.
Además, para facilitar el mantenimiento, la configuración común está centralizada en el plugin Config y las diferencias están definidas en un catálogo de versiones de Gradle.

## Framework

Este módulo nute principalmente la capa de datos, pero también incluye providers y envoltorios que encapsulan entornos y librerías de terceros, de modo que se evite depender de implementaciones ajenas y se facilite el aprovechamiento del código para soluciones multiplataforma.

Un ejemplo de esto es el `ImageLoader`, que permite que la capa de UI se mantenga aeno,gnóstia frente a la implementación actual con Coil. Si en un momenton hubiera una alternativa mejor a Coil, bastarían con crear una nueva implementación con otra librería sin necesidad de cambiar el código de otras capas.

Del mismo modo, en el paquete `data.sources.local` se ha implementado una solución provisional de persistencia de datos mediante un `HashMap`, que podría reemplazarse facilmente en el futuro con una implementación que use `DataStore` o `Room`.

La conexión a los servicios se realiza mediante `Retrofit`, que se sirve de `Moshi` para el parseo de datos.

## Data

Este módulo define los interfaces de las fuentes de datos, para adaptar APIS ajenas a la lógica de dominio, e implementa los repositorios que gestionan el tratamiento de datos.

## Domain

En este módulo están definidos los modelos de las entidades, los interfaces de los repositorios y los casos de uso que determinan los requerimientos funcionales de la app.

Unos modelos de datos especiales son las clase `DomainResult` y `DomainError`. `DomainResult` es una implementación propia de la clase nativa de Kotlin `Result`, y es una variación más idiomática y práctica del habitual patrón `Either<T, R>`. 
Un `DomainResult` viene a ser un `Either` que siempre devuelve un valor `T` o un `DomainError` y que, a diferencia de `Result`, relanza las excepciones de cancelación de corrutinas.

Aunque por lo general se ha optado por evitar la herencia, `BaseUseCase` es la excepción que confirma la regla. Permite reforzar las buenas prácticas en los casos de uso, y mueve la ejecución de las corrutinas por defecto al dispatcher `IO`, liberando el hilo principal.

## App

Este módulo combina la lógica propia de una aplicación de Android con la implementación MVVM de la UI, que podría ser abstraida a su propio módulo en el caso de desear transformar la aplicación en multiplataforma.

El paquete `di` define la inyección de dependencias con `Koin`.

La lógica de navegación se basa en el `NavHost` de `Compose` y está centralizada en el paquete `Navigation`. Aunque no se ha llegado a implementar, está preparada para la inclusión de un menú lareral.

En el paquete `ui` se encuentran las distintas pantallas y componentes realizados con `Compose`, así como los `ViewModel` que gobiernan su lógica.

### UserListScreen

El listado de usuarios es la pantalla inicial, y usa el componente `UserListItem` para pintar cada elemento dentro de una `LazyColumn`. 
Se ha optado por una solución de paginación propia por la mayor flexibilidad que otorga. En cuanto el usuario hace scroll al final de la pantalla, si no se están filtrando los resultados ni se está en proceso de carga, se llama al caso de uso correspondiente para obtener una nueva página de usuarios.

### UserProfileScreen

Al seleccionar a un usuario se navega hasta su perfil. Los datos de cada usuario son almacenados en caché en el momento de su obtención, y el e-mail es empleado como identificador único del usuario para recuperar esos datos del almacenamiento local.
Al quedar fuera del alcance, los campos del perfil no son editables. Tampoco se ha llegado a implementar el mapa con la ubicación del usuario, por falta de tiempo.

