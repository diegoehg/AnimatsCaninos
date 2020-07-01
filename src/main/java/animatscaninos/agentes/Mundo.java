package animatscaninos.agentes;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

import animatscaninos.interfaz.Interfase;

/** Applet donde esta implementado el mundo que rodea a los Animats y que además
 *  controla los Threads que estan siendo ejecutados a cada instante */
public class Mundo extends Applet implements Runnable {
	private final static int NUMERO_MAXIMO_PLATOS = 50;

	private final static double DIMENSION_PLATO = 30;

	private final static Color COLOR_PLATO_AGUA = Color.blue;

	private final static Color COLOR_PLATO_COMIDA = Color.green;

	// TODO Si es posible, implementar muros, postes y rastros de orina

	private Ellipse2D platoBorrar;

	Ellipse2D[] contornoPlatos;

	Color[] colorPlatos;

	private int numeroPlatos;

	// TODO Checar si son necesarios estos atributos para la implementacion del desplazamiento
	// de Animats usando el mouse
	double lastmx, lastmy , lastx, lasty;  // Ultimas coordenadas del rectangulo
	
	// Marcadores de eventos
	boolean bClickAfuera, bPrimeraVez, bCrearAnimat, bPonerAgua, bPonerComida, bMatarAnimat,
			bQuitarAgua, bQuitarComida, bMonitorearAnimat, bBorrarComidaAgua, bBorrarAnimat,
			bAnimatMovido, bPlatoComidoBebido;
	
	// Población de Animats existentes en el mundo
	Animat[] Perro; // Lista de Animats instanciados
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

	public int getNumeroPlatos() {
		return numeroPlatos;
	}

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
		bBorrarComidaAgua = false;
		bBorrarAnimat = false;
		bAnimatMovido = false;
		bPlatoComidoBebido = false;
		
		// Inicializar Elementos del mundo
		Ellipse2D Agua1 = new Ellipse2D.Double(50,70,30,30);
		Ellipse2D Agua2 = new Ellipse2D.Double(300,70,30,30);
		Ellipse2D Agua3 = new Ellipse2D.Double(450,280,30,30);
		Ellipse2D PlatoComida1 = new Ellipse2D.Double(19,300,30,30);
		Ellipse2D PlatoComida2 = new Ellipse2D.Double(600,50,30,30);
		
		// Introducir elementos del mundo en el arreglo
		contornoPlatos = new Ellipse2D[NUMERO_MAXIMO_PLATOS];
		contornoPlatos[0] = Agua1;
		contornoPlatos[1] = Agua2;
		contornoPlatos[2] = Agua3;
		contornoPlatos[3] = PlatoComida1;
		contornoPlatos[4] = PlatoComida2;
		
		// Les da su color a los elementos del mundo
		colorPlatos = new Color[NUMERO_MAXIMO_PLATOS];
		colorPlatos[0] = Color.blue;
		colorPlatos[1] = Color.blue;
		colorPlatos[2] = Color.blue;
		colorPlatos[3] = Color.green;
		colorPlatos[4] = Color.green;
		numeroPlatos = 5; // Numero de alimentos iniciales en el mundo
		
		// Inicializa este objeto para cuando se necesite borrar un plato de comida o agua

		Perro = new Animat[iMaxAnimats];
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
				int i = 0;
				boolean AnimatNoApuntado = true;
				
