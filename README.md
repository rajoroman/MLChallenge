# MLChallenge
 Este repositorio contiene la solución propuesta al Challenge Técnico de Mercado Libre

## INTRODUCCIÓN

La solución está desarrollada en Java 11 con el FrameWork SpringBoot.
&nbsp;

Está estructurada por tres microservicios:
&nbsp;

1.springboot-servicio-adn
&nbsp;

2.springboot-servicio-eureka-server
&nbsp;

3.springboot-servicio-zuul-server
&nbsp;

Se emplearon las herramientes EUREKA y ZUUL, para cubrir la exigencia que se menciona en el documento del challenge:

&nbsp;

      "Tener en cuenta que la API puede recibir fluctuaciones agresivas de tráfico (Entre 100 y 1
      millón de peticiones por segundo)."


&nbsp;
Con estas herramientas se pueden desplegar varias instancias del microservcio springboot-servicio-adn, para atender la demanda.

&nbsp;
La solución se desplegó en AWS junto con una instancia de base de datos MySQL para almacenar los ADN validos.
&nbsp;

## **USO DE LA API**

Para probar la API se puede emplear el software POSTMAN o similar.
&nbsp;

**SERVICIO:** mutant
&nbsp;

**DESCRIPCION:** Por medio de éste servicio se envía la cadena de ADN, y se determina si es mutante o no. Si la cadena es valida se almacena una sola vez en la base de datos.
&nbsp;

**TIPO:** POST
&nbsp;

**URL:ec2-3-8-21-133.eu-west-2.compute.amazonaws.com:8090/api/adn/mutant**
&nbsp;

**BODY:** Se debe enviar en el body de la petición un JSON con la siguiente estructura como se observa en la imagen.
&nbsp;

     {
     "dna":["ATGCGA","CTTTGC","TTATGT","AGATTG","CACCTA","TCACTG"]
     }


&nbsp;
![image](https://user-images.githubusercontent.com/51220078/113524551-01de7300-9575-11eb-8e65-7de3aeb6ee81.png)

&nbsp;
Si el ADN es mutante se recibe un código 200 y un JSON  con la siguiente estructura:


         {
             "RESULT": "true"
         }

&nbsp;
Si el ADN es no mutante se recibe un cídigo 403 y un JSON con la siguiente estructura
&nbsp;


         {
             "RESULT": "true"
         }

Si el ADN ya está registrado se recibe un JSON con la siguiente estructura y código 403:
&nbsp;

         {
             "CODE:": "403 FORBIDDEN",
             "ERROR": "DNA is previously registered"
         }

Si la petición tiene un carácter invalido se recibe un JSON con la siguiente estructura y código 403:
&nbsp;

          {
              "CODE:": "403 FORBIDDEN",
              "ERROR": "Character Invalid"
          }

&nbsp;

Si se envía una cadena de ADN con estructura invalida se recibe un JSON con la siguiente estrcutura y código 403:
&nbsp;

         {
             "CODE:": "403 FORBIDDEN",
             "ERROR": "DNA structure error length difference"
         }
&nbsp;

**SERVICIO:** stats
&nbsp;

**DESCRIPCION:** Por medio de éste servicio se obtienen las estádisticas, cantidad de mutantes, cantidad de humanos, y su ratio.
&nbsp;

**TIPO:** GET
&nbsp;

**URL:ec2-3-8-21-133.eu-west-2.compute.amazonaws.com:8090/api/adn/stats
&nbsp;

**BODY:** Sin contenido.
&nbsp;

![image](https://user-images.githubusercontent.com/51220078/113524816-d5c3f180-9576-11eb-8dba-6f1ad207b346.png)
