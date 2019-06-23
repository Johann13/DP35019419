package ha07.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import ha07.base.HA07Server;
import ha07.builder.WarehouseBuilder;
import org.fulib.yaml.Yamler;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WarehouseServer extends HA07Server {
	public static WarehouseBuilder builder;
	public static int port = 9092;
	public static final String WAREHOUSE_URL = "http://localhost:" + port;

	private static ExecutorService service;
	private static ExecutorService treadPool;

	public static void main(String[] args) {
		HttpServer server = null;

		try {
			builder = new WarehouseBuilder();
			service = Executors.newSingleThreadExecutor();
			treadPool = Executors.newCachedThreadPool();
			server = HttpServer.create(new InetSocketAddress(port), 0);
			server.setExecutor(service);

			//server.createContext("/ping", WarehouseServer::handlePing);
			server.createContext("/do", WarehouseServer::handleRequest);
			server.createContext("/shopProxy", WarehouseServer::handleWarehouseProxyRequest);
			server.createContext("/getShopEvents",WarehouseServer::handleGetShopEvents);
			server.start();
			System.out.println("WarehouseServer Start " + server.getAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void handleGetShopEvents(HttpExchange exchange) {


	}

	private static void handleWarehouseProxyRequest(HttpExchange httpExchange) {

	}

	private static void handleRequest(HttpExchange exchange) throws IOException {
		System.out.println("WarehouseServer handleRequest");
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
	}
}