				// Verifica banderas
				if(bCrearAnimat) CrearAnimat(e);
				else if(bMatarAnimat) MatarAnimat(e);
				else if(bPonerComida) CrearPlatoComidaAgua(e,true);
				else if(bPonerAgua) CrearPlatoComidaAgua(e,false);
				else if(bQuitarComida) QuitarPlatoComidaAgua(e,true);
				else if(bQuitarAgua) QuitarPlatoComidaAgua(e, false);
				else if(bMonitorearAnimat) MonitorearAnimat(e);
				else {
					// Se verifica si el click esta contenido en un Animat para poder moverlo
					while(i<iNumeroAnimats && AnimatNoApuntado){
						// si el click del mouse esta contenido en un animat ...
						if (Perro[i].getContorno().contains((double) e.getX(), (double) e.getY())){
							Perro[i].stop(); // Se detiene el thread
							iAnimatApuntado = i; 	// se guarda el Animat que se movera,
							AnimatNoApuntado = false; // se indica que se apunto a un Animat
							lastmx = Perro[i].getContorno().getX() - (double)e.getX(); // se guarda la posicion del animat con respecto al puntero del mouse
							lastmy = Perro[i].getContorno().getY() - (double)e.getY();
							updateLocation(e); // se actualiza la bandera y la grafica
							bClickAfuera = false;
							bAnimatMovido = true;
						}
						else i++; // si no, se verifica el siguiente
					}
				}
			}
			public void mouseReleased(MouseEvent e){
				// Checa si el mouse estaba dentro del area del rectangulo al momento de soltar
				// el botón y si ya había sido creado
				if (iNumeroAnimats > 0) {
				if (Perro[iAnimatApuntado].getContorno().contains((double) e.getX(), (double) e.getY()) && !bCrearAnimat) {
					updateLocation(e);   // Si fue así, se actualiza la bandera
					bClickAfuera = true;
					bAnimatMovido = false;
					Perro[iAnimatApuntado].start();
				}}
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
		int i = 0;
		RoundRectangle2D BorrarAnimat;
		
		if(bCrearAnimat){
			g2d.setPaint(Color.white);  // Borra la sombra del animat si esta siendo creado apenas
			g2d.setStroke(BordeBorrarShadow);
			g2d.draw(new RoundRectangle2D.Double(lastShadowX,lastShadowY,Animat.ANCHO_ANIMAT,Animat.ALTURA_ANIMAT,4,4));
		}
		
		if(bBorrarComidaAgua){			// Borra el plato de comida o de agua que fue
			g2d.setPaint(Color.white);	// seleccionado
			g2d.fill(platoBorrar);
			bBorrarComidaAgua = false;
		}
		
		if(bBorrarAnimat){
			g2d.setPaint(Color.white);	// Borra el Animat que ha sido eliminado
			g2d.fill(PerroBorrado);
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

		for(i = 0; i< numeroPlatos; i++) {		// Imprime los alimentos de los Animats
			g2d.setPaint(colorPlatos[i]);
			g2d.fill(contornoPlatos[i]);
			g2d.setStroke(BordeShadow);
			g2d.setPaint(Color.black);
			g2d.draw(contornoPlatos[i]);
		}
		
		for(i = 0; i<iNumeroAnimats; i++){
			if (Perro[i].dameLadrando() || Perro[i].damePeleando())
				g2d.setPaint(Animat.COLOR_ANIMAT_LADRANDO);
			else if (Perro[i].dameHuyendo()) {
				g2d.setPaint(Animat.COLOR_ANIMAT_HUYENDO);
			}
			else {
				g2d.setPaint(Animat.COLOR_ANIMAT);
			}
				       // Se vuelven a imprimir todos los Animats
			g2d.fill(Perro[i].getContorno());                // que estan instanciados
	        g2d.setPaint(Animat.COLOR_BORDE_ANIMAT);
	        g2d.setStroke(Animat.BORDE_ANIMAT);
	        g2d.draw(Perro[i].getContorno());
		}
		
        if(bCrearAnimat){
        	g2d.setPaint(ColorBordeShadow);  // Dibuja la sombra del Animat que se esta creando
        	g2d.setStroke(BordeShadow);
        	g2d.draw(ShadowAnimat);
        }
	}

	public void updateLocation (MouseEvent e) {
		
		lastx = Perro[iAnimatApuntado].getContorno().getX();  // Guarda la posición actual del Animat
		lasty = Perro[iAnimatApuntado].getContorno().getY();
		// Se obtiene la nueva posicion del Animat
		Perro[iAnimatApuntado].setPosicion(lastmx + (double)e.getX(), lastmy + (double)e.getY());
		
		//repaint();
	}
	
	public void getVentana(Interfase V){
		Ventana = V;
	}
	
	/*
	 * 		METODOS PARA MANEJAR LOS ELEMENTOS DEL MUNDO
	 *  */

	// Crear un plato de comida o de agua: si id = true, comida, agua en otro caso
	private void CrearPlatoComidaAgua(MouseEvent e, boolean id) {
		contornoPlatos[numeroPlatos] = new Ellipse2D.Double((double)e.getX() - (DIMENSION_PLATO /2),(double)e.getY() - (DIMENSION_PLATO /2),30,30);
		if (id) {
			colorPlatos[numeroPlatos] = COLOR_PLATO_COMIDA;
			bPonerComida = false;
		}
		else {
			colorPlatos[numeroPlatos] = COLOR_PLATO_AGUA;
			bPonerAgua = false;
		}
		numeroPlatos++;
	}

	public void setCrearPlatoComida() {
		bPonerComida = numeroPlatos < NUMERO_MAXIMO_PLATOS;
	}

	public void setCrearPlatoAgua() {
		bPonerAgua = numeroPlatos < NUMERO_MAXIMO_PLATOS;
	}
	
	// Le indica al mundo la creacion del plato de comida si id es true o de agua en otro caso
	public void SetCrearPlatoComidaAgua(boolean id) {
		if (id) bPonerComida = true;
		else bPonerAgua = true;
	}
	
	// Elimina platos de agua y de comida; si id = true de comida, de agua en otro caso
	private void QuitarPlatoComidaAgua(MouseEvent e, boolean id) {
		int i;
		
		for(i = 0; i< numeroPlatos; i++){
			if (contornoPlatos[i].contains((double)e.getX(), (double)e.getY()) &&
					(((colorPlatos[i] == COLOR_PLATO_COMIDA) && id) ||
					((colorPlatos[i] == COLOR_PLATO_AGUA) && !id))) {
				// Guarda la posicion del alimento que se borrara y pone la bandera de borrado
				// en alto
				platoBorrar = new Ellipse2D.Double(contornoPlatos[i].getX()-2,
					contornoPlatos[i].getY()-2, DIMENSION_PLATO +4, DIMENSION_PLATO +4);
				bBorrarComidaAgua = true;
				contornoPlatos[i] = contornoPlatos[numeroPlatos - 1];
				contornoPlatos[numeroPlatos - 1] = null;
				colorPlatos[i] = colorPlatos[numeroPlatos - 1];
				colorPlatos[numeroPlatos -1] = null;
				numeroPlatos--;
				if (id) bQuitarComida = false;  // Desmarca la bandera, segun el caso
				else bQuitarAgua = false;
			}
		}
	}

	public boolean hasPlatos(Color alimento) {
		for(Color colorPlato: colorPlatos)
			if(colorPlato == alimento)
				return true;

		return false;
	}

	public void setQuitarPlatoComida() {
		bQuitarComida = hasPlatos(Color.green);
	}

	public void setQuitarPlatoAgua() {
		bQuitarAgua = hasPlatos(Color.blue);
	}
	
	public void SetQuitarPlatoComidoBebido(Ellipse2D ComidaEliminada) {
		int i = 0, h = 0;
		boolean PlatoEncontrado = false;
		
		for(i = 0; i < numeroPlatos; i++){
			if (contornoPlatos[i] == ComidaEliminada) {
				PlatoEncontrado = true;
				h = i; // Para no perder el indice
			}
		}
		
		if (PlatoEncontrado) {
			platoBorrar = new Ellipse2D.Double(contornoPlatos[h].getX()-2,
					contornoPlatos[h].getY()-2, DIMENSION_PLATO +4, DIMENSION_PLATO +4);
			bBorrarComidaAgua = true;
			contornoPlatos[h] = contornoPlatos[numeroPlatos - 1];
			contornoPlatos[numeroPlatos - 1] = null;
			colorPlatos[h] = colorPlatos[numeroPlatos - 1];
			colorPlatos[numeroPlatos - 1] = null;
			numeroPlatos--;
		}
	}
	
	/*
	 * Metodos para manejar a los animats
	 */
	
	// Crea un nuevo Animat
	private void CrearAnimat(MouseEvent e) {
		int i;
		boolean bAnimatNoApuntado = true;
		
		// Verificamos que el puntero del mouse no se encuentre apuntando encima de otro
		// Animat
		for(i = 0; i < iNumeroAnimats; i++) 
			bAnimatNoApuntado = !Perro[i].getContorno().contains((double)e.getX(), (double)e.getY())
								&& bAnimatNoApuntado;
		
		
		// Si la condicion anterior se cumple, se crea un animat en esa posicion
		if (bAnimatNoApuntado) {
			Perro[iNumeroAnimats] = new Animat((double)e.getX() - (Animat.ANCHO_ANIMAT /2), (double)e.getY() - (Animat.ALTURA_ANIMAT /2), this, Ventana);
			Perro[iNumeroAnimats].start();
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
		int i;
		for(i = 0; i < iNumeroAnimats; i++) {
			if(Perro[i].getContorno().contains((double)e.getX(), (double)e.getY())) {
				Perro[i].stop();
				if(i == iAnimatApuntado) iAnimatApuntado = 0;
				PerroBorrado.setRoundRect(Perro[i].getContorno().getX() - 2, Perro[i].getContorno().getY() - 2, Animat.ANCHO_ANIMAT + 4, Animat.ALTURA_ANIMAT + 4, 4, 4);
				Perro[i] = Perro[iNumeroAnimats-1];
				Perro[iNumeroAnimats - 1] = null;
				iNumeroAnimats--;
				bBorrarAnimat = true;
				bMatarAnimat = false;
			}
		}
	}
	
	// Setea la bandera de matar animat en alto
	public void setMatarAnimat() {
		bMatarAnimat = iNumeroAnimats > 0;
	}
	
	
	private void MonitorearAnimat(MouseEvent e) {
		int i;
		for(i = 0; i < iNumeroAnimats; i++) {
			Perro[i].setImprimirDatos(false);
			if(Perro[i].getContorno().contains(e.getX(), e.getY())) {
				Perro[i].setImprimirDatos(true);
				iAnimatApuntado = i;
			}
		}
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
