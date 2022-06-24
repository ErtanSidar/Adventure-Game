public class SafeHouse extends NormalLoc {

	SafeHouse(Player player) {
		super(player, "Safe House");
	}
	
	public boolean getLocation() {
		player.setHealthy(player.getrHealthy());
		System.out.println("Healed up...");
		System.out.println("You are at safe house now");
		return true;
	}

}
