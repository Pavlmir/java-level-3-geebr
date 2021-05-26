package lesson2test.server;

import lesson2test.ChatConstants;
import lesson2test.ClientHandler;
import lesson2test.database.JdbcInteractor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Непосредственно сервер
 */
public class MyServer {

    private JdbcInteractor ji = null;
    private List<ClientHandler> clients;
    private AuthService authService;

    public MyServer() {
        // Connect to DB
        ji = new JdbcInteractor();

        try (ServerSocket server = new ServerSocket(ChatConstants.PORT)) {
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized boolean isNickBusy(String nick) {
        return clients.stream().anyMatch(client -> client.getName().equals(nick));
       /* for (ClientHandler client : clients) {
            if (client.getName().equals(nick)) {
                return true;
            }
        }
        return false;*/
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }

    /**
     * Отправляет сообщение всем пользователям
     *
     * @param message
     */
    public synchronized void broadcastMessage(String message) {
        clients.forEach(client -> client.sendMsg(message));
        /*for (ClientHandler client : clients) {
            client.sendMsg(message);
        }*/
    }

    public boolean isLoginAuthorised(String login){
        for (ClientHandler c: clients ) {
            if(c.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }
}