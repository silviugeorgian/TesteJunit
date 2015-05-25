package com.example.ctsproject.testare;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.ctsproject.beans.Atacant;
import com.example.ctsproject.beans.Fotbalist;
import com.example.ctsproject.beans.Fundas;
import com.example.ctsproject.beans.Mijlocas;
import com.example.ctsproject.beans.Portar;
import com.example.ctsproject.controllers.MainController;
import com.example.ctsproject.exceptions.AttributeNotInRangeException;
import com.example.ctsproject.exceptions.NameNotEmptyException;
import com.example.ctsproject.exceptions.NotEnoughAttributesException;
import com.example.ctsproject.exceptions.NotEnoughPlayersException;
import com.example.ctsproject.factories.FotbalistFactory;
import com.example.ctsproject.factories.FotbalistFactory.FotbalistType;

public class TestareMetode {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MainController.getInstance().getListaFotbalisti().clear();
		MainController.getInstance().getListaAtacanti().clear();
		MainController.getInstance().getListaMijlocasi().clear();
		MainController.getInstance().getListaFundasi().clear();
		MainController.getInstance().getListaPortari().clear();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCrearePortar() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException {
		int[] params = new int[]{89, 89};
		String nume = "Duckadam";
		GregorianCalendar dataNastere = new GregorianCalendar(1960, 11, 28);
		FotbalistType type = FotbalistType.PORTAR;
		
		Fotbalist portar1 = new Portar(nume, dataNastere, params, type);
		Fotbalist portarFromFactory = FotbalistFactory.getFotbalist(type, nume, dataNastere, params);
		
		assertEquals(portar1, portarFromFactory);
	}
	
	@Test
	public void testCreareFundas() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException {
		int[] params = new int[]{75, 74, 74};
		String nume = "Belodedici";
		GregorianCalendar dataNastere = new GregorianCalendar(1954, 5, 2);
		FotbalistType type = FotbalistType.FUNDAS;
		
		Fotbalist fundas1 = new Fundas(nume, dataNastere, params, type);
		Fotbalist fundasFromFactory = FotbalistFactory.getFotbalist(type, nume, dataNastere, params);
		
		assertEquals(fundas1, fundasFromFactory);
	}
	
	@Test
	public void testCreareMijlocas() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException {
		int[] params = new int[]{81, 60, 55};
		String nume = "Rosu";
		GregorianCalendar dataNastere = new GregorianCalendar(1954, 7, 11);
		FotbalistType type = FotbalistType.MIJLOCAS;
		
		Fotbalist mijlocas1 = new Mijlocas(nume, dataNastere, params, type);
		Fotbalist mijlocasFromFactory = FotbalistFactory.getFotbalist(type, nume, dataNastere, params);
		
		assertEquals(mijlocas1, mijlocasFromFactory);
	}
	
	@Test
	public void testCreareAtacant() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException {
		int[] params = new int[]{77, 55, 55, 66};
		String nume = "Ciocoiu";
		GregorianCalendar dataNastere = new GregorianCalendar(1986, 5, 16);
		FotbalistType type = FotbalistType.ATACANT;
		
		Fotbalist atacant1 = new Atacant(nume, dataNastere, params, type);
		Fotbalist atacantFromFactory = FotbalistFactory.getFotbalist(type, nume, dataNastere, params);
		
		assertEquals(atacant1, atacantFromFactory);
	}
	
