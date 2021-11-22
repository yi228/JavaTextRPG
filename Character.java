import java.util.Scanner;

public class Character{
	public String job; //직업
	public static String skill; //스킬
	private int level; //레벨
	private int hp; //체력
	private int power; //공격력
	private int skillPower; //스킬 공격력
	public int exp; //경험치
	public static int action; //전투 시 행동
	public int attack; //공격 종류
	
	public Character(){ //초기 캐릭터 정보
		level = 1;
		exp = 0;
	}
	
	public void levelUp(){ //레벨업
		level++;
		hp *= 1.1;
		power *= 1.1;
		skillPower *=1.1;
	}
	
	public static void huntAction() { //사냥 시 행동
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose your action 1. Attack  2. Flee : ");
		action = sc.nextInt();
		if (action==1) {
			System.out.printf("Choose your attack : 1. Normal attack  2. %s", skill);
			action = sc.nextInt();
		}
	}
	
	public void huntFinish(){ //사냥 종료 후 경험치 증가
		exp += 50;
	}
	
	public void getInfo() { //캐릭터 정보 출력
		System.out.printf("Job : %s  HP : %d  Power : %d  EXP : %d\n", job, hp, power, exp);
	}
	
	
}