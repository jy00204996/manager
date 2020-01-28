package com.zero.system.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtilNew {

    public DateUtilNew() {
    }

    public static Date getNow() {
        Calendar cal = Calendar.getInstance();
        Date currDate = cal.getTime();
        return currDate;
    }

    public static Date getminweek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(7, calendar.getActualMinimum(7));
        return calendar.getTime();
    }

    public static Date getmaxweek() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(7, calendar.getActualMaximum(7));
        return calendar.getTime();
    }

    public static Date getmindate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(5, calendar.getActualMinimum(5));
        return calendar.getTime();
    }

    public static Date getmaxdate() {
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        calendar2.set(5, calendar2.getActualMaximum(5));
        return calendar2.getTime();
    }

    public static String dateStr(Date date, String f) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat format = new SimpleDateFormat(f);
            String str = format.format(date);
            return str;
        }
    }

    public static String dateStr(Date date) {
        return dateStr(date, "MM月dd日 hh:mm");
    }

    public static String dateStr2(Date date) {
        return dateStr(date, "yyyy-MM-dd");
    }

    public static String dateStr5(Date date) {
        return dateStr(date, "yyyy年MM月dd日 HH时mm分ss秒");
    }

    public static String dateStr3(Date date) {
        return dateStr(date, "yyyy-MM-ddHHmmss");
    }

    public static String dateStr4(Date date) {
        return dateStr(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String dateStr6(Date date) {
        return dateStr(date, "yyyy年MM月dd日");
    }

    public static String dateStr7(Date date) {
        return dateStr(date, "yyyy-MM-dd");
    }

    public static String dateStr8(Date date) {
        return dateStr(date, "MM-dd");
    }

    public static String dateStr9(Date date) {
        return dateStr(date, "HH:mm");
    }

    public static Date getDate(String times) {
        long time = Long.parseLong(times);
        return new Date(time * 1000L);
    }

    public static String dateStr(String times) {
        return dateStr(getDate(times));
    }

    public static String dateStr2(String times) {
        return dateStr2(getDate(times));
    }

    public static String dateStr3(String times) {
        return dateStr3(getDate(times));
    }

    public static String dateStr4(String times) {
        return dateStr4(getDate(times));
    }

    public static String dateStr5(String times) {
        return dateStr5(getDate(times));
    }

    public static long getTime(Date date) {
        return date.getTime() / 1000L;
    }

    public static int getDay(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal.get(5);
    }

    public static Date valueOf(String s) {
//        int YEAR_LENGTH = true;
//        int MONTH_LENGTH = true;
//        int DAY_LENGTH = true;
//        int MAX_MONTH = true;
//        int MAX_DAY = true;
        int threeDash = 0;
        int fourDash = 0;
        Date d = null;
        if (s == null) {
            throw new IllegalArgumentException();
        } else {
            int firstDash = s.indexOf(45);
            int secondDash = s.indexOf(45, firstDash + 1);
            if (s.contains(":")) {
                threeDash = s.indexOf(58);
                fourDash = s.indexOf(58, threeDash + 1);
            }

            if (firstDash > 0 && secondDash > 0 && secondDash < s.length() - 1) {
                String yyyy = s.substring(0, firstDash);
                String mm = s.substring(firstDash + 1, secondDash);
                String dd = "";
                String hh = "";
                String MM = "";
                String ss = "";
                if (s.contains(":")) {
                    dd = s.substring(secondDash + 1, threeDash - 3);
                    hh = s.substring(threeDash - 2, threeDash);
                    MM = s.substring(threeDash + 1, fourDash);
                    ss = s.substring(fourDash + 1);
                } else {
                    dd = s.substring(secondDash + 1);
                }

                if (yyyy.length() == 4 && mm.length() == 2 && dd.length() == 2) {
                    int year = Integer.parseInt(yyyy);
                    int month = Integer.parseInt(mm);
                    int day = Integer.parseInt(dd);
                    int hour = 0;
                    int minute = 0;
                    int second = 0;
                    if (s.contains(":")) {
                        hour = Integer.parseInt(hh);
                        minute = Integer.parseInt(MM);
                        second = Integer.parseInt(ss);
                    }

                    if (month >= 1 && month <= 12) {
                        int maxDays = 31;
                        switch(month) {
                            case 2:
                                if ((year % 4 != 0 || year % 100 == 0) && year % 400 != 0) {
                                    maxDays = 28;
                                } else {
                                    maxDays = 29;
                                }
                            case 3:
                            case 5:
                            case 7:
                            case 8:
                            case 10:
                            default:
                                break;
                            case 4:
                            case 6:
                            case 9:
                            case 11:
                                maxDays = 30;
                        }

                        if (day >= 1 && day <= maxDays) {
                            Calendar cal = Calendar.getInstance();
                            cal.set(year, month - 1, day, hour, minute, second);
                            cal.set(14, 0);
                            d = cal.getTime();
                        }
                    }
                }
            }

            if (d == null) {
                throw new IllegalArgumentException();
            } else {
                return d;
            }
        }
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(7) - 1;
        if (w < 0) {
            w = 0;
        }

        return weekDays[w];
    }

    public static int getWeekOfInt(Date dt) {
        int[] weekDays = new int[]{7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(7) - 1;
        if (w < 0) {
            w = 0;
        }

        return weekDays[w];
    }

    public static Date rollMinute(Date d, int minute) {
        return new Date(d.getTime() + (long)(minute * 60 * 1000));
    }

    public static Date rollDay(Date d, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(5, day);
        return cal.getTime();
    }

    public static Date rollMon(Date d, int mon) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(2, mon);
        return cal.getTime();
    }

    public static Date rollYear(Date d, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(1, year);
        return cal.getTime();
    }

    public static Date rollDate(Date d, int year, int mon, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(1, year);
        cal.add(2, mon);
        cal.add(5, day);
        return cal.getTime();
    }

    public static String getNowTimeStr() {
        String str = Long.toString(System.currentTimeMillis() / 1000L);
        return str;
    }

    public static String getTimeStr(Date time) {
        long date = time.getTime();
        String str = Long.toString(date / 1000L);
        return str;
    }

    public static Date getIntegralTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 0);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date getLastIntegralTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(11, 23);
        cal.set(13, 59);
        cal.set(12, 59);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date getLastSecIntegralTime(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(11, 23);
        cal.set(13, 59);
        cal.set(12, 59);
        cal.set(14, 0);
        return cal.getTime();
    }

    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(7) - 1;
        return dayOfWeek == 1 ? 0 : 1 - dayOfWeek;
    }

    public static String getFirstDayOfMonth(String first) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(2, 0);
        c.set(5, 1);
        first = format.format(c.getTime());
        return first;
    }

    public static String getLastDayOfMonth(String last) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.set(5, ca.getActualMaximum(5));
        last = format.format(ca.getTime());
        return last;
    }

    public static Integer getLastDayOfMonthInt(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(5);
    }

    public static Date timeMonthManage(Date d, int month) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(d);
        rightNow.add(2, month);
        return rightNow.getTime();
    }

    public static Date monthLastDay(int year_time, int month_time) {
        Calendar cal = Calendar.getInstance();
        cal.set(year_time, month_time, 0, 23, 59, 59);
        return cal.getTime();
    }

    public static Date monthFirstDay(int year_time, int month_time) {
        Calendar cal = Calendar.getInstance();
        cal.set(year_time, month_time - 1, 1, 0, 0, 0);
        return cal.getTime();
    }

    public static Date currMonthFirstDay(Date d) {
        Calendar cal = Calendar.getInstance();
        if (d != null) {
            cal.setTime(d);
        }

        cal.set(5, cal.getActualMinimum(5));
        cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
        return cal.getTime();
    }

    public static Date currMonthLastDay(Date d) {
        Calendar cal = Calendar.getInstance();
        if (d != null) {
            cal.setTime(d);
        }

        cal.set(5, cal.getActualMaximum(5));
        cal.set(cal.get(1), cal.get(2), cal.get(5), 23, 59, 59);
        return cal.getTime();
    }

    public static int getTimeYear(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(1);
    }

    public static int getTimeMonth(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(2) + 1;
    }

    public static int getTimeDay(Date date) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(5);
    }

    public static Date getFirstSecIntegralTime(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(11, 0);
        cal.set(13, 0);
        cal.set(12, 0);
        cal.set(14, 0);
        cal.set(5, 0);
        return cal.getTime();
    }

    public static Date getDayEndTime(long d) {
        Date day = null;
        if (d <= 0L) {
            day = new Date();
        } else {
            day = new Date(d * 1000L);
        }

        Calendar cal = Calendar.getInstance();
        if (day != null) {
            cal.setTimeInMillis(day.getTime());
        }

        cal.set(cal.get(1), cal.get(2), cal.get(5), 23, 59, 59);
        return cal.getTime();
    }

    public static Date getDayStartTime(long d) {
        Date day = null;
        if (d <= 0L) {
            day = new Date();
        } else {
            day = new Date(d * 1000L);
        }

        Calendar cal = Calendar.getInstance();
        if (day != null) {
            cal.setTimeInMillis(day.getTime());
        }

        cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
        return cal.getTime();
    }

    public static Date getDateByFullDateStr(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(dateStr);
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static Date getDateByStr(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.parse(dateStr);
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static Date getDateByDateStr(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr);
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static int monthsBetween(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        if (c1.getTimeInMillis() < c2.getTimeInMillis()) {
            return 0;
        } else {
            int year1 = c1.get(1);
            int year2 = c2.get(1);
            int month1 = c1.get(2);
            int month2 = c2.get(2);
            int day1 = c1.get(5);
            int day2 = c2.get(5);
            int yearInterval = year1 - year2;
            if (month1 < month2 || month1 == month2 && day1 < day2) {
                --yearInterval;
            }

            int monthInterval = month1 + 12 - month2;
            if (day1 < day2) {
                --monthInterval;
            }

            monthInterval %= 12;
            return yearInterval * 12 + monthInterval;
        }
    }

    public static int secondBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        return time2 - time1 <= 0L ? 0 : Integer.parseInt(String.valueOf((time2 - time1) / 1000L)) + 1;
    }

    public static int millisecondBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        return time2 - time1 <= 0L ? 0 : Integer.parseInt(String.valueOf(time2 - time1));
    }

    public static Date getMonthStartTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        Date nowdate = cal.getTime();
        String date1 = sdf.format(nowdate);
        date1 = date1 + "-01 00:00:00";

        try {
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf2.parse(date1);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static String getMonthStartTimeStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        Date nowdate = cal.getTime();
        String date = sdf.format(nowdate);
        date = date + "-01 00:00:00";
        return date;
    }

    public static Date getMonthEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, calendar.getActualMaximum(5));
        DateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        calendar.getTime();
        String date1 = sdf1.format(calendar.getTime());
        date1 = date1 + " 23:59:59";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return sdf2.parse(date1);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static Date getCurrYearLast() {
        Calendar currCal = Calendar.getInstance();
        int currentYear = currCal.get(1);
        return getYearLast(currentYear);
    }

    public static Date getYearLast(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(1, year);
        calendar.roll(6, -1);
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }

    public static int getAge(Date birthday) {
        int age = 0;
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        if (birthday != null) {
            now.setTime(new Date());
            born.setTime(birthday);
            if (born.after(now)) {
                throw new IllegalArgumentException("年龄不能超过当前日期");
            }

            age = now.get(1) - born.get(1);
            int nowDayOfYear = now.get(6);
            int bornDayOfYear = born.get(6);
            if (nowDayOfYear < bornDayOfYear) {
                --age;
            }
        }

        return age;
    }

    public static boolean checkedTransactionTime() {
        Date da = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String now = sdf.format(da);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        try {
            c1.setTime(sdf.parse(now));
            c2.setTime(sdf.parse("15:00:00"));
        } catch (ParseException var6) {
            var6.printStackTrace();
        }

        return c1.compareTo(c2) <= 0;
    }

    public static boolean checkedTransactionTowTime() {
        Date da = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String now = sdf.format(da);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        try {
            c1.setTime(sdf.parse(now));
            c2.setTime(sdf.parse("14:30:00"));
        } catch (ParseException var6) {
            var6.printStackTrace();
        }

        return c1.compareTo(c2) <= 0;
    }

    public static Date dateFormat(Date date, String f) {
        Date date1 = new Date();
        if (date == null) {
            return null;
        } else {
            SimpleDateFormat format = new SimpleDateFormat(f);
            String str = format.format(date);

            try {
                date1 = format.parse(str);
            } catch (ParseException var6) {
                var6.printStackTrace();
            }

            return date1;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(checkedTransactionTowTime());
        System.out.println(dateStr(rollMon(new Date(), 1), "yyyy-MM-dd"));
        System.out.println(dateFormat(new Date(), "yyyy-MM-dd"));
    }

}
