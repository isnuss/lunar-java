package com.nlf.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 阳历半年
 */
public class SolarHalfYear {
  /** 年 */
  private int year;
  /** 月 */
  private int month;

  /**
   * 默认当月
   */
  public SolarHalfYear(){
    this(new Date());
  }

  /**
   * 通过日期初始化
   */
  public SolarHalfYear(Date date){
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    year = c.get(Calendar.YEAR);
    month = c.get(Calendar.MONTH)+1;
  }

  /**
   * 通过日历初始化
   */
  public SolarHalfYear(Calendar calendar){
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH)+1;
  }

  /**
   * 通过年月初始化
   *
   * @param year 年
   * @param month 月
   */
  public SolarHalfYear(int year,int month){
    this.year = year;
    this.month = month;
  }

  /**
   * 获取年
   *
   * @return 年
   */
  public int getYear(){
    return year;
  }

  /**
   * 获取月
   *
   * @return 月
   */
  public int getMonth(){
    return month;
  }

  /**
   * 获取当月是第几半年
   * @return 半年序号，从1开始
   */
  public int getIndex(){
    return (int)Math.ceil(month/6D);
  }

  /**
   * 半年推移
   * @param halfYears 推移的半年数，负数为倒推
   * @return 推移后的半年
   */
  public SolarHalfYear next(int halfYears){
    if(0==halfYears){
      return new SolarHalfYear(year,month);
    }
    Calendar c = Calendar.getInstance();
    c.set(year,month-1,1);
    c.add(Calendar.MONTH,6*halfYears);
    return new SolarHalfYear(c);
  }

  /**
   * 获取本半年的月份
   * @return 本半年的月份列表
   */
  public List<SolarMonth> getMonths(){
    List<SolarMonth> l = new ArrayList<SolarMonth>();
    int index = getIndex()-1;
    for(int i=0;i<6;i++){
      l.add(new SolarMonth(year,6*index+i+1));
    }
    return l;
  }

  public String toString(){
    return year+"."+getIndex();
  }

  public String toFullString(){
    return year+"年"+(getIndex()==1?"上":"下")+"半年";
  }
}