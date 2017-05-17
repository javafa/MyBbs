package com.kodonho.mybbs.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class BbsLoader {
	final String DATABASE_DIR = "c:/temp/mybbs/";
	final String DATABASE_NAME = "mybbs.db";
	final String COLUMN_SEPERATOR = "@@"; // 한칸 단위
	final String RECORD_SEPERATOR = "\r\n"; // 한줄 단위
	/**
	 * 저장소에서 데이터를 읽어옴
	 * @return 반환값에 대한 설명
	 */
	public ArrayList<Bbs> read(){
		ArrayList<Bbs> result = new ArrayList<>();
		
		// TODO
		// 1. 파일 존재 유무 검사
		File database = new File(DATABASE_DIR + DATABASE_NAME);
		if(database.exists()){
			// 2. 있으면 파일을 읽어서 줄단위로 Bbs 에 저장하고
			// 2.1 파일에 빨대를 꽂아서 읽을 준비를 한다.
			BufferedReader br;
			try {
				br = new BufferedReader(          // 3. 버퍼를 씌운다(한번에 대량의 데이터를 읽어들인다) - 밥주걱 단위로 데이터를 읽는다
						new InputStreamReader(    // 2. 래퍼클래스 씌워준다 (부가적인 동작). 캐릭터셋을 변경해주는 역할
								new FileInputStream(database),"UTF-8") // 1. 스트림 생성 - 숫가락을 생성해서 한글자씩 읽어들인다.
						);
				String temp = "";
				while( (temp = br.readLine()) != null ){ // 줄단위로 파일을 읽어와서
					String bbsTemp[] = temp.split(COLUMN_SEPERATOR);
					// Bbs 에 담고
					Bbs bbs = new Bbs();
					bbs.setId(Long.parseLong(bbsTemp[0]));
					bbs.setTitle(bbsTemp[1]);
					bbs.setAuthor(bbsTemp[2]);
					bbs.setDate(bbsTemp[3]);
					bbs.setView(Integer.parseInt(bbsTemp[4]));
					bbs.setContent(bbsTemp[5]);
					// 완성된 Bbs 를 result 에 add 한다. (반복)
					result.add(bbs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	/**
	 * 저장소에 데이터를 저장
	 * @param bbs
	 * @throws  
	 */
	public void write(Bbs bbs) {
		// bbs에 있는 데이터를 텍스트 파일에 저장한다.
		// 1. 디렉토리가 있는지 검사하고 없으면 생성
		File dir = new File(DATABASE_DIR);
		if(!dir.exists()){
			dir.mkdirs(); // 검사한 경로상의 모든 디렉토리를 생성해준다
		}
		// 1.1 파일이 있는지 검사하고 없으면 생성
		File database = new File(DATABASE_DIR + DATABASE_NAME);
		if(!database.exists()){
			try {
				database.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// 2. bbs 에 내용을 database 파일에 저장을 한다.
		// 2.1 먼저 저장하는 데이터의 구조를 설계해야 한다.
		// 2.1.1 구분자를 먼저 정의해 두어야 한다.
		
		// 2.2 bbs 의 내용을 횡으로 펼친다
		String oneData = bbs.getId() + COLUMN_SEPERATOR 
				+ bbs.getTitle() + COLUMN_SEPERATOR
				+ bbs.getAuthor() + COLUMN_SEPERATOR
				+ bbs.getDate() + COLUMN_SEPERATOR
				+ bbs.getView() + COLUMN_SEPERATOR
				+ bbs.getContent() + RECORD_SEPERATOR;
		
		// 3. 횡으로 펼쳐진 데이터를 파일의 제일 마지막줄에 저장한다.
		// 3.1 FileWriter 객체를 사용해서 기존 데이터에 계속 더해쓴다.
		try {
			//FileWriter writer = new FileWriter(database, true); // 두번째 인자가 append 를 허용할것인지 여부
			Writer writer = new BufferedWriter(
					new OutputStreamWriter(
							new FileOutputStream(database,true),"UTF-8"));
			writer.append(oneData); // 
			writer.close(); // ! 필수
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
