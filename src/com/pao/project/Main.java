package com.pao.project;


import com.pao.project.gui.createacc.CreateAccount;
import com.pao.project.manager.Manageable;
import com.pao.project.manager.Manager;
import com.pao.project.services.UserService;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class Main {

    static UserService uServ;
    public static void main(String[] args)  {
        CreateAccount account = new CreateAccount();
    }

    static void fkingFrameTesting() {
        JFrame newFkingFrame = new JFrame("yee");
        newFkingFrame.setSize(500, 300);

        JTextField fkingTxtField = new JTextField();
        fkingTxtField.setSize(300, 50);

        JPanel emptyFkingPanel = new JPanel();
        emptyFkingPanel.setLayout(null);
        emptyFkingPanel.setBounds(10,5, 400, 100);
        emptyFkingPanel.add(fkingTxtField);

        newFkingFrame.add(emptyFkingPanel);
        newFkingFrame.setLayout(null);
        newFkingFrame.setVisible(true);
    }

    Function<int[], GridBagConstraints> cons =
            i -> {
                GridBagConstraints constr = new GridBagConstraints();
                constr.gridx = i[0];
                constr.gridy = i[1];
                if(i.length > 2) {
                    constr.weightx = i[2];
                    constr.weighty = i[3];
                    if (i.length > 4) {
                        constr.fill = i[4];
                    }
                }
                return constr;
            };

    static int[] arri(int... i) {
        return i;
    }

    static void printGBC(GridBagConstraints cons) {
        System.out.println("x: " + cons.gridx + "| y: " + cons.gridy +
                " | wX: " + cons.weightx + " | wY: " + cons.weighty);
    }

    static void oneItemCsvReadTest() {
        File file = new File(Manageable.path + "pills.csv");
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            scanner.useDelimiter(Manageable.csv_delimiter);
            String first = scanner.next();
            String second = scanner.next();
            String line = scanner.nextLine();

            System.out.println(first);
            System.out.println(second);
            System.out.println(line);

            Scanner miniScan = new Scanner(line);
            miniScan.useDelimiter(Manageable.csv_delimiter);
            System.out.println("mini scan: " + miniScan.next());

            scanner.close();;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    static void menuTest() {
        uServ = new UserService();
        try {
            uServ.readTest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void hexaReadText() {
        Scanner cin = new Scanner(System.in);
        int nr = -1;
        while(nr != 0) {
        nr = cin.nextInt(16);
        System.out.println(nr + "\t" + String.format("%02X", nr));
        }
    }

    static void bigImport() {
        File importFile = new File(Manageable.path + "dataimport.csv");
        ArrayList<Manager> manager = new ArrayList<>();
        int manIndex = 0;
        try {
            Scanner scan = new Scanner(importFile);
            while(scan.hasNextLine()) {
                manager.add(new Manager());
                manager.get(manIndex).importData(scan);

                System.out.println(" -> " + manIndex);
                showmanager(manager.get(manIndex));

                manIndex ++;
            }

            scan.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showmanager(Manager manager) {
        if(manager.size() == 0) {
            System.out.println("# Manager is empty #");
            return;
        }

        System.out.println("##");

        System.out.println("\t Infos of the object created: ");

        for (int i = 0; i < manager.size(); i++) {
            Manageable currElm = manager.get(i);
            System.out.println("\t\t" + currElm.getName());
        }
        System.out.println("##");
    }

}
