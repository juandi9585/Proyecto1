Proyecto 1
Estructuras de Datos

Mauricio Durán
Juan Diego Flores
David Mizrahi
--------------------------------------------------------------------------------

Instrucciones de la interfaz:

Para inicializar el grafo, se debe clickear el botón “cargar archivo”, y elegir 
el archivo con los datos del grafo que va a ser utilizado. Si el archivo es 
válido, aparecerá representado el grafo en la parte inferior de la pestaña 1. 
Ahora, se puede proceder a utilizar los demás elementos de la interfaz. 

Para buscar el número de islas, se debe elegir el tipo de recorrido en el 
comboBox de la izquierda, y clickear el botón “islas”, esto hará que aparezca 
el número de islas en el textArea directamente a la derecha.

Al clickear el botón identificar puentes, aparecerán en la lista directamente a 
la derecha del botón, aquellas aristas que son consideradas puentes, expresadas 
en forma de los ID’s de los dos usuarios que conectan.

En la pestaña 2 se puede modificar el grafo. En la lista de la parte superior, 
se puede seleccionar un usuario para ser eliminado al clickear el botón 
“eliminar”. En la parte inferior, se deben suministrar todos los datos para la 
creación de un usuario nuevo. Para añadir relaciones al usuario nuevo, se debe 
seleccionar uno o varios usuarios en la lista “Relaciones” e introducir el peso 
(duración de la relación) en el spinner “Peso”; al presionar el botón “+”, 
aparecerá el id del usuario y el peso de la relación en la lista de la derecha. 
Al clickear el botón “Agregar”, se creará el usuario con las relaciones que 
estén en la lista derecha. Si se cometió algún error introduciendo las 
relaciones, se puede seleccionar una o varias de la lista derecha y clickear el 
botón ”-” para eliminar la relación, y el usuario que estaba en dicha relación 
será regresado a la lista “Relaciones”. Todos los cambios realizados podrán ser 
visualizados en el gráfico de la pestaña 1.

Finalmente, el botón “Actualizar Repositorio” permitirá elegir un archivo, en 
el cual se escribirán los datos del grafo actual, con todos los cambios 
realizados.