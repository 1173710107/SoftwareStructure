package P3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class igameTest {

	
	@Test
	void testMovePiece() {
		igame game = new igame();
		game.setplayername("0", 0);
		game.setplayername("1", 1);
		Piece1 x  =new Piece1();
		x.setPname("test");
		x.setPiecestate(1);
		x.setPx(1);
		x.setPy(1);
		x.setfrom("0");
		game.addPiece(x, 0);
		game.setPiece(1, 1, x);
		game.movePiece(0, 1, 1, 2, 2);
		Piece1 y = game.getPiece(2, 2);
		assertEquals("test",y.getPname());
	}

	@Test
	void testRemovePiece() {
		igame game = new igame();
		game.setplayername("0", 0);
		game.setplayername("1", 1);
		Piece1 x  =new Piece1();
		x.setPname("test");
		x.setPiecestate(1);
		x.setPx(1);
		x.setPy(1);
		x.setfrom("0");
		game.addPiece(x, 0);
		game.setPiece(1, 1, x);
		game.remove(x, 0);
		Piece1 y = game.getPiece(1, 1);
		assertEquals(0,y.getPiecestate());
		
	}

	@Test
	void testEatPiece() {
		igame game = new igame();
		game.setplayername("0", 0);
		game.setplayername("1", 1);
		Piece1 x  =new Piece1();
		x.setPname("test");
		x.setPiecestate(1);
		x.setPx(1);
		x.setPy(1);
		x.setfrom("0");
		game.addPiece(x, 0);
		game.setPiece(1, 1, x);
		Piece1 y  =new Piece1();
		y.setPname("test1");
		y.setPiecestate(1);
		y.setPx(2);
		y.setPy(2);
		y.setfrom("1");
		game.addPiece(y, 1);
		game.setPiece(2, 2, y);
		game.eatPiece(0, 1, 1, 2, 2);
		Piece1 z  = game.getPiece(2, 2);
		Piece1 m = game.getPiece(1, 1);
		assertEquals("test",z.getPname());
		assertEquals(0,m.getPiecestate());
	}

	@Test
	void testgetnumPiece() {
		igame game = new igame();
		game.setplayername("0", 0);
		game.setplayername("1", 1);
		Piece1 x  =new Piece1();
		x.setPname("test");
		x.setPiecestate(1);
		x.setPx(1);
		x.setPy(1);
		x.setfrom("0");
		game.addPiece(x, 0);
		game.setPiece(1, 1, x);
		Piece1 y  =new Piece1();
		y.setPname("test1");
		y.setPiecestate(1);
		y.setPx(2);
		y.setPy(2);
		y.setfrom("0");
		game.addPiece(y, 0);
		game.setPiece(2, 2, y);
		assertEquals(2,game.getnumPiece(0));
	}


}
