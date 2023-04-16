package animatscaninos.agentes;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import animatscaninos.elementos.Plato;
import animatscaninos.elementos.PlatosFactory;
import animatscaninos.elementos.Sprite;
import animatscaninos.interfaz.Interfase;

/** Applet donde esta implementado el mundo que rodea a los Animats y que además
 *  controla los Threads que estan siendo ejecutados a cada instante */
public class Mundo extends Applet implements Runnable {
	private final static int NUMERO_MAXIMO_PLATOS = 50;

	// TODO Si es posible, implementar muros, postes y rastros de orina

	private List<Plato> platosComida = new ArrayList<>();

	private List<Plato> platosAgua = new ArrayList<>();

	private List<RectangularShape> elementosABorrar = new ArrayList<>();

	// TODO Checar si son necesarios estos atributos para la implementacion del desplazamiento
	// de Animats usando el mouse
	double lastmx, lastmy , lastx, lasty;  // Ultimas coordenadas del rectangulo

	// Marcadores de eventos
	boolean bClickAfuera, bPrimeraVez, bCrearAnimat, bPonerAgua, bPonerComida, bMatarAnimat,
			bQuitarAgua, bQuitarComida, bMonitorearAnimat, bBorrarAnimat,
			bAnimatMovido;

	// Población de Animats existentes en el mundo
	List<Animat> Perro = new ArrayList<>(); // Lista de Animats instanciados
	RoundRectangle2D PerroBorrado;
	int iNumeroAnimats;
	final static int iMaxAnimats = 10; // Máximo número de Animats que puede haber
	int iAnimatApuntado; // Animat sobre el cual se clickeo

	// Atributos necesarios para la animación usada para crear un Animat
	private RoundRectangle2D ShadowAnimat; // Sombra del Animat a punto de ser creado
	final static BasicStroke BordeBorrarShadow = new BasicStroke(3.0f); // Borde para borrar la sombra del Animat
	final static BasicStroke BordeShadow = new BasicStroke(0.5f);
	final static Color ColorBordeShadow = Color.lightGray;
	double lastShadowX, lastShadowY;
	Interfase Ventana;
	final static long serialVersionUID = 1;

	private boolean simulacionActiva = false;

	/**
	 * Inicializacion de condiciones del mundo de los Animats
	 */
	public void init() {

		setBackground(Color.white); // Inicializa el fondo de pantalla

		bPrimeraVez = true; // Inicializa banderas de estado
		bClickAfuera = true;
		bCrearAnimat = false;
		bPonerAgua = false;
		bPonerComida = false;
		bMatarAnimat = false;
		bQuitarAgua = false;
		bQuitarComida = false;
		bMonitorearAnimat = false;
		bBorrarAnimat = false;
		bAnimatMovido = false;

		platosComida.add(PlatosFactory.getPlatoComida(19, 300));
		platosComida.add(PlatosFactory.getPlatoComida(600, 50));
		platosAgua.add(PlatosFactory.getPlatoAgua(50, 70));
		platosAgua.add(PlatosFactory.getPlatoAgua(300, 70));
		platosAgua.add(PlatosFactory.getPlatoAgua(450, 280));

		// Inicializa este objeto para cuando se necesite borrar un plato de comida o agua

		iAnimatApuntado = 0;  				// Inicializa lista de Animats
		iNumeroAnimats = 0;
		PerroBorrado = new RoundRectangle2D.Double(0, 0, Animat.ANCHO_ANIMAT + 2, Animat.ALTURA_ANIMAT + 2, 4, 4);

		// Inicializa sombra de Animat
		ShadowAnimat = new RoundRectangle2D.Double(0, 0, Animat.ANCHO_ANIMAT, Animat.ALTURA_ANIMAT, 4, 4);

		start(); // Dispara el Thread timer

	}

