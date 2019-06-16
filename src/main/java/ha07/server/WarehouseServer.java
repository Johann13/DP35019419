package ha07.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import ha07.builder.WarehouseBuilder;
import org.fulib.yaml.Yamler;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class WarehouseServer {
	public static WarehouseBuilder builder;
	public static int port = 9092;

	public static void main(String[] args) {
		HttpServer server = null;

		try {
			server = HttpServer.create(new InetSocketAddress("localhost", port), 1);
			server.createContext("/do", WarehouseServer::handleRequest);
			server.createContext("/shopProxy", WarehouseServer::handleWarehouseProxyRequest);
			server.start();
			builder = new WarehouseBuilder();
			System.out.println("WarehouseServer Start " + server.getAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
