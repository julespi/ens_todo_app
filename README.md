# Scripts

Se crearon dos script para instalar las dependencias tanto de backend como de frontend. Se debe ejecutar con privilegios sudo y en entornos aislados, para evitar conflictos de dependencia.

## Backend

### Requirements

* Java 1.8.0 (Java 8)
* Spring Boot 2.5.6
* Gradle 6.9.1
* Base de Datos H2

### Script

El script consta de dos etapas: la primera es instalar las dependencias requeridas y luego constriur la aplicación y ejecutarla en segundo plano.

### Opciones del script
```bash
sudo ./run-server.sh -h

```
```
Syntax: run-server.sh [-h|k|r]
options:
-h     Help.
-r     Build and Run Spring Boot App.
-k     Kill all instances of Spring Boot App.

```

### Instalación de dependencias, empaquetado del war y ejecución en backgound
```bash
sudo ./run-server.sh -r
```
```
...
Gradle Installed
All requirements satisfied
> Task :clean
> Task :compileJava
> Task :processResources
> Task :classes
> Task :bootWarMainClassName
> Task :bootWar
> Task :war
> Task :assemble
> Task :compileTestJava NO-SOURCE
> Task :processTestResources NO-SOURCE
> Task :testClasses UP-TO-DATE
> Task :test NO-SOURCE
> Task :check UP-TO-DATE
> Task :build

BUILD SUCCESSFUL in 2s
6 actionable tasks: 6 executed
kill: (23929): No such process
Exceute the next command to follow the logger: tail -f nohup.out
nohup: appending output to 'nohup.out'
```
### Seguir el loggeo
```bash
tail -f nohup.out
```

## Frontend

### Requirements

* Node 14.18.1
* npm 6.14.15
* Vue 2.5.2
* axios 0.21.4
* bootstrap-vue 0.21.4

### Script

El script consta de dos etapas: la primera es instalar las dependencias requeridas y lluego ejecutar el entorno de desarrollo de la app desarrollada en Vue.

### Opciones del script
```bash
sudo ./run-server.sh -h
```
```
Syntax: run-server.sh [-h|k|r]
options:
-h     Help.
-r     Build and Run Front End App
-k     Kill all instances of Front End App.

```
### Instalación de dependencias, ejecución en background
```bash
sudo ./run-server.sh -r
```
```
...
npm WARN notsup SKIPPING OPTIONAL DEPENDENCY: Unsupported platform for fsevents@1.2.13: wanted {"os":"darwin","arch":"any"} (current: {"os":"linux","arch":"x64"})

audited 1416 packages in 5.929s

65 packages are looking for funding
  run `npm fund` for details

found 33 vulnerabilities (16 moderate, 17 high)
  run `npm audit fix` to fix them, or `npm audit` for details
Dependencies installed
kill: (25264): No such process
Exceute the next command to follow the logger: tail -f nohup.out
nohup: appending output to 'nohup.out
```

### Seguir el loggeo
```bash
tail -f nohup.out
```

## Terminar ejecución Backend y FrontEnd
```bash
sudo ./run-server.sh -k
sudo ./run-server-fe.sh -k
```
```
Spring Boot App Finished
```
```
Front End App Finished
```
