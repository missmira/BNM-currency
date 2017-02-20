/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Myra
 */
@XmlRootElement(name="ValCurs")
public class ValCurs {
    private String date;
    private String name;
    private List<Valute> valutes;
    
    @XmlAttribute(name="Date")
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    
    @XmlAttribute(name="name")
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    @XmlElement(name="Valute")
    public List<Valute> getValutes() {
        return this.valutes;
    }
    public void setValutes(List<Valute> valutes) {
        this.valutes = valutes;
    }
}
