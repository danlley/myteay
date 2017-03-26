/**
 * Copyright (c) 2005-2009 All Rights Reserved.
 */
package com.myteay.common.util.comm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

/**
 * ����CRM�ʼ���Ⱦ���󣬸�����Դ��CRMϵͳ
 * 
 * @author shi_jason This file is for iwallet-biz-common
 */
public class DateUtil {
    /** logger */
    private static final Logger log                  = LoggerFactory.getLogger(DateUtil.class);

    /** һ���������� */
    public static final long    ONE_DAY_SECONDS      = 86400;

    /** �����ڸ�ʽ */
    public static final String  shortFormat          = "yyyyMMdd";

    /** �����ڸ�ʽ */
    public static final String  longFormat           = "yyyyMMddHHmmss";

    /** WEBҳ�����ڸ�ʽ */
    public static final String  webFormat            = "yyyy-MM-dd";

    /** ʱ�������ڸ�ʽ */
    public static final String  timeFormat           = "HHmmss";

    /** �������ڸ�ʽ */
    public static final String  monthFormat          = "yyyyMM";

    /** 1999091412--->1999��09��14��12ʱ */
    public final static String  dayFormat            = "yyyyMMddhh";

    /** �������������ڸ�ʽ */
    public static final String  chineseDtFormat      = "yyyy��MM��dd��";

    /** ���ھ�ȷ��ʽ����ȷ���� */
    public static final String  newFormat            = "yyyy-MM-dd HH:mm:ss";

    /** CST���ڸ�ʽ*/
    public static final String  cstFormat            = "EEE MMM dd HH:mm:ss 'CST' yyyy";

    /** һ�����ĺ����� */
    public static final long    ONE_DAY_MILL_SECONDS = 86400000;

