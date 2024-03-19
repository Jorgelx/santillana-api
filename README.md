# santillana-api

Desarrollo de la funcionalidad definida en el siguiente enlace en SpringBoot (Java): https://codeforces.com/problemset/problem/1374/A.
Desarrollo de un API con un servicio GET, y otro POST, que permita enviar los parámetros de entrada (x, y, n) necesarios para ejecutar la funcionalidad indicada anteriormente y que devuelva una respuesta con la salida de esa misma funcionalidad en Java 17.
Formato del cuerpo de la petición y de la respuesta: puede ser XML o JSON.
Incluyendo test unitarios de la funcionalidad desarrollada.

# Descripción de la prueba

You are given three integers x,y and n. Your task is to find the maximum integer k such that 0≤k≤n that kmodx=y, where mod is modulo operation. Many programming languages use percent operator % to implement it.

In other words, with given x,y and n you need to find the maximum possible integer from 0 to n that has the remainder y modulo x.

You have to answer t independent test cases. It is guaranteed that such k exists for each test case.

Input: 
    The first line of the input contains one integer t
     (1≤t≤5⋅104) — the number of test cases. The next t lines contain test cases.

    The only line of the test case contains three integers x,y and n
     (2≤x≤109; 0≤y<x; y≤n≤109).

    It can be shown that such k always exists under the given constraints.

Output
For each test case, print the answer — maximum non-negative integer k such that 0≤k≤n and kmodx=y. It is guaranteed that the answer always exists.

Example Input:
    7
    7 5 12345
    5 0 4
    10 5 15
    17 8 54321
    499999993 9 1000000000
    10 5 187
    2 0 999999999

Example Output:
    12339
    0
    15
    54306
    999999995
    185
    999999998

Note
In the first test case of the example, the answer is 12339=7⋅1762+5
 (thus, 12339mod7=5). It is obvious that there is no greater integer not exceeding 12345 which has the remainder 5 modulo 7.
 
 # Servicio REST:
 
 La aplicación consta de 2 endpoints, la llamada GET recibe las variables x, y, n y devuelve el valor de k para la operación. La llamada POST recibe varias variables para diferentes calculos.
 
 Ejemplos de llamadas:
 GET:
 http://localhost:8080/calculatek?x=20&y=3&n=2000
 Response:
 1983
 
 POST:
 http://localhost:8080/calculatek
 
 Body:
 [
    {
      "x": 5,
      "y": 3,
      "n": 100
    },
    {
      "x": 10,
      "y": 2,
      "n": 50
    },
    {
      "x": 15,
      "y": 1,
      "n": 200
    }
]

Response:
[
    98,
    42,
    196
]

# Validaciones
Las validaciones de los campos se han realizado utilizando las anotaciones @Valid de springboot. En el caso de no validar los campos en la llamada GET se devolvera un listado de los errores encontrados.

Ejemplo GET (x=1&y=2&n=10):
    La mísma llamada contiene 2 errores :
        y no puede ser mayor que x
        x=1, x no puede ser menor que 2
        
Ejemplo POST:
    Se reportan los errores en las validaciones indicando cuales de ellas tienen problemas:
    Input: 
    [
        {
          "x": 1,
          "y": 3,
          "n": 100
        },
        {
          "x": 10,
          "y": 20,
          "n": 50
        },
        {
          "x": 15,
          "y": 2,
          "n": 200
        }
    ]
    Errores:
    Parámetros incorrectos, operación 0: x=1, x no puede ser menor que 2
    Parámetros incorrectos, operación 0: y no puede ser mayor que x
    Parámetros incorrectos, operación 1: y no puede ser mayor que x
    
    
Estas validaciones pueden mejorarse y reportar errores de las operaciones que no tengan parametros validos, pero reportando el resultado de los que si, para ello desarrollaria unas validaciones mas manuales para tenerlas controladas sin utilizar las anotaciones @Valid de springboot.


# Pruebas
Se han desarrollado 2 clases de pruebas senillas utilizando JUnit:

SantillanaApplicationTests
test() -> Prueba sencilla que comprueba el calculo de 1 operación, comprobando que el resultado correspone a la operación inversa de la formula, es decir, que el resultado de x múltiplicado por k/x + 1 sea igual a k.
test2() -> Comprueba que los valores aportados en la prueba coinciden.

RandomRepeatTeast
randomTests() -> Repite n pruebas parametrizadas por la constante RANDOM_TESTS utilizando números random basados en las validaciones aportadas.