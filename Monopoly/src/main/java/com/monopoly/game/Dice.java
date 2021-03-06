package com.monopoly.game;

import java.util.Observable;
import java.util.Random;

public class Dice 
	extends Observable
{
	private int d1; //Dado numero 1
	private int d2; //Dado numero 2
	private int numberOfSides; // Quantidade de lados
	private Random dice;
	
	public Dice( int numberOfSides ){
		// Inicializa os dados com valores aleatórios
		dice = new Random();
		this.numberOfSides = numberOfSides;
	}
	
	public void roll(){
		// Designa um valor aleatório de 1 a 6 para cada dado
		d1 = dice.nextInt(numberOfSides) + 1;
		d2 = dice.nextInt(numberOfSides) + 1;
		
		updateObservers();
	}
	
	private void updateObservers() {
		this.setChanged();		
		this.notifyObservers( "DICE_dicesRolled" );		
	}

	public int getDie1(){
		// retorna o valor do dado 1
		return d1;
	}
	
	public int getDie2(){
		// retorna o valor do dado 2
		return d2;
	}
	
	public int getSum(){
		// retorna a soma dos valores obtidos nos dados 1 e 2
		return d1+d2;
	}

}