    /**
     * �õ��ض���ʽ��DateFormat����
     * @param pattern
     * @return
     */
    public static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);

        df.setLenient(false);
        return df;
    }

    /**
     * �����ض���ʽת��
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(format).format(date);
    }

    /**
     * ����׼CSTʱ��ת����ָ���ĸ�ʽ
     * 
     * @param dateString
     * @param format
     * @return
     */
    public static String formatCSTDate(String dateString, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(cstFormat, Locale.US);
        Date date = null;
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            log.error("���ڸ�ʽת������", e);
        }
        return date == null ? "null" : format(date, format);
    }

    /**
     * ȥ�����ڵ�ʱ����ֵ
     * @param sDate
     * @return
     * @throws ParseException
     */
    public static Date parseDateNoTime(String sDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() < shortFormat.length())) {
            throw new ParseException("length too little", 0);
        }

        if (!StringUtils.isNumeric(sDate)) {
            throw new ParseException("not all digit", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * ���ض���ʽ���ַ���תDate����
     * @param sDate
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseDateNoTime(String sDate, String format) throws ParseException {
        if (StringUtils.isBlank(format)) {
            throw new ParseException("Null format. ", 0);
        }

        DateFormat dateFormat = new SimpleDateFormat(format);

        if ((sDate == null) || (sDate.length() < format.length())) {
            throw new ParseException("length too little", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * ��ĳ����ת��Ϊ�����ڸ�ʽ��Date����
     * @param sDate
     * @param delimit
     * @return
     * @throws ParseException
     */
    public static Date parseDateNoTimeWithDelimit(String sDate,
                                                  String delimit) throws ParseException {
        sDate = sDate.replaceAll(delimit, "");

        DateFormat dateFormat = new SimpleDateFormat(shortFormat);

        if ((sDate == null) || (sDate.length() != shortFormat.length())) {
            throw new ParseException("length not match", 0);
        }

        return dateFormat.parse(sDate);
    }

    /**
     * ��"yyyyMMddHHmmss"��ʽ�������ַ���ת��ΪDate����
     * @param sDate
     * @return
     */
    public static Date parseDateLongFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);
        Date d = null;

        if ((sDate != null) && (sDate.length() == longFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }

        return d;
    }

    /**
     * �ڵ�ǰ���ڼ��·���,�ټ�����
     * @param date
     * @param months
     * @param days
     * @return
     */
    public static Date addMonthsAndDays(Date date, int months, int days) {
        Calendar sysDate = new GregorianCalendar();
        sysDate.setTime(date);
        sysDate.add(Calendar.MONTH, months);
        sysDate.add(Calendar.DAY_OF_MONTH, days);
        return sysDate.getTime();
    }

    /**
     * ��"yyyy-MM-dd HH:mm:ss"��ʽ�������ַ���ת��ΪDate����
     * @param sDate
     * @return
     */
    public static Date parseDateNewFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(newFormat);
        Date d = null;
        if ((sDate != null) && (sDate.length() == newFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }
        return d;
    }

    /**
     * ��WEB��ʽ�����ַ���תDate����
     * @param sDate
     * @return
     */
    public static Date parseDateWebFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(webFormat);
        Date d = null;
        if ((sDate != null) && (sDate.length() == webFormat.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }
        return d;
    }

    /**
     * ���㵱ǰʱ�伸Сʱ֮���ʱ��
     *
     * @param date
     * @param hours
     *
     * @return
     */
    public static Date addHours(Date date, long hours) {
        return addMinutes(date, hours * 60);
    }

    /**
     * ���㵱ǰʱ�伸����֮���ʱ��
     *
     * @param date
     * @param minutes
     *
     * @return
     */
    public static Date addMinutes(Date date, long minutes) {
        return addSeconds(date, minutes * 60);
    }

    /**
     * �����ڽ��м�N�봦������Date����
     * @param date1
     * @param secs
     *
     * @return
     */
    public static Date addSeconds(Date date1, long secs) {
        return new Date(date1.getTime() + (secs * 1000));
    }

    /**
     * �жϲ���ʱ���Ƿ�ȵ�ǰʱ����
     * @param date
     * @return
     */
    public static boolean isBeforeNow(Date date) {
        if (date == null)
            return false;
        return date.compareTo(new Date()) < 0;
    }

    /**
     * �ж�������ַ����Ƿ�Ϊ�Ϸ���Сʱ
     *
     * @param hourStr
     *
     * @return true/false
     */
    public static boolean isValidHour(String hourStr) {
        if (!StringUtils.isEmpty(hourStr) && StringUtils.isNumeric(hourStr)) {
            int hour = new Integer(hourStr).intValue();

            if ((hour >= 0) && (hour <= 23)) {
                return true;
            }
        }

        return false;
    }

    /**
     * �ж�������ַ����Ƿ�Ϊ�Ϸ��ķֻ���
     *
     * @param minuteStr
     *
     * @return true/false
     */
    public static boolean isValidMinuteOrSecond(String str) {
        if (!StringUtils.isEmpty(str) && StringUtils.isNumeric(str)) {
            int hour = new Integer(str).intValue();

            if ((hour >= 0) && (hour <= 59)) {
                return true;
            }
        }

        return false;
    }

    /**
     * ȡ���µ�����
     *
     * @param date1 ����
     * @param days ����
     *
     * @return �µ�����
     */
    public static Date addDays(Date date1, long days) {
        return addSeconds(date1, days * ONE_DAY_SECONDS);
    }

    /**
     * �Ե�ǰ���ڽ��м�N�괦��
     * @param date
     * @param years
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date addYears(Date date, int years) {
        date.setYear(date.getYear() + years);
        return date;
    }

    /**
     * �Ե�ǰ���ڽ��м�N����һ�촦��
     * @param date
     * @param years
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date addYearsWithOneDayAhead(Date date, int years) {
        date.setYear(date.getYear() + years);
        date.setDate(date.getDate() - 1);
        return date;
    }

    /**
     * �����ڽ��м�N�¶�һ�촦��
     * @param date
     * @param months
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date addMonthsWithOneDayAhead(Date date, String months) {
        //���򣬱��磺1���ڵĺ�ͬ����������Ϊ��ʼ���ڼ�12���¼�1��
        Date endDate = addMonthsAndDays(date, Integer.valueOf(months).intValue(), -1);

        //�����ͬ��Ч��ʼʱ��Ϊ2��29�գ���Ϊ�����ϵ������Դ�
        if (date.getDate() == 29 && date.getMonth() == 1) {
            Date endDateAdd1day = addDays(endDate, 1);
            if (!(endDateAdd1day.getMonth() == 1 && endDateAdd1day.getDate() == 29)) {
                endDate = addDays(endDate, 1);
            }
        }
        return endDate;
    }

    /**
     * �Ե�ǰ���ڽ��м�N����һ�촦��
     * @param date
     * @param years
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date addYearsWithOneDayAheadWithoutSideEffect(Date date, int years) {
        Date returnDate = new Date();
        returnDate.setYear(date.getYear() + years);
        returnDate.setMonth(date.getMonth());
        returnDate.setDate(date.getDate() - 1);
        return returnDate;
    }

    /**
     * ��String��ʽ�õ�ĳ����֮��һ�������
     * @param sDate
     * @return
     * @throws ParseException
     */
    public static String getTomorrowDateString(String sDate) throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    /**
     * �����ڽ��и�ʽ�������"yyyyMMddHHmmss"��ʽ�ַ���
     * @param date
     * @return
     */
    public static String getLongDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(longFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * �����ڽ��и�ʽ���������yyyy-MM-dd HH:mm:ss��ʽ���ַ���
     * @param date
     * @return
     */
    public static String getNewFormatDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(newFormat);
        return getDateString(date, dateFormat);
    }

    /**
     * ���ض���ʽ��ĳ���ڽ��и�ʽ��������String��ʽ����
     * @param date
     * @param dateFormat
     * @return
     */
    public static String getDateString(Date date, DateFormat dateFormat) {
        if (date == null || dateFormat == null) {
            return null;
        }

        return dateFormat.format(date);
    }

    /**
     * ���ĳ����ǰһ���Ӧ����
     * @param sDate
     * @return
     * @throws ParseException
     */
    public static String getYesterDayDateString(String sDate) throws ParseException {
        Date aDate = parseDateNoTime(sDate);

        aDate = addSeconds(aDate, -ONE_DAY_SECONDS);

        return getDateString(aDate);
    }

    /**
     * @return �����ʱ���ʽ��Ϊ"yyyyMMdd"
     */
    public static String getDateString(Date date) {
        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(date);
    }

    /**
     * getDayString
     * @param date
     * @return
     */
    public static String getDayString(Date date) {
        DateFormat dateFormat = getNewDateFormat(dayFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * ��ȡĳ���ڵ�WEB��ҳ�����ʽ
     * @param date
     * @return
     */
    public static String getWebDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(webFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * ȡ�á�X��X��X�ա������ڸ�ʽ
     *
     * @param date
     *
     * @return
     */
    public static String getChineseDateString(Date date) {
        DateFormat dateFormat = getNewDateFormat(chineseDtFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * ���ؽ��쵱��Ķ����ڸ�ʽ�ַ���
     * @return
     */
    public static String getTodayString() {
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(new Date(), dateFormat);
    }

    /**
     * �����ض����ڵ���ʱ����ʱ�����ʾ
     * @param date
     * @return
     */
    public static String getTimeString(Date date) {
        DateFormat dateFormat = getNewDateFormat(timeFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * ���㵱ǰʱ��֮ǰĳ�����ڣ������ַ�����ʽ����
     * @param days
     * @return
     */
    public static String getBeforeDayString(int days) {
        Date date = new Date(System.currentTimeMillis() - (ONE_DAY_MILL_SECONDS * days));
        DateFormat dateFormat = getNewDateFormat(shortFormat);

        return getDateString(date, dateFormat);
    }

    /**
     * ȡ���������ڼ������������1-����2��
     *
     * @param one ����1
     * @param two ����2
     *
     * @return �������
     */
    public static long getDiffSeconds(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / 1000;
    }

    /**
     * �������ڼ��������
     * @param one
     * @param two
     * @return
     */
    public static long getDiffMinutes(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / (60 * 1000);
    }

    /**
     * ȡ���������ڵļ������
     *
     * @param one
     * @param two
     *
     * @return �������
     */
    public static long getDiffDays(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (sysDate.getTimeInMillis() - failDate.getTimeInMillis()) / (24 * 60 * 60 * 1000);
    }

    /**
     * �������ڼ�������㷨
     * @param one
     * @param two
     * @return
     */
    public static int getDiffDays2(Date one, Date two) {
        Calendar sysDate = new GregorianCalendar();

        sysDate.setTime(one);

        Calendar failDate = new GregorianCalendar();

        failDate.setTime(two);
        return (int) ((sysDate.getTimeInMillis() - failDate.getTimeInMillis())
                      / (24 * 60 * 60 * 1000));
    }

    /**
     * ����date��ʱ�䣬�������ڼ����������one-two��
     * @param one
     * @param two
     * @return
     */
    public static int getDiffDays3(Date one, Date two) {

        Date date1 = null;
        Date date2 = null;
        try {
            date1 = parseDateNoTime(format(one, "yyyyMMdd"));
            date2 = parseDateNoTime(format(two, "yyyyMMdd"));
        } catch (ParseException e) {
            log.warn("ʱ��ת��ʱ����;", e);
            return -1;
        }

        return getDiffDays2(date1, date2);
    }

    /**
     * ͨ��ĳ���ڼ��㵱ǰ����֮ǰĳ�������ڣ������ַ�����ʽ����
     * @param dateString
     * @param days
     * @return
     */
    public static String getBeforeDayString(String dateString, int days) {
        Date date;
        DateFormat df = getNewDateFormat(shortFormat);

        try {
            date = df.parse(dateString);
        } catch (ParseException e) {
            date = new Date();
        }

        date = new Date(date.getTime() - (ONE_DAY_MILL_SECONDS * days));

        return df.format(date);
    }

    /**
     * �̸�ʽ�����ַ����Ϸ���У��
     * @param strDate
     * @return
     */
    public static boolean isValidShortDateFormat(String strDate) {
        if (strDate.length() != shortFormat.length()) {
            return false;
        }

        try {
            Integer.parseInt(strDate); //---- ������������������� ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(shortFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * �����ڸ�ʽУ��
     * @param strDate
     * @param delimiter
     * @return
     */
    public static boolean isValidShortDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidShortDateFormat(temp);
    }

    /**
     * �жϱ�ʾʱ����ַ��Ƿ�Ϊ����yyyyMMddHHmmss��ʽ
     * 
     * @param strDate
     * @return
     */
    public static boolean isValidLongDateFormat(String strDate) {
        if (strDate.length() != longFormat.length()) {
            return false;
        }

        try {
            Long.parseLong(strDate); //---- ������������������� ----
        } catch (Exception NumberFormatException) {
            return false;
        }

        DateFormat df = getNewDateFormat(longFormat);

        try {
            df.parse(strDate);
        } catch (ParseException e) {
            return false;
        }

        return true;
    }

    /**
     * �жϱ�ʾʱ����ַ��Ƿ�Ϊ����yyyyMMddHHmmss��ʽ
     * 
     * @param strDate
     * @param delimiter
     * @return
     */
    public static boolean isValidLongDateFormat(String strDate, String delimiter) {
        String temp = strDate.replaceAll(delimiter, "");

        return isValidLongDateFormat(temp);
    }

    /**
     * ����ת��-�����ڸ�ʽ
     * @param strDate
     * @return
     */
    public static String getShortDateString(String strDate) {
        return getShortDateString(strDate, "-|/");
    }

    /**
     * ����������ض��ַ���ʹ����תΪ�����ڸ�ʽ����У�����ںϷ���
     * @param strDate
     * @param delimiter
     * @return
     */
    public static String getShortDateString(String strDate, String delimiter) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }

        String temp = strDate.replaceAll(delimiter, "");

        if (isValidShortDateFormat(temp)) {
            return temp;
        }

        return null;
    }

    /**
     * �õ��̸�ʽ�͵��µ�һ�������ַ���
     * @return
     */
    public static String getShortFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(shortFormat);

        return df.format(cal.getTime());
    }

    /**
     * �õ�WEB�����ʽ�ĵ�ǰ����
     * @return
     */
    public static String getWebTodayString() {
        DateFormat df = getNewDateFormat(webFormat);

        return df.format(new Date());
    }

    /**
     * ��web���ڸ�ʽ������µĵ�һ���ַ���
     * @return
     */
    public static String getWebFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date();

        cal.setTime(dt);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        DateFormat df = getNewDateFormat(webFormat);

        return df.format(cal.getTime());
    }

    /**
     * String��������ת�͹�����ͨ��һ�����ڸ�ʽת��������һ�������String�������ڸ�ʽ
     * @param dateString
     * @param formatIn
     * @param formatOut
     * @return
     */
    public static String convert(String dateString, DateFormat formatIn, DateFormat formatOut) {
        try {
            Date date = formatIn.parse(dateString);

            return formatOut.format(date);
        } catch (ParseException e) {
            log.warn("convert() --- orign date error: " + dateString);
            return "";
        }
    }

    /**
     * ������תΪ��ҳ��ʾ�������ڸ�ʽ
     * @param dateString
     * @return
     */
    public static String convert2WebFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df1, df2);
    }

    /**
     * ������תΪ���ķ������
     * @param dateString
     * @return
     */
    public static String convert2ChineseDtFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(chineseDtFormat);

        return convert(dateString, df1, df2);
    }

    /**
     * ��ҳ���ڸ�ʽתΪ�����ڸ�ʽ
     * @param dateString
     * @return
     */
    public static String convertFromWebFormat(String dateString) {
        DateFormat df1 = getNewDateFormat(shortFormat);
        DateFormat df2 = getNewDateFormat(webFormat);

        return convert(dateString, df2, df1);
    }

    /**
     * ʱ���Ⱥ��ж��㷨
     * @param date1
     * @param date2
     * @return
     */
    public static boolean webDateNotLessThan(String date1, String date2) {
        DateFormat df = getNewDateFormat(webFormat);

        return dateNotLessThan(date1, date2, df);
    }

    /**
     * ʱ���Ⱥ��ж��㷨
     * @param date1
     * @param date2
     * @param dateWebFormat2
     *
     * @return
     */
    public static boolean dateNotLessThan(String date1, String date2, DateFormat format) {
        try {
            Date d1 = format.parse(date1);
            Date d2 = format.parse(date2);

            if (d1.before(d2)) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            if (log.isDebugEnabled()) {
                log.debug("dateNotLessThan() --- ParseException(" + date1 + ", " + date2 + ")");
            }
            return false;
        }
    }

    /**
     * ���ڸ�ʽ����"yyyy��MM��dd��HH:mm:ss"
     * @param today
     * @return
     */
    public static String getEmailDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��HH:mm:ss");

        todayStr = sdf.format(today);
        return todayStr;
    }

    /**
     * ���ڸ�ʽ����"MM��dd��HH:mm"
     * @param today
     * @return
     */
    public static String getSmsDate(Date today) {
        String todayStr;
        SimpleDateFormat sdf = new SimpleDateFormat("MM��dd��HH:mm");

        todayStr = sdf.format(today);
        return todayStr;
    }

    /**
     * ʱ���ʽ��
     * @param startDate
     * @param endDate
     * @param format
     * @return
     */
    public static String formatTimeRange(Date startDate, Date endDate, String format) {
        if ((endDate == null) || (startDate == null)) {
            return null;
        }

        String rt = null;
        long range = endDate.getTime() - startDate.getTime();
        long day = range / DateUtils.MILLIS_PER_DAY;
        long hour = (range % DateUtils.MILLIS_PER_DAY) / DateUtils.MILLIS_PER_HOUR;
        long minute = (range % DateUtils.MILLIS_PER_HOUR) / DateUtils.MILLIS_PER_MINUTE;

        if (range < 0) {
            day = 0;
            hour = 0;
            minute = 0;
        }

        rt = format.replaceAll("dd", String.valueOf(day));
        rt = rt.replaceAll("hh", String.valueOf(hour));
        rt = rt.replaceAll("mm", String.valueOf(minute));

        return rt;
    }

    /**
     * ���ڸ�ʽ����"yyyyMM"
     * @param date
     * @return
     */
    public static String formatMonth(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(monthFormat).format(date);
    }

    /**
     * ��ȡϵͳ���ڵ�ǰһ�����ڣ�����Date
     *
     * @return
     */
    public static Date getBeforeDate() {
        Date date = new Date();

        return new Date(date.getTime() - (ONE_DAY_MILL_SECONDS));
    }

    /**
     * ���ָ��ʱ�䵱�����ʱ��
     * 
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        df.setLenient(false);

        String dateString = df.format(date);

        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            log.warn("ParseException : ", e);
            return date;
        }
    }

    /**
     * �õ�һ���е����һ��ʱ��
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date getDayEnd(Date date) {
        date.setHours(23);
        date.setMinutes(59);
        date.setSeconds(59);
        return date;
    }

    /**��õ�ǰ�µ����һ��
     * 
     * @param sDate1
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date getLastDayOfMonth(Date sDate) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(sDate);
        int lastDay = cDay.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date lastDate = cDay.getTime();
        lastDate.setDate(lastDay);
        lastDate.setHours(23);
        lastDate.setMinutes(59);
        lastDate.setSeconds(59);
        return lastDate;
    }

    /**
     * ��õ�ǰ�µĵ�һ��
     * 
     * @param sDate1
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Date getFirstDayOfMonth(Date sDate) {
        Calendar cDay = Calendar.getInstance();
        cDay.setTime(sDate);
        //final int firstDay = cDay.getActualMinimum(Calendar.DAY_OF_MONTH);
        Date firstDayDate = cDay.getTime();
        firstDayDate.setDate(1);
        firstDayDate.setHours(0);
        firstDayDate.setMinutes(0);
        firstDayDate.setSeconds(0);
        return firstDayDate;
    }

    /**
     * �жϲ�date��min���Ӻ��Ƿ�С�ڵ�ǰʱ��
     * 
     * @param date
     * @param min
     * @return
     */
    public static boolean dateLessThanNowAddMin(Date date, long min) {
        return addMinutes(date, min).before(new Date());
    }

}
