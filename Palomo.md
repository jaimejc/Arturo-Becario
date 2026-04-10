Calidad Global
•- -
+ Buen uso del patrón Singleton
- Mal uso de instanceof en Tienda.aplicarDescuentoConNotificacion, Tienda. cancelarIntercambio [todo lo que tiene que ver con notificaciones]
- SistemaNotificacion -> métodos repetitivos. Mucha parte del trabajo hay que hacerla fuera de la clase (p.ej. seleccionar los destinatarios)
- Métodos "demo" en clase Tienda, inicialización con datos de prueba en RepositorioProducto.inicializarDatosIniciales, RepositorioUsuarios
- RepositorioUsuario. agregarUsuario -> hay distintas condiciones de error 0>
lanzar excepciones mejor que devolver un boolean (idem en
ServicioPago. pagarTasacion)
- Métodos sin mucho sentido: Tienda. registrarPedido(ClienteRegistrado, Pedido)
- Mal diseño de comparadores:
ServicioBusqueda. compararProductos -> los
distintos métodos de comparación estarían mejor en el propio enumerado.
- Mal diseño jerarquía de Notificacion: muchos constructores en Notificacion que reciben parámetros que son
de los que sólo se quiere obtener un mensaje
-> crear un interfaz "Mensajeable" que los hace a todos compatibles y sólo se necesitaría un constructor. Más aún, los constructores de Notificacion comprueban que no sean null un montón de parámetros que no se usan en esa clase.
- Gestor.darDeAltaEmpleado), Gestor. revocarPermiso),
Gestor. setContraseñaEmpleado, Gestor.verListadoUsuarios, etc -> estos métodos no tiene sentido aquí (confusión de Actor con Clase)
- Empleado. crearPack, Empleado. setEstadoPedido, esSiguienteEstadoPedido, tasarProducto, cancelarIntercambio, eliminarOfertaIntercambio, verObjetosPorTasar, etc -> estos métodos no tienen sentido en clase Empleado
(confusión de Actor con Clase)
- ClienteRegistrado. aceptarOferta, rechazarOferta, cancelaroferta →> estos métodos no tiene sentido aquí (confusión de Actor con Clase)
- Código repetido en constructores de Empleado
- Pack y ProductoVenta podrían tener un tipo compatible -› evita tener varios métodos añadirProducto en Carrito, evita tener dos colecciones en Pack con subPacks y productos, etc
- paquete sistemProductos -> partir en más (sub)paquetes, por ejemplo para los descuentos, intercambios, etc.
- recomendación: no es un diseño extensible para añadir nuevos algoritmos de recomendación. El algoritmo usa números que deberían ser constantes, incluso
parametrizables. No se implementa filtrado colaborativo ni basado en similaridad de productos.

FUNCIONALIDAD:
Gestión Global
- ServicioBusqueda. buscarPorCategoria no busca por CategoriaJerarquica. Los productos no parecen usar esas categorías jerárquicas, sino un String.
- ServicioBusqueda. ordenarProductos -> usar los métodos de ordenación estándar de Java
Gestor
- Descuentos : metodos "demo" en Tienda? Aplicable a categorías jerárquicas?
Comprobación de no producto con varios descuentos?. configurarRegaloPorCantidad
-> no parece que ese regalo se aplique a nada (productos, categorías, etc)
Empleado
- Asignar categorías: Solo se pueden asignar categorías a figura? Y CategoriaJerarquica: no parecen usarse para mucho. (los productos tienen un
String) [-0,2]
- Packs: mal diseño, que lo hace complejo de usar
- No hay lectura de fichero CSV de productos:
Cliente
- Cancelar pedido [cancelar pedido: manual por el cliente, y automático al pasar 48 horas sin realizar el pago (notificar al cliente), se libera el stock reservado]
- comentar y puntuar productos comprados (1--5) [no se comprueba si el cliente compró o no el producto a valorar]
- rechazar oferta => notificar al ofertante
- fin intercambio: al llegar la fecha de fin, notificar al subastador; tras 7 días desde la fecha de fin sin aceptar oferta ni prorrogar, cancelar el intercambio
- prorrogar intercambio (establecer nueva fecha de fin)

- No se implementa filtrado colaborativo ni basado en similaridad de productos
