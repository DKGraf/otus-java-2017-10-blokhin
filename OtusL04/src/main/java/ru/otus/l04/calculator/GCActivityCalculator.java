package ru.otus.l04.calculator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GCActivityCalculator {
    private static File report = new File("report.txt");

    public static void main(String[] args) {
        new GCActivityCalculator().calculate();
    }

    private void calculate() {
        File[] allFiles = new File("./").listFiles();
        List<File> fileList = new ArrayList<>();
        if (allFiles != null) {
            for (File file :
                    allFiles) {
                if (file.getName().endsWith(".log")) fileList.add(file);
            }
        }
        for (File file :
                fileList) {
            extract(file);
        }
    }

    private void extract(File file) {
        int minorCollections0 = 0;
        int minorCollections1 = 0;
        int minorCollections2 = 0;
        int minorCollections3 = 0;
        int minorCollections4 = 0;
        int majorCollections0 = 0;
        int majorCollections1 = 0;
        int majorCollections2 = 0;
        int majorCollections3 = 0;
        int majorCollections4 = 0;
        long minorCollectionsTime0 = 0;
        long minorCollectionsTime1 = 0;
        long minorCollectionsTime2 = 0;
        long minorCollectionsTime3 = 0;
        long minorCollectionsTime4 = 0;
        long majorCollectionsTime0 = 0;
        long majorCollectionsTime1 = 0;
        long majorCollectionsTime2 = 0;
        long majorCollectionsTime3 = 0;
        long majorCollectionsTime4 = 0;
        String nameOfGC = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String s;
            while ((s = reader.readLine()) != null) {
                String[] line = s.split(" ");
                if (line[0].equals("CommandLine")) {
                    for (String str :
                            line) {
                        if (str.startsWith("-XX:+Use") && str.endsWith("GC")) {
                            nameOfGC = str + "\n";
                            break;
                        }
                    }
                }

                if (line[0].matches("^\\d{1,3}\\.\\d{3}:")) {
                    int timeLength = line[0].length() - 1;
                    long secs = 0;
                    if (line[line.length - 1].equals("secs]")) {
                        secs = (long) (Float.valueOf(line[line.length - 2]) * 10_000_000);
                    }
                    long time = (long) (Float.valueOf(line[0].substring(0, timeLength)) * 1000);
                    if (time < 60_000) {
                        if (line[1].equals("[GC")) {
                            minorCollections0++;
                            minorCollectionsTime0 += secs;
                        } else if (line[1].equals("[Full")) {
                            majorCollections0++;
                            majorCollectionsTime0 += secs;
                        }
                    } else if (time < 120_000) {
                        if (line[1].equals("[GC")) {
                            minorCollections1++;
                            minorCollectionsTime1 += secs;
                        } else if (line[1].equals("[Full")) {
                            majorCollections1++;
                            majorCollectionsTime1 += secs;
                        }
                    } else if (time < 180_000) {
                        if (line[1].equals("[GC")) {
                            minorCollections2++;
                            minorCollectionsTime2 += secs;
                        } else if (line[1].equals("[Full")) {
                            majorCollections2++;
                            majorCollectionsTime2 += secs;
                        }
                    } else if (time < 240_000) {
                        if (line[1].equals("[GC")) {
                            minorCollections3++;
                            minorCollectionsTime3 += secs;
                        } else if (line[1].equals("[Full")) {
                            majorCollections3++;
                            majorCollectionsTime3 += secs;
                        }
                    } else if (time < 300_000) {
                        if (line[1].equals("[GC")) {
                            minorCollections4++;
                            minorCollectionsTime4 += secs;
                        } else if (line[1].equals("[Full")) {
                            majorCollections4++;
                            majorCollectionsTime4 += secs;
                        }
                    } else {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(report, true))) {
            writer.write(nameOfGC);
            writer.write("Сборок в Young Gen на первой минуте работы: " + minorCollections0 + "\n");
            writer.write("Сборок в Old Gen на первой минуте работы: " + majorCollections0 + "\n");
            writer.write("Время работы сборщика мусора в Young Gen на первой минуте работы: "
                    + (minorCollectionsTime0 / 10_000) + " мс" + "\n");
            writer.write("Время работы сборщика мусора в Old Gen на первой минуте работы: "
                    + (majorCollectionsTime0 / 10_000) + " мс" + "\n");

            writer.write("Сборок в Young Gen на второй минуте работы: " + minorCollections1 + "\n");
            writer.write("Сборок в Old Gen на второй минуте работы: " + majorCollections1 + "\n");
            writer.write("Время работы сборщика мусора в Young Gen на второй минуте работы: "
                    + (minorCollectionsTime1 / 10_000) + " мс" + "\n");
            writer.write("Время работы сборщика мусора в Old Gen на второй минуте работы: "
                    + (majorCollectionsTime1 / 10_000) + " мс" + "\n");

            writer.write("Сборок в Young Gen на третьей минуте работы: " + minorCollections2 + "\n");
            writer.write("Сборок в Old Gen на третьей минуте работы: " + majorCollections2 + "\n");
            writer.write("Время работы сборщика мусора в Young Gen на третьей минуте работы: "
                    + (minorCollectionsTime2 / 10_000) + " мс" + "\n");
            writer.write("Время работы сборщика мусора в Old Gen на третьей минуте работы: "
                    + (majorCollectionsTime2 / 10_000) + " мс" + "\n");

            writer.write("Сборок в Young Gen на четвертой минуте работы: " + minorCollections3 + "\n");
            writer.write("Сборок в Old Gen на четвертой минуте работы: " + majorCollections3 + "\n");
            writer.write("Время работы сборщика мусора в Young Gen на четвертой минуте работы: "
                    + (minorCollectionsTime3 / 10_000) + " мс" + "\n");
            writer.write("Время работы сборщика мусора в Old Gen на четвертой минуте работы: "
                    + (majorCollectionsTime3 / 10_000) + " мс" + "\n");

            writer.write("Сборок в Young Gen на пятой минуте работы: " + minorCollections4 + "\n");
            writer.write("Сборок в Old Gen на пятой минуте работы: " + majorCollections4 + "\n");
            writer.write("Время работы сборщика мусора в Young Gen на пятой минуте работы: "
                    + (minorCollectionsTime4 / 10_000) + " мс" + "\n");
            writer.write("Время работы сборщика мусора в Old Gen на пятой минуте работы: "
                    + (majorCollectionsTime4 / 10_000) + " мс" + "\n" + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}