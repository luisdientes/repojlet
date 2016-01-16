/*
 * View.java
 *
 * Created on 6 de junio de 2002, 12:08
 */

package arquitectura.controller;

import arquitectura.objects.ObjectIO;


public abstract class View {

    protected Object request;
    protected Object response;
    
    public View() {
    }
    
    public void setRequest(Object request){
        this.request = request;
    }
    
    protected Object getRequest(){
        return request;
    }
    
    public void setResponse(Object response){
        this.response = response;
    }
    
    protected Object getResponse(){
        return response;
    }
    
    public abstract void process(ObjectIO output) throws Exception;
    
    public abstract void processException(Exception e) throws Exception;

}
