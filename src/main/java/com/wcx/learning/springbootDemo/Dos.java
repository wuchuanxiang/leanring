package com.wcx.learning.springbootDemo;

import java.util.*;
import java.io.*;

public class Dos {
    static boolean logined = false;

    public static void main(String[] args) {
        User user = new User();
        int k = 0;
        while ((k = Main(user)) >= 1 && k < 5) {
            switch (k) {
                case 1:
                    System.out.print((k = user.login(user)) == -1 ? "此用户不存在!\n" : (k == -2) ? "===<<警告>>用户：[" + user.userName + "]已处于登录状态，无需重复登录！\n" : "");
                    break;
                case 2:
                    user.regist();
                    break;
                case 3:
                    user.getLuckly();
                    break;
                default:
                    System.exit(0);
            }
        }
    }

    static int Main(User user) {
        System.out.println("**********************************************");
        System.out.println("********************主菜单********************");
        System.out.println("**********************************************");
        System.out.println("******          <1> 登   录             ******");
        System.out.println("******          <2> 注   册             ******");
        System.out.println("******          <3> 抽   奖             ******");
        System.out.println("******          <4> 退   出             ******");
        System.out.println("**********************************************");
        System.out.println("==============================================");
        System.out.println(logined ? "-[已登录]-  （1）用户名:" + user.userName + "   （2）用户账号:" + user.userId : "-[未登录]-   （1）用户名:NaN   （2）用户账号:NaN");
        System.out.println("==============================================");
        System.out.print("###===>请输入您的选择：");
        return (new Scanner(System.in)).nextInt();
    }
}

 class User {
    String userName, userId, userPwd;

    public User() {
    }

    public User(String userName, String userId, String userPwd) {
        this.userName = userName;
        this.userId = userId;
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (!userName.equals("")) {
            ArrayList<String> temp;
            this.userId = (temp = (new operatorFile(this.userName = userName)).getUserMess(0)).get(1);
            this.userPwd = temp.get(2);
        }
    }

    public String getUserId() {
        return userId;
    }

    public String setUserId() {
        String userId = "";
        while ((new operatorFile(userId = String.valueOf((int) (Math.random() * 9000 + 1000)))).getUserMess(1).size() > 0) {
        }
        return (this.userId = userId);
    }

    public int login(User u) {
        int inputTimes = 3;
        operatorFile getUserMessage = new operatorFile();
        System.out.print("======>请输入您的用户名：");
        String uName = "";
        getUserMessage.setUser(uName = (new Scanner(System.in)).nextLine());
        ArrayList<String> userMess = getUserMessage.getUserMess(0);
        if (userMess.size() < 1) return -1;//返回-1表示用户不存在
        if (uName.equals(userName)) return -2;//返回-2表示用户重复登录
        System.out.print("======>请输入您的登录密码：");
        while (!(new Scanner(System.in)).next().equals(userMess.get(2)) && inputTimes > 0)
            System.out.print("===>密码输入错误！" + ((--inputTimes) > 0 ? "您还剩" + inputTimes + "次机会！" : "三次机会已经用完了！输入任意退出"));
        System.out.println(inputTimes > 0 ? "==>登录成功！您本次输入密码" + (4 - inputTimes) + "次！" : "==>登录失败！");
        setUserName(inputTimes > 0 ? uName : "");
        Dos.logined = inputTimes > 0 ? true : false;
        return 0;
    }

    public void regist() {
        User u = new User();
        System.out.print("===>请输入新的用户名：");
        String name;
        while (new operatorFile(name = (new Scanner(System.in)).nextLine()).getUserMess(0).size() > 0)
            System.out.print("已存在此用户,注册失败！\n===>请重新输入新的用户名：");
        System.out.print("======>请设置您的(六位数字)登录密码：");
        String regex = "[0-9]{6}", pwd;
        while (!(pwd = (new Scanner(System.in)).nextLine()).matches(regex))
            System.out.print("==>密码格式不正确，请重新设置您的(六位数字)登录密码：");
        System.out.println("已为用户： " + (u.userName = name) + " 生成唯一ID: " + (u.userPwd = pwd));
        (new operatorFile()).writeUserMess(u);
        System.out.println("=======>注册成功！");
    }

    public static HashMap<String, String> lucklyUsers = new HashMap<>();

    public void getLuckly() {
        if (!Dos.logined) {
            System.out.println("===>警告：没有用户登录，无法抽奖！");
            return;
        }
        while (lucklyUsers.size() < 5) {
            String id = "";
            ArrayList<String> u;
            while ((u = (new operatorFile(id = String.valueOf((int) (Math.random() * 9000 + 1000)))).getUserMess(1)).size() < 1) {
            }
            lucklyUsers.put(u.get(1), u.get(0));
        }
        Iterator iterator = lucklyUsers.entrySet().iterator();
        int no = 1;
        boolean LUCKLY = false;
        System.out.println("====>恭喜以下用户获得幸运称号：");
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            System.out.println("幸运用户[" + (no++) + "]  用户名：" + entry.getValue() + "   用户编号：" + entry.getKey());
            LUCKLY = entry.getKey().equals(this.userId) ? true : LUCKLY;
        }
        System.out.println(LUCKLY ? "=========>恭喜您在本次抽奖中获得幸运称号！" : "=========>很遗憾，今日您未获奖 ！-_-！");
    }

    public String toString() {
        return this.userName + " " + this.userId + " " + this.userPwd;
    }
}

 class operatorFile {
    String user;

    public void setUser(String user) {
        this.user = user;
    }

    public operatorFile(String user) {
        this.user = user;
    }

    public operatorFile() {
    }

    public ArrayList<String> getUserMess(int index) {
        ArrayList<String> temp = new ArrayList<String>();
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("user.txt")));
            while ((line = br.readLine()) != null && line != "\n") {
                temp.clear();
                StringTokenizer sk = new StringTokenizer(line);
                while (sk.hasMoreTokens()) {
                    temp.add(sk.nextToken());
                }
                if (temp.get(index).equals(this.user)) break;
            }
        } catch (IOException e) {
        }
        return (line == null) ? new ArrayList<String>() : temp;
    }

    public void writeUserMess(User u) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File("user.txt"), true));
            bw.write(u.toString() + "\n");
            bw.close();
        } catch (IOException e) {
        }
    }
}