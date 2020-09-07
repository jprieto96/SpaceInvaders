package tp.p2.model;


public interface IExecuteRandomActions {
	
	static final double PROB_EXPLOSIVE_SHIP = 0.05;
	
	static boolean canGenerateRandomOvni(Game game){
		return game.getRandom().nextDouble() < game.getLevel().getOvniFrequency();
	}
	
	static boolean  canGenerateRandomBomb(Game game){
		return game.getRandom().nextDouble() < game.getLevel().getShootFrequency();	
	}
	
	static boolean  canConvertToExplosiveShip(Game game){
		return game.getRandom().nextDouble() < PROB_EXPLOSIVE_SHIP;	
	}
	
}
