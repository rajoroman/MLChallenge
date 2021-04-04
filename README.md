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

Para probar la API se puede emplear elsoftware POSTMAN o similar.
&nbsp;

**SERVICIO:** mutant
&nbsp;

**TIPO:** POST
&nbsp;

**URL:ec2-3-8-21-133.eu-west-2.compute.amazonaws.com:8090/api/adn/mutant**
&nbsp;

**BODY:** Se debe enviar en el body de la petición un JSON con la siguiente estructura como se observa en la imagen.
&nbsp;

![image](https://user-images.githubusercontent.com/51220078/113524513-c643a900-9574-11eb-822f-fa72dbf927c3.png)

