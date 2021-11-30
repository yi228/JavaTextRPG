import java.util.Scanner;
import java.util.Random;

public class Main{
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Random randomNum = new Random();
		int tmpJob, tmpPlace;
		int killCount = 0;
		int deathCount = 0;
		int skillCount = 0;
		String[] monKind = {"�����ΰ�", "�𵥵�", "����", "���� �巡��", "�����", "��", "�����̾�", "Ʈ��"}; //���� ����
		double[] monHp = {35, 35, 30, 40, 40, 35, 30, 35}; //���� ü��
		double[] monPo = {30, 20, 25, 30, 25, 30, 35, 25}; //���� ���ݷ�
		Boss boss = new Boss();
		System.out.println("==============================JAVA �ؽ�Ʈ RPG==============================");
		System.out.println("==========================================================2018125050 ������");
		System.out.println("===========================================================================\n�� �������� ü�°� ���ݷ��� �ٸ��ϴ�.\n��ų�� �Ϲ� ���ݺ��� 1.5�� �����ϸ� �� ����Ϳ��� �� �� ����� �� �ֽ��ϴ�.\n����Ϳ��� ������ ����ġ�� ��, ���� ������ 0�� �˴ϴ�.\n���� ����Ϳ��� ������ 5�� ����� ���� ������ �� �ֽ��ϴ�.\n�������� �޽��� ���ϸ� ü���� �ִ�ġ�� ������ ����ġ�� 0�� �˴ϴ�.\n===========================================================================\n");
		Character ch = new Character();
		while(true) { //���� ����
			System.out.printf("������ �����ϼ��� : 1. ����  2. �ϻ���  3. ���\n");
			tmpJob = sc.nextInt();
			if(tmpJob == 1) {
				ch.job = "����";
				ch.hp = 110;
				ch.fullhp = 110;
				ch.power = 20;
				ch.skill = "ȭ���� �߻�";
				ch.skillPower = 1.5*ch.power;
				break;
			}
			else if(tmpJob ==2) {
				ch.job = "�ϻ���";
				ch.hp = 80;
				ch.fullhp = 80;
				ch.power = 26;
				ch.skill = "�޼� ���";
				ch.skillPower = 1.5*ch.power;
				break;
			}
			else if(tmpJob == 3) {
				ch.job = "���";
				ch.hp = 120;
				ch.fullhp = 120;
				ch.power = 16;
				ch.skill = "���� ����";
				ch.skillPower = 1.5*ch.power;
				break;
			}
			else if(tmpJob == 4) {
				ch.job = "tester";
				ch.hp = 1000000;
				ch.fullhp = 1000000;
				ch.power = 1000000;
				ch.skill = "?";
				ch.skillPower = ch.power;
				ch.money = 100000;
				break;
			}
			else {
				System.out.println("�߸��� �����Դϴ�. �ٽ� �����ϼ���.\n");
				continue;
			}
		}
		ch.getInfo();
		System.out.println(" ");
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
								killCount++;
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
								killCount++;
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
							deathCount++;
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
							killCount++;
							ch.exp+=50;
							ch.money+=50;
							ch.huntFinish();
							boss.levelUp();
							for(int i=0; i<8; i++) {
								monHp[i] *=1.5;
								monPo[i] *=1.5;
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
							deathCount++;
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
				System.out.printf("�������� �޽��� ���߽��ϴ�. ü���� �ִ�ġ�� �ǰ� ����ġ�� �ʱ�ȭ�˴ϴ�.\n");
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
						System.out.printf("���� %d�� �߰�, �� %d�� ����\n", poNum, price);
					}
				}
				else if(stAct == 2) {
					System.out.println("�׷� �� �Ծ��? �����ּ���..");
				}
				else {
					System.out.println("�߸��� �����Դϴ�. �ٽ� �����ϼ���.\n");
					continue;
				}
			}
			else if(tmpPlace == 5) {
				System.out.println("�÷��� ���ּż� �����մϴ�. ���� �Ϸ� �Ǽ���.");
				break;
			}
			else {
				System.out.println("�߸��� �����Դϴ�. �ٽ� �����ϼ���.\n");
				continue;
			}
			skillCount = 0;
			ch.getInfo();
			System.out.printf("ų : %d   ���� : %d\n", killCount, deathCount);
			System.out.println(" ");
		}
	} //main �� ��ȣ
} //class �� ��ȣ