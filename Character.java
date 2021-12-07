import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Character extends Creature{
	public static String skill; //스킬
	public double fullhp; //풀피 저장
	public double skillPower; //스킬 공격력
	public int exp; //경험치
	public static int action; //전투 시 행동
	public int attack; //공격 종류
	public int money; //물약 살 돈
	public int potion; //물약 개수
	public static int tmpJob;
	public int killCount;
	public int deathCount;
	public int loadCount;
	public int ld;
	private int i;
	
	public Character(){ //초기 캐릭터 정보
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
			System.out.println("지난 게임을 불러왔습니다.\n");
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
		System.out.println("저장된 게임이 없습니다.\n");
		ld = 2;
		i = 0;
	}
	
	public void jobSelect() {
		while (true) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.printf("직업을 선택하세요 : 1. 법사  2. 암살자  3. 기사\n");
		tmpJob = sc.nextInt();
		if(tmpJob == 1) {
			kind = "법사";
			hp = 110;
			fullhp = 110;
			power = 20;
			skill = "화염구 발사";
			break;
		}
		else if(tmpJob ==2) {
			kind = "암살자";
			hp = 80;
			fullhp = 80;
			power = 26;
			skill = "급소 찌르기";
			break;
		}
		else if(tmpJob == 3) {
			kind = "기사";
			hp = 120;
			fullhp = 120;
			power = 16;
			skill = "연속 베기";
		break;
		}
		else if(tmpJob == 4) {
			kind = "기사";
			hp = 1200000;
			fullhp = 1200000;
			power = 1600000;
			skill = "연속 베기";
		break;
		}
		else {
			System.out.println("잘못된 선택입니다. 다시 선택하세요.\n");
			continue;
		}
		}
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
		System.out.printf("직업 : %s   레벨 : %d   HP : %.2f   공격력 : %.2f   EXP : %d   돈 : %d원   물약 : %d개\n", kind, level, hp, power, exp, money, potion);
	}
	
	public void getHp() {
		System.out.printf("당신의 현재 HP : %.2f\n", hp);
	}
	
}