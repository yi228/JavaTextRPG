import java.util.*;
import java.io.*;

public class Game{
	public static int tmpPlace;
	public static int skillCount;
	public static int monLevel;
	public static String[] monKind;
	public static double[] monHp;
	public static double[] monPo;
	
	public Game(){
		skillCount = 0;
		monLevel = 0;
		
		System.out.println("==============================JAVA �ؽ�Ʈ RPG==============================");
		System.out.println("==========================================================2018125050 ������");
		System.out.println("===========================================================================\n�� �������� ü�°� ���ݷ��� �ٸ��ϴ�.\n��ų�� �Ϲ� ���ݺ��� 1.5�� �����ϸ� �� ����Ϳ��� �� �� ����� �� �ֽ��ϴ�.\n����Ϳ��� ������ ����ġ�� ��, ���� ������ 0�� �˴ϴ�.\n���� ����Ϳ��� ������ 5�� ����� ���� ������ �� �ֽ��ϴ�.\n�������� �޽��� ���ϸ� ü���� �ִ�ġ�� ������ ����ġ�� 0�� �˴ϴ�.\n===========================================================================\n");
	}
	
	@SuppressWarnings("static-access")
	public static void process() throws IOException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Random randomNum = new Random();
		Boss boss = new Boss();
		Character ch = new Character();
		String[] monKind = {"�����ΰ�", "�𵥵�", "����", "���� �巡��", "�����", "��", "�����̾�", "Ʈ��"}; //���� ����
		double[] monHp = {35, 35, 30, 40, 40, 35, 30, 35}; //���� ü��
		double[] monPo = {30, 20, 25, 30, 25, 30, 35, 25}; //���� ���ݷ�
		System.out.println("1. �ҷ�����  2. �� ����");
		ch.ld = sc.nextInt();
		if(ch.ld==1) {
			monLevel = ch.load();
		}
		if(ch.ld==2) {
			ch.jobSelect();
		}
		ch.skillPower = 1.5*ch.power;
		ch.getInfo();
		System.out.println(" ");
		if(ch.loadCount!=0) {
			for(int i=1; i<=monLevel; i++) {
				boss.levelUp();
				for(int j=0; j<8; j++) {
					monHp[j] *=2.1;
					monPo[j] *=2.1;
				}
			}
		while(true) {
			tmpPlace = 0;
			System.out.printf("��Ҹ� �����ϼ��� : 1. �Ϲ� �����   2. ���� �����   3. ����   4. ���� ����   5. ���� ����\n");
			tmpPlace = sc.nextInt();
			if(tmpPlace == 1) { //�Ϲ� �����
				int randNum1 = randomNum.nextInt(8);
				int randNum2 = randomNum.nextInt(8);
				Monster mon1 = new Monster(monKind[randNum1], monHp[randNum1], monPo[randNum1]); //���� ��ü1 ����
				Monster mon2 = new Monster(monKind[randNum2], monHp[randNum2], monPo[randNum2]); //���� ��ü2 ����
				System.out.println(" ");
				mon1.getInfo();
				mon2.getInfo();
				while(true) {
					System.out.println(" ");
					int action = ch.huntAction();
					if(action==1) {
						System.out.printf("\n� ���� ������ �� ������ : 1. %s  2. %s\n", mon1.kind, mon2.kind);
						int attObj = sc.nextInt();
						if(attObj == 1) {
							if(mon1.hp<=0) {
								System.out.println("�̹� ���� ���Դϴ�..");
								continue;
							}
							int attack = ch.attSelect();
							if (attack==1) {
								System.out.printf("%s���� �Ϲ� ����\n\n", mon1.kind);
								mon1.hp -= ch.power;
							}
							else if(attack==2&&skillCount<2) {
								System.out.printf("%s���� %s\n\n", mon1.kind, ch.skill);
								mon1.hp -= ch.skillPower;
								skillCount++;
							}
							else if(attack==2&&skillCount>=2) {
								System.out.println("��ų ��� Ƚ���� ��� �����߽��ϴ�..\n");
								continue;
							}
							else {
								System.out.println("�߸��� �����Դϴ�. �ٽ� �����ϼ���.\n");
								continue;
							}
							if(mon1.hp>0) {
								mon1.getHp();
							}
							else {
								System.out.printf("%s(��)�� �׿����ϴ�!\n", mon1.kind);
								ch.killCount++;
							}
						}
						else if(attObj ==2) {
							if(mon2.hp<=0) {
								System.out.println("�̹� ���� ���Դϴ�..");
								continue;
							}
							int attack = ch.attSelect();
							if (attack==1) {
								System.out.printf("%s���� �Ϲ� ����\n\n", mon2.kind);
								mon2.hp -= ch.power;
							}
							else if(attack==2&&skillCount<2) {
								System.out.printf("%s���� %s\n\n", mon2.kind, ch.skill);
								mon2.hp -= ch.skillPower;
								skillCount++;
							}
							else if(attack==2&&skillCount>=2) {
								System.out.println("��ų ��� Ƚ���� ��� �����߽��ϴ�..\n");
								continue;
							}
							else {
								System.out.println("�߸��� �����Դϴ�. �ٽ� �����ϼ���.\n");
								continue;
							}
							if(mon2.hp>0) {
								mon2.getHp();
							}
							else {
								System.out.printf("%s(��)�� �׿����ϴ�!\n", mon2.kind);
								ch.killCount++;
							}
						}
						else {
							System.out.println("�߸��� �����Դϴ�. �ٽ� �����ϼ���.\n");
							continue;
						}
						if(mon1.hp<=0 && mon2.hp<=0) {
							System.out.printf("�¸�! ��� ������ �׿����ϴ�! EXP +50 �� +50\n\n");
							ch.huntFinish();
							break;
						}
						if(mon1.hp>0) {
							System.out.printf("%s(��)�� ����� �����߽��ϴ�. : HP -%.2f\n\n", mon1.kind, mon1.power);
							ch.hp -= mon1.power;
						}
						if(mon2.hp>0) {
							System.out.printf("%s(��)�� ����� �����߽��ϴ�. : HP -%.2f\n\n", mon2.kind, mon2.power);
							ch.hp -= mon2.power;
						}
						if(ch.hp>0) {
							ch.getHp();
						}
						else {
							System.out.println("����� �й��Դϴ�...\n");
							ch.death();
							ch.deathCount++;
							break;
						}
					}
					else if(action==2) {
						System.out.println("�����߽��ϴ�...\n");
						break;
					}
					else if(action==3&&ch.potion>0) {
						ch.potion--;
						ch.hp = ch.fullhp;
						System.out.printf("������ ����߽��ϴ�. ü���� �ִ�ġ�� �Ǿ����ϴ�. ���� ���� ���� : %d\n", ch.potion);
					}
					else if(action==3&&ch.potion<=0) {
						System.out.println("������ �����ϴ�.");
					}
					else {
						System.out.println("�߸��� �����Դϴ�. �ٽ� �����ϼ���.\n");
						continue;
					}
				}
			}
			else if(tmpPlace == 2 && ch.level%5 !=0) {
				System.out.println("���� ����ϱ⿣ �ʹ� ���մϴ�..\n");
			}
			else if(tmpPlace == 2 && ch.level%5 ==0) {
				boss.kind = boss.bossSelect();
				boss.getInfo();
				while(true) {
					int action = ch.huntAction();
					if(action==1) {
						int attack = ch.attSelect();
						if (attack==1) {
							boss.hp -= ch.power;
							System.out.printf("%s���� �Ϲ� ����\n\n", boss.kind);
						}
						else if(attack==2&&skillCount<2) {
							boss.hp -= ch.skillPower;
							System.out.printf("%s���� %s\n\n", boss.kind, ch.skill);
							skillCount++;
						}
						else if(attack==2&&skillCount>=2) {
							System.out.println("��ų ��� Ƚ���� ��� �����߽��ϴ�..\n");
							continue;
						}
						else {
							System.out.println("�߸��� �����Դϴ�. �ٽ� �����ϼ���.\n");
							continue;
						}
						if(boss.hp>0) {
							boss.getHp();
						}
						else {
							System.out.printf("�¸�! ����� %s(��)�� �׿����ϴ�! EXP +100 ��+100\n\n", boss.kind);
							ch.killCount++;
							ch.exp+=50;
							ch.money+=50;
							ch.huntFinish();
							boss.levelUp();
							for(int i=0; i<8; i++) {
								monHp[i] *=1.8;
								monPo[i] *=1.8;
							}
							break;
						}
						System.out.printf("%s(��)�� ����� �����߽��ϴ�. : HP -%.2f\n\n", boss.kind, boss.power);
						ch.hp -= boss.power;
						if(ch.hp>0) {
							ch.getHp();
						}
						else {
							System.out.println("����� �й��Դϴ�...\n");
							ch.death();
							ch.deathCount++;
							break;
						}
					}
					else if(action==2) {
						System.out.println("�����߽��ϴ�...\n");
						break;
					}
					else if(action==3&&ch.potion>0) {
						ch.potion--;
						ch.hp = ch.fullhp;
						System.out.printf("������ ����߽��ϴ�. ü���� �ִ�ġ�� �Ǿ����ϴ�. ���� ���� ���� : %d\n", ch.potion);
					}
					else if(action==3&&ch.potion<=0) {
						System.out.println("������ �����ϴ�.");
					}
					else {
						System.out.println("�߸��� �����Դϴ�. �ٽ� �����ϼ���.\n");
						continue;
					}
				}
			}
			else if(tmpPlace ==3) {
				System.out.printf("�������� �޽��� ���߽��ϴ�. ü���� �ִ�ġ�� �ǰ� ����ġ�� �ʱ�ȭ�˴ϴ�.\n\n");
				ch.exp = 0;
				ch.hp = ch.fullhp;
			}
			else if(tmpPlace == 4) {
				System.out.println("���� ������ ���� ���� ȯ���մϴ�. ���� ������ �� ���� ���ŵ� ü���� �ִ�ġ�� �ȴ�ϴ�!");
				System.out.println("������ �ϳ� �� 30���Դϴ�. ������ ��ðھ��?  1. ��   2. �ƴϿ�");
				int stAct = sc.nextInt();
				if(stAct == 1) {
					System.out.printf("�󸶳� ��ðھ��? (�ִ� ���� ���� ����: %d��)\n", ch.money/30);
					int poNum = sc.nextInt();
					int price = poNum*30;
					if(price>ch.money) {
						System.out.println("���� ���°� ��ɸ� ����! ����!");
						continue;
					}
					else {
						System.out.printf("������ %d���Դϴ�! ���� �����մϴ�!\n", price);
						ch.potion += poNum;
						ch.money -= price;
						System.out.printf("���� %d�� �߰�, �� %d�� ����\n\n", poNum, price);
					}
				}
				else if(stAct == 2) {
					System.out.println("�׷� �� �Ծ��? �����ּ���..\n");
				}
				else {
					System.out.println("�߸��� �����Դϴ�. �ٽ� �����ϼ���.\n\n");
					continue;
				}
			}
			else if(tmpPlace == 5) {
				System.out.println("�����Ͻðڽ��ϱ�? 1. ��  2. �ƴϿ�");
				int sv = sc.nextInt();
				if(sv==1) {
					FileWriter fw = new FileWriter("./save.txt");
					String data = ch.kind+"$"+ch.hp+"$"+ch.power+"$"+ch.exp+"$"+ch.fullhp+"$"+ch.money+"$"+ch.potion+"$"+boss.index+"$"+ch.level+"$"+ch.skill+"$"+ch.killCount+"$"+ch.deathCount;
					fw.write(data);
					fw.close();
				}
				else if(sv ==2) {
					FileWriter fw = new FileWriter("./save.txt");
					fw.close();
				}
				else if(sv!=2) {
					System.out.println("�߸��� �����Դϴ�. �ٽ� �����ϼ���.\n");
					continue;
				}
				System.out.println("�÷��� ���ּż� �����մϴ�. ���� �Ϸ� �Ǽ���.");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			else {
				System.out.println("�߸��� �����Դϴ�. �ٽ� �����ϼ���.\n");
				continue;
			}
			skillCount = 0;
			ch.getInfo();
			System.out.printf("ų : %d   ���� : %d\n", ch.killCount, ch.deathCount);
			System.out.println(" ");
		}
	}
}
}