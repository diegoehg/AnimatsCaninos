AnimatsCaninos
==============

Este es un proyecto basado en los artículos de Pattie Maes[1,2], en donde
plantea cómo seleccionar el comportamiento activo de agentes autónomos, los
cuales denomina como _animats_.

Cada comportamiento tiene un umbral de activación, un nivel de activación, y una
regla de activación. Cada regla establece cómo varía el nivel de activación del
comportamiento, en función de condiciones necesarias para la activación 
presentes en el entorno y de la magnitud de los niveles de activación de los
otros comportamientos.

Todos los comportamientos se encuentran ligados por medio de una red, y cada
enlace se clasifica en _predecesores_, _sucesores_ y _conflictores_. Los
predecesores son aquellos comportamientos que facilitan que las condiciones para
que otro comportamiento se dispare se presenten; los conflictores son aquellos
que inhiben dichas condiciones, y los sucesores son los comportamientos
correspondientes del lado de los predecesores.

El mecanismo planteado por Maes está inspirado en cómo se comportan los
animales, con el objetivo de modelar la propiedad de la emergencia de los
comportamientos.

Este proyecto hacía uso del esquema de Maes para modelar la conducta de unos
animats que simulan los comportamientos de unos perros. Los animats aquí
implementados buscan comida y agua, y cuando se encuentran con otro, se
enfurecen y llegan a pelear o a huir, si el miedo se activa con más nivel que la
ira.

Es un sistema bastante pequeño y sencillo, ya que fue realizado para una tarea
de una asignatura de Agentes computacionales. Tiene varios bugs aún, así como el
código está desordenado. Esta fue la versión que pude recuperar del proyecto.

El objetivo de recuperar este proyecto era mejorar el esquema planteado por
Maes, así como incorporar otros mecanismos de selección de comportamiento. En la
actualidad, solo pretendo tener una base de código la cual limpiar y aplicar lo
que he aprendido durante los años. La primer tarea es actualizarlo y tener una
versión funcional.

Cualquier comentario o contribución es bienvenido :)


Referencias bibliográficas.
---------------------------

[1] Maes, P. "How to do the right thing." _Connection Science_ 1(3):291-323.

[2] Pattie M. 1991. "A bottom-up mechanism for behavior selection in an artificial creature". En _Proceedings of the first international conference on simulation of adaptive behavior on From animals to animats_, Jean-Arcady Meyer and Stewart W. Wilson (Eds.). MIT Press, USA, 238-246.