	public Mundo() {
		// Agregando los listeners para el mouse:
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				double x = e.getX(), y = e.getY();

				// Verifica banderas
				if(bCrearAnimat) CrearAnimat(e);
				else if(bMatarAnimat) MatarAnimat(e);
				else if(bPonerComida) {
					putPlatoComida(x, y);
					bPonerComida = false;
				}
				else if(bPonerAgua) {
					putPlatoAgua(x, y);
					bPonerAgua = false;
				}
				else if(bQuitarComida) {
					removePlatoComida(x, y);
					bQuitarComida = false;
				}
				else if(bQuitarAgua) {
					removePlatoAgua(x, y);
					bQuitarAgua = false;
				}
				else if(bMonitorearAnimat) MonitorearAnimat(e);
				else {
					Perro.stream().filter(a -> a.getContorno().contains(e.getX(), e.getY())).findAny().ifPresent(a -> {
						a.stop();
						iAnimatApuntado = Perro.indexOf(a);
						lastmx = a.getX() - e.getX();
						lastmy = a.getY() - e.getY();
						updateLocation(e);
						bClickAfuera = false;
						bAnimatMovido = true;
					});
				}
			}
			public void mouseReleased(MouseEvent e){
				// Checa si el mouse estaba dentro del area del rectangulo al momento de soltar
				// el botón y si ya había sido creado
				if (!Perro.isEmpty() && Perro.get(iAnimatApuntado).getContorno().contains(e.getX(), e.getY()) && !bCrearAnimat) {
					updateLocation(e);   // Si fue así, se actualiza la bandera
					bClickAfuera = true;
					bAnimatMovido = false;
					Perro.get(iAnimatApuntado).start();
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e){
				// Solo actualiza la posición del Animat si el mouse se encuentra
				// contenido en este y si no hay que crear uno
				if(!bClickAfuera && !bCrearAnimat) updateLocation(e);
			}
			public void mouseMoved(MouseEvent e){
				if(bCrearAnimat){ // Si se esta creando un Animat, esta subrutina muestra su sombra
					lastShadowX = ShadowAnimat.getX(); // Guarda las coordenadas para poder borrar la sombra posteriormente
					lastShadowY = ShadowAnimat.getY();
					// Recoloca la sombra
					ShadowAnimat.setRoundRect(e.getX() - (Animat.ANCHO_ANIMAT /2), e.getY() - (Animat.ALTURA_ANIMAT /2), Animat.ANCHO_ANIMAT, Animat.ALTURA_ANIMAT, 4, 4);
				}
			}
		});
	}

	public void paint(Graphics g) {
        update(g);
    }

	public void update(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		RoundRectangle2D BorrarAnimat;

		if(bCrearAnimat){
			g2d.setPaint(Color.white);  // Borra la sombra del animat si esta siendo creado apenas
			g2d.setStroke(BordeBorrarShadow);
			g2d.draw(new RoundRectangle2D.Double(lastShadowX,lastShadowY,Animat.ANCHO_ANIMAT,Animat.ALTURA_ANIMAT,4,4));
		}

		elementosABorrar.forEach(e -> eraseContorno(e, g2d));
		elementosABorrar = new ArrayList<>();

		if(bBorrarAnimat){
			eraseContorno(PerroBorrado, g2d);
			bBorrarAnimat = false;
		}

		if (bAnimatMovido) {
			g2d.setPaint(Color.white);  // Borra el Animat que ha sido movido
			BorrarAnimat = new RoundRectangle2D.Double(lastx-2, lasty-2, Animat.ANCHO_ANIMAT +4, Animat.ALTURA_ANIMAT +4, 4, 4);
			g2d.fill(BorrarAnimat);
		}

		for(Animat perro:Perro) {
			if(perro != null && perro.isDesplazandose()) {
				g2d.setPaint(Color.white);
				g2d.fill(perro.getPosicionAnterior());
			}
		}

		platosComida.forEach(p -> printPlato(p, g2d));
		platosAgua.forEach(p -> printPlato(p, g2d));

		Perro.forEach(animat -> {
			if (animat.dameLadrando() || animat.damePeleando())
				g2d.setPaint(Animat.COLOR_ANIMAT_LADRANDO);
			else if (animat.dameHuyendo()) {
				g2d.setPaint(Animat.COLOR_ANIMAT_HUYENDO);
			}
			else {
				g2d.setPaint(Animat.COLOR_ANIMAT);
			}
			// Se vuelven a imprimir todos los Animats
			g2d.fill(animat.getContorno());                // que estan instanciados
			g2d.setPaint(Animat.COLOR_BORDE_ANIMAT);
			g2d.setStroke(Animat.BORDE_ANIMAT);
			g2d.draw(animat.getContorno());
		});

        if(bCrearAnimat){
        	g2d.setPaint(ColorBordeShadow);  // Dibuja la sombra del Animat que se esta creando
        	g2d.setStroke(BordeShadow);
        	g2d.draw(ShadowAnimat);
        }
	}

	private void eraseContorno(Shape contorno, Graphics2D g2d) {
		g2d.setPaint(Color.white);
		g2d.fill(contorno);
		g2d.setStroke(BordeShadow);
		g2d.setPaint(Color.white);
		g2d.draw(contorno);
	}

	private void printPlato(Plato plato, Graphics2D g2d) {
		g2d.setPaint(plato.getColor());
		g2d.fill(plato.getContorno());
		g2d.setStroke(BordeShadow);
		g2d.setPaint(Color.black);
		g2d.draw(plato.getContorno());
	}

	public void updateLocation (MouseEvent e) {
		Animat animat = Perro.get(iAnimatApuntado);
		lastx = animat.getX();  // Guarda la posición actual del Animat
		lasty = animat.getY();
		// Se obtiene la nueva posicion del Animat
		animat.setPosicion(lastmx + e.getX(), lastmy + e.getY());
		
		//repaint();
	}
	
	public void getVentana(Interfase V){
		Ventana = V;
	}
	
	/*
	 * 		METODOS PARA MANEJAR LOS ELEMENTOS DEL MUNDO
	 *  */

	int getNumeroPlatosComida() {
		return platosComida.size();
	}

	int getNumeroPlatosAgua() {
		return platosAgua.size();
	}

	void putPlatoComida(double centerX, double centerY) {
	    Plato p = PlatosFactory.getPlatoComida(centerX, centerY);
	    platosComida.add(p);
	}

	void putPlatoAgua(double centerX, double centerY) {
		Plato p = PlatosFactory.getPlatoAgua(centerX, centerY);
	    platosAgua.add(p);
	}

	boolean isPlatoComidaWithinRange(Sprite sprite, double range) {
	    return isPlatoWithinRange(platosComida, sprite, range);
	}

	boolean isPlatoAguaWithinRange(Sprite sprite, double range) {
		return isPlatoWithinRange(platosAgua, sprite, range);
	}

	Plato getPlatoComidaMasCercano(Sprite sprite) {
		return getPlatoMasCercano(platosComida, sprite);
	}

	Plato getPlatoAguaMasCercano(Sprite sprite) {
		return getPlatoMasCercano(platosAgua, sprite);
	}

	private Plato getPlatoMasCercano(List<Plato> platos, Sprite sprite) {
		return platos.stream().min(
				Comparator.comparingDouble(p -> p.getDistancia(sprite)))
				.orElseThrow(IllegalStateException::new);
	}

	private boolean isPlatoWithinRange(List<Plato> platos, Sprite sprite, double range) {
		return platos.stream().anyMatch(p -> p.getDistancia(sprite) <= range);
	}

	public void setCrearPlatoComida() {
		bPonerComida = NUMERO_MAXIMO_PLATOS >
				getNumeroPlatosComida() + getNumeroPlatosAgua();
	}

	public void setCrearPlatoAgua() {
		bPonerAgua = NUMERO_MAXIMO_PLATOS >
				getNumeroPlatosComida() + getNumeroPlatosAgua();
	}

	void removePlatoComida(double x, double y) {
		removePlatoFromList(x, y, platosComida);
	}

	void removePlatoComida(Plato plato) {
	    removePlatoFromList(plato, platosComida);
	}

	void removePlatoAgua(double x, double y) {
		removePlatoFromList(x, y, platosAgua);
	}

	void removePlatoAgua(Plato plato) {
		removePlatoFromList(plato, platosAgua);
	}

	private void removePlatoFromList(double x, double y, List<Plato> platos) {
		platos.stream()
				.filter(p -> p.getContorno().contains(x, y))
				.findAny()
				.ifPresent(p -> removePlatoFromList(p, platos));
	}

	private void removePlatoFromList(Plato plato, List<Plato> platos) {
		platos.remove(plato);
		elementosABorrar.add(plato.getContorno());
	}

	public void setQuitarPlatoComida() {
		bQuitarComida = platosComida.size() > 0;
	}

	public void setQuitarPlatoAgua() {
		bQuitarAgua = platosAgua.size() > 0;
	}
	
	/*
	 * Metodos para manejar a los animats
	 */
	
	// Crea un nuevo Animat
	private void CrearAnimat(MouseEvent e) {
		boolean isNoneAnimatPointedTo = Perro.stream().noneMatch(a -> a.getContorno().contains(e.getX(), e.getY()));

		// Si la condicion anterior se cumple, se crea un animat en esa posicion
		if (isNoneAnimatPointedTo) {
			Animat animat = new Animat((double)e.getX() - (Animat.ANCHO_ANIMAT /2), (double)e.getY() - (Animat.ALTURA_ANIMAT /2), this, Ventana);
			Perro.add(animat);
			animat.start();
			iNumeroAnimats++;
			bCrearAnimat = false;
		}
	}
	
	// Setea la bandera de crear animat en alto
	public void setCrearAnimat() {
		bCrearAnimat = iNumeroAnimats < iMaxAnimats;
	}
	
	
	// Elimina el Animat seleccionado
	private void MatarAnimat(MouseEvent e) {
		Perro.stream().filter(p -> p.getContorno().contains(e.getX(), e.getY())).findFirst().ifPresent(perro -> {
			perro.stop();
			int i = Perro.indexOf(perro);
			if(i == iAnimatApuntado) iAnimatApuntado = 0;
			PerroBorrado.setRoundRect(perro.getContorno().getX() - 2, perro.getContorno().getY() - 2, Animat.ANCHO_ANIMAT + 4, Animat.ALTURA_ANIMAT + 4, 4, 4);
			Perro.remove(i);
			iNumeroAnimats--;
			bBorrarAnimat = true;
			bMatarAnimat = false;

		});
	}
	
	// Setea la bandera de matar animat en alto
	public void setMatarAnimat() {
		bMatarAnimat = iNumeroAnimats > 0;
	}
	
	
	private void MonitorearAnimat(MouseEvent e) {
		Perro.forEach(perro -> {
			perro.setImprimirDatos(false);
			if(perro.getContorno().contains(e.getX(), e.getY())) {
				perro.setImprimirDatos(true);
				iAnimatApuntado = Perro.indexOf(perro);
			}
		});
		bMonitorearAnimat = false;
	}
	
	public void setMonitorearAnimat() {
		bMonitorearAnimat = true;
	}
	
	public void start() {
		Thread simulacion = new Thread(this);
		simulacion.start();
		simulacionActiva = true;
	}
	
	public void run() {
		while(simulacionActiva) {
			try {
				Thread.sleep(10);
			}
			catch (InterruptedException e) {
				
			}
			repaint();
		}
	}
	
	public void stop(){
		simulacionActiva = false;
	}
	
}
