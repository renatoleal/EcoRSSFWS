package Entities;
// Generated Nov 21, 2012 10:42:37 PM by Hibernate Tools 3.2.1.GA



/**
 * Sensores generated by hbm2java
 */
public class Sensores  implements java.io.Serializable {


     private int id;
     private boolean status;
     private Integer maxValue;
     private Integer minValue;
     private String info;

    public Sensores() {
    }

	
    public Sensores(int id, boolean status) {
        this.id = id;
        this.status = status;
    }
    public Sensores(int id, boolean status, Integer maxValue, Integer minValue, String info) {
       this.id = id;
       this.status = status;
       this.maxValue = maxValue;
       this.minValue = minValue;
       this.info = info;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public boolean isStatus() {
        return this.status;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }
    public Integer getMaxValue() {
        return this.maxValue;
    }
    
    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }
    public Integer getMinValue() {
        return this.minValue;
    }
    
    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }
    public String getInfo() {
        return this.info;
    }
    
    public void setInfo(String info) {
        this.info = info;
    }




}


