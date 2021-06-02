/*
 * Основная часть ДЗ:
 * 1. Создать три потока,
 * каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
 * Используйте wait/notify/notifyAll.
 */

package lesson4;

public class PrintSequenceABCABC {
    private final Object mon = new Object(); // В роли монитора выступает обычный Java Object.
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {
        PrintSequenceABCABC w = new PrintSequenceABCABC();
        Thread t1 = new Thread(() -> {
            w.printA();
        });
        Thread t2 = new Thread(() -> {
            w.printB();
        });
        Thread t3 = new Thread(() -> {
            w.printC();
        });
        t1.start();
        t2.start();
        t3.start();

    }

    public void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A') {
                        mon.wait(); // если текущая буква не А тогда ожидаем, уступаем монитор.
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    mon.notifyAll(); // возобновляет исполнение всех потоков, из которых был вызван метод wait()
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') {
                        mon.wait();
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void printC() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C') {
                        mon.wait();
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}