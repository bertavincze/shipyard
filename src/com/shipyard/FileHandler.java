package com.shipyard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;

public class FileHandler {

    public String[][] read(String file) { 

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String[][] data = new String[0][0];
            while (br.ready()) {
                int numOfLines = countLines(file);
                int numOfFields = countCols(file);
                
                data = new String[numOfLines][numOfFields];
                for (int i = 0; i < numOfLines; i++) {
                    data[i] = br.readLine().split(",");
                }
            }
            return data;

        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String readLine(String file, int line) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String data = "";
            int currentLine = 0;
            while (br.ready() && currentLine != line) {
                br.readLine();
                currentLine++;
                if (currentLine == line) {
                    data = br.readLine();
                    return data;
                }
            }
            return null;

        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void append(String file, String[] items) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            for (String item : items) {
                if (item != null && !item.equals(items[items.length-1])) {
                    bw.append(item + ",");
                } else {
                    bw.append(item);
                }
            }
            bw.append("\n");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void write(String file, String[] items) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String item : items) {
                if (item != null) {
                    bw.append(item + "\n");
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public int countLines(String file) {
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                br.readLine();
                i++;
            }
            return i;

        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
            return 0;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int countCols(String file) {
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String countFieldString = br.readLine();
            i = countFieldString.length() - countFieldString.replace(",", "").length() + 1;
            return i;
            
        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
            return 0;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}