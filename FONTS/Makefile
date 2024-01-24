JAVAC = javac
JAVA = java
JAR = jar
JAVADOC = javadoc
DOCS_DIR = ../DOCS/Documentacion

DOMINIO = Dominio/*.java
CONTROLADORESDOMINIO = Dominio/Controladores/*.java
PRESENTACION = Presentacion/*.java
CONTROLADORESPRESENTACION = Presentacion/Controladores/*.java
EXCEPCIONES = Excepciones/*.java
PERSISTENCIA = Persistencia/*.java
CONTROLADORESPERSISTENCIA = Persistencia/Controladores/*.java
TESTS = Tests/*.java
LIB = lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:lib/byte-buddy-1.12.19.jar:lib/objenesis-3.3.jar:lib/mockito-core-4.11.0.jar:lib:byte-buddy-agent-1.12.19.jar
SOURCES = $(DOMINIO) $(PRESENTACION) $(EXCEPCIONES) $(TESTS) $(CONTROLADORESDOMINIO) $(PERSISTENCIA) $(CONTROLADORESPERSISTENCIA) $(CONTROLADORESPRESENTACION)

CLASSES = $(SOURCES:.java=.class)
EXE_DIR = ../EXE/CLASS
JAR_FILE = ../EXE/APP/FastKey.jar

all: $(CLASSES)

$(CLASSES):
	$(JAVAC) -d $(EXE_DIR) -cp $(LIB) $(SOURCES)

jar: $(CLASSES)
	$(JAR) cfe $(JAR_FILE) Presentacion.Main -C $(EXE_DIR) .

run: jar
	$(JAVA) -jar $(JAR_FILE)

justrun:
	$(JAVA) -jar $(JAR_FILE)

test: $(CLASSES)
	java -cp "../EXE/CLASS/:$(LIB)" org.junit.runner.JUnitCore Tests.TestSuite

docs:
	$(JAVADOC) -classpath $(LIB) -d $(DOCS_DIR) -sourcepath . -subpackages Dominio -subpackages Presentacion Excepciones Tests -subpackages Persistencia

clean:
	rm -rf $(EXE_DIR) $(CLASSES) $(JAR_FILE) $(DOCS_DIR)
