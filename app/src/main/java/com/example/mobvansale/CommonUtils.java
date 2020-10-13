package com.example.mobvansale;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    String TAG=CommonUtils.class.getSimpleName();
    private static final String DEFAULT_DATE_FORMAT ="dd-MM-yyyy";
    public static  String datePrintFormat="dd/MM/yyyy";
    public static  String shortDateFormat="dd/MM/yy";

    public static String getDefaultDateFormat() {
        return DEFAULT_DATE_FORMAT;
    }

    public static String getDate() {
        return getDate(Calendar.getInstance().getTime());
    }

    public static String getDate(Object date){
        if(date == null)
            return null;
        SimpleDateFormat dateformat = new SimpleDateFormat(getDefaultDateFormat());
        String dateString = dateformat.format(date);
        return dateString;
    }

    public static long getDate(String date){
        if(date == null)
            return 0;
        SimpleDateFormat dateformat = new SimpleDateFormat(getDefaultDateFormat());
        try {
            Date d = dateformat.parse(date);
            long milliseconds = d.getTime();
            return milliseconds;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static String getPrintDate(Date date){
        SimpleDateFormat dateformat = new SimpleDateFormat(datePrintFormat);
        String dateString = dateformat.format(date);
        return dateString;
    }
    public static String getTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(date);
        return time;
    }

    public static String getDateAndTime(Date date){
        SimpleDateFormat dateformat = new SimpleDateFormat(datePrintFormat);
        String result="";
        String dateString="";
        try {
            dateString = dateformat.format(date);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String time = sdf.format(date);
            result = "Date/Time: " + dateString + " " + time;
        }
        catch (NullPointerException e)
        {
            Log.d("",e.toString());
        }
        return result;
    }
    public static String getDateTimeInSystemFormat(Context context,Date date){
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat(shortDateFormat);
        String dateString=dateFormat.format(date);
        SimpleDateFormat time24 = new SimpleDateFormat("kk:mm");
        SimpleDateFormat timeFormat12 =new SimpleDateFormat("hh:mm a");
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);

        String  timeString="";

        if (android.text.format.DateFormat.is24HourFormat(context)) {

            timeString=time24.format(date);
        }
        else
        {
            timeString = timeFormat12.format(date);
        }

        String  result =  dateString +"-"+ timeString;

        return result;
    }


    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }

    public static Date parseDate(String dateString)
    {
        if(dateString == null || dateString.isEmpty())
            return null;
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(getDefaultDateFormat());
        try {

            return simpleDateFormat.parse(dateString);

        } catch (ParseException e) {
            Log.e("CommonUtils", "Invalid Date. " + e.getMessage(), e);
        }
        return null;
    }
    /**
     * Pad the String to the given length and append to the ByteBuffer
     * @param str    String input
     * @param maxLength    Max length of padding
     * @return  Padded / Truncated String
     */
    public static String padString(String str, int maxLength) {
        StringBuilder buffer = new StringBuilder();
        int length = str != null?str.length():0;
        if(length > 0) {
            char[] strBuff = str.toCharArray();
            if(length >= maxLength) {
                length = maxLength-1;
                strBuff[length-1] = '*'; // Add a * to indicate truncation
            }
            buffer.append(strBuff, 0, length);  // Max maxLength-1
        }
        for(int i=length; i< maxLength; i++) buffer.append(' '); // Pad the name with (atleast one) NULL
        return buffer.toString();
    }

    public static String padBigDecimal(BigDecimal bd, int maxLength) {
        StringBuilder buffer = new StringBuilder();
        DecimalFormat df;
        if(bd.scale() > 0) {
            df = new DecimalFormat("#,##,##0.00"); // TODO use currency Format... or use actual scale
        } else {
            df = new DecimalFormat("#,##,##0");
        }
        String str = df.format(bd);
        return prePadString(str, maxLength);
    }

    public static String prePadString(String str, int maxLength) {
        StringBuilder buffer = new StringBuilder();
        int length = str != null?str.length():0;
        if(length < maxLength) { // PAD ON LEFT SIDE
            buffer.append(padString("", maxLength-length));  // Max maxLength-1
        }
        buffer.append(str);
        return buffer.toString();
    }

    public static  String getCenterAligned(String string)
    {

        int remainingSpace=48- string.length();
        int indent=0;
        if(remainingSpace>0)
        {

            indent = remainingSpace / 2;
        }
        String indents=CommonUtils.padString("",indent);
        string=String.format("%s%s%s",indents, string ,indents);
        return  string;
    }

    public static int toInt(String value)
    {
        int data;
        if(value == null)
            return 0;
        try
        {
            data=Integer.parseInt(value);
        }
        catch (Exception e)
        {
            Log.w("toInt", e.getMessage(), e);
            data=0;
        }
        return data ;
    }


    public static String toString(int value)
    {

        return String.valueOf(value);
    }

    public static BigDecimal toBigDecimal(String data) {
        if (data != null && data instanceof String) {
            BigDecimal decimalValue;
            try {
                decimalValue = new BigDecimal((String) data);
            } catch (NumberFormatException ex) {
                decimalValue = BigDecimal.ZERO;
            }
            return decimalValue;
        }
        return BigDecimal.ZERO;
    }

    public static String fromHtml(String text) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            text = Html.fromHtml(new String(text), Html.FROM_HTML_MODE_LEGACY).toString();
        else
            text = Html.fromHtml(new String(text)).toString();
        text = text.replaceAll("<!--.*-->", ""); // Remove HTML comments, if any
        return text;
    }

    public static boolean isIntegerValue(BigDecimal bd) {
        return bd != null && (bd.signum() == 0 || bd.scale() <= 0 || bd.stripTrailingZeros().scale() <= 0);
    }

    public static Object quoteIfString(Object value) {
        if(value != null && value instanceof String) {
            return quoteString((String) value);
        }
        return value;
    }
    public static String quoteString(String value) {
        if(value == null)
            return null;
        if(!value.startsWith("\"") && !value.startsWith("'")) {
            value = "'" + value + "'";
        }
        return value;
    }


    public static List<?> exclude(List<?> list, Object item) {
        ArrayList newList = new ArrayList<>(list);
        newList.remove(item);
        return newList;
    }

    public static Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity)cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper)cont).getBaseContext());

        return null;
    }



    public static Bitmap getResizedBitmap(Bitmap bm) {
        int newHeight = 120; int newWidth = 160;
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);

        return resizedBitmap;
    }

    public static void hideKeyboard(final Activity activity) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                //Find the currently focused view, so we can grab the correct window token from it.
                View view = activity.getCurrentFocus();
                //If no view currently has focus, create a new one, just so we can grab a window token from it
                if (view == null) {
                    view = new View(activity);
                }
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

    }
    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getRootView().getWindowToken(), 0);
    }

    public static void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
    }

    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static SecureRandom rnd = new SecureRandom();

    public static String randomString( int len ){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public static boolean isRtl(String string) {
        if (string == null) {
            return false;
        }

        for (int i = 0, n = string.length(); i < n; ++i) {
            byte d = Character.getDirectionality(string.charAt(i));

            switch (d) {
                case Character.DIRECTIONALITY_RIGHT_TO_LEFT:
                case Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC:
                case Character.DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING:
                case Character.DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE:
                    return true;

                case Character.DIRECTIONALITY_LEFT_TO_RIGHT:
                case Character.DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING:
                case Character.DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE:
                    return false;
            }
        }

        return false;
    }

    public static boolean isAscii(String string) {
        StringCharacterIterator iter = new StringCharacterIterator(string);
        for(int i = iter.getBeginIndex(); i < iter.getEndIndex(); iter.next(), i++) {
            char c = iter.current(); // Current is initially '0'. Next will increment it. Hence use current
            if (Character.UnicodeBlock.of(c) != Character.UnicodeBlock.BASIC_LATIN) {
                return false;
            }
        }
        return true;
    }

    private static final Pattern localeMatcher = Pattern.compile
            ("^([^_]*)(_([^_]*)(_#(.*))?)?$");

    public static Locale getLocale(String value) {
        Matcher matcher = localeMatcher.matcher(value.replace('-', '_'));
        return matcher.find()
                ? TextUtils.isEmpty(matcher.group(5))
                ? TextUtils.isEmpty(matcher.group(3))
                ? TextUtils.isEmpty(matcher.group(1))
                ? null
                : new Locale(matcher.group(1))
                : new Locale(matcher.group(1), matcher.group(3))
                : new Locale(matcher.group(1), matcher.group(3), matcher.group(5))
                : null;
    }

}