package com.example.demo.Thread;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/11/2
 */
public class WaitNotifyInsert {
    public static void main(String[] args) {
        DBTools dbTools = new DBTools();
        for (int i = 0; i < 20; i++) {
            BackupA backupA = new BackupA(dbTools);
            backupA.start();
            BackupB backupB = new BackupB(dbTools);
            backupB.start();
        }


    }
}


class BackupA extends Thread {
    private DBTools dbTools;

    public BackupA(DBTools dbTools) {
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        dbTools.backupA();
    }
}
class BackupB extends Thread {
    private DBTools dbTools;

    public BackupB(DBTools dbTools) {
        this.dbTools = dbTools;
    }

    @Override
    public void run() {
        dbTools.backupB();
    }
}

class DBTools {
    volatile private boolean prevIsA = false;
    synchronized public void backupA() {
        try {
            while (prevIsA == true) {
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("00000");
            }
            prevIsA = true;
            notifyAll();
        } catch (Exception e) {

        }
    }

    synchronized public void backupB() {
        try {
            while (prevIsA ==false) {
                wait();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println("ooooo");
            }
            prevIsA = false;
            notifyAll();
        } catch (Exception e) {

        }
    }
}
