package com.kodonho.mybbs.view;

import java.util.ArrayList;

import com.kodonho.mybbs.model.Bbs;

public class BbsList {
	// 목록 뿌리기
	public void showList(ArrayList<Bbs> datas){
		for( Bbs bbs : datas){
			System.out.print(bbs.getId()+" | ");
			System.out.print(bbs.getTitle()+" | ");
			System.out.print(bbs.getAuthor()+" | ");
			System.out.print(bbs.getDate()+" | ");
			System.out.println(bbs.getView()+"");
		}
	}
	
	// 쓰기 이동
	public void goInput(){
		
	}
	
	// 검색
	public void search(String word){
		
	}
	
	// 삭제
	public void delete(long id){
		
	}
}
