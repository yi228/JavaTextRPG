import java.util.Random;

public class Monster{
	protected String kind; //���� ����
	protected int hp; //ü��
	protected int power; //���ݷ�
	protected static int num; //���� ���� �迭 �ε��� ����
	
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
	
	public static String kiki(int n) { //���� ����
		String[] monKind = {"Werewolf", "Zombie", "Ghoul", "Assassin"};
		return monKind[n];
	}
	
	public static int hphp(int n) { //���� ü��
		int[] hparr = {40, 30, 35, 25};
		return hparr[n];
	}
	
	public static int popo(int n) { //���� ���ݷ�
		int[] poarr = {25, 35, 30, 40};
		return poarr[n];
	}
	
	public void getInfo() { //���� ���� ���
		System.out.printf("Monster : %s  HP : %d  Power : %d", kind, hp, power);
	}
	
	public void getHp() { //���� ���� ü�� ���
		System.out.printf("Enemy's remain HP : %d\n", hp);
	}
}

class Boss extends Monster{ //���� ����
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