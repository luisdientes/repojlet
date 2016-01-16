package arquitectura.utils;

import java.util.Date;

public class Trace {

    public static final int FATAL   = 0;
    public static final int ERROR   = 1;
    public static final int WARN    = 2;
    public static final int INFO    = 3;
    public static final int DEBUG   = 4;    
    
    private static int viewLevel   = DEBUG;
    private static boolean showInfoTrace  = false;
    private static String[] infoTraceDesc = new String[]{"[FATAL]","[ERROR]","[WARNI]","[INFOR]","[DEBUG]"};
    private static boolean showTimeTrace   = false;
    
    
    public static void trace(String traceStr){
        trace(DEBUG, traceStr);
    }
    
    public static void trace(int level, String traceStr){
        if(level <= viewLevel){
            String prefix="";
            if (showInfoTrace)
                prefix=infoTraceDesc[level] + " ";
            
            if (showTimeTrace)
                prefix=prefix+"[" + new Date() + "] ";
            
            System.out.println(prefix+traceStr);
        }        
    }
    
    public static void setLevel(int level){
        viewLevel = level;
    }
    
    public static int getLevel(){
        return viewLevel;
    }
    
    public static void setShowInfoTrace(boolean infoTrace){
        showInfoTrace=infoTrace;
    }

    public static void setShowTimeTrace(boolean timeTrace){
        showTimeTrace=timeTrace;
    }
}
