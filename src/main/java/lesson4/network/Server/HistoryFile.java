package lesson4.network.Server;

import java.io.*;

public class HistoryFile {
    public static final int VIEWCOUNT = 2;
    public static File file = new File("chat.history");

    public void saveHistory(String nickName, String message){
        try(FileWriter writer = new FileWriter(file, true))
        {
            writer.write(nickName + "-:-" + message + '\n');
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public StringBuilder getHistoryChat() throws IOException{
        if (!file.exists()){
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder();

        FileInputStream fstream = new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;

        int linesNumber = linesCount();

        //Пропускаем строки, что были раньше количества VIEWCOUNT
        if(linesNumber > VIEWCOUNT){
            for (int i = 0; i <= linesNumber - VIEWCOUNT; i++) {
                br.readLine();
            }
        }

        while ((strLine = br.readLine()) != null){
            String[] list = strLine.split("-:-", 2);
            if(list.length == 2) {
                stringBuilder.append(list[0] + " " + list[1] + "\n");
            }
        }

        return stringBuilder;
    }

    private int linesCount() throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(file));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean endsWithoutNewLine = false;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
                endsWithoutNewLine = (c[readChars - 1] != '\n');
            }
            if(endsWithoutNewLine) {
                ++count;
            }
            return --count;
        } finally {
            is.close();
        }
    }
}
