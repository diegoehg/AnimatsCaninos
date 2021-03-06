# AnimatsCaninos

Este es un proyecto basado en un par de artículos[1,2] donde plantea cómo seleccionar el comportamiento activo de agentes autónomos, los cuales son denominados como _animats_. El mecanismo planteado por Maes está inspirado en cómo se comportan los animales, y el objetivo era modelar la propiedad de emergencia de comportamientos.

Este proyecto hace uso del esquema de Maes para modelar la conducta de unos animats que simulan los comportamientos de unos perros. Los animats aquí implementados buscan comida y agua, y cuando se encuentran con otro, se enfurecen y llegan a pelear o a huir.

Es un sistema bastante pequeño y sencillo, ya que fue realizado para una tarea de una asignatura de Agentes computacionales. Tiene varios bugs aún, así como el código está desordenado. Esta fue la versión que pude recuperar del proyecto, hay partes del código que se han perdido.

El objetivo de recuperar este proyecto era mejorar el esquema planteado por Maes, así como incorporar otros mecanismos de selección de comportamiento. En la actualidad, solo pretendo tener una base de código la cual limpiar y aplicar lo que he aprendido durante los años. La primer tarea es actualizarlo y tener una versión funcional.

Cualquier contribución es bienvenida :)


## Modelo de selección de comportamientos

Todos los animats tienen diversos comportamientos. Cada comportamiento tiene:

* un nivel de activación: es un valor que determina hasta que punto un comportamiento ha sido estimulado o inhibido
* un umbral de activación: es un valor que determina a partir de que nivel un comportamiento puede ser activado y ejecutado por el animat
* una regla de activación: establece cómo el entorno y el estado interno del animat estimulan o inhiben el nivel de activación

Cuando el nivel de activación de un comportamiento sobrepasa un umbral, este comportamiento es activado y ejecutado por el animat. i. e., se vuelve el comportamiento activo. El animat se mantiene con dicho comportamiento hasta que su nivel de activación baja y es reemplazado por otro comportamiento.

Las reglas de activación establecen cómo varía el nivel de activación del comportamiento, en función de condiciones necesarias presentes en el entorno, y de la magnitud de los niveles de activación de los otros comportamientos.

De acuerdo al entorno, las condiciones pueden estimular o inhibir un comportamiento. E. g., cuando hay comida cercana, la regla de activación del comportamiento _Alimentarse_ aumentará su nivel de activación.

En el caso del estado interno, el modelo de Maes establece a los comportamientos como nodos de un grafo cuyos vértices se clasifican en _predecesores_, _sucesores_ y _conflictores_.

- Los enlaces predecesores son aquellos comportamientos que favorecen las condiciones para que otro comportamiento se dispare (e. g. el comportamiento _Desplazarse_ favorece las condiciones del comportamiento _Alimentarse_).
- Los enlaces conflictores son aquellos que inhiben dichas condiciones (e. g. el comportamiento _Alimentarse_ inhibe el comportamiento de _Luchar_).
- Los sucesores son los comportamientos correspondientes del lado de los predecesores.


## Referencias bibliográficas.

[1] Maes, P. "How to do the right thing." _Connection Science_ 1(3):291-323.
[2] Pattie M. 1991. "A bottom-up mechanism for behavior selection in an artificial creature". En _Proceedings of the first international conference on simulation of adaptive behavior on From animals to animats_, Jean-Arcady Meyer and Stewart W. Wilson (Eds.). MIT Press, USA, 238-246.

