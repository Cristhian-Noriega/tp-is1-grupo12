# Reglas

El siguiente documento contienes las reglas que se pueden aplicar en el archivo rules.json
con sus correspondientes campos.

El archivo rules.json consiste en una lista de objetos json de reglas. Para aprobar
un pedido se deben cumplir todas las reglas descritas en el archivo.

Cabe aclarar que todas las reglas poseen los campos comunes:
- "regla", que aclara el tipo de regla a aplicar sobre los productos
- "mensaje_error", aclara el mensaje de error que se devolvera en caso de que la 
regla no se cumpla y el pedido no se apruebe.

Ambos campos deben encontrarse al crear el programa, mas los campos que corresponden
a la propia regla

## Regla Or ("regla" : "or")

```json
  {
    "regla": "or",
    "reglas": [
      {
        "regla1" : "regla1"
      },
      {
        "regla2" : "regla2"
      }
    ],
    "mensaje_error": "No se cumple ninguna de las reglas internas: "
  }
```

### Campos:
- "reglas": es una lista que contiene otras reglas definidas. Dichas reglas también
pueden ser nuevas reglas Or o reglas And.

La regla or es un contenedor de reglas, para que la regla or apruebe el pedido alguna
de las reglas en su lista de reglas debe de devolver true.

En caso de que la regla or no se cumpla, devuelve su propio mensaje de error concatenado
con los mensajes de error de las reglas que no se cumplieron.

## Regla And ("regla" : "and")

```json
  {
    "regla": "and",
    "reglas": [
      {
        "regla1" : "regla1"
      },
      {
        "regla2" : "regla2"
      }
    ],
    "mensaje_error": "No se cumple todas las reglas internas: "
  }
```

### Campos:
- "reglas": es una lista que contiene otras reglas definidas. Dichas reglas también
  pueden ser nuevas reglas And o reglas Or.

La regla and es un contenedor de reglas, para que la regla or apruebe el pedido todas
las reglas en su lista de reglas deben de devolver true.

En caso de que alguna de las reglas dentro del and no se cumpla, 
devuelve su propio mensaje de error concatenado con los mensajes de error de las 
reglas que no se cumplieron.

## Regla Max Products / Min Products ("regla" : "max_products" / "regla" : "min_products")

```json
[
  {
    "regla": "min_products",
    "valor": 4,
    "mensaje_error": "Deben haber minimo 4 productos en el pedido"
  },
  {
    "regla": "max_products",
    "valor": 20,
    "mensaje_error": "Deben haber como mucho 20 productos en el pedido"
  }
]
```

### Campos:
- "valor": es un valor de tipo Int que representa la cantidad maxima o minima de
productos que puede haber en el pedido.

La regla Max Products marca la cantidad maxima de productos que puede
haber en un pedido, sin importar atributos como tamano, peso, etc.
La regla Min Products funciona igual, pero establece una cantidad minima de productos

## Regla Max String / Min String ("regla" : "<=_str" / "regla" : ">=_str")

```json
[
  {
    "regla": "<=_str",
    "atributo_afectado": "color",
    "valor_atributo": "rojo",
    "valor": 3,
    "mensaje_error": "Máximo 3 productos de color rojo."
  },
  {
    "regla": ">=_str",
    "atributo_afectado": "color",
    "valor_atributo": "amarillo",
    "valor": 1,
    "mensaje_error": "Minimo 1 producto de color amarillo."
  }
]
```

### Campos:
- "valor": es un valor de tipo Int que representa la cantidad maxima o minima de
  productos con el valor del atributo elegido que puede haber en el pedido.
- "atributo_afectado": es un valor string que representa el atributo que la regla
afecta. El valor del atributo afectado debe de ser un String (como color, categoria, etc).
- "valor_atributo": es el valor del atributo que estamos buscando sobre el cual
aplicamos la regla de Max String o Min String

