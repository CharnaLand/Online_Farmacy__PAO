package com.pao.project.products;

import com.pao.project.manager.IDentity;
import com.pao.project.manager.Manageable;
import com.pao.project.manager.Mask;

import java.io.IOException;
import java.util.Scanner;

public abstract class Product implements Manageable {

    static protected IDentity iDentity;

    static {
        iDentity = new IDentity(Mask.Product.getMask());
    }

    private int uniqID;
    private String name;
    private float price;
    private int receip_mask;  ///when an order is put, this mask gives a discount generated by the receip ID
    protected String description;

    /** This function returns the amount of data each class is passing
     * trough the String vector from one generation to the other
     * */
    protected int nrOfData () {
        return 4;
    }


    public float getPrice() {
        return price;
    }

    public float getPrice (int receip_ID) {

        float discount = (float)
                (((0b11111111 << 4) & receip_ID) | receip_mask);
        if(discount > 100) discount = 100;
        discount = 1 - discount /100;

        return discount * price;
    }

    /** MANAGEABLE METHODS*/

    @Override
    public String[] dataToStore() {

        String[] data = new String[nrOfData()];
        data[0] = String.valueOf(uniqID);
        data[1] = name;
        data[2] = String.valueOf(price);
        data[3] = String.valueOf(receip_mask);

        return data;
    }

    @Override
    public int getClassMask() {
        return Mask.Product.getMask();
    }

    @Override
    public String[] importData(Scanner fin) throws IOException {
        String[] data = new String[nrOfData()];
        if(fin.hasNext("\\w+"))
            data[0] = String.valueOf(fin.nextInt());
        data[1] = fin.next();
        data[2] = String.valueOf(fin.nextFloat());
        data[3] = String.valueOf(fin.nextInt());

        return data;
    }

    public void fillTheRest(String[] data) {
        this.description = data[data.length -1];
    }

    @Override
    public void incrementalSetter(String[] data) {
        uniqID = iDentity.incrementalIndexing(this);
        this.name = data[1];
        this.price = Float.parseFloat(data[2]);
        this.receip_mask = Integer.parseInt(data[3]);
    }

    @Override
    public void nonIncrementalSetter(String[] data) {
        this.uniqID = Integer.parseInt(data[0]);
        iDentity.nonIncrementalIndexing(this, this.uniqID);

        this.name = data[1];
        this.price = Float.parseFloat(data[2]);
        this.receip_mask = Integer.parseInt(data[3]);
    }

    @Override
    public String toString() {
        String bigString = "";
        bigString += "ID:     " + uniqID + "\n";
        bigString += "Name:   " + name + "\n";
        bigString += "Price:  " + price + "\n";
        bigString += "Receip: " + Integer.toBinaryString( receip_mask ) + "\n";

        return bigString;
    }
}