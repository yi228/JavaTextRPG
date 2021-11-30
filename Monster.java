public class Monster{
	public String kind; //���� ����
	public double hp; //ü��
	public double power; //���ݷ�
	public static int num; //���� ���� �迭 �ε��� ����
	
	public Monster() {
		this("none",0,0);
	}
	
	public Monster(String kind, double hp, double power) {
		this.kind = kind;
		this.hp = hp;
		this.power = power;
	}
	
	public void getInfo() { //���� ���� ���
		System.out.printf("���� : %s   HP : %.2f   ���ݷ� : %.2f\n", kind, hp, power);
	}
	
	public void getHp() { //���� ���� ü�� ���
		System.out.printf("%s�� ���� HP : %.2f\n", kind, hp);
	}
}

class Boss extends Monster{ //���� ����
	int fullhp;
	int index;
	
	public Boss() {
		hp = 150;
		power = 45;
		fullhp = 150;
		index = 0;
	}
	
	public String bossSelect() {
		String[] bossKind = {"�渱", "�丣������", "�����ŷ", "�巡��"}; //���� ����
		return bossKind[index%4];
	}
	
	public void levelUp() {
		hp = fullhp;
		hp *= 1.8;
		fullhp *= 1.8;
		power *= 1.8;
		index++;
	}
}