public class ToolStore extends NormalLoc {

	ToolStore(Player player) {
		super(player, "Tool Store");
	}

	public boolean getLocation() {
		System.out.println("Money : " + player.getMoney());
		System.out.println("1. Weapons");
		System.out.println("2. Armours");
		System.out.println("3. Exit");
		System.out.print("Your choice : ");
		int selTool = scan.nextInt();
		int selItemID;
		switch (selTool) {
		case 1:
			selItemID = weaponMenu();
			buyWeapon(selItemID);
			break;
		case 2:
			selItemID = armorMenu();
			buyArmor(selItemID);
			break;
		default:
			break;
		}

		return true;
	}
	
	public int armorMenu() {
		System.out.println("1. Light \t <Cost : 15 - Defence : 1>");
		System.out.println("2. Medium \t <Cost : 25 - Defence : 3>");
		System.out.println("3. Heavy \t <Cost : 40 - Defence : 5>");
		System.out.println("4. Exit");
		System.out.print("Your armour choice : ");
		int selArmorID = scan.nextInt();
		return selArmorID;
	}
	
	public void buyArmor(int itemID) {
		int avoid = 0, price = 0;
		String aName = null;
		switch (itemID) {
		case 1:
			avoid = 1;
			aName = "Light Armour";
			price = 15;
			break;
		case 2:
			avoid = 3;
			aName = "Medium Armour";
			price = 25;
			break;
		case 3:
			avoid = 5;
			aName = "Heavy Armour";
			price = 40;
			break;
		case 4:
			System.out.println("Exiting.");
			break;
		default:
			System.out.println("Invalid Input !");
			break;
		}

		if (price > 0) {
			if (player.getMoney() >= price) {
				player.getInv().setArmor(avoid);
				player.getInv().setaName(aName);
				player.setMoney(player.getMoney() - price);
				System.out.println("You've bought " + aName + " Defence : " + player.getInv().getArmor());
				System.out.println("Current Money :" + player.getMoney());
			} else {
				System.out.println("Not enough money !");
			}
		}
	}

	public int weaponMenu() {
		System.out.println("1. Gun\t<Cost : 25 - Damage : 2>");
		System.out.println("2. Sword\t<Cost : 35 - Damage : 3>");
		System.out.println("3. Rifle\t<Cost : 45 - Damage : 7>");
		System.out.println("4. Exit");
		System.out.print("Your choice : ");
		int selWeaponID = scan.nextInt();
		return selWeaponID;
	}

	public void buyWeapon(int itemID) {
		int damage = 0, price = 0;
		String wName = null;
		switch (itemID) {
		case 1:
			damage = 2;
			wName = "Gun";
			price = 25;
			break;
		case 2:
			damage = 3;
			wName = "Sword";
			price = 35;
			break;
		case 3:
			damage = 7;
			wName = "Rifle";
			price = 45;
			break;
		case 4:
			System.out.println("Exiting.");
			break;
		default:
			System.out.println("Invalid Input !");
			break;
		}

		if (price > 0) {
			if (player.getMoney() > price) {
				player.getInv().setDamage(damage);
				player.getInv().setwName(wName);
				player.setMoney(player.getMoney() - price);
				System.out.println("You've bought " + wName + "  Old Damage :" + player.getDamage() + ", New Damage : "
						+ player.getTotalDamage());
				System.out.println("Current Money :" + player.getMoney());
			} else {
				System.out.println("Not Enough Money !");
			}
		}
	}

}