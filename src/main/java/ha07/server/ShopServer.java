package ha07.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import ha07.base.HA07Server;
import ha07.builder.ShopBuilder;
import org.fulib.yaml.Yamler;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShopServer extends HA07Server {
	public static ShopBuilder builder;
	public static int port = 8081;
	public static final String SHOP_URL = "http://localhost:" + port;

	private static ExecutorService service;
	private static ExecutorService treadPool;

	public static void main(String[] args) {
		HttpServer server = null;

		try {
			builder = new ShopBuilder();
			service = Executors.newSingleThreadExecutor();
			treadPool = Executors.newCachedThreadPool();
			server = HttpServer.create(new InetSocketAddress(port), 0);
			server.setExecutor(service);

			server.createContext("/do", ShopServer::handleRequest);
			server.createContext("/ping", ShopServer::handleWarehousePing);
			server.createContext("/warehouseProxy", ShopServer::handleShopProxyRequest);
			server.start();
			retrieveNewEventsFromWarehouse();
			System.out.println("ShopServer Start " + server.getAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void handleWarehousePing(HttpExchange exchange) {
		String body = getBody(exchange);
		print("ShopServer: " + body);
		writeAnswer(exchange, "OK");
		retrieveNewEventsFromWarehouse();
	}

	private static void retrieveNewEventsFromWarehouse() {
		String yaml = sendRequest(WarehouseServer.WAREHOUSE_URL + "/getShopEvents", "lastEvent: ");

		ArrayList<LinkedHashMap<String, String>> events = new Yamler().decodeList(yaml);

		service.execute(() -> builder.applyEvents(events));

	}

	private static void handleShopProxyRequest(HttpExchange httpExchange) {

	}

	private static void handleRequest(HttpExchange exchange) throws IOException {
		try {
			System.out.println("ShopServer handleRequest");
			URI uri = exchange.getRequestURI();
			InputStream body = exchange.getRequestBody();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(body, StandardCharsets.UTF_8));
			StringBuilder stringBuilder = new StringBuilder();
			while (true) {
				String line = bufferedReader.readLine();
				if (line == null) {
					break;
				}
				stringBuilder.append(line).append("\n");
			}

			String yaml = stringBuilder.toString();
			ArrayList<LinkedHashMap<String, String>> list = new Yamler().decodeList(yaml);
			builder.applyEvents(list);
			String resp = "OK" + uri;
			exchange.sendResponseHeaders(200, resp.getBytes().length);
			OutputStream os = exchange.getResponseBody();
			os.write(resp.getBytes());
			os.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}


}