	@Test
	public void testVarstaFromDataNastere() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist fotbalist = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "Sanmartean", new GregorianCalendar(1992, 2, 4), new int[]{90, 70, 60});
		assertEquals(23, fotbalist.getVarsta());
	}
	
	@Test
	public void testVarstaZERO() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist fotbalist = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "Sanmartean", new GregorianCalendar(), new int[]{90, 70, 60});

		assertEquals(0, fotbalist.getVarsta());
	}
	
	@Test
	public void testVarstaInViitor() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist fotbalist = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "Sanmartean", new GregorianCalendar(2020, 10, 2), new int[]{90, 70, 60});
		
		assertEquals(0, fotbalist.getVarsta());
	}
	
	@Test
	public void testSortareValoareDesc() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist portar = FotbalistFactory.getFotbalist(FotbalistType.PORTAR, "P", new GregorianCalendar(), new int[]{70, 70});
		Fotbalist fundas = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F", new GregorianCalendar(), new int[]{90, 90, 90});
		Fotbalist mijlocas = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M", new GregorianCalendar(), new int[]{60, 60, 60});
		Fotbalist atacant = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A", new GregorianCalendar(), new int[]{80, 80, 80, 80});	
		
		ArrayList<Fotbalist> expected = new ArrayList<>();
		expected.add(fundas);
		expected.add(atacant);
		expected.add(portar);
		expected.add(mijlocas);
		
		MainController.getInstance().addFotbalist(portar);
		MainController.getInstance().addFotbalist(fundas);
		MainController.getInstance().addFotbalist(mijlocas);
		MainController.getInstance().addFotbalist(atacant);
		
		ArrayList<Fotbalist> actual = MainController.getInstance().getListaFotbalisti();
		actual.sort(Fotbalist.comparatorValoareDesc);
	}
	
	@Test
	public void testSortareValoareAsc() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist portar = FotbalistFactory.getFotbalist(FotbalistType.PORTAR, "P", new GregorianCalendar(), new int[]{70, 70});
		Fotbalist fundas = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F", new GregorianCalendar(), new int[]{90, 90, 90});
		Fotbalist mijlocas = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M", new GregorianCalendar(), new int[]{60, 60, 60});
		Fotbalist atacant = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A", new GregorianCalendar(), new int[]{80, 80, 80, 80});	
		
		ArrayList<Fotbalist> expected = new ArrayList<>();
		expected.add(mijlocas);
		expected.add(portar);
		expected.add(atacant);
		expected.add(fundas);
		
		MainController.getInstance().addFotbalist(portar);
		MainController.getInstance().addFotbalist(fundas);
		MainController.getInstance().addFotbalist(mijlocas);
		MainController.getInstance().addFotbalist(atacant);
		
		ArrayList<Fotbalist> actual = MainController.getInstance().getListaFotbalisti();
		actual.sort(Fotbalist.comparatorValoareAsc);
	}
	
	@Test
	public void testValoarePortar() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist portar  =FotbalistFactory.getFotbalist(FotbalistType.PORTAR, "P", new GregorianCalendar(), new int[]{70, 80});
		assertEquals(75, portar.getValoare());
	}
	
	@Test
	public void testValoareMijlocas() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist mijlocas = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M", new GregorianCalendar(), new int[]{50, 60, 70});
		assertEquals(60, mijlocas.getValoare());
	}
	
	@Test
	public void testValoareFundas() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist fundas = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F", new GregorianCalendar(), new int[]{90, 90, 90});
		assertEquals(90, fundas.getValoare());
	}
	
	@Test
	public void testValoareAtacant() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist atacant = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A", new GregorianCalendar(), new int[]{10, 30, 70, 90});
		assertEquals(50, atacant.getValoare());
	}
	
	
	@Test
	public void testAdaugaFotbalistCuAcelasiNume() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist f1 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A", new GregorianCalendar(), new int[]{50, 50, 50, 50});
		MainController.getInstance().addFotbalist(f1);
		
		Fotbalist f2 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "A", new GregorianCalendar(), new int[]{30, 80, 77});
		assertFalse(MainController.getInstance().addFotbalist(f2));
		
	}
	
	@Test
	public void testAdaugaFotbalistCuNumeDiferit() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist f1 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A", new GregorianCalendar(), new int[]{50, 50, 50, 50});
		MainController.getInstance().addFotbalist(f1);
		
		Fotbalist f2 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "B", new GregorianCalendar(), new int[]{30, 80, 77});
		assertTrue(MainController.getInstance().addFotbalist(f2));
		
	}
	
	@Test
	public void testRemoveFotbalist() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist f1 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A", new GregorianCalendar(), new int[]{50, 50, 50, 50});
		MainController.getInstance().addFotbalist(f1);
		Fotbalist f2 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "B", new GregorianCalendar(), new int[]{30, 80, 77});
		MainController.getInstance().addFotbalist(f2);
		Fotbalist f3 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "C", new GregorianCalendar(), new int[]{30, 80, 77, 11});
		MainController.getInstance().addFotbalist(f2);
		Fotbalist f4 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "D", new GregorianCalendar(), new int[]{30, 80, 77, 77});
		MainController.getInstance().addFotbalist(f2);
		
		assertTrue(MainController.getInstance().removeFotbalist("A"));
	}
	
	@Test
	public void testRemoveFotbalist2() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist f1 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A", new GregorianCalendar(), new int[]{50, 50, 50, 50});
		MainController.getInstance().addFotbalist(f1);
		Fotbalist f2 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "B", new GregorianCalendar(), new int[]{30, 80, 77});
		MainController.getInstance().addFotbalist(f2);
		Fotbalist f3 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "C", new GregorianCalendar(), new int[]{30, 80, 77, 11});
		MainController.getInstance().addFotbalist(f3);
		Fotbalist f4 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "D", new GregorianCalendar(), new int[]{30, 80, 77, 77});
		MainController.getInstance().addFotbalist(f4);
		
		MainController.getInstance().removeFotbalist("A");
		boolean removed = true;
		for(Fotbalist f:MainController.getInstance().getListaAtacanti()){
			if(f.getNume().equals("A")){
				removed = false;
			}
		}
		
		assertTrue(removed);
	}
	
	@Test
	public void testBestPortar() throws NotEnoughAttributesException, AttributeNotInRangeException, NotEnoughPlayersException, NameNotEmptyException{
		Fotbalist portar1 = FotbalistFactory.getFotbalist(FotbalistType.PORTAR, "P1", new GregorianCalendar(), new int[]{15, 15});
		Fotbalist portar2 = FotbalistFactory.getFotbalist(FotbalistType.PORTAR, "P2", new GregorianCalendar(), new int[]{10, 15});
		Fotbalist portar3 = FotbalistFactory.getFotbalist(FotbalistType.PORTAR, "P3", new GregorianCalendar(), new int[]{80, 65});
		
		MainController.getInstance().addFotbalist(portar1);
		MainController.getInstance().addFotbalist(portar2);
		MainController.getInstance().addFotbalist(portar3);
		
		assertEquals(portar3, MainController.getInstance().getBestPortar());
	}
	
	@Test
	public void testWorstPortar() throws NotEnoughAttributesException, AttributeNotInRangeException, NotEnoughPlayersException, NameNotEmptyException{
		Fotbalist portar1 = FotbalistFactory.getFotbalist(FotbalistType.PORTAR, "P1", new GregorianCalendar(), new int[]{15, 15});
		Fotbalist portar2 = FotbalistFactory.getFotbalist(FotbalistType.PORTAR, "P2", new GregorianCalendar(), new int[]{10, 15});
		Fotbalist portar3 = FotbalistFactory.getFotbalist(FotbalistType.PORTAR, "P3", new GregorianCalendar(), new int[]{80, 65});
		
		MainController.getInstance().addFotbalist(portar1);
		MainController.getInstance().addFotbalist(portar2);
		MainController.getInstance().addFotbalist(portar3);
		
		assertEquals(portar2, MainController.getInstance().getWorstPortar());
	}
	
	@Test
	public void testBestFundasi() throws NotEnoughAttributesException, AttributeNotInRangeException, NotEnoughPlayersException, NameNotEmptyException{
		Fotbalist fundas1 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F1", new GregorianCalendar(), new int[]{10, 10, 10});
		Fotbalist fundas2 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F2", new GregorianCalendar(), new int[]{30, 30, 30});
		Fotbalist fundas3 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F3", new GregorianCalendar(), new int[]{50, 50, 50});
		Fotbalist fundas4 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F4", new GregorianCalendar(), new int[]{20, 20, 20});
		Fotbalist fundas5 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F5", new GregorianCalendar(), new int[]{60, 60, 60});
		Fotbalist fundas6 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F6", new GregorianCalendar(), new int[]{50, 40, 50});
		
		MainController.getInstance().addFotbalist(fundas1);
		MainController.getInstance().addFotbalist(fundas2);
		MainController.getInstance().addFotbalist(fundas3);
		MainController.getInstance().addFotbalist(fundas4);
		MainController.getInstance().addFotbalist(fundas5);
		MainController.getInstance().addFotbalist(fundas6);
		
		ArrayList<Fotbalist> expected = new ArrayList<>();
		expected.add(fundas5);
		expected.add(fundas3);
		expected.add(fundas6);
		expected.add(fundas2);
		
		assertEquals(expected, MainController.getInstance().getBestFundasi());
	}
	
	@Test
	public void testWorstFundasi() throws NotEnoughAttributesException, AttributeNotInRangeException, NotEnoughPlayersException, NameNotEmptyException{
		Fotbalist fundas1 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F1", new GregorianCalendar(), new int[]{10, 10, 10});
		Fotbalist fundas2 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F2", new GregorianCalendar(), new int[]{30, 30, 30});
		Fotbalist fundas3 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F3", new GregorianCalendar(), new int[]{50, 50, 50});
		Fotbalist fundas4 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F4", new GregorianCalendar(), new int[]{20, 20, 20});
		Fotbalist fundas5 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F5", new GregorianCalendar(), new int[]{60, 60, 60});
		Fotbalist fundas6 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F6", new GregorianCalendar(), new int[]{50, 40, 50});
		
		MainController.getInstance().addFotbalist(fundas1);
		MainController.getInstance().addFotbalist(fundas2);
		MainController.getInstance().addFotbalist(fundas3);
		MainController.getInstance().addFotbalist(fundas4);
		MainController.getInstance().addFotbalist(fundas5);
		MainController.getInstance().addFotbalist(fundas6);
		
		ArrayList<Fotbalist> expected = new ArrayList<>();
		expected.add(fundas1);
		expected.add(fundas4);
		expected.add(fundas2);
		expected.add(fundas6);
		
		assertEquals(expected, MainController.getInstance().getWorstFundasi());
	}
	
	@Test
	public void testBestMijlocasi() throws NotEnoughAttributesException, AttributeNotInRangeException, NotEnoughPlayersException, NameNotEmptyException{
		Fotbalist mijlocas1 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M1", new GregorianCalendar(), new int[]{10, 10, 10});
		Fotbalist mijlocas2 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M2", new GregorianCalendar(), new int[]{30, 30, 30});
		Fotbalist mijlocas3 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M3", new GregorianCalendar(), new int[]{50, 50, 50});
		Fotbalist mijlocas4 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M4", new GregorianCalendar(), new int[]{20, 20, 20});
		Fotbalist mijlocas5 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M5", new GregorianCalendar(), new int[]{60, 60, 60});
		Fotbalist mijlocas6 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M6", new GregorianCalendar(), new int[]{50, 40, 50});
		
		MainController.getInstance().addFotbalist(mijlocas1);
		MainController.getInstance().addFotbalist(mijlocas2);
		MainController.getInstance().addFotbalist(mijlocas3);
		MainController.getInstance().addFotbalist(mijlocas4);
		MainController.getInstance().addFotbalist(mijlocas5);
		MainController.getInstance().addFotbalist(mijlocas6);
		
		ArrayList<Fotbalist> expected = new ArrayList<>();
		expected.add(mijlocas5);
		expected.add(mijlocas3);
		expected.add(mijlocas6);
		expected.add(mijlocas2);
		
		assertEquals(expected, MainController.getInstance().getBestMijlocasi());
	}
	
	@Test
	public void testWorstMijlocasi() throws NotEnoughAttributesException, AttributeNotInRangeException, NotEnoughPlayersException, NameNotEmptyException{
		Fotbalist mijlocas1 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M1", new GregorianCalendar(), new int[]{10, 10, 10});
		Fotbalist mijlocas2 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M2", new GregorianCalendar(), new int[]{30, 30, 30});
		Fotbalist mijlocas3 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M3", new GregorianCalendar(), new int[]{50, 50, 50});
		Fotbalist mijlocas4 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M4", new GregorianCalendar(), new int[]{20, 20, 20});
		Fotbalist mijlocas5 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M5", new GregorianCalendar(), new int[]{60, 60, 60});
		Fotbalist mijlocas6 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M6", new GregorianCalendar(), new int[]{50, 40, 50});
		
		MainController.getInstance().addFotbalist(mijlocas1);
		MainController.getInstance().addFotbalist(mijlocas2);
		MainController.getInstance().addFotbalist(mijlocas3);
		MainController.getInstance().addFotbalist(mijlocas4);
		MainController.getInstance().addFotbalist(mijlocas5);
		MainController.getInstance().addFotbalist(mijlocas6);
		
		ArrayList<Fotbalist> expected = new ArrayList<>();
		expected.add(mijlocas1);
		expected.add(mijlocas4);
		expected.add(mijlocas2);
		expected.add(mijlocas6);
		
		assertEquals(expected, MainController.getInstance().getWorstMijlocasi());
	}
	
	@Test
	public void testBestAtacanti() throws NotEnoughAttributesException, AttributeNotInRangeException, NotEnoughPlayersException, NameNotEmptyException{
		Fotbalist atancat1 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "M1", new GregorianCalendar(), new int[]{10, 10, 10, 10});
		Fotbalist atancat2 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A2", new GregorianCalendar(), new int[]{30, 30, 30, 30});
		Fotbalist atancat3 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A3", new GregorianCalendar(), new int[]{50, 50, 50, 50});
		Fotbalist atancat4 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A4", new GregorianCalendar(), new int[]{20, 20, 20, 20});
		Fotbalist atancat5 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A5", new GregorianCalendar(), new int[]{60, 60, 60, 60});
		Fotbalist atancat6 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A6", new GregorianCalendar(), new int[]{50, 50, 40, 50});
		
		MainController.getInstance().addFotbalist(atancat1);
		MainController.getInstance().addFotbalist(atancat2);
		MainController.getInstance().addFotbalist(atancat3);
		MainController.getInstance().addFotbalist(atancat4);
		MainController.getInstance().addFotbalist(atancat5);
		MainController.getInstance().addFotbalist(atancat6);
		
		ArrayList<Fotbalist> expected = new ArrayList<>();
		expected.add(atancat5);
		expected.add(atancat3);
		
		assertEquals(expected, MainController.getInstance().getBestAtacanti());
	}
	
	@Test
	public void testWorstAtacanti() throws NotEnoughAttributesException, AttributeNotInRangeException, NotEnoughPlayersException, NameNotEmptyException{
		Fotbalist atancat1 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "M1", new GregorianCalendar(), new int[]{10, 10, 10, 10});
		Fotbalist atancat2 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A2", new GregorianCalendar(), new int[]{30, 30, 30, 30});
		Fotbalist atancat3 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A3", new GregorianCalendar(), new int[]{50, 50, 50, 50});
		Fotbalist atancat4 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A4", new GregorianCalendar(), new int[]{20, 20, 20, 20});
		Fotbalist atancat5 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A5", new GregorianCalendar(), new int[]{60, 60, 60, 60});
		Fotbalist atancat6 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A6", new GregorianCalendar(), new int[]{50, 50, 40, 50});
		
		MainController.getInstance().addFotbalist(atancat1);
		MainController.getInstance().addFotbalist(atancat2);
		MainController.getInstance().addFotbalist(atancat3);
		MainController.getInstance().addFotbalist(atancat4);
		MainController.getInstance().addFotbalist(atancat5);
		MainController.getInstance().addFotbalist(atancat6);
		
		ArrayList<Fotbalist> expected = new ArrayList<>();
		expected.add(atancat1);
		expected.add(atancat4);
		
		assertEquals(expected, MainController.getInstance().getWorstAtacanti());
	}
	
	@Test
	public void testMedieVarstaBestEleven() throws NotEnoughPlayersException{
		Fotbalist fotbalist;
		
		//portari
		fotbalist = mock(Portar.class);
		when(fotbalist.getNume()).thenReturn("1");
		when(fotbalist.getVarsta()).thenReturn(37);
		when(fotbalist.getType()).thenReturn(FotbalistType.PORTAR);
		when(fotbalist.getValoare()).thenReturn(70);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Portar.class);
		when(fotbalist.getNume()).thenReturn("2");
		when(fotbalist.getVarsta()).thenReturn(33);
		when(fotbalist.getType()).thenReturn(FotbalistType.PORTAR);
		when(fotbalist.getValoare()).thenReturn(60);
		MainController.getInstance().addFotbalist(fotbalist);
		
		//fundasi
		fotbalist = mock(Fundas.class);
		when(fotbalist.getNume()).thenReturn("3");
		when(fotbalist.getVarsta()).thenReturn(27);
		when(fotbalist.getType()).thenReturn(FotbalistType.FUNDAS);
		when(fotbalist.getValoare()).thenReturn(67);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Fundas.class);
		when(fotbalist.getNume()).thenReturn("4");
		when(fotbalist.getVarsta()).thenReturn(26);
		when(fotbalist.getType()).thenReturn(FotbalistType.FUNDAS);
		when(fotbalist.getValoare()).thenReturn(55);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Fundas.class);
		when(fotbalist.getNume()).thenReturn("5");
		when(fotbalist.getVarsta()).thenReturn(80);
		when(fotbalist.getType()).thenReturn(FotbalistType.FUNDAS);
		when(fotbalist.getValoare()).thenReturn(67);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Fundas.class);
		when(fotbalist.getNume()).thenReturn("6");
		when(fotbalist.getVarsta()).thenReturn(18);
		when(fotbalist.getType()).thenReturn(FotbalistType.FUNDAS);
		when(fotbalist.getValoare()).thenReturn(45);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Fundas.class);
		when(fotbalist.getNume()).thenReturn("7");
		when(fotbalist.getVarsta()).thenReturn(31);
		when(fotbalist.getType()).thenReturn(FotbalistType.FUNDAS);
		when(fotbalist.getValoare()).thenReturn(33);
		MainController.getInstance().addFotbalist(fotbalist);
		
		//mijlocasi
		fotbalist = mock(Mijlocas.class);
		when(fotbalist.getNume()).thenReturn("8");
		when(fotbalist.getVarsta()).thenReturn(33);
		when(fotbalist.getType()).thenReturn(FotbalistType.MIJLOCAS);
		when(fotbalist.getValoare()).thenReturn(77);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Mijlocas.class);
		when(fotbalist.getNume()).thenReturn("9");
		when(fotbalist.getVarsta()).thenReturn(22);
		when(fotbalist.getType()).thenReturn(FotbalistType.MIJLOCAS);
		when(fotbalist.getValoare()).thenReturn(85);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Mijlocas.class);
		when(fotbalist.getNume()).thenReturn("10");
		when(fotbalist.getVarsta()).thenReturn(25);
		when(fotbalist.getType()).thenReturn(FotbalistType.MIJLOCAS);
		when(fotbalist.getValoare()).thenReturn(77);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Mijlocas.class);
		when(fotbalist.getNume()).thenReturn("11");
		when(fotbalist.getVarsta()).thenReturn(26);
		when(fotbalist.getType()).thenReturn(FotbalistType.MIJLOCAS);
		when(fotbalist.getValoare()).thenReturn(60);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Mijlocas.class);
		when(fotbalist.getNume()).thenReturn("12");
		when(fotbalist.getVarsta()).thenReturn(23);
		when(fotbalist.getType()).thenReturn(FotbalistType.MIJLOCAS);
		when(fotbalist.getValoare()).thenReturn(40);
		MainController.getInstance().addFotbalist(fotbalist);
		
		//atacanti
		fotbalist = mock(Atacant.class);
		when(fotbalist.getNume()).thenReturn("13");
		when(fotbalist.getVarsta()).thenReturn(24);
		when(fotbalist.getType()).thenReturn(FotbalistType.ATACANT);
		when(fotbalist.getValoare()).thenReturn(80);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Atacant.class);
		when(fotbalist.getNume()).thenReturn("14");
		when(fotbalist.getVarsta()).thenReturn(27);
		when(fotbalist.getType()).thenReturn(FotbalistType.ATACANT);
		when(fotbalist.getValoare()).thenReturn(60);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Atacant.class);
		when(fotbalist.getNume()).thenReturn("15");
		when(fotbalist.getVarsta()).thenReturn(22);
		when(fotbalist.getType()).thenReturn(FotbalistType.ATACANT);
		when(fotbalist.getValoare()).thenReturn(50);
		MainController.getInstance().addFotbalist(fotbalist);
		
		assertEquals(MainController.getInstance().getMediaDeVarstaBestEleven(), 31.36f, .5f);
	}
	
	@Test
	public void testMedieVarstaWorstEleven() throws NotEnoughPlayersException{
		Fotbalist fotbalist;
		
		//portari
		fotbalist = mock(Portar.class);
		when(fotbalist.getNume()).thenReturn("1");
		when(fotbalist.getVarsta()).thenReturn(37);
		when(fotbalist.getType()).thenReturn(FotbalistType.PORTAR);
		when(fotbalist.getValoare()).thenReturn(70);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Portar.class);
		when(fotbalist.getNume()).thenReturn("2");
		when(fotbalist.getVarsta()).thenReturn(33);
		when(fotbalist.getType()).thenReturn(FotbalistType.PORTAR);
		when(fotbalist.getValoare()).thenReturn(60);
		MainController.getInstance().addFotbalist(fotbalist);
		
		//fundasi
		fotbalist = mock(Fundas.class);
		when(fotbalist.getNume()).thenReturn("3");
		when(fotbalist.getVarsta()).thenReturn(27);
		when(fotbalist.getType()).thenReturn(FotbalistType.FUNDAS);
		when(fotbalist.getValoare()).thenReturn(68);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Fundas.class);
		when(fotbalist.getNume()).thenReturn("4");
		when(fotbalist.getVarsta()).thenReturn(26);
		when(fotbalist.getType()).thenReturn(FotbalistType.FUNDAS);
		when(fotbalist.getValoare()).thenReturn(55);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Fundas.class);
		when(fotbalist.getNume()).thenReturn("5");
		when(fotbalist.getVarsta()).thenReturn(80);
		when(fotbalist.getType()).thenReturn(FotbalistType.FUNDAS);
		when(fotbalist.getValoare()).thenReturn(67);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Fundas.class);
		when(fotbalist.getNume()).thenReturn("6");
		when(fotbalist.getVarsta()).thenReturn(18);
		when(fotbalist.getType()).thenReturn(FotbalistType.FUNDAS);
		when(fotbalist.getValoare()).thenReturn(45);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Fundas.class);
		when(fotbalist.getNume()).thenReturn("7");
		when(fotbalist.getVarsta()).thenReturn(31);
		when(fotbalist.getType()).thenReturn(FotbalistType.FUNDAS);
		when(fotbalist.getValoare()).thenReturn(33);
		MainController.getInstance().addFotbalist(fotbalist);
		
		//mijlocasi
		fotbalist = mock(Mijlocas.class);
		when(fotbalist.getNume()).thenReturn("8");
		when(fotbalist.getVarsta()).thenReturn(33);
		when(fotbalist.getType()).thenReturn(FotbalistType.MIJLOCAS);
		when(fotbalist.getValoare()).thenReturn(77);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Mijlocas.class);
		when(fotbalist.getNume()).thenReturn("9");
		when(fotbalist.getVarsta()).thenReturn(22);
		when(fotbalist.getType()).thenReturn(FotbalistType.MIJLOCAS);
		when(fotbalist.getValoare()).thenReturn(85);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Mijlocas.class);
		when(fotbalist.getNume()).thenReturn("10");
		when(fotbalist.getVarsta()).thenReturn(25);
		when(fotbalist.getType()).thenReturn(FotbalistType.MIJLOCAS);
		when(fotbalist.getValoare()).thenReturn(77);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Mijlocas.class);
		when(fotbalist.getNume()).thenReturn("11");
		when(fotbalist.getVarsta()).thenReturn(26);
		when(fotbalist.getType()).thenReturn(FotbalistType.MIJLOCAS);
		when(fotbalist.getValoare()).thenReturn(60);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Mijlocas.class);
		when(fotbalist.getNume()).thenReturn("12");
		when(fotbalist.getVarsta()).thenReturn(23);
		when(fotbalist.getType()).thenReturn(FotbalistType.MIJLOCAS);
		when(fotbalist.getValoare()).thenReturn(40);
		MainController.getInstance().addFotbalist(fotbalist);
		
		//atacanti
		fotbalist = mock(Atacant.class);
		when(fotbalist.getNume()).thenReturn("13");
		when(fotbalist.getVarsta()).thenReturn(24);
		when(fotbalist.getType()).thenReturn(FotbalistType.ATACANT);
		when(fotbalist.getValoare()).thenReturn(80);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Atacant.class);
		when(fotbalist.getNume()).thenReturn("14");
		when(fotbalist.getVarsta()).thenReturn(27);
		when(fotbalist.getType()).thenReturn(FotbalistType.ATACANT);
		when(fotbalist.getValoare()).thenReturn(60);
		MainController.getInstance().addFotbalist(fotbalist);
		
		fotbalist = mock(Atacant.class);
		when(fotbalist.getNume()).thenReturn("15");
		when(fotbalist.getVarsta()).thenReturn(22);
		when(fotbalist.getType()).thenReturn(FotbalistType.ATACANT);
		when(fotbalist.getValoare()).thenReturn(50);
		MainController.getInstance().addFotbalist(fotbalist);
		
		assertEquals(MainController.getInstance().getMediaDeVarstaWorstEleven(), 31.27f, .5f);
	}
	
	@Test
	public void testToString() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist f = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "Adi", new GregorianCalendar(1995, 4, 3), new int[]{60, 70, 60, 30});
		assertEquals("Adi s-a nascut in 1995 si are valoarea "+(60+70+60+30)/4, f.toString());
	}
	
	@Test
	public void testNumeMiltiplu(){
		Fotbalist f1 = mock(Atacant.class);
		when(f1.getNume()).thenReturn("Adi");
		when(f1.getType()).thenReturn(FotbalistType.ATACANT);
		
		Fotbalist f2 = mock(Atacant.class);
		when(f2.getNume()).thenReturn("Adi");
		when(f2.getType()).thenReturn(FotbalistType.MIJLOCAS);
		
		MainController.getInstance().addFotbalist(f1);
		assertFalse(MainController.getInstance().addFotbalist(f2));
	}
	
	@Test
	public void testSingleton(){
		MainController controller1 = MainController.getInstance();
		MainController controller2 = MainController.getInstance();
		
		assertSame(controller1, controller2);
	}
	
	@Test
	public void testValoareMedieBestTeam() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException, NotEnoughPlayersException{
		Fotbalist portar1 = FotbalistFactory.getFotbalist(FotbalistType.PORTAR, "P1", new GregorianCalendar(), new int[]{20, 20});
		Fotbalist portar2 = FotbalistFactory.getFotbalist(FotbalistType.PORTAR, "P2", new GregorianCalendar(), new int[]{10, 10});
		
		MainController.getInstance().addFotbalist(portar1);
		MainController.getInstance().addFotbalist(portar2);
		
		Fotbalist fundas1 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F1", new GregorianCalendar(), new int[]{10, 10, 10});
		Fotbalist fundas2 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F2", new GregorianCalendar(), new int[]{30, 30, 30});
		Fotbalist fundas3 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F3", new GregorianCalendar(), new int[]{50, 50, 50});
		Fotbalist fundas4 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F4", new GregorianCalendar(), new int[]{20, 20, 20});
		Fotbalist fundas5 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F5", new GregorianCalendar(), new int[]{60, 60, 60});
		
		MainController.getInstance().addFotbalist(fundas1);
		MainController.getInstance().addFotbalist(fundas2);
		MainController.getInstance().addFotbalist(fundas3);
		MainController.getInstance().addFotbalist(fundas4);
		MainController.getInstance().addFotbalist(fundas5);
		
		Fotbalist mijlocas1 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M1", new GregorianCalendar(), new int[]{10, 10, 10});
		Fotbalist mijlocas2 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M2", new GregorianCalendar(), new int[]{30, 30, 30});
		Fotbalist mijlocas3 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M3", new GregorianCalendar(), new int[]{50, 50, 50});
		Fotbalist mijlocas4 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M4", new GregorianCalendar(), new int[]{20, 20, 20});
		Fotbalist mijlocas5 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M5", new GregorianCalendar(), new int[]{60, 60, 60});
		
		MainController.getInstance().addFotbalist(mijlocas1);
		MainController.getInstance().addFotbalist(mijlocas2);
		MainController.getInstance().addFotbalist(mijlocas3);
		MainController.getInstance().addFotbalist(mijlocas4);
		MainController.getInstance().addFotbalist(mijlocas5);
		
		Fotbalist atancat1 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "M1", new GregorianCalendar(), new int[]{10, 10, 10, 10});
		Fotbalist atancat2 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A2", new GregorianCalendar(), new int[]{30, 30, 30, 30});
		Fotbalist atancat3 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A3", new GregorianCalendar(), new int[]{50, 50, 50, 50});
		
		MainController.getInstance().addFotbalist(atancat1);
		MainController.getInstance().addFotbalist(atancat2);
		MainController.getInstance().addFotbalist(atancat3);
		
		assertEquals(38.18f, MainController.getInstance().getValoareMedieBestEleven(), .5f);

	}
	
	@Test
	public void testValoareMedieWorstTeam() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException, NotEnoughPlayersException{
		Fotbalist portar1 = FotbalistFactory.getFotbalist(FotbalistType.PORTAR, "P1", new GregorianCalendar(), new int[]{20, 20});
		Fotbalist portar2 = FotbalistFactory.getFotbalist(FotbalistType.PORTAR, "P2", new GregorianCalendar(), new int[]{10, 10});
		
		MainController.getInstance().addFotbalist(portar1);
		MainController.getInstance().addFotbalist(portar2);
		
		Fotbalist fundas1 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F1", new GregorianCalendar(), new int[]{10, 10, 10});
		Fotbalist fundas2 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F2", new GregorianCalendar(), new int[]{30, 30, 30});
		Fotbalist fundas3 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F3", new GregorianCalendar(), new int[]{50, 50, 50});
		Fotbalist fundas4 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F4", new GregorianCalendar(), new int[]{20, 20, 20});
		Fotbalist fundas5 = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F5", new GregorianCalendar(), new int[]{60, 60, 60});
		
		MainController.getInstance().addFotbalist(fundas1);
		MainController.getInstance().addFotbalist(fundas2);
		MainController.getInstance().addFotbalist(fundas3);
		MainController.getInstance().addFotbalist(fundas4);
		MainController.getInstance().addFotbalist(fundas5);
		
		Fotbalist mijlocas1 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M1", new GregorianCalendar(), new int[]{10, 10, 10});
		Fotbalist mijlocas2 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M2", new GregorianCalendar(), new int[]{30, 30, 30});
		Fotbalist mijlocas3 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M3", new GregorianCalendar(), new int[]{50, 50, 50});
		Fotbalist mijlocas4 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M4", new GregorianCalendar(), new int[]{20, 20, 20});
		Fotbalist mijlocas5 = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M5", new GregorianCalendar(), new int[]{60, 60, 60});
		
		MainController.getInstance().addFotbalist(mijlocas1);
		MainController.getInstance().addFotbalist(mijlocas2);
		MainController.getInstance().addFotbalist(mijlocas3);
		MainController.getInstance().addFotbalist(mijlocas4);
		MainController.getInstance().addFotbalist(mijlocas5);
		
		Fotbalist atancat1 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "M1", new GregorianCalendar(), new int[]{10, 10, 10, 10});
		Fotbalist atancat2 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A2", new GregorianCalendar(), new int[]{30, 30, 30, 30});
		Fotbalist atancat3 = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A3", new GregorianCalendar(), new int[]{50, 50, 50, 50});
		
		MainController.getInstance().addFotbalist(atancat1);
		MainController.getInstance().addFotbalist(atancat2);
		MainController.getInstance().addFotbalist(atancat3);
		
		assertEquals(28.18f, MainController.getInstance().getValoareMedieWorstEleven(), .5f);

	}
	
	@Test
	public void testPortarNotNull() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist portar = FotbalistFactory.getFotbalist(FotbalistType.PORTAR, "P1", new GregorianCalendar(), new int[]{20, 20});
		assertNotNull(portar);
	}
	
	@Test
	public void testPortarNull() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist portar = FotbalistFactory.getFotbalist(null, "P1", new GregorianCalendar(), new int[]{20, 20});
		assertNull(portar);
	}
	
	@Test
	public void testFundasNotNull() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist fundas = FotbalistFactory.getFotbalist(FotbalistType.FUNDAS, "F1", new GregorianCalendar(), new int[]{20, 20, 20});
		assertNotNull(fundas);
	}
	
	@Test
	public void testFundasNull() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist fundas = FotbalistFactory.getFotbalist(null, "F1", new GregorianCalendar(), new int[]{20, 20, 20});
		assertNull(fundas);
	}
	
	@Test
	public void testMijlocasNotNull() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist mijlocas = FotbalistFactory.getFotbalist(FotbalistType.MIJLOCAS, "M1", new GregorianCalendar(), new int[]{20, 20, 20});
		assertNotNull(mijlocas);
	}
	
	@Test
	public void testMijlocasNull() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist mijlocas = FotbalistFactory.getFotbalist(null, "M1", new GregorianCalendar(), new int[]{20, 20, 20});
		assertNull(mijlocas);
	}
	
	@Test
	public void testAtacantNotNull() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist atacant = FotbalistFactory.getFotbalist(FotbalistType.ATACANT, "A1", new GregorianCalendar(), new int[]{20, 20, 20, 40});
		assertNotNull(atacant);
	}
	
	@Test
	public void testAtacantNull() throws NotEnoughAttributesException, AttributeNotInRangeException, NameNotEmptyException{
		Fotbalist atacant = FotbalistFactory.getFotbalist(null, "A1", new GregorianCalendar(), new int[]{20, 20, 20, 40});
		assertNull(atacant);
	}
}
