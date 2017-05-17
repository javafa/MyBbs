package com.kodonho.mybbs.presenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.kodonho.mybbs.model.Bbs;
import com.kodonho.mybbs.view.BbsDetail;
import com.kodonho.mybbs.view.BbsInput;
import com.kodonho.mybbs.view.BbsList;

public class BbsPresenter {
	final boolean START = true;
	final boolean FINISH = false;
	boolean runFlag = START;
	
	// 데이터 임시 저장소                     1    3    3
	ArrayList<Bbs> datas; // { Bbs, Bbs, Bbs ... }
	
	Scanner scanner;
	BbsInput input;
	BbsList list;
	BbsDetail detail;
	
	int number = 0;
	
	public BbsPresenter(){
		init();
	}
	
	/**
	 * 초기화 함수, 사용할 객체들을 미리 메모리에 로드해둔다
	 */
	private void init(){
		scanner = new Scanner(System.in);
		input = new BbsInput();
		list = new BbsList();
		detail = new BbsDetail();
		datas = new ArrayList<>();
	}
	
	public void start(){
		while(runFlag){
			System.out.println("명령어를 입력하세요 [l:목록, w:쓰기, r:상세보기]");
			String command = scanner.nextLine();
			switch(command){
			case "l":
				list.showList(datas);
				break;
			case "w":
				write();
				break;
			case "r":
				goDetail();
				break;
			}
		}
	}
	
	private void write(){
		// datas.add(input.process(scanner));
		Bbs bbs =input.process(scanner);
		number = number + 1;
		bbs.setId(number);
		bbs.setDate(getDate());
		datas.add(bbs);
	}
	
	private String getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long currentTime = System.currentTimeMillis();
		return sdf.format(currentTime);
	}
	
	// 상세보기 이동
	private void goDetail(){
		System.out.println("글번호를 입력하세요;");
		String temp = scanner.nextLine();
		long id = Long.parseLong(temp);
		for( Bbs bbs : datas){
			if(bbs.getId() == id){
				detail.showNo(bbs.getId());
				detail.showTitle(bbs.getTitle());
				detail.showAuthor(bbs.getAuthor());
				detail.showDate(bbs.getDate());
				detail.showCount(bbs.getView());
				detail.showContent(bbs.getContent());
				detail.endDetail(); // 가장 아래에 밑줄을 그어준다.
				break; // 조건문에 부합되면 반복문을 중지한다.
			}
		}
	}
	
	public void end(){
		runFlag = FINISH;
	}
	
}
