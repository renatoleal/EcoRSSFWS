package Entities;
// Generated Nov 21, 2012 10:42:37 PM by Hibernate Tools 3.2.1.GA



/**
 * Dados generated by hbm2java
 */
public class Dados  implements java.io.Serializable {


     private Integer id;
     private int idSensor;
     private int value;
     private long timeTicks;

    public Dados() {
    }

    public Dados(int idSensor, int value, long timeTicks) {
       this.idSensor = idSensor;
       this.value = value;
       this.timeTicks = timeTicks;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public int getIdSensor() {
        return this.idSensor;
    }
    
    public void setIdSensor(int idSensor) {
        this.idSensor = idSensor;
    }
    public int getValue() {
        return this.value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    public long getTimeTicks() {
        return this.timeTicks;
    }
    
    public void setTimeTicks(long timeTicks) {
        this.timeTicks = timeTicks;
    }




}


