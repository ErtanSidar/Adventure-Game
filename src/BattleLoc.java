public abstract class BattleLoc extends Location {
	protected Obstacle obstacle;
	protected String award;

	BattleLoc(Player player, String name, Obstacle obstacle, String award) {
		super(player);
		this.obstacle = obstacle;
		this.name = name;
		this.award = award;
	}

	public boolean getLocation() {
		int obsCount = obstacle.count();
		System.out.println("You are here now : " + this.getName());
		System.out.println("Be aware ! " + obsCount + " " + obstacle.getName() + "s lives there!");
		System.out.print("<F>ight or <R>un :");
		String selCase = scan.nextLine();
		selCase = selCase.toUpperCase();
		if (selCase.equals("F")) {
			if (combat(obsCount)) {
				System.out.println(this.getName() + " has been cleared !");
				if (this.award.equals("Food") && player.getInv().isFood() == false) {
					System.out.println("You won " + this.award);
					player.getInv().setFood(true);
				} else if (this.award.equals("Water") && player.getInv().isWater() == false) {
					System.out.println("You won " + this.award);
					player.getInv().setWater(true);
				} else if (this.award.equals("Firewood") && player.getInv().isFirewood() == false) {
					System.out.println("You won " + this.award);
					player.getInv().setFirewood(true);
				}
				return true;
			}
			
			if(player.getHealthy() <= 0) {
				System.out.println("You died !");
				return false;
			}
		
		}
		return true;
	}

	public boolean combat(int obsCount) {
		for (int i = 0; i < obsCount; i++) {
			int defObsHealth = obstacle.getHealth();
			playerStats();
			enemyStats();
			while (player.getHealthy() > 0 && obstacle.getHealth() > 0) {
				System.out.print("<H>it or <R>un :");
				String selCase = scan.nextLine();
				selCase = selCase.toUpperCase();
				if (selCase.equals("H")) {
					System.out.println("You hit !");
					obstacle.setHealth(obstacle.getHealth() - player.getTotalDamage());
					afterHit();
					if (obstacle.getHealth() > 0) {
						System.out.println();
						System.out.println("You got hit by the monster !");
						player.setHealthy(player.getHealthy() - (obstacle.getDamage() - player.getInv().getArmor()));
						afterHit();
					}
				} else {
					return false;
				}
			}

			if (obstacle.getHealth() < player.getHealthy()) {
				System.out.println("You've defeated the enemy !");
				player.setMoney(player.getMoney() + obstacle.getAward());
				System.out.println("Current Money : " + player.getMoney());
				obstacle.setHealth(defObsHealth);
			} else {
				return false;
			}
			System.out.println("-------------------");
		}
		return true;
	}

	public void playerStats() {
		System.out.println("Player Information\n--------------");
		System.out.println("Health:" + player.getHealthy());
		System.out.println("Damage:" + player.getTotalDamage());
		System.out.println("Money:" + player.getMoney());
		if (player.getInv().getDamage() > 0) {
			System.out.println("Weapon:" + player.getInv().getwName());
		}
		if (player.getInv().getArmor() > 0) {
			System.out.println("Armour:" + player.getInv().getaName());
		}
	}

	public void enemyStats() {
		System.out.println("\n" + obstacle.getName() + " Information\n--------------");
		System.out.println("Health:" + obstacle.getHealth());
		System.out.println("Damage:" + obstacle.getDamage());
		System.out.println("Award:" + obstacle.getAward());
	}

	public void afterHit() {
		System.out.println("Player's Health:" + player.getHealthy());
		System.out.println(obstacle.getName() + "'s Health:" + obstacle.getHealth());
		System.out.println();
	}

}
