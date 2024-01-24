Si quiere una version más detallada de como funciona la aplicación puede consultar el manual de usuario en la carpeta
DOCS/ManualUsuario.pdf

----CÓMO COMPILAR Y EJECUTAR----

Para compilar el proyecto ejecute el siguiente comando:
$ make all
Esto creará el .jar con la aplicación y lo guardará en la carpeta EXE/APP/FastKey.jar

Para ejecutar el proyecto ejecute el siguiente comando:
$ make justrun

Si quiere compilar y ejecutar el proyecto todo a la vez ejecute:
$ make run

Si quiere ejecutar los tests hagalo con el siguiente comando:
$ make test

Si quiere crear la documentación ejecute el siguiente comando:
$ make docs
Esto creará toda la documentación en la carpeta DOCS/Documentacion
Si quiere verla ejecute con su navegador preferido el archivo DOCS/Documentacion/index.html

Si quiere limpiar el proyecto ejecute el siguiente comando:
$ make clean

----CÓMO UTILIZAR EL PROGRAMA----

Una vez ejecute el programa verá un menú con tres opciones:
1. Crear un teclado
2. Cargar un teclado
3. Salir

Desde la opción de crear teclado puede crear entradas y modificarlas manualmente o cargarlas desde un fichero.
También puede renombrar las entradas y borrarlas. Se aceptan dos tipos de entradas: textos y listas de palabras.

Para crear un teclado siga los siguientes pasos:
1. Seleccione la opción 1 del menú principal.
2. Pulsar a seleccionar entradas
3. Cree y seleccione las entradas que quiera
4. Escoja un alfabeto
5. Escoja un algoritmo
6. Ponga un nombre al teclado
7. Cree el teclado

Una vez creado el teclado puede renombrarlo, editarlo y crear otros teclados para compararlo con el que se ha
creado y asi conseguir la versión más eficiente posible.

----PRUEBAS----

Como se ha comentado anteriormente, se pueden ejecutar los tests con el comando:
$ make test

Si se quieren ver los juegos de prueba estan planteados en el fichero EXE/JuegosDePrueba/resultadosJuegosDePrueba.txt
