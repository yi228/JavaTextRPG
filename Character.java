import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Character extends Creature{
	public static String skill; //��ų
	public double fullhp; //Ǯ�� ����
	public double skillPower; //��ų ���ݷ�
	public int exp; //����ġ
	public static int action; //���� �� �ൿ
	public int attack; //���� ����
	public int money; //���� �� ��
	public int potion; //���� ����
	public static int tmpJob;
	public int killCount;
	public int deathCount;
	public int loadCount;
	public int ld;
	private int i;
	
	public Character(){ //�ʱ� ĳ���� ����
		level = 1;
		exp = 0;
		money = 0;
		potion = 0;
		loadCount = 0;
		killCount = 0;
		deathCount = 0;
	}
	
	public int load() throws IOException {
		@SuppressWarnings({ "resource", "unused" })
		Scanner sc = new Scanner(System.in);
		try {
			BufferedReader br = new BufferedReader(new FileReader("./save.txt"));
			String line = br.readLine();
			br.close();
			StringTokenizer st = new StringTokenizer(line, "$");
			String[] strSave = new String[12];
			for (int i=0; i<12; i++) {
				strSave[i] = st.nextToken();
			}
			kind = strSave[0];
			hp = Double.parseDouble(strSave[1]);
			power = Double.parseDouble(strSave[2]);
			exp = Integer.parseInt(strSave[3]);
			fullhp = Double.parseDouble(strSave[4]);
			money = Integer.parseInt(strSave[5]);
			potion = Integer.parseInt(strSave[6]);
			level = Integer.parseInt(strSave[8]);
			skill = strSave[9];
			killCount = Integer.parseInt(strSave[10]);
			deathCount = Integer.parseInt(strSave[11]);
			loadCount++;
			FileWriter fw = new FileWriter("./save.txt");
			fw.close();
			System.out.println("���� ������ �ҷ��Խ��ϴ�.\n");
			i = Integer.parseInt(strSave[7]);
		} catch(NullPointerException ne){
			sc = new Scanner(System.in);
			noFile();;
		} catch(NoSuchElementException ns){
			sc = new Scanner(System.in);
			noFile();
		} catch(FileNotFoundException fn){
			sc = new Scanner(System.in);
			noFile();
		}
		return i;
	}
	
	public void noFile() {
		System.out.println("����� ������ �����ϴ�.\n");
		ld = 2;
		i = 0;
	}
	
	public void jobSelect() {
		while (true) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.printf("������ �����ϼ��� : 1. ����  2. �ϻ���  3. ���\n");
		tmpJob = sc.nextInt();
		if(tmpJob == 1) {
			kind = "����";
			hp = 110;
			fullhp = 110;
			power = 20;
			skill = "ȭ���� �߻�";
			break;
		}
		else if(tmpJob ==2) {
			kind = "�ϻ���";
			hp = 80;
			fullhp = 80;
			power = 26;
			skill = "�޼� ���";
			break;
		}
		else if(tmpJob == 3) {
			kind = "���";
			hp = 120;
			fullhp = 120;
			power = 16;
			skill = "���� ����";
		break;
		}
		else if(tmpJob == 4) {
			kind = "���";
			hp = 1200000;
			fullhp = 1200000;
			power = 1600000;
			skill = "���� ����";
		break;
		}
		else {
			System.out.println("�߸��� �����Դϴ�. �ٽ� �����ϼ���.\n");
			continue;
		}
		}
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
		System.out.printf("���� : %s   ���� : %d   HP : %.2f   ���ݷ� : %.2f   EXP : %d   �� : %d��   ���� : %d��\n", kind, level, hp, power, exp, money, potion);
	}
	
	public void getHp() {
		System.out.printf("����� ���� HP : %.2f\n", hp);
	}
	
}