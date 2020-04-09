package lineCT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class p6 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"/",
                "/hello",
                "/hello/tmp",
                "/root",
                "/root/abcd",
                "/root/abcd/etc",
                "/root/abcd/hello"}, new String[] {"mkdir /root/tmp",
                "cp /hello /root/tmp",
                "rm /hello"})));
    }

    static ArrayList<String> answer = new ArrayList<>();

    public static String[] solution(String[] directory, String[] command) {
        Directory root = new Directory("");
        Directory curr = root;
        for (int i = 0; i < directory.length; i++) {
            String[] str = directory[i].split("/");
            for (int j = 1; j < str.length; j++) {
                for (int k = 0; k < curr.directories.size(); k++) {
                    if (curr.directories.get(k).equals(str[j])){
                        curr = curr.directories.get(k);
                        break;
                    }
                }
            }
            if (str.length > 0) {
                curr.directories.add(new Directory(str[str.length - 1]));
            }
            else {
                curr.directories.add(new Directory(""));
            }
            curr = root;
        }

//        for (String comm : command) {
//            switch (comm.split(" ")[0]) {
//                case "mkdir":
//                    String dir = comm.split(" ")[1];
//                    String[] dirs = dir.split("/");
//                    curr = root;
//                    for (int i = 1; i < dirs.length - 1; i++) {
//                        for (int j = 0; j < curr.directories.size(); j++) {
//                            if (curr.directories.get(j).equals(dirs[i])){
//                                curr = curr.directories.get(j);
//                                break;
//                            }
//                        }
//                    }
//                    curr.directories.add(new Directory(dirs[dirs.length - 1]));
//                    break;
//                case "rm":
//
//                    break;
//                case "cp":
//
//                    break;
//            }
//        }

        getAnswer(root, "");
        return answer.toArray(new String[answer.size()]);
    }

    static void getAnswer(Directory dir, String ans) {
        String myans = ans + "/" + dir.name;
        System.out.println(myans);
        answer.add(myans);
        Collections.sort(dir.directories);
        for (Directory child : dir.directories) {
            getAnswer(child, myans);
        }
    }
}

class Directory implements Comparable<Directory> {
    String name;
    ArrayList<Directory> directories;

    public Directory(String name) {
        this.name = name;
        directories = new ArrayList<>();
    }

    public Directory(String name, ArrayList<Directory> dirs) {
        this.name = name;
        directories = dirs;
    }

    @Override
    public int compareTo(Directory directory) {
        return this.name.compareTo(directory.name);
    }
}
