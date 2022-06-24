import java.util.Scanner;

public class Game {
	Player player;
	Location location;
	Scanner scan = new Scanner(System.in);

	public void login() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Greetings adventurer !");
		System.out.print("Enter your nickname: ");
		String playerName = scan.nextLine();
		player = new Player(playerName);
		player.selectCha();
		start();
	}

	public void start() {
		while (true) {
			System.out.println();
			System.out.println("=================================================");
			System.out.println();
			System.out.println("Choose a location : ");
			System.out.println("1. Safe House --> Your safe place, no enemies !");
			System.out.println("2. Cave --> You may see a zombie !");
			System.out.println("3. Forest --> You may see a vampire  !");
			System.out.println("4. River --> You may see a bear  !");
			System.out.println("5. Tool Store --> You can buy weapon or armour !");
			System.out.print("Pick the place you want to go : ");
			int selLoc = scan.nextInt();
			while (selLoc < 0 || selLoc > 5) {
				System.out.print("Please enter a valid input : ");
				selLoc = scan.nextInt();
			}

			switch (selLoc) {
			case 1:
				location = new SafeHouse(player);
				break;
			case 2:
				location = new Cave(player);
				break;
			case 3:
				location = new Forest(player);
				break;
			case 4:
				location = new River(player);
				break;
			case 5:
				location = new ToolStore(player);
				break;
			default:
				location = new SafeHouse(player);
			}

			if (location.getClass().getName().equals("SafeHouse")) {
				if (player.getInv().isFirewood() && player.getInv().isFood() && player.getInv().isWater()) {
					System.out.println("Congratulations you won the game !");
					break;
				}
			}
			if (!location.getLocation()) {
				System.out.println("Game Over !");
				break;
			}

		}
	}
}