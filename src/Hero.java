
public class Hero {
	private String name;
	private String type;
	private int health;
	private int recoveryRate;
	
	public Hero(String inputName, String inputType) {
		name = inputName;
		type = inputType;
		if (type == "Gambler") {
			health = 200;
			recoveryRate = 8;
		} else if (type == "Medic") {
			health = 150;
			recoveryRate = 15;
		} else if (type == "Diplomat") {
			health = 200;
			recoveryRate = 8;
		} else if (type == "Tank") {
			health = 400;
			recoveryRate = 10;
		} else if (type == "Explorer") {
			health = 300;
			recoveryRate = 8;
		} else if (type == "Lucky") {
			health = 250;
			recoveryRate = 9;
		}
	}
	
	

public class Tank {
	private int health = 400;
	private int recovery_rate = 10;
	private String ability_description = "Tank has higher health and recovery rate.";
}

public class Gambler {
	private int health = 200;
	private int recovery_rate = 8;
	private String ability_description = "Gambler has an extra dice roll and an extra guess at the guess the number game.");
	
}
public class Explorer {
	private int health = 300;
	private int recovery_rate = 8;
	private String ability_description = "Explorer knows the layout of every city.";
}
public class Medic{
	private int health = 150;
	private int recovery_rate = 15;
	private String ability_description = "Medic increases everyones recovery rate by 5, and healing items increase health by more.";
	
}
public class Diplomat {
	private int health = 200;
	private int recovery_rate = 8;
	private String ability_description = "Diplomat decreases the cost of items in the store, and can skip one Villain per game (not including super vilain).";
	
}
public class Lucky {
	private int health = 250;
	private int recovery_rate = 9;
	private String ability_description = "Lucky gets more money from winning each battle.";
}


	
	
	
	
	
}
