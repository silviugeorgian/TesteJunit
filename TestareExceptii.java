package com.example.ctsproject.testare;

import java.util.GregorianCalendar;

import org.junit.Test;

import com.example.ctsproject.beans.Fotbalist;
import com.example.ctsproject.controllers.MainController;
import com.example.ctsproject.exceptions.AttributeNotInRangeException;
import com.example.ctsproject.exceptions.NameNotEmptyException;
import com.example.ctsproject.exceptions.NotEnoughAttributesException;
import com.example.ctsproject.exceptions.NotEnoughPlayersException;
import com.example.ctsproject.factories.FotbalistFactory;
import com.example.ctsproject.factories.FotbalistFactory.FotbalistType;

public class TestareExceptii {
	
	@Test(expected=NotEnoughAttributesException.class)
	public void testAtacantAttributes() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist atacant = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A", new GregorianCalendar(), new int[]{10});
	}
	
	@Test(expected=NotEnoughAttributesException.class)
	public void testMijlocasAttributes() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist mijlocas = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M", new GregorianCalendar(), new int[]{10});
	}
	
	@Test(expected=NotEnoughAttributesException.class)
	public void testFundasAttributes() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist fundas = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F", new GregorianCalendar(), new int[]{10});
	}
	
	@Test(expected=NotEnoughAttributesException.class)
	public void testPortarAttributes() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist portar = FotbalistFactory.getFotbalist(FotbalistType.PORTAR, "P", new GregorianCalendar(), new int[]{10});
	}
	
	@Test(expected=AttributeNotInRangeException.class)
	public void testAtacantAttributesRange() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist atacant = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A", new GregorianCalendar(), new int[]{0, 0, 99, 44});
	}
	
	@Test(expected=AttributeNotInRangeException.class)
	public void testMijlocasAttributesRange() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist mijlocas = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M", new GregorianCalendar(), new int[]{5, 3, -200});
	}
	
	@Test(expected=AttributeNotInRangeException.class)
	public void testFundasAttributesRange() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist fundas = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "F", new GregorianCalendar(), new int[]{77, 4000, 80});
	}
	
	@Test(expected=AttributeNotInRangeException.class)
	public void testPortarAttributesRange() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist portar = FotbalistFactory.getFotbalist(FotbalistType.PORTAR, "P", new GregorianCalendar(), new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE});
	}
	
	@Test(expected=NotEnoughPlayersException.class)
	public void testBestPortarNotEnoughPlayers() throws NotEnoughPlayersException{
		MainController.getInstance().getBestPortar();
	}
	
	@Test(expected=NotEnoughPlayersException.class)
	public void testBestFundasiNotEnoughPlayers() throws NotEnoughPlayersException, NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist fundas1 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F1", new GregorianCalendar(), new int[]{10, 10, 10});
		Fotbalist fundas2 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F2", new GregorianCalendar(), new int[]{30, 30, 30});
		MainController.getInstance().addFotbalist(fundas1);
		MainController.getInstance().addFotbalist(fundas2);
		MainController.getInstance().getBestFundasi();
	}
	
	@Test(expected=NotEnoughPlayersException.class)
	public void testBestMijlocasiNotEnoughPlayers() throws NotEnoughPlayersException, NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist mijlocas1 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M1", new GregorianCalendar(), new int[]{10, 10, 10});
		Fotbalist mijlocas2 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M2", new GregorianCalendar(), new int[]{30, 30, 30});
		Fotbalist mijlocas5 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M5", new GregorianCalendar(), new int[]{60, 60, 60});
		MainController.getInstance().addFotbalist(mijlocas1);
		MainController.getInstance().addFotbalist(mijlocas2);
		MainController.getInstance().addFotbalist(mijlocas5);
		MainController.getInstance().getBestMijlocasi();
	}
	
	@Test(expected=NotEnoughPlayersException.class)
	public void testBestAtacantNotEnoughPlayers() throws NotEnoughPlayersException, NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist atacant1 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A1", new GregorianCalendar(), new int[]{10, 10, 10, 10});
		MainController.getInstance().addFotbalist(atacant1);
		MainController.getInstance().getBestAtacanti();
	}
	

	@Test(expected=NameNotEmptyException.class)
	public void testNumeStringGol() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist f = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "", new GregorianCalendar(), new int[]{60, 70, 60, 30});
	}
}
