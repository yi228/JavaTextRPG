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
		
		System.out.println("==============================JAVA 텍스트 RPG==============================");
		System.out.println("==========================================================2018125050 이정훈");
		System.out.println("===========================================================================\n각 직업마다 체력과 공격력이 다릅니다.\n스킬은 일반 공격보다 1.5배 강력하며 한 사냥터에서 두 번 사용할 수 있습니다.\n사냥터에서 죽으면 경험치와 돈, 보유 물약이 0이 됩니다.\n보스 사냥터에는 레벨이 5의 배수일 때만 입장할 수 있습니다.\n마을에서 휴식을 취하면 체력이 최대치가 되지만 경험치가 0이 됩니다.\n===========================================================================\n");
	}
	
	@SuppressWarnings("static-access")
	public static void process() throws IOException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Random randomNum = new Random();
		Boss boss = new Boss();
		Character ch = new Character();
		String[] monKind = {"늑대인간", "언데드", "구울", "새끼 드래곤", "오우거", "골렘", "뱀파이어", "트롤"}; //몬스터 종류
		double[] monHp = {35, 35, 30, 40, 40, 35, 30, 35}; //몬스터 체력
		double[] monPo = {30, 20, 25, 30, 25, 30, 35, 25}; //몬스터 공격력
		System.out.println("1. 불러오기  2. 새 게임");
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
			System.out.printf("장소를 선택하세요 : 1. 일반 사냥터   2. 보스 사냥터   3. 마을   4. 물약 상점   5. 게임 종료\n");
			tmpPlace = sc.nextInt();
			if(tmpPlace == 1) { //일반 사냥터
				int randNum1 = randomNum.nextInt(8);
				int randNum2 = randomNum.nextInt(8);
				Monster mon1 = new Monster(monKind[randNum1], monHp[randNum1], monPo[randNum1]); //몬스터 객체1 생성
				Monster mon2 = new Monster(monKind[randNum2], monHp[randNum2], monPo[randNum2]); //몬스터 객체2 생성
				System.out.println(" ");
				mon1.getInfo();
				mon2.getInfo();
				while(true) {
					System.out.println(" ");
					int action = ch.huntAction();
					if(action==1) {
						System.out.printf("\n어떤 적을 공격할 지 고르세요 : 1. %s  2. %s\n", mon1.kind, mon2.kind);
						int attObj = sc.nextInt();
						if(attObj == 1) {
							if(mon1.hp<=0) {
								System.out.println("이미 죽은 적입니다..");
								continue;
							}
							int attack = ch.attSelect();
							if (attack==1) {
								System.out.printf("%s에게 일반 공격\n\n", mon1.kind);
								mon1.hp -= ch.power;
							}
							else if(attack==2&&skillCount<2) {
								System.out.printf("%s에게 %s\n\n", mon1.kind, ch.skill);
								mon1.hp -= ch.skillPower;
								skillCount++;
							}
							else if(attack==2&&skillCount>=2) {
								System.out.println("스킬 사용 횟수를 모두 소진했습니다..\n");
								continue;
							}
							else {
								System.out.println("잘못된 선택입니다. 다시 선택하세요.\n");
								continue;
							}
							if(mon1.hp>0) {
								mon1.getHp();
							}
							else {
								System.out.printf("%s(을)를 죽였습니다!\n", mon1.kind);
								ch.killCount++;
							}
						}
						else if(attObj ==2) {
							if(mon2.hp<=0) {
								System.out.println("이미 죽은 적입니다..");
								continue;
							}
							int attack = ch.attSelect();
							if (attack==1) {
								System.out.printf("%s에게 일반 공격\n\n", mon2.kind);
								mon2.hp -= ch.power;
							}
							else if(attack==2&&skillCount<2) {
								System.out.printf("%s에게 %s\n\n", mon2.kind, ch.skill);
								mon2.hp -= ch.skillPower;
								skillCount++;
							}
							else if(attack==2&&skillCount>=2) {
								System.out.println("스킬 사용 횟수를 모두 소진했습니다..\n");
								continue;
							}
							else {
								System.out.println("잘못된 선택입니다. 다시 선택하세요.\n");
								continue;
							}
							if(mon2.hp>0) {
								mon2.getHp();
							}
							else {
								System.out.printf("%s(을)를 죽였습니다!\n", mon2.kind);
								ch.killCount++;
							}
						}
						else {
							System.out.println("잘못된 선택입니다. 다시 선택하세요.\n");
							continue;
						}
						if(mon1.hp<=0 && mon2.hp<=0) {
							System.out.printf("승리! 모든 적들을 죽였습니다! EXP +50 돈 +50\n\n");
							ch.huntFinish();
							break;
						}
						if(mon1.hp>0) {
							System.out.printf("%s(이)가 당신을 공격했습니다. : HP -%.2f\n\n", mon1.kind, mon1.power);
							ch.hp -= mon1.power;
						}
						if(mon2.hp>0) {
							System.out.printf("%s(이)가 당신을 공격했습니다. : HP -%.2f\n\n", mon2.kind, mon2.power);
							ch.hp -= mon2.power;
						}
						if(ch.hp>0) {
							ch.getHp();
						}
						else {
							System.out.println("당신의 패배입니다...\n");
							ch.death();
							ch.deathCount++;
							break;
						}
					}
					else if(action==2) {
						System.out.println("도주했습니다...\n");
						break;
					}
					else if(action==3&&ch.potion>0) {
						ch.potion--;
						ch.hp = ch.fullhp;
						System.out.printf("물약을 사용했습니다. 체력이 최대치가 되었습니다. 남은 물약 개수 : %d\n", ch.potion);
					}
					else if(action==3&&ch.potion<=0) {
						System.out.println("물약이 없습니다.");
					}
					else {
						System.out.println("잘못된 선택입니다. 다시 선택하세요.\n");
						continue;
					}
				}
			}
			else if(tmpPlace == 2 && ch.level%5 !=0) {
				System.out.println("보스 사냥하기엔 너무 약합니다..\n");
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
							System.out.printf("%s에게 일반 공격\n\n", boss.kind);
						}
						else if(attack==2&&skillCount<2) {
							boss.hp -= ch.skillPower;
							System.out.printf("%s에게 %s\n\n", boss.kind, ch.skill);
							skillCount++;
						}
						else if(attack==2&&skillCount>=2) {
							System.out.println("스킬 사용 횟수를 모두 소진했습니다..\n");
							continue;
						}
						else {
							System.out.println("잘못된 선택입니다. 다시 선택하세요.\n");
							continue;
						}
						if(boss.hp>0) {
							boss.getHp();
						}
						else {
							System.out.printf("승리! 당신은 %s(을)를 죽였습니다! EXP +100 돈+100\n\n", boss.kind);
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
						System.out.printf("%s(이)가 당신을 공격했습니다. : HP -%.2f\n\n", boss.kind, boss.power);
						ch.hp -= boss.power;
						if(ch.hp>0) {
							ch.getHp();
						}
						else {
							System.out.println("당신의 패배입니다...\n");
							ch.death();
							ch.deathCount++;
							break;
						}
					}
					else if(action==2) {
						System.out.println("도주했습니다...\n");
						break;
					}
					else if(action==3&&ch.potion>0) {
						ch.potion--;
						ch.hp = ch.fullhp;
						System.out.printf("물약을 사용했습니다. 체력이 최대치가 되었습니다. 남은 물약 개수 : %d\n", ch.potion);
					}
					else if(action==3&&ch.potion<=0) {
						System.out.println("물약이 없습니다.");
					}
					else {
						System.out.println("잘못된 선택입니다. 다시 선택하세요.\n");
						continue;
					}
				}
			}
			else if(tmpPlace ==3) {
				System.out.printf("마을에서 휴식을 취했습니다. 체력이 최대치가 되고 경험치가 초기화됩니다.\n\n");
				ch.exp = 0;
				ch.hp = ch.fullhp;
			}
			else if(tmpPlace == 4) {
				System.out.println("물약 상점에 오신 것을 환영합니다. 저희 물약은 한 병만 마셔도 체력이 최대치가 된답니다!");
				System.out.println("물약은 하나 당 30원입니다. 물약을 사시겠어요?  1. 예   2. 아니오");
				int stAct = sc.nextInt();
				if(stAct == 1) {
					System.out.printf("얼마나 사시겠어요? (최대 구매 가능 개수: %d개)\n", ch.money/30);
					int poNum = sc.nextInt();
					int price = poNum*30;
					if(price>ch.money) {
						System.out.println("돈도 없는게 욕심만 많네! 나가!");
						continue;
					}
					else {
						System.out.printf("가격은 %d원입니다! 구매 감사합니다!\n", price);
						ch.potion += poNum;
						ch.money -= price;
						System.out.printf("물약 %d개 추가, 돈 %d원 감소\n\n", poNum, price);
					}
				}
				else if(stAct == 2) {
					System.out.println("그럼 왜 왔어요? 나가주세요..\n");
				}
				else {
					System.out.println("잘못된 선택입니다. 다시 선택하세요.\n\n");
					continue;
				}
			}
			else if(tmpPlace == 5) {
				System.out.println("저장하시겠습니까? 1. 예  2. 아니오");
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
					System.out.println("잘못된 선택입니다. 다시 선택하세요.\n");
					continue;
				}
				System.out.println("플레이 해주셔서 감사합니다. 좋은 하루 되세요.");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			else {
				System.out.println("잘못된 선택입니다. 다시 선택하세요.\n");
				continue;
			}
			skillCount = 0;
			ch.getInfo();
			System.out.printf("킬 : %d   데스 : %d\n", ch.killCount, ch.deathCount);
			System.out.println(" ");
		}
	}
}
}