La regla Max String marca la cantidad maxima de productos que pueden tener un
atributo con un valor especifico. Por ejemplo, marca la cantidad maxima de productos
con el atributo color y valor rojo.
La regla Min String funciona igual, pero establece una cantidad minima de productos
con dicho atributo y valor.

## Regla Max Total Int / Min Total Int ("regla" : "<=_int" / "regla" : ">=_int")

```json
[
  {
    "regla": "<=_int",
    "atributo_afectado": "peso",
    "valor": 100,
    "mensaje_error": "El peso total de los productos debe ser menos de 100 kg."
  },
  {
    "regla": ">=_int",
    "atributo_afectado": "peso",
    "valor": 5,
    "mensaje_error": "El peso total de los productos debe ser al menos 5 kg."
  }
]
```

### Campos:
- "valor": es un valor de tipo Int que representa la cantidad maxima o minima del atributo
 afectado que puede haber.
- "atributo_afectado": es un valor string que representa el atributo que la regla
  afecta. El valor del atributo afectado debe de ser un Int o un Double (como precio, peso, etc).

La regla Max Total Int marca la cantidad maxima de valor conjunto de un atributo dentro de
todos los productos del pedido. Se realiza una sumatoria del valor del atributo de un producto
multiplicado por la cantidad de productos del mismo tipo en el pedido. Al final se compara
con el valor maximo estimado. Es muy util para revisar pesos maximos de pedidos.
La regla Min Total Int funciona igual, pero establece un valor minimo que se debe cumplir.
Es muy util para establecer precios minimos de pedidos.

## Regla Max Individual Int / Min Individual Int ("regla" : "<_int" / "regla" : ">_int")

```json
[
  {
    "regla": ">_int",
    "atributo_afectado": "peso",
    "valor_atributo": 2,
    "mensaje_error": "Cada producto debe pesar más de 2 kg."
  },
  {
    "regla": "<_int",
    "atributo_afectado": "peso",
    "valor_atributo": 10,
    "mensaje_error": "Cada producto debe pesar menos de 10 kg."
  }
]
```

### Campos:
- "valor_atributo": es un valor de tipo Int que representa el valor maximo o minimo que el
atributo afectado puede tener.
- "atributo_afectado": es un valor string que representa el atributo que la regla
  afecta. El valor del atributo afectado debe de ser un Int o un Double (como precio, peso, etc).

La regla Max Individual Int marca el valor maximo que puede tener un atributo numerico de
un producto. Evalua que cada producto cumpla con esta regla del maximo, independientemente
de los otros productos. Es util para establecer un peso maximo para cada producto individual.
La regla Min Individual Int funciona igual, pero establece un valor minimo que se debe cumplir.
Es muy util para establecer precios minimos de cada producto.

## Regla No Mix ("regla" : "no_mix")

```json
{
  "regla": "no_mix",
  "atributo_afectado": "color",
  "valor_atributo": "rojo",
  "valor_atributo2": "azul",
  "mensaje_error": "No pueden haber productos rojos con azules."
}
```

### Campos:
- "atributo_afectado": es un valor string que representa el atributo que la regla
  afecta.
- "valor_atributo": representa un valor posible del atributo que no puede coincidir
con el atributo 2.
- "valor_atributo2": representa un valor posible del atributo que no puede coincidir
con el primer atributo.

La regla No Mix establece que dos valores de un atributo particular no pueden coincidir
dentro de un pedido.

## Regla Not In ("regla" : "not_in")

```json
{
    "regla": "not_in",
    "atributo_afectado": "color",
    "valor_atributo": "verde",
    "mensaje_error": "No deben haber productos de color verde."
}
```

### Campos:
- "atributo_afectado": es un valor string que representa el atributo que la regla
  afecta.
- "valor_atributo": representa un valor del atributo que no se puede encontrar en el
pedido.

La regla No Mix establece que no puede haber un producto con un valor particular de
un atributo en el pedido.
