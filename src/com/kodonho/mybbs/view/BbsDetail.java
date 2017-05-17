package com.kodonho.mybbs.view;

import com.kodonho.mybbs.model.Bbs;

public class BbsDetail {
	
	public void showDetail(Bbs bbs){
		System.out.println("글번호:"+bbs.getId());
		System.out.println("제목:"+bbs.getTitle());
		System.out.println("작성자:"+bbs.getAuthor());
		System.out.println("작성일자:"+bbs.getDate());
		System.out.println("조회소:"+bbs.getView());
		System.out.println(bbs.getContent());
	}
}
