import java.util.Scanner;

public class Character{
	public String job; //����
	public static String skill; //��ų
	public int level; //����
	public double hp; //ü��
	public double fullhp; //Ǯ�� ����
	public double power; //���ݷ�
	public double skillPower; //��ų ���ݷ�
	public int exp; //����ġ
	public static int action; //���� �� �ൿ
	public int attack; //���� ����
	public int money; //���� �� ��
	public int potion; //���� ����
	
	public Character(){ //�ʱ� ĳ���� ����
		level = 1;
		exp = 0;
		money = 0;
		potion = 0;
	}
	
	public void levelUp(){ //������
		level++;
		hp *= 1.1;
		fullhp *= 1.1;
		power *= 1.1;
		skillPower *= 1.1;
		System.out.println("���� ��!\n");
		if(level%5==0) {
			System.out.printf("���� ���� ����Ϳ� �� �� �ֽ��ϴ�.\n");
		}
	}
	
	public static int huntAction() { //��� �� �ൿ
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("������ �� �� ������ 1. ����  2. ����  3.���� ���");
		action = sc.nextInt();
		return action;
	}
	
	public int attSelect() { //���� ����
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.printf("� ������ �� �� ������. : 1. �Ϲ� ����  2. %s\n", skill);
		attack = sc.nextInt();
		return attack;
	}
	
	public void huntFinish(){ //��� ���� �� ����ġ, �� ����
		exp += 50;
		money += 50;
		if (exp>=100) {
			levelUp();
			exp -= 100;
		}
	}
	
	public void death() { //ĳ���� ��� ��
		hp = fullhp;
		exp = 0;
		money = 0;
		potion = 0;
	}
	
	public void getInfo() { //ĳ���� ���� ���
		System.out.printf("���� : %s   ���� : %d   HP : %.2f   ���ݷ� : %.2f   EXP : %d   �� : %d��   ���� : %d��\n", job, level, hp, power, exp, money, potion);
	}
	
	public void getHp() {
		System.out.printf("����� ���� HP : %.2f\n", hp);
	}
	
}