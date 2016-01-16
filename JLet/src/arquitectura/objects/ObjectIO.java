package arquitectura.objects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ObjectIO implements Serializable{
    
    HashMap hash = new HashMap();

    protected void putValue(String var, Object value) throws Exception {            
        hash.put(var, value);        
    }
    
    public ArrayList getValues(){
    	
    	ArrayList valores = new ArrayList();
    	
    	Set st = hash.keySet();
    	//System.out.println("Set created from HashMap Keys contains :");
    	Iterator itr = st.iterator();
    
    	while(itr.hasNext()){
    		valores.add(itr.next());
    	}
		return valores;
 	    	
    }
    
    public void setValue(String var, Object value) throws Exception {
        if (hash.containsKey(var)==false){
            throw new Exception("Variable " + var + " not definned");
        }
        else{
            putValue(var, value);
        }        
    }

    public Object getValue(String var) throws Exception{
        Object value = hash.get(var);
        if(!hash.containsKey(var)){
            throw new Exception("Variable " + var + " does not exist");
        }
        else{
            return value;
        }
    }
    
    public Date getDateValue(String var) throws Exception {        
        Object obj = getValue(var);
        Date date = null;
        if (obj!=null){
            if(obj instanceof java.util.Date){
                date =(Date)obj;
            } else{
                date = new SimpleDateFormat("dd/MM/yyyy").parse(obj.toString());            
            }
        }
        return date;
    }

    public String getStringValue(String var) throws Exception{        
        Object value;
        String strValue=null;
        value=getValue(var);
        if (value!=null) strValue=value.toString();
        return strValue;
    }

    public String getStringValue(String var, String defValue) throws Exception{        
        String value = getStringValue(var);
        if(value==null)
            value = defValue;
        return value;
    }
    
    public int getIntValue(String var) throws Exception{
        Object obj = getValue(var);
        int theInt;
        if(obj==null){
            throw new Exception(var + " has a null value");
        } else{
            if(obj instanceof java.lang.Integer){
                theInt = ((Integer)obj).intValue();
            }        
            else{            
                theInt = Integer.parseInt(obj.toString());
            }
        }
        return theInt;
    }
    
    public long getLongValue(String var) throws Exception{
        Object obj = getValue(var);
        long theLong;
        if(obj==null){
            throw new Exception(var + " has a null value");
        } else{
            if(obj instanceof java.lang.Long){
                theLong = ((Long)obj).longValue();
            }        
            else{            
                theLong = Long.parseLong(obj.toString());
            }
        }
        return theLong;
    }    
       
    public float getFloatValue(String var) throws Exception{
        Object obj=getValue(var);
        float theFloat;
        if(obj==null){
            throw new Exception(var + " has a null value");
        } else{
            if (obj instanceof java.lang.Float){            
                theFloat = ((Float)obj).floatValue();
            }
            else{            
                theFloat = Float.parseFloat(obj.toString());
            }
        }
        return theFloat;
    }
    
    public double getDoubleValue(String var) throws Exception{
        Object obj = getValue(var);
        double theDouble;
        if(obj==null){
            throw new Exception(var + " has a null value");
        } else{
            if(obj instanceof java.lang.Double){            
                theDouble = ((Double)obj).floatValue();
            } else{            
                theDouble = Float.parseFloat(obj.toString());
            }
        }
        return theDouble;
    }

    public BigDecimal getBigDecimalValue(String var) throws Exception{
        Object obj = getValue(var);
        BigDecimal theBigDecimal;
        if(obj==null){
            theBigDecimal = null;
        } else if(obj instanceof java.math.BigDecimal){            
            theBigDecimal = (BigDecimal)obj;
        } else{            
            theBigDecimal = new BigDecimal(obj.toString());
        }
        return theBigDecimal;
    }    
    
    public void addIntVariable(String var, int value) throws Exception{        
        addVariable(var, new Integer(value));
    }
    
    public void addFloatVariable(String var, float value) throws Exception{
        addVariable(var, new Float(value));
    }
    
    public void addDoubleVariable(String var, double value) throws Exception{
        addVariable(var, new Double(value));
    }
    
    public void addVariable(String var) throws Exception{
        if (hash.containsKey(var)==true){
            throw new Exception("Variable " + var + " already definned");
        } else{            hash.put(var, null);
        }
    }
    
    public void addVariable(String var, Object value) throws Exception{
        if (hash.containsKey(var)==true){
            throw new Exception("Variable " + var + " already definned");
            
        } else{
            hash.put(var, value);
        }
    }

    public Grid addGrid(String name) throws Exception {
        Grid grid = new Grid();        
        addVariable(name, grid);
        return grid;
    }
    
    public void addGrid(String name, Grid grid){
        try {
			addVariable(name,grid);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public boolean isVarDefined(String var){
        return hash.containsKey(var);
    }

    public Grid getGrid(String name) throws Exception{
        Grid grid = (Grid)getValue(name);
        return grid;
    }    
    
    public ArrayList getArrayList(String name) throws Exception{
    	ArrayList array = (ArrayList)getValue(name);
        return array;
    }
    
    public void setGrid(String name, Grid grid) throws Exception{
        setValue(name,grid);
    }
       
    public void setArrayList(String name, ArrayList array) throws Exception{
    	setValue(name,array);
    }
    
}

