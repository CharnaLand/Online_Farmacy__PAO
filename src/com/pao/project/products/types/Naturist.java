package com.pao.project.products.types;

import com.pao.project.manager.Mask;
import com.pao.project.manager.ProductCodes;
import com.pao.project.products.Product;

import java.io.IOException;
import java.util.Scanner;

public class Naturist extends Product {

    static final int MASK = ProductCodes.NATURIST;
    private String[] natural_ingredients = new String[0];

    @Override
    protected int nrOfData() {
        /* Fields superclass passed (minus description),
         number of natural ingredients,
         natural ingredients,
         description
         */
        return super.nrOfData() + 1 + natural_ingredients.length + 1;
    }



    @Override
    public String[] dataToStore() {
        String[] oldData = super.dataToStore();
        String[] newData = new String[nrOfData()];
        int old = super.nrOfData();
        if (old >= 0) System.arraycopy(oldData, 0, newData, 0, old);

        newData[old] = String.valueOf(natural_ingredients.length);
        for (int index = 0; index <natural_ingredients.length; index++)
            newData[old + index + 1] = natural_ingredients[index];

        newData[newData.length - 1] = this.description;
        return newData;
    }

    @Override
    public int getClassMask() { return NATURIST; }

//    private String[] importSuperData(Scanner fin) throws IOException {
//
//        String[] oldData = super.importData(fin);
//        String[] newData = new String[nrOfData()];
//        int old = super.nrOfData();
//        if (old >= 0) System.arraycopy(oldData, 0, newData, 0, old);
//
//        return newData;
//    }
//
//    @Override
//    public String[] importData(Scanner fin) throws IOException {
//        String[] data = importSuperData(fin);
//        int old = super.nrOfData();
//
//        data[old] = fin.next();
//        int to_read = Integer.parseInt(data[old]);
//
//        for (int index = 0; index < to_read; index++) {
//            data[old + index + 1] = fin.next();
//        }
//
//        data[data.length - 1] = fin.nextLine();
//
//        return data;
//    }

    @Override
    public void fillTheRest(String[] data) {
        int old = super.nrOfData();
        natural_ingredients = new String[Integer.parseInt(data[old])];

        for (int index = 0; index < natural_ingredients.length; index++)
            natural_ingredients[index] = data[old + index + 1];

        super.fillTheRest(data);
    }

    @Override
    public void setData(String[] data) {
        super.setData(data);
        fillTheRest(data);
    }

    @Override
    public String[] inputData(Scanner fin) {
        String[] oldData = super.inputData(fin);
        System.out.print("Input the NR OF \n\tNATURAT INGREDIENTS:  ");
        int nat_ingr = fin.nextInt();


        String[] newData = new String[super.nrOfData() + nat_ingr + 1 + 1];
        int old = super.nrOfData();
        if (old >= 0) System.arraycopy(oldData, 0, newData, 0, old);

        newData[old] = String.valueOf(nat_ingr);

        System.out.println("Input the " + newData[old] + " natural ingredients:   ");
        for (int index = 0; index < Integer.parseInt(newData[old]); index++) {
            System.out.print(index + 1 + ". ");
            newData[old + index + 1] = fin.next();
        }

        fin.nextLine();

        System.out.println("Input the description: ");
        newData[newData.length - 1] = fin.nextLine();

        return newData;
    }
}
