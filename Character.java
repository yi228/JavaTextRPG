import java.util.Scanner;

public class Character{
	public String job; //����
	public static String skill; //��ų
	private int level; //����
	private int hp; //ü��
	private int power; //���ݷ�
	private int skillPower; //��ų ���ݷ�
	public int exp; //����ġ
	public static int action; //���� �� �ൿ
	public int attack; //���� ����
	
	public Character(){ //�ʱ� ĳ���� ����
		level = 1;
		exp = 0;
	}
	
	public void levelUp(){ //������
		level++;
		hp *= 1.1;
		power *= 1.1;
		skillPower *=1.1;
	}
	
	public static void huntAction() { //��� �� �ൿ
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose your action 1. Attack  2. Flee : ");
		action = sc.nextInt();
		if (action==1) {
			System.out.printf("Choose your attack : 1. Normal attack  2. %s", skill);
			action = sc.nextInt();
		}
	}
	
	public void huntFinish(){ //��� ���� �� ����ġ ����
		exp += 50;
	}
	
	public void getInfo() { //ĳ���� ���� ���
		System.out.printf("Job : %s  HP : %d  Power : %d  EXP : %d\n", job, hp, power, exp);
	}
	
	
}