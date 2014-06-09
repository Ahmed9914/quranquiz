/****
* Copyright (C) 2011-2013 Quran Quiz Net 
* Tarek Eldeeb <tarekeldeeb@gmail.com>
* License: see LICENSE.txt
****/
package net.quranquiz.model;

import java.io.Serializable;

import net.quranquiz.model.QQSession.SetAsyncQQDailyQuiz;
import net.quranquiz.util.QQUtils;
import android.text.format.Time;
import android.util.Log;

public class QQDailyQuiz implements Serializable {

	private static final long serialVersionUID = 1L;
	public Time timeStamp;
	public int partCount;
	public int questionsCount;
	public QQQuestionObject[][] objects;

	public QQDailyQuiz(SetAsyncQQDailyQuiz setAsyncQQDailyQuiz){
		timeStamp 		= new Time();
		timeStamp.setToNow();
		
		partCount 		= QQUtils.DAILYQUIZ_PARTS_COUNT;
		questionsCount	= QQUtils.DAILYQUIZ_QPERPART_COUNT;
		
		objects = new QQQuestionObject[QQUtils.DAILYQUIZ_PARTS_COUNT][QQUtils.DAILYQUIZ_QPERPART_COUNT];
		
		for(int i=1;i<=partCount;i++ ){ //Skip Al-Fatiha!
			setAsyncQQDailyQuiz.triggerUpdateProgress(i);
			for(int j=0;j<questionsCount;j++){
				objects[i-1][j] = QQQuestionaire.createDefinedQuestion(i);
			}
			Log.d("DailyQuiz","created part"+i);
		}
	}
	

}