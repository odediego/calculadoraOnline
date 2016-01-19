Feature: Calculo de pruebas de una calculadora online

  Background: 
    Given Accedo a la calculadora online

  @operacionSimple
  Scenario Outline: Se comprueba operaciones simples con dos numeros.
    Given Elegimos el numero  "<numero1>" y "<numero2>"
    When Realizo una "<operacion>"
    Then El resultado es "<resultado>"

    Examples: 
      | numero1 | numero2 | operacion | resultado |
      | 1       | 2       | SUMA      | 3         |
      | 5       | 1       | RESTA     | 4         |
      | 20      | 5       | DIVISION  | 4         |

  @operacionesMultiples
  Scenario Outline: Se comprueba operaciones con multiples numeros.
    Given Queremos operar con los siguientes numeros :
      | 200 |
      | 10  |
      | 5   |
    When La operacion realizada es "<operacion>"
    Then El resultado es "<resultado>"

    Examples: 
      | operacion      | resultado |
      | SUMA           | 215       |
      | RESTA          | 185       |
      | MULTIPLICACION | 10000     |
