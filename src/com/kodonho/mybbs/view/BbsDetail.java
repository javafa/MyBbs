package com.kodonho.mybbs.view;

import com.kodonho.mybbs.model.Bbs;

public class BbsDetail {
	
	public void showNo(long id){
		System.out.println("글번호:"+id);
	}
	public void showTitle(String title){
		System.out.println("제목:"+title);
	}
	public void showAuthor(String author){
		System.out.println("작성자:"+author);
	}
	public void showDate(String date){
		System.out.println("작성일자:"+date);
	}
	public void showCount(int count){
		System.out.println("조회수:"+count);
	}
	public void showContent(String content){
		System.out.println(content);
	}
	public void endDetail(){
		System.out.println("----------------------");
	}
}
