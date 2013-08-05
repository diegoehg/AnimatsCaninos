AnimatsCaninos
==============

Proyecto realizado durante la maestría, basado en el artículo de Pattie Maes, 
titulado _A Bottom Up Mechanism for Behavior Selection in an Artificial 
Creature_. En dicho artículo se plantea cómo seleccionar el comportamiento
activo de agentes autónomos, a los cuales Maes denomina como _animats_. 

Cada comportamiento tiene un umbral de activación, un nivel de activación, y una regla de activación. Cada regla establece
cómo varía el nivel de activación del comportamiento, en función de condicio-
nes necesarias para la activación presentes en el entorno y de la magnitud de 
los niveles de activación de los otros comportamientos.

Todos los comportamientos se encuentran ligados por medio de una red, y ca-
da enlace se clasifica en _predecesores_, _sucesores_ y _conflictores_. Los pre-
decesores son aquellos comportamientos que facilitan que las condiciones para
que otro comportamiento se dispare se presenten; los conflictores son aquellos
que inhiben dichas condiciones, y los sucesores son los comportamientos co-
rrespondientes del lado de los predecesores.

El mecanismo planteado por Maes está inspirado en cómo se comportan los
animales, con el objetivo de modelar la propiedad de la emergencia de los
comportamientos.

Este proyecto hace uso del esquema de Maes para modelar la conducta de
unos animats que simulan los comportamientos de unos perros. Los animats
aquí implementados buscan comida y agua, y cuando se encuentran con otro,
se enfurecen y llegan a pelear o a huir, si el miedo se activa con más nivel que
la ira.

Es un sistema bastante pequeño y sencillo, ya que fue realizado para una
tarea de una asignatura de Agentes computacionales. Además, tiene varios 
bugs aún, así como el código está desordenado.


El objetivo de recuperar este proyecto es mejorar el esquema planteado por 
Maes, así como incorporar otros mecanismos de selección de comportamiento. 
Además, se pretende incorporar emociones sintéticas a los animats de este 
proyecto, basándose en las ideas planteadas por Rosalynd Picard. La fase en la que se encuentra es en el análisis y diseño del sistema.

Cualquier comentario o contribución es bienvenido :)