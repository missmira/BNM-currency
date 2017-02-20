
package javaapplication1;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Myra
 */
@XmlRootElement
@XmlType(propOrder={"numCode", "charCode", "nominal", "name", "value"})
public class Valute {
    
    private int id;
    private int numCode;
    private String charCode;
    private int nominal;
    private String name;
    private String value;
    
    @XmlAttribute(name="ID")
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @XmlElement(name="NumCode")
    public int getNumCode() {
        return this.numCode;
    }
    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }
 
    @XmlElement(name="CharCode")
    public String getCharCode() {
        return this.charCode;
    }
    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    @XmlElement(name="Nominal")
    public int getNominal() {
        return this.nominal;
    }
    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    @XmlElement(name="Name")
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @XmlElement(name="Value")
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    
    public void afisare(){
        System.out.println("ID: " +  getId());
        System.out.println("Num code: " + getNumCode());
        System.out.println("Char code: " +  getCharCode());
        System.out.println("Nominal: " +  getNominal());
        System.out.println("Name: " +  getName());
        System.out.println("Value: " +  getValue());
    }

}

