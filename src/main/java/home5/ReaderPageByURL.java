package home5;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Класс отображает на экран содержимое сайта, ссылка на который задаётся параметром url
 *
 * @version 1.0
 * @autor Trotsenko Konstantin
 */
public class ReaderPageByURL {

    /**
     * Метод ститывает стриницу в консоль с заданным URL
     * @param urlString введенный с консоли URL
     * @return возвращает true если считвание прошло успешно и false в противном случае
     */
    private static boolean readContent(String urlString) {
        String line;
        URL url;
        InputStream is = null;
        try {
            url = new URL(urlString);
            is = url.openStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (MalformedURLException e) {
            System.out.println("URL is wrong!");
            return true;
        } catch (FileNotFoundException fnf) {
            System.out.println("File not found");
            return true;
        } catch (IOException e) {
            System.out.println("Inner ioe");
            return true;
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ignored) {
            }
        }
        return false;
    }

    /**
     * Точка входа в класс
     *
     * @param args Массив строковых аргументов
     */
    public static void main(String[] args) {
        System.out.println("Input URL or Q to exit.");
        boolean flag = true;
        while (flag) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String url = reader.readLine().trim();
                flag = !url.equals("Q") && readContent(url);
            } catch (IOException e) {
                System.out.println("Outer ioe!");
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!flag) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
