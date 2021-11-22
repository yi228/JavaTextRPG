import java.util.Random;

public class Monster{
	protected String kind; //몬스터 종류
	protected int hp; //체력
	protected int power; //공격력
	protected static int num; //몬스터 종류 배열 인덱스 선택
	
	public Monster() {
		this(kiki(getnum()), hphp(getnum()), popo(getnum()));
	}
	
	public Monster(String kind, int hp, int power) {
		this.kind = kind;
		this.hp = hp;
		this.power = power;
	}
	
	private static int getnum() {
		Random randomNum = new Random();
		num = randomNum.nextInt(4);
		return num;
	}
	
	public static String kiki(int n) { //몬스터 종류
		String[] monKind = {"Werewolf", "Zombie", "Ghoul", "Assassin"};
		return monKind[n];
	}
	
	public static int hphp(int n) { //몬스터 체력
		int[] hparr = {40, 30, 35, 25};
		return hparr[n];
	}
	
	public static int popo(int n) { //몬스터 공격력
		int[] poarr = {25, 35, 30, 40};
		return poarr[n];
	}
	
	public void getInfo() { //몬스터 정보 출력
		System.out.printf("Monster : %s  HP : %d  Power : %d", kind, hp, power);
	}
	
	public void getHp() { //몬스터 남은 체력 출력
		System.out.printf("Enemy's remain HP : %d\n", hp);
	}
}

class Boss extends Monster{ //보스 몬스터
	public Boss() {
		kind = "Baron Humba";
		hp = 150;
		power = 45;
	}
	
	public void levelUp() {
		hp *= 1.1;
		power *= 1.1;
	}
}