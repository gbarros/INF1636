package com.monopoly.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.util.Observable;
import java.util.Observer;

import com.monopoly.game.Player;

public class PlayerPinView 
	extends JPanel 
	implements Observer 
{
	private static final long serialVersionUID = 2386349901642426601L;
	private Point coordinates , offset , jitterOffset ;
	private ImageIcon pin;
	private Dimension pinDimension;
	
	public PlayerPinView( final Player p , Point offset )
	{
		this.coordinates = Board.BoardSpaces.spaceToPoint( p.getPosition() );
		this.offset = offset;
		
		// Adiciona um pequeno offset aleat�rio em torno da posi��o real do pin, para que
		//  evite-se overlap.
		this.jitterOffset = new Point( (int) ((Math.random() - 0.5) * 25),
									   (int) ((Math.random() - 0.5) * 25) );
		
		// Carrega o pino correto a ser exibido dada a cor do jogador
		loadPinImage( Player.PlayerColor.colorToString( p.getPinColor() ) );
		
		this.pinDimension = new Dimension( pin.getIconWidth() , pin.getIconHeight() );
		
		this.setPreferredSize( this.pinDimension );
		updateBounds();
	}
	
	public PlayerPinView()
	{
		this( new Player() , new Point(0,0) );
	}
	
	public void paint(Graphics g)
	{
		Graphics2D graphics2D = (Graphics2D) g;
		graphics2D.drawImage( pin.getImage() , 0 , 0 , null );
	}
		
	// retorna 
	public Dimension getDimension()
	{
		return this.pinDimension;
	}

	// Player notifica seus observadores que teve sua posi��o no tabuleiro alterada
	public void update( Observable playerModel , Object update ) {
		if( update instanceof Board.BoardSpaces ){
			Board.BoardSpaces space = (Board.BoardSpaces) update;
			
			this.coordinates = Board.BoardSpaces.spaceToPoint( space );
			
			updateBounds();
		}
	}
	
	private void loadPinImage( String pinColor )
	{
		String path = "src/main/resources/pins/" + pinColor + "_pin.png";
		
		pin = new ImageIcon(path);
	}
	
	private void updateBounds(){		
		this.setBounds( coordinates.x  + offset.x + jitterOffset.x , 
						coordinates.y  + offset.y + jitterOffset.y ,
						pinDimension.width, pinDimension.height );
	}
}
