import java.util.Scanner;

public class Character{
	public String job; //직업
	public static String skill; //스킬
	public int level; //레벨
	public double hp; //체력
	public double fullhp; //풀피 저장
	public double power; //공격력
	public double skillPower; //스킬 공격력
	public int exp; //경험치
	public static int action; //전투 시 행동
	public int attack; //공격 종류
	public int money; //물약 살 돈
	public int potion; //물약 개수
	
	public Character(){ //초기 캐릭터 정보
		level = 1;
		exp = 0;
		money = 0;
		potion = 0;
	}
	
	public void levelUp(){ //레벨업
		level++;
		hp *= 1.1;
		fullhp *= 1.1;
		power *= 1.1;
		skillPower *= 1.1;
		System.out.println("레벨 업!\n");
		if(level%5==0) {
			System.out.printf("이제 보스 사냥터에 들어갈 수 있습니다.\n");
		}
	}
	
	public static int huntAction() { //사냥 시 행동
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("무엇을 할 지 고르세요 1. 공격  2. 도주  3.물약 사용");
		action = sc.nextInt();
		return action;
	}
	
	public int attSelect() { //공격 선택
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.printf("어떤 공격을 할 지 고르세요. : 1. 일반 공격  2. %s\n", skill);
		attack = sc.nextInt();
		return attack;
	}
	
	public void huntFinish(){ //사냥 종료 후 경험치, 돈 증가
		exp += 50;
		money += 50;
		if (exp>=100) {
			levelUp();
			exp -= 100;
		}
	}
	
	public void death() { //캐릭터 사망 시
		hp = fullhp;
		exp = 0;
		money = 0;
		potion = 0;
	}
	
	public void getInfo() { //캐릭터 정보 출력
		System.out.printf("직업 : %s   레벨 : %d   HP : %.2f   공격력 : %.2f   EXP : %d   돈 : %d원   물약 : %d개\n", job, level, hp, power, exp, money, potion);
	}
	
	public void getHp() {
		System.out.printf("당신의 현재 HP : %.2f\n", hp);
	}
	
}