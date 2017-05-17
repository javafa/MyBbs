package com.kodonho.mybbs.presenter;

import java.util.ArrayList;
import java.util.Scanner;

import com.kodonho.mybbs.model.Bbs;
import com.kodonho.mybbs.view.BbsInput;
import com.kodonho.mybbs.view.BbsList;

public class BbsPresenter {
	final boolean START = true;
	final boolean FINISH = false;
	boolean runFlag = START;
	
	// 데이터 임시 저장소
	ArrayList<Bbs> datas;
	
	Scanner scanner;
	BbsInput input;
	BbsList list;
	
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
				// datas.add(input.process(scanner));
				Bbs bbs =input.process(scanner);
				datas.add(bbs);
				break;
			case "r":
				
				break;
			}
		}
	}
	
	public void end(){
		runFlag = FINISH;
	}
	
}